package templates.tests;


import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import templates.steps.GoogleSteps;
import templates.testcases.WebTestCase;

@Feature("Search")
@Story("google.com")
public class GoogleTest extends WebTestCase {

    private final GoogleSteps steps = new GoogleSteps(driver);

    @Test(testName = "Simple search test", description = "Simply open google.com, type search phrase and see result items")
    public void simpleSearch() throws Exception {
        steps.openGoogle();
        steps.enterSearchPhrase("yandex browser");
        steps.search();
        steps.resultsIsPresent();
    }
}
