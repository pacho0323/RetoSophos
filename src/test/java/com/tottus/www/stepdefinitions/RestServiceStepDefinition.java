package com.tottus.www.stepdefinitions;

import static com.tottus.www.exceptions.ServicioRestException.MESSAGE_FAILED_THE_RESPONSE_SERVICE_REST;
import static com.tottus.www.utils.Constantes.ENDPOINT;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.containsString;

import java.util.List;

import com.tottus.www.exceptions.ServicioRestException;
import com.tottus.www.models.User;
import com.tottus.www.questions.TheResponseDate;
import com.tottus.www.tasks.FindAUser;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

public class RestServiceStepDefinition {

	@Before
	public void set_the_stage() {
		OnStage.setTheStage(new OnlineCast());

	}

	@Given("^the user enter the endpoint of the rest service$")
	public void theUserEnterTheEndpointOfTheRestService() {
		theActorCalled("juan").whoCan(CallAnApi.at(ENDPOINT));
	}

	@When("^he enter the dates$")
	public void heEnterTheDates(List<User> user) {
		theActorInTheSpotlight().attemptsTo(FindAUser.withData(user.get(0)));
	}

	@Then("^he verify the expected data$")
	public void heVerifyTheExpectedData(List<String> datos) {
		theActorInTheSpotlight().should(
				seeThat(TheResponseDate.firstName(), containsString(datos.get(0)))
						.orComplainWith(ServicioRestException.class, MESSAGE_FAILED_THE_RESPONSE_SERVICE_REST),
				seeThat(TheResponseDate.lastName(), containsString(datos.get(1)))
						.orComplainWith(ServicioRestException.class, MESSAGE_FAILED_THE_RESPONSE_SERVICE_REST),
				seeThat(TheResponseDate.code(), containsString(datos.get(2)))
						.orComplainWith(ServicioRestException.class, MESSAGE_FAILED_THE_RESPONSE_SERVICE_REST),
				seeThat(TheResponseDate.statusCode(), containsString(datos.get(3)))
						.orComplainWith(ServicioRestException.class, MESSAGE_FAILED_THE_RESPONSE_SERVICE_REST));

	}

}
