package com.templates.tests;

import com.templates.steps.YandexSteps;
import com.templates.testcases.WebTestCase;
import org.junit.Test;

public class YandexTest extends WebTestCase {

    private final YandexSteps steps = new YandexSteps(driver);

    @Test
    public void simpleSearch() throws Exception {
        steps.openYandex();
        steps.enterSearchPhrase("google chrome");
        steps.search();
        steps.resultsIsPresent();
    }
}
