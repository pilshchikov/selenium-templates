package com.templates.pages.google;

import com.templates.annotations.Url;
import com.templates.core.Driver;
import com.templates.pages.AbstractPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

@Url("https://google.com")
public class GooglePage extends AbstractPage {

    @Name("Search input")
    @FindBy(id = "lst-ib")
    public HtmlElement searchInput;

    @Name("Search button")
    @FindBy(name = "btnK")
    public HtmlElement searchBtn;

    @Name("Search results")
    @FindBy(className = "g")
    public List<HtmlElement> resultItems;

    public GooglePage(Driver driver) {
        super(driver);
    }

    public void typeSearchPhrase(String phrase) {
        inputText(searchInput, phrase);
    }

    public void search() {
        inputSeq(searchInput, Keys.ENTER);
    }
}
