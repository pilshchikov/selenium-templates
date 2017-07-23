package com.templates.steps;

import com.templates.core.Driver;
import com.templates.pages.google.GooglePage;
import io.qameta.allure.Step;

import static org.junit.Assert.assertTrue;

public class GoogleSteps extends CommonSteps {

    private final GooglePage googlePage;

    public GoogleSteps(Driver driver) {
        super(driver);
        this.googlePage = new GooglePage(driver);
    }

    @Step("Enter search phrase: {phrase}")
    public void enterSearchPhrase(String phrase) {
        googlePage.typeSearchPhrase(phrase);
    }

    @Step("Click search button")
    public void search() {
        googlePage.search();
    }

    @Step("Results is present")
    public void resultsIsPresent() {
        assertTrue("Results is not loaded", page().waitForListPresent(googlePage.resultItems));
        page().makeScreenShot("Results");
    }

    public void openGoogle() {
        googlePage.open();
    }
}
