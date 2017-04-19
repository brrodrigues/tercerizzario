/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.supplier.integration;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tercerizzario.service.supplier.SupplierApp;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import tercerizzario.service.supplier.domain.Supplier;

/**
 *
 * @author bruno
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SupplierApp.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SupplierResourceRestCallSteps {

    @Autowired
    private volatile WebApplicationContext webApplicationContext;

    private volatile MockMvc mockMvc;
    
    private List<Supplier> suppliers = new ArrayList<>();

    private ResultActions resultActions;

    private HttpHeaders httpHeaders = new HttpHeaders();

    @Given("^Iniciado o servidor e subido o contexto$")
    public void iniciadoOServidorESubindoOContexto() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Given("^Lista de prestadores:$")
    public void listaDePrestadoresExistentes(DataTable dataTable) {
        List<Supplier> jsonMap = dataTable.asList(Supplier.class);
        suppliers.addAll(jsonMap);
    }

    @Given("^O seguinte cabecalho do header sera enviado para a requisicao :$")
    public void oSeguinteCabecalhoDoHeaderSeraEnviadoParaARequisicao(DataTable requestHeaderNameValues) {
        Map<String, String> headerNameValues = requestHeaderNameValues.asMap(String.class, String.class);
        headerNameValues.forEach((k, v) -> httpHeaders.add(k, v));
    }

    @When("^requisitado via GET ([\\S]*)$")
    public void requisitadoViaGET(String resourceUri) throws Exception {
        resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get(resourceUri).headers(httpHeaders));
    }

    @When("^requisitado via DELETE (.*)$")
    public void requisitadoViaDELETE(String resourceUri) throws Throwable {
        resultActions = this.mockMvc.perform(MockMvcRequestBuilders.delete(resourceUri).headers(httpHeaders));
    }

    @When("^requisitado via PUT (.*) com formato json :$")
    public void requisitadoViaPUTComFormatoJSON(String resourceUri, String jsonData) throws Throwable {
        resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put(resourceUri).contentType(MediaType.APPLICATION_JSON) //
                .content(jsonData.getBytes()).headers(httpHeaders));
    }

    @When("^requisitado via PUT (.*) com formato nulo$")
    public void requisitadoViaPUTComFormatoNulo(String resourceUri) throws Throwable {
        resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put(resourceUri)
                .headers(httpHeaders));
    }

    @When("^requisitado via POST (.*) com formato json:$")
    public void requisitadoViaPOSTComFormatoJSON(String resourceUri, String jsonData) throws Exception {
        resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(resourceUri).contentType(MediaType.APPLICATION_JSON) //
                .content(jsonData.getBytes()).headers(httpHeaders));
    }

    @When("requisitado upload de arquivo para (.*)")
    public void requisitadoUploadArquivoPara(String resourceUri) throws Exception {
        MockMultipartFile firstFile = new MockMultipartFile("file", "filename.txt", "text/plain", "some xml".getBytes());
        resultActions = mockMvc.perform(MockMvcRequestBuilders.fileUpload(resourceUri)
                .file(firstFile));
    }

    @When("requisitado upload de multiplos arquivos para (.*)")
    public void requisitadoUploadDeMultiploArquivos(String resourceUri) throws Exception {
        MockMultipartFile firstFile = new MockMultipartFile("file", "filename.txt", "text/plain", "some xml".getBytes());
        MockMultipartFile secondFile = new MockMultipartFile("data", "other-file-name.data", "text/plain", "some other type".getBytes());
        MockMultipartFile jsonFile = new MockMultipartFile("json", "", "application/json", "{\"json\": \"someValue\"}".getBytes());
        resultActions = mockMvc.perform(MockMvcRequestBuilders.fileUpload(resourceUri)
                .file(firstFile)
                .file(secondFile)
                .file(jsonFile));
    }

    @Then("^O codigo da resposta sera (\\d*)$")
    public void oCodigoDaRespostaSera(int statusCode) throws Exception {
        resultActions.andExpect(status().is(statusCode));
    }

    @Then("^O resultado JSON sera :$")
    public void oResultadoJSONSera(String jsonString) throws Exception {
        resultActions.andExpect(content().json(jsonString));
    }

    @Then("^O resultado JSON sera vazio")
    public void oResultadoJSONSeraVazio() throws Exception {
        resultActions.andExpect(content().json("[]"));
    }

    /**
     * Should be only used to test what json should be returned!
     */
    @Then("^O resultado da string devera ser :$")
    public void oResultadoDaStringDeveraSer(String jsonString) throws Exception {
        resultActions.andExpect(content().string(jsonString));
    }

    @Then("^O resultado devera conter (\\d*) registros $")
    public void checkResponseJsonMatch(int recordNumber) throws Exception {
        resultActions.andExpect(jsonPath("$", hasSize(recordNumber)));
    }

    @Then("^O resultado sera falso $")
    public void oResultadoSeraFalso() throws Exception {
        resultActions.andExpect(content().string(String.valueOf(false)));
    }

    @Then("^O resultado sera verdadeiro $")
    public void oResultadoSeraVerdadeiro() throws Exception {
        resultActions.andExpect(content().string(String.valueOf(true)));
    }

    @Then("^O seguinte cabecalho da requisicao contera \"(.*)\"$")
    public void oSeguinteCabecalhoDaRequisicaoContera(List<String> headers) throws Exception {
        headers.stream().forEach((header) -> {
            assertNotNull("Cabecalho da requisicao nao presente : " + header, resultActions.andReturn().getResponse().getHeaderValue(header));
        });
    }

    @Then("^O seguinte cabecalho da requisicao contera \"(.*)\" com valor \"(.*)\"$")
    public void oSeguinteCabecalhoDaRequisicaoConteraComValor(String headerName, String headerValue) throws Exception {
        assertEquals("Cabecalho da requisicao nao presente com o valor  : " + headerName + "=" + headerValue, headerValue, resultActions.andReturn().getResponse().getHeaderValue(headerName));
    }
}
