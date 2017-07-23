package templates.steps;

import com.templates.core.Driver;
import io.qameta.allure.Step;
import templates.pages.duckduckgo.DuckDuckGoPage;

import static org.junit.Assert.assertTrue;

public class DuckDuckGoSteps extends CommonSteps {
    private final DuckDuckGoPage duckDuckGoPage;

    public DuckDuckGoSteps(Driver driver) {
        super(driver);
        this.duckDuckGoPage = new DuckDuckGoPage(driver);
    }

    @Step("Enter search phrase: {phrase}")
    public void enterSearchPhrase(String phrase) {
        duckDuckGoPage.typeSearchPhrase(phrase);
    }

    @Step("Click search button")
    public void search() {
        duckDuckGoPage.search();
    }

    @Step("Results is present")
    public void resultsIsPresent() {
        assertTrue("Results is not loaded", page().waitForListPresent(duckDuckGoPage.resultItems));
        page().makeScreenShot("Results");
    }

    public void openDuckDuckGo() {
        duckDuckGoPage.open();
    }
}
