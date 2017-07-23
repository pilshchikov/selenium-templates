package templates.pages.yandex;

import com.templates.annotations.Url;
import com.templates.core.Driver;
import templates.pages.AbstractPage;

import java.util.List;

@Url("https://yandex.ru")
public class YandexPage extends AbstractPage {

    public ArrowBlock arrow;
    public List<ResultItem> resultItems;

    public YandexPage(Driver driver) {
        super(driver);
    }

    public void typeSearchPhrase(String searchPhrase) {
        clearText(arrow.input);
        inputText(arrow.input, searchPhrase);
    }

    public void search() {
        clickOn(arrow.searchBtn);
    }
}
