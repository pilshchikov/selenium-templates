package templates.pages.duckduckgo;

import com.templates.annotations.Url;
import com.templates.core.Driver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import templates.pages.AbstractPage;

import java.util.List;

@Url("https://duckduckgo.com")
public class DuckDuckGoPage extends AbstractPage {

    @Name("Search input")
    @FindBy(id = "search_form_input_homepage")
    public HtmlElement searchInput;

    @Name("Search button")
    @FindBy(id = "search_button_homepage")
    public HtmlElement searchBtn;

    @Name("Search results")
    @FindBy(css = ".result")
    public List<HtmlElement> resultItems;

    public DuckDuckGoPage(Driver driver) {
        super(driver);
    }

    public void typeSearchPhrase(String phrase) {
        inputText(searchInput, phrase);
    }

    public void search() {
        clickOn(searchBtn);
    }
}
