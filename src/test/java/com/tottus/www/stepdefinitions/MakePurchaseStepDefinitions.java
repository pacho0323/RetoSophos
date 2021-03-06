package com.tottus.www.stepdefinitions;

import static com.tottus.www.exceptions.MakePurchaseException.THE_PRODUCT_WAS_NOT_FOUND;
import static com.tottus.www.utils.Constantes.URL_TOTTUS;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.containsString;

import org.openqa.selenium.WebDriver;

import com.tottus.www.exceptions.MakePurchaseException;
import com.tottus.www.questions.TheProductAdded;
import com.tottus.www.tasks.AddProduct;
import com.tottus.www.tasks.SelectProduct;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;

public class MakePurchaseStepDefinitions {

	@Managed
	WebDriver driver;

	@Before
	public void set_the_stage() {
		OnStage.setTheStage(new OnlineCast());

	}
	@Given("^the user is on the store page$")
	public void theUserIsOnTheStorePage() {
		theActorCalled("juan").can(BrowseTheWeb.with(driver)).attemptsTo(Open.url(URL_TOTTUS));
	}

	@When("^he select a producto (.*)$")
	public void heSelectAProductoControlMagicRemoteANMRBAAWP(String product) {
		theActorInTheSpotlight().attemptsTo(SelectProduct.inTheStore(product), AddProduct.toShoppingCart());
	}

	@Then("^he valid that was added successfully (.*)$")
	public void heValidThatWasAddedSuccessfullyControlMagicRemoteANMRBAAWP(String product) {
		theActorInTheSpotlight().should(seeThat(TheProductAdded.is(), containsString(product))
				.orComplainWith(MakePurchaseException.class, THE_PRODUCT_WAS_NOT_FOUND));
	}
}
