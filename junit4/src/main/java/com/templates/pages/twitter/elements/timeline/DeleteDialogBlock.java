package com.templates.pages.twitter.elements.timeline;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Delete agree dialog")
@FindBy(id = "delete-tweet-dialog-dialog")
public class DeleteDialogBlock extends HtmlElement {

    @Name("Agree button")
    @FindBy(css = ".delete-action")
    public HtmlElement deleteBtn;
}
