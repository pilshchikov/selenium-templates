package com.templates.pages.twitter.elements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("SignIn form")
@FindBy(id = "login-dialog-dialog")
public class LoginBlock extends HtmlElement {

    @Name("Email/Phone input")
    @FindBy(css = ".LoginForm-username input")
    public HtmlElement emailIpt;

    @Name("Password input")
    @FindBy(css = ".LoginForm-password input")
    public HtmlElement passwordIpt;

    @Name("SingIn button")
    @FindBy(css = ".js-submit")
    public HtmlElement submitBtn;
}
