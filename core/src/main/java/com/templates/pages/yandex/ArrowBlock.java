package com.templates.pages.yandex;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Main search bar")
@FindBy(css = ".search2")
public class ArrowBlock extends HtmlElement {

    @Name("Search button")
    @FindBy(css = ".search2__button")
    public HtmlElement searchBtn;

    @Name("Search input")
    @FindBy(css = ".input__control")
    public HtmlElement input;
}
