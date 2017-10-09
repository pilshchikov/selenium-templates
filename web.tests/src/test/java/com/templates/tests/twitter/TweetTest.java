package com.templates.tests.twitter;

import com.templates.steps.TwitterSteps;
import com.templates.testcases.TwitterTestCase;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

@Feature("Twitter")
@Story("Type message")
public class TweetTest extends TwitterTestCase {

    private final TwitterSteps steps = new TwitterSteps(driver);

    @Before
    public void logIn() {
        steps.openWelcomePage();
        steps.loginAs(user);
        steps.timelineIsLoaded();
    }

    @Test
    @DisplayName("Type tweet")
    public void typeTweet() {
        String message = getMessage();

        steps.typeMessage(message);
        steps.messageIsDisplayed(user, message);
    }

    @Test
    @DisplayName("Delete tweet")
    public void deleteTweet() {
        String message = getMessage();

        steps.typeMessage(message);
        steps.messageIsDisplayed(user, message);
        steps.deleteMessage(user, message);
        steps.messageIsDeleted(user, message);
    }

}
