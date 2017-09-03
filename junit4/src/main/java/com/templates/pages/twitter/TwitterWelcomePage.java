package com.templates.pages.twitter;

import com.templates.annotations.Url;
import com.templates.core.Driver;
import com.templates.pages.AbstractPage;
import com.templates.pages.twitter.elements.LoginBlock;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Url("https://twitter.com")
public class TwitterWelcomePage extends AbstractPage {

    @Name("SignUp button")
    @FindBy(css = ".js-signav")
    public HtmlElement signUp;

    @Name("Login button")
    @FindBy(css = ".js-login")
    public HtmlElement loginBtn;

    public LoginBlock loginBlock;

    public TwitterWelcomePage(Driver driver) {
        super(driver);
    }

    /**
     * Open sing form on main page
     */
    public void openSignInForm() {
        clickOn(loginBtn);
    }

    /**
     * Type email/phone on sign form
     *
     * @param email email
     */
    public void enterEmail(String email) {
        inputText(loginBlock.emailIpt, email);
    }

    /**
     * Type password on sign form
     *
     * @param password password
     */
    public void enterPassword(String password) {
        inputText(loginBlock.passwordIpt, password);
    }

    /**
     * Press signIn button
     */
    public void pressSignIn() {
        clickOn(loginBlock.submitBtn);
    }
}
