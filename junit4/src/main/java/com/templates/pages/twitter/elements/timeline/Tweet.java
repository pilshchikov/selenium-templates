package com.templates.pages.twitter.elements.timeline;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Tweet")
@FindBy(css = ".tweet")
public class Tweet extends HtmlElement{

    @Name("Header")
    @FindBy(css = ".stream-item-header")
    public HtmlElement header;

    @Name("Text content")
    @FindBy(css = ".js-tweet-text-container")
    public HtmlElement textContent;

    @Name("Dropdown btn")
    @FindBy(css = ".dropdown")
    public HtmlElement dropdownBtn;

    @Name("Delete tweet button")
    @FindBy(css = ".js-actionDelete")
    public HtmlElement deleteAction;

    public String getHeaderText() {
        return header.getText();
    }

    public String getTweetText() {
        return textContent.getText();
    }
}
