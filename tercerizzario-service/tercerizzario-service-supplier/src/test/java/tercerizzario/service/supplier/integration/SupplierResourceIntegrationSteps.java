/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.supplier.integration;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import tercerizzario.service.supplier.SupplierApp;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import tercerizzario.service.supplier.domain.Supplier;

/**
 *
 * @author bruno
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SupplierApp.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SupplierResourceIntegrationSteps {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private RestTemplate restTemplate;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    MockMvc mockMvc;

    List<Supplier> suppliers = new ArrayList<>();

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Given("^Iniciando o contexto da aplicacao$")
    public void startupSupplierResource() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Given("^Limpando a base de dados$")
    public void clearDatabase() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Then("^Checando recurso prestadores$")
    public void avaliableResource() {
        mockRestServiceServer.expect(requestTo("/suppliers")).andRespond(withSuccess("[]", MediaType.APPLICATION_JSON));
    }

    @Given("^Lista de prestadores existentes:$")
    public void listaDePrestadoresExistentes(DataTable dataTable) {
        List<Supplier> jsonMap = dataTable.asList(Supplier.class);
        suppliers.addAll(jsonMap);

    }

}
