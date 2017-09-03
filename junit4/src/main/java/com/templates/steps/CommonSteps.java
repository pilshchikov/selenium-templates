package com.templates.steps;

import com.templates.core.Driver;
import com.templates.enums.AttachmentFormat;
import com.templates.pages.AbstractPage;
import com.templates.utils.CommonFunctions;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.experimental.Accessors;

public class CommonSteps {

    protected Driver driver;

    @Accessors(fluent = true)
    @Getter(lazy = true)
    private final CommonFunctions functions = new CommonFunctions();

    @Accessors(fluent = true)
    @Getter(lazy = true)
    private final AbstractPage page = new AbstractPage(driver);

    public CommonSteps(Driver driver) {
        this.driver = driver;
    }

    /**
     * Прикладываем информацию об объекте к отчету
     *
     * @param object         объект
     * @param attachmentName название приложения
     */
    @Step("{attachmentName}")
    public void attach(Object object, String attachmentName) {

        functions().takeAttachment(AttachmentFormat.TEXT, functions().getInfo(object), attachmentName);
    }
}
