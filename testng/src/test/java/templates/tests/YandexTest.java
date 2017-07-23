package templates.tests;


import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import templates.steps.YandexSteps;
import templates.testcases.WebTestCase;

@Feature("Search")
@Story("yandex.ru")
public class YandexTest extends WebTestCase {

    private final YandexSteps steps = new YandexSteps(driver);

    @Test(testName = "Simple search test",
            description = "Simply open yandex.ru, type search phrase and see result items")
    public void simpleSearch() throws Exception {
        steps.openYandex();
        steps.enterSearchPhrase("google chrome");
        steps.search();
        steps.resultsIsPresent();
    }
}
