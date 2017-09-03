package com.templates.pages.twitter.elements.timeline;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

@Name("Main timeline")
@FindBy(id = "timeline")
public class TimelineBlock extends HtmlElement {

    public TopTimelineTweetBoxBlock topTweetBox;
    public List<Tweet> tweets;
}
