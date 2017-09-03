package com.templates.tests.twitter;

import com.templates.steps.TwitterSteps;
import com.templates.testcases.TwitterTestCase;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class LoginTest extends TwitterTestCase {

    private final TwitterSteps steps = new TwitterSteps(driver);

    @Test
    @DisplayName("Login test")
    public void logIn() {
        steps.openWelcomePage();
        steps.loginAs(user);
        steps.timelineIsLoaded();
    }
}
