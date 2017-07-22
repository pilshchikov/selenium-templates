package com.templates.pages.yandex;

import com.templates.core.Driver;
import com.templates.pages.AbstractPage;

import java.util.List;

@Url("https://yandex.ru")
public class YandexPage extends AbstractPage {

    public YandexPage(Driver driver) {
        super(driver);
    }

    public ArrowBlock arrow;

    public List<ResultItem> resultItems;

    public void typeSearchPhrase(String searchPhrase) {
        clearText(arrow.input);
        inputText(arrow.input, searchPhrase);
    }

    public void search() {
        clickOn(arrow.searchBtn);
    }
}
