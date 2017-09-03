package com.templates.steps;

import com.templates.core.Driver;
import com.templates.models.User;
import com.templates.pages.twitter.TwitterHomePage;
import com.templates.pages.twitter.TwitterWelcomePage;
import io.qameta.allure.Step;

import static org.junit.Assert.assertTrue;

public class TwitterSteps extends CommonSteps {

    private final TwitterWelcomePage welcomePage = new TwitterWelcomePage(driver);
    private final TwitterHomePage homePage = new TwitterHomePage(driver);

    public TwitterSteps(Driver driver) {
        super(driver);
    }

    @Step("Open welcome page")
    public void openWelcomePage() {
        welcomePage.open();
        assertTrue("LogIn button is not present", page().waitForPresent(welcomePage.loginBtn));
    }

    @Step("Login as {user.name}")
    public void loginAs(User user) {
        welcomePage.openSignInForm();
        assertTrue("LogIn block is not present", page().waitForPresent(welcomePage.loginBlock));
        welcomePage.enterEmail(user.email());
        welcomePage.enterPassword(user.password());
        welcomePage.pressSignIn();
        assertTrue("Block is not disappeared", page().waitForNotPresent(welcomePage.loginBlock));
    }

    @Step("Check tweet timeline is open")
    public void timelineIsLoaded() {
        assertTrue("Timeline is not loaded", homePage.timelineIsLoaded());
    }

    @Step("Type tweet")
    public void typeMessage(String tweetText) {
        homePage.openTweetField();
        homePage.enterTweet(tweetText);
        homePage.tweet();
        assertTrue("Tweet is not tweeted", page().waitForNotPresent(homePage.timeline.topTweetBox.tweetBtn));
    }

    @Step("Check message is displayed")
    public void messageIsDisplayed(User user, String tweetMessage) {
        assertTrue("Cant find message", functions().waitFor(
                () -> homePage.findTweet(user.name(), tweetMessage) != null,
                3, 3));
    }

    @Step("Delete message")
    public void deleteMessage(User user, String message) {
        homePage.pressDeleteTweet(user.name(), message);
        assertTrue("Delete dialog is not present", page().waitForPresent(homePage.deleteDialog));
        homePage.agreeToDelete();
        assertTrue("Delete dialog is not disappeared", page().waitForNotPresent(homePage.deleteDialog));
    }

    @Step("Check message is deleted")
    public void messageIsDeleted(User user, String message) {
        assertTrue("Cant find message", functions().waitFor(
                () -> homePage.findTweet(user.name(), message) == null,
                3, 3));
    }
}
