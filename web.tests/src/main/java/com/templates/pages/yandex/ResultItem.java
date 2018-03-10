package com.templates.pages.yandex;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Result item")
@FindBy(css = ".serp-item")
public class ResultItem extends HtmlElement {
}
