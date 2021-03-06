/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.profession.integration;


import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasSize;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.Assert.assertNotNull;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import tercerizzario.service.profession.Startup;
import tercerizzario.service.profession.domain.Profession;
import tercerizzario.service.profession.repository.DefaultRepository;

/**
 *
 * @author bruno
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Startup.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ResourceRestCallSteps {

    @Autowired
    private volatile WebApplicationContext webApplicationContext;

    @Autowired
    private DefaultRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;
    private volatile MockMvc mockMvc;

//    private List<Supplier> suppliers = new ArrayList<>();
    private ResultActions resultActions;

    private HttpHeaders httpHeaders = new HttpHeaders();

    @Given("^Iniciado o servidor e subido o contexto$")
    public void iniciadoOServidorESubindoOContexto() {
        LOG.log(Level.INFO, "Iniciando e injetando o contexto da aplicacao para o mockMvc");
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        LOG.log(Level.INFO, "Limpando o ergistro da base de dados");
        mongoTemplate.dropCollection(Profession.class);
        LOG.log(Level.INFO, "Feito...");
    }

    @Given("^Conjunto de elementos :$")
    public void listaDePrestadoresExistentes(DataTable dataTable) {

        List<Profession> list = dataTable.asList(Profession.class);
        repository.save(list);
//        for (Profession profession : list) {
//            LOG.log(Level.INFO, "populating  with {0}", profession.toString());
//            mongoTemplate.save(profession);
//        }

    }

    @Given("^O seguinte cabecalho do header sera enviado para a requisicao :$")
    public void oSeguinteCabecalhoDoHeaderSeraEnviadoParaARequisicao(DataTable requestHeaderNameValues) {
        Map<String, String> headerNameValues = requestHeaderNameValues.asMap(String.class, String.class);
        headerNameValues.forEach((k, v) -> httpHeaders.add(k, v));
    }

    @When("^requisitado via GET ([^\"]*)$")
    public void requisitadoViaGET(String resourceUri) throws Exception {
        LOG.log(Level.INFO, "/GET {0}", new Object[]{resourceUri});
        resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get(resourceUri).headers(httpHeaders));
    }

    @When("^requisitado via DELETE (.*)$")
    public void requisitadoViaDELETE(String resourceUri) throws Throwable {
        resultActions = this.mockMvc.perform(MockMvcRequestBuilders.delete(resourceUri).headers(httpHeaders));
    }

    @When("^requisitado via PUT (.*) com formato json :$")
    public void requisitadoViaPUTComFormatoJSON(String resourceUri, String jsonData) throws Throwable {
        LOG.log(Level.INFO, "/PUT {0} {1}", new Object[]{resourceUri, jsonData});
        resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put(resourceUri).contentType(MediaType.APPLICATION_JSON) //
                .content(jsonData.getBytes()).headers(httpHeaders));
    }

    @When("^requisitado via PUT (.*) com formato nulo$")
    public void requisitadoViaPUTComFormatoNulo(String resourceUri) throws Throwable {
        resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put(resourceUri)
                .headers(httpHeaders));
    }

    @When("^requisitado via POST (.*) com formato json :$")
    public void requisitadoViaPOSTComFormatoJSON(String resourceUri, String jsonData) throws Exception {
        LOG.log(Level.INFO, "/POST {0} {1}", new Object[]{resourceUri, jsonData});
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
        LOG.log(Level.INFO, "statusCode {0}", statusCode);
        resultActions.andExpect(status().is(statusCode));
    }

    @Then("^O resultado JSON sera :$")
    public void oResultadoJSONSera(String jsonString) throws Exception {
        System.out.println(String.format("JSON EXPECTED RESULT %s", jsonString));
        System.out.println(String.format("JSON RESULT %s", resultActions.andReturn().getResponse().getContentAsString()));
//        LOG.log(Level.INFO, "json result {0}", jsonString);
        resultActions.andExpect(content().json(jsonString));
    }

    @Then("^O resultado JSON sera vazio")
    public void oResultadoJSONSeraVazio() throws Exception {
        LOG.log(Level.INFO, "json result {0}", "[]");
        resultActions.andExpect(content().json("[]"));
    }

    /**
     * Should be only used to test what json should be returned!
     *
     * @param jsonString
     * @throws java.lang.Exception
     */
    @Then("^O resultado da string sera :$")
    public void oResultadoDaStringDeveraSer(String jsonString) throws Exception {
        LOG.log(Level.INFO, "json {0}", jsonString);
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
    
    /**
     * Should be only used to test what json should be returned!
     * @param expression
     * @param jsonString
     * @throws java.lang.Exception
     */ 
    @Then("^O resultado da expressao (.*) :$")
    public void oResultadoDoAtributoXContera(String expression, String jsonString) throws Exception {
        LOG.log(Level.INFO, "Expression {0} json {1}", new Object[] {expression, jsonString});
        LOG.log(Level.INFO, "Result {0} ", new Object[] {resultActions.andReturn().getResponse().getContentAsString()});
        resultActions.andExpect(jsonPath(expression, Matchers.equalToIgnoringCase(jsonString)));
    }

    @Then("^O seguinte cabecalho da requisicao contera \"(.*)\" com valor \"(.*)\"$")
    public void oSeguinteCabecalhoDaRequisicaoConteraComValor(String headerName, String headerValue) throws Exception {
        assertEquals("Cabecalho da requisicao nao presente com o valor  : " + headerName + "=" + headerValue, headerValue, resultActions.andReturn().getResponse().getHeaderValue(headerName));
    }

    private static final Logger LOG = Logger.getLogger(ResourceRestCallSteps.class.getName());

}
