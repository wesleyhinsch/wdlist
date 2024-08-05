package com.hinsch.wdlist.bdd;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatusCode;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StepAdicionaProdutoIntegrationTest extends SpringIntegrationTest {


    @When("o cliente chama user")
    public void oClienteChamaUser() throws IOException {
        executePostWD();
    }

    @Then("o cliente recebe status code of {int}")
    public void oClienteRecebeStatusCodeOf(int statusCode) throws IOException {
        final HttpStatusCode currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));executeGet("http://localhost:8080/wd/adicionarProduto");
    }

}
