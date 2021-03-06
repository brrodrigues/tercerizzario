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
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hamcrest.Matchers;
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
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import tercerizzario.service.supplier.domain.SupplierDomain;
import tercerizzario.service.supplier.Startup;

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
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoRepository<SupplierDomain, String> mongoRepository;
    
    private volatile MockMvc mockMvc;
    
    private ResultActions resultActions;


    private HttpHeaders httpHeaders = new HttpHeaders();

    @Given("^Iniciado o servidor e subido o contexto$")
    public void iniciadoOServidorESubindoOContexto() {
        LOG.log(Level.INFO, "Iniciando e injetando o contexto da aplicacao para o mockMvc");
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        LOG.log(Level.INFO, "Limpando registros na base de dados");
        mongoRepository.deleteAll();
        LOG.log(Level.INFO, "Feito...");
    }

    @Given("^Conjunto de elementos:$")
    public void listaDePrestadoresExistentes(DataTable dataTable) {

        List<Map<String, Object>> params = dataTable.asMaps(String.class, Object.class);
        for (Map<String, Object> map : params) {
            String id = (String) map.get("id");
            String name = (String) map.get("name");
            String longitude = (String) map.get("longitude");
            String latitude = (String) map.get("latitude");
            String cellPhone = (String) map.get("cellPhone");
            String address = (String) map.get("address");
            String email = (String) map.get("email");
            String document = (String) map.get("document");
//            GeoJsonPoint point = new GeoJsonPoint(Double.parseDouble(longitude), Double.parseDouble(latitude));
            Double[] location = new Double[]{Double.parseDouble(longitude), Double.parseDouble(latitude)};
            SupplierDomain supplier = new SupplierDomain(id, name, location, cellPhone, address, email, document);
//            LOG.log(Level.INFO, "populating supplier  with {0}", supplier.toString());
            mongoTemplate.save(supplier);
        }

    }

    @Given("^O seguinte cabecalho do header sera enviado para a requisicao :$")
    public void oSeguinteCabecalhoDoHeaderSeraEnviadoParaARequisicao(DataTable requestHeaderNameValues) {
        Map<String, String> headerNameValues = requestHeaderNameValues.asMap(String.class, String.class);
        headerNameValues.forEach((k, v) -> httpHeaders.add(k, v));
    }

    @When("^requisitado via GET ([^\"]*)$")
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
        LOG.log(Level.INFO, "statusCode {0}", statusCode);
        resultActions.andExpect(status().is(statusCode));
    }

    @Then("^O resultado JSON sera :$")
    public void oResultadoJSONSera(String jsonString) throws Exception {
        LOG.log(Level.INFO, "json result {0}", jsonString);
        resultActions.andExpect(content().json(jsonString));
    }

    @Then("^O resultado JSON sera vazio")
    public void oResultadoJSONSeraVazio() throws Exception {
        LOG.log(Level.INFO, "json result {0}", "[]");
        resultActions.andExpect(content().json("[]"));
    }

    /**
     * Should be only used to test what json should be returned!
     * @param jsonString
     * @throws java.lang.Exception
     */
    @Then("^O resultado da string sera :$")
    public void oResultadoDaStringSera(String jsonString) throws Exception {
        LOG.log(Level.INFO, "json {0}", jsonString);
        resultActions.andExpect(content().string(jsonString));
    }
    
    /**
     * Should be only used to test what json should be returned!
     * @param jsonString
     * @throws java.lang.Exception
     */
    @Then("^O resultado da string contera :$")
    public void oResultadoDaStringContera(String jsonString) throws Exception {
        LOG.log(Level.INFO, "json {0}", jsonString);
        resultActions.andExpect(content().string(jsonString));
    }
    
    /**
     * Should be only used to test what json should be returned!
     * @param expression
     * @param jsonString
     * @throws java.lang.Exception
     */
    @Then("^O resultado da expressao \"(.*)\" contera :$")
    public void oResultadoDoAtributoXContera(String expression, String jsonString) throws Exception {
        LOG.log(Level.INFO, "expression {0} json {1}", new Object[] {expression, jsonString});
        resultActions.andExpect(jsonPath(expression, Matchers.equalToIgnoringCase(jsonString)));
    }

    @Then("^O resultado devera conter (\\d*) registros $")
    public void oResultadoDeveraConterNRegistros(int recordNumber) throws Exception {
        resultActions.andExpect(jsonPath("$", Matchers.hasSize(recordNumber)));
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

    private static final Logger LOG = Logger.getLogger(ResourceRestCallSteps.class.getName());

}
