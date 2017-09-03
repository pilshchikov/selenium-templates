package com.templates.pages.twitter;

import com.templates.annotations.Url;
import com.templates.core.Driver;
import com.templates.pages.AbstractPage;
import com.templates.pages.twitter.elements.timeline.DeleteDialogBlock;
import com.templates.pages.twitter.elements.timeline.TimelineBlock;
import com.templates.pages.twitter.elements.timeline.Tweet;

@Url("https://twitter.com")
public class TwitterHomePage extends AbstractPage {

    public TimelineBlock timeline;
    public DeleteDialogBlock deleteDialog;

    public TwitterHomePage(Driver driver) {
        super(driver);
    }

    /**
     * Check timeline is loaded
     *
     * @return state
     */
    public Boolean timelineIsLoaded() {
        return waitForPresent(timeline);
    }

    /**
     * Open full tweet field
     */
    public void openTweetField() {
        clickOn(timeline.topTweetBox.tweetTextContentField);
    }

    /**
     * Type tweet text
     *
     * @param tweetText tweet text
     */
    public void enterTweet(String tweetText) {
        inputText(timeline.topTweetBox.tweetTextContentField, tweetText);
    }

    /**
     * Press btn "Tweet"
     */
    public void tweet() {
        clickOn(timeline.topTweetBox.tweetBtn);
    }

    /**
     * Find tweet in timeline
     *
     * @param authorName  tweet author
     * @param messageText tweet text message
     * @return founded tweet
     */
    public Tweet findTweet(String authorName, String messageText) {
        return timeline.tweets.stream()
                .filter(tweet -> tweet.getHeaderText().contains(authorName))
                .filter(tweet -> tweet.getTweetText().contains(messageText))
                .findFirst()
                .orElse(null);
    }

    /**
     * Find tweet, press options menu, press "Delete tweet"
     *
     * @param authorName  tweet author
     * @param messageText tweet message
     */
    public void pressDeleteTweet(String authorName, String messageText) {
        Tweet tweet = findTweet(authorName, messageText);
        clickOn(tweet.dropdownBtn);
        clickOn(tweet.deleteAction);
    }

    /**
     * Accept tweet delete when delete tweet dialog is opened
     */
    public void agreeToDelete() {
        clickOn(deleteDialog.deleteBtn);
    }
}
