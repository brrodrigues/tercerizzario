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
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author bruno
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SupplierResourceRestCallSteps {

    @Autowired
    private volatile WebApplicationContext webApplicationContext;

    private volatile MockMvc mockMvc;

    private ResultActions resultActions;

    private HttpHeaders httpHeaders = new HttpHeaders();

    @Given("^the web context is set$")
    public void givenServerIsUpAndRunning() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Given("^the following HTTP headers will be sent with requests:$")
    public void requestHeaderWillBeSentWithRequest(DataTable requestHeaderNameValues) {
        Map<String, String> headerNameValues = requestHeaderNameValues.asMap(String.class, String.class);
        headerNameValues.forEach((k, v) -> httpHeaders.add(k, v));
    }

    @When("^client request GET ([\\S]*)$")
    public void performGetOnResourceUri(String resourceUri) throws Exception {
        resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get(resourceUri).headers(httpHeaders));
    }

    @When("^client request DELETE (.*)$")
    public void performDeleteOnResourceUri(String resourceUri) throws Throwable {
        resultActions = this.mockMvc.perform(MockMvcRequestBuilders.delete(resourceUri).headers(httpHeaders));
    }

    @When("^client request PUT (.*) with json data:$")
    public void performPutOnResourceUri(String resourceUri, String jsonData) throws Throwable {
        resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put(resourceUri).contentType(MediaType.APPLICATION_JSON) //
                .content(jsonData.getBytes()).headers(httpHeaders));
    }

    @When("^client request PUT (.*) with null$")
    public void performPutWithNullOnResourceUri(String resourceUri) throws Throwable {
        resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put(resourceUri)
                .headers(httpHeaders));
    }

    @When("^client request POST (.*) with json data:$")
    public void performPostOnResourceUriWithJsonData(String resourceUri, String jsonData) throws Exception {
        resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(resourceUri).contentType(MediaType.APPLICATION_JSON) //
                .content(jsonData.getBytes()).headers(httpHeaders));
    }

    @When("Client uploads file to (.*)")
    public void clientUploadsFileTo(String resourceUri) throws Exception {
        MockMultipartFile firstFile = new MockMultipartFile("file", "filename.txt", "text/plain", "some xml".getBytes());
        resultActions = mockMvc.perform(MockMvcRequestBuilders.fileUpload(resourceUri)
                .file(firstFile));
    }

    @When("Client uploads more files to (.*)")
    public void clientUploadsMoreFilesTo(String resourceUri) throws Exception {
        MockMultipartFile firstFile = new MockMultipartFile("file", "filename.txt", "text/plain", "some xml".getBytes());
        MockMultipartFile secondFile = new MockMultipartFile("data", "other-file-name.data", "text/plain", "some other type".getBytes());
        MockMultipartFile jsonFile = new MockMultipartFile("json", "", "application/json", "{\"json\": \"someValue\"}".getBytes());
        resultActions = mockMvc.perform(MockMvcRequestBuilders.fileUpload(resourceUri)
                .file(firstFile)
                .file(secondFile)
                .file(jsonFile));
    }

    @Then("^the response code should be (\\d*)$")
    public void checkResponse(int statusCode) throws Exception {
        resultActions.andExpect(status().is(statusCode));
    }

    @Then("^the result json should be:$")
    public void checkResponseJsonMatch(String jsonString) throws Exception {
        resultActions.andExpect(content().json(jsonString));
    }

    @Then("^the result json should be empty")
    public void checkResponseJsonIsEmpty() throws Exception {
        resultActions.andExpect(content().json("[]"));
    }

    /**
     * Should be only used to test what json should be returned!
     */
    @Then("^the result string should be:$")
    public void checkResponseStringMatch(String jsonString) throws Exception {
        resultActions.andExpect(content().string(jsonString));
    }

    @Then("^the result set should contain (\\d*) records$")
    public void checkResponseJsonMatch(int recordNumber) throws Exception {
        resultActions.andExpect(jsonPath("$", hasSize(recordNumber)));
    }

    @Then("^the result should be false$")
    public void checkResponseIfFalse() throws Exception {
        resultActions.andExpect(content().string(String.valueOf(false)));
    }

    @Then("^the result should be true$")
    public void checkResponseIfTrue() throws Exception {
        resultActions.andExpect(content().string(String.valueOf(true)));
    }

    @Then("^the following headers should present \"(.*)\"$")
    public void checkHeaderPresence(List<String> headers) throws Exception {
        for (String header : headers) {
            assertNotNull("Header not present: " + header, resultActions.andReturn().getResponse().getHeaderValue(header));
        }
    }

    @Then("^the following header should present \"(.*)\" with value \"(.*)\"$")
    public void checkHeaderPresenceWithValue(String headerName, String headerValue) throws Exception {
        assertEquals("Header not present with value: " + headerName + "=" + headerValue, headerValue, resultActions.andReturn().getResponse().getHeaderValue(headerName));
    }
}
