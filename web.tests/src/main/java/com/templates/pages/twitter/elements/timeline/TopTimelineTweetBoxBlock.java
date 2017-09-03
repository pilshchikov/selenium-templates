package com.templates.pages.twitter.elements.timeline;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Top timeline tweet box")
@FindBy(css = ".home-tweet-box ")
public class TopTimelineTweetBoxBlock extends HtmlElement {

    @Name("Tweet box")
    @FindBy(id = "tweet-box-home-timeline")
    public HtmlElement tweetTextContentField;

    @Name("Tweet button")
    @FindBy(css = ".js-tweet-btn")
    public HtmlElement tweetBtn;
}
