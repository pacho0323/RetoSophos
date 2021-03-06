package com.tottus.www.questions;

import static com.tottus.www.userinterfaces.MakePurchaseUserInterface.SEEPRODUCT;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class TheProductAdded implements Question<String> {

	@Override
	public String answeredBy(Actor actor) {
		return Text.of(SEEPRODUCT).viewedBy(actor).asString();
	}

	public static TheProductAdded is() {
		return new TheProductAdded();
	}
}
