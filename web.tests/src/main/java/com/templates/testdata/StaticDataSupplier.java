package com.templates.testdata;

import com.templates.enums.AttachmentFormat;
import com.templates.models.User;
import com.templates.utils.CommonFunctions;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaticDataSupplier {

    private static List<User> users = new ArrayList<>();

    static {
        users = Arrays.asList(
                User.builder()
                        .email("your test email")
                        .password("your test password")
                        .name("your test name")
                        .build()
        );
    }

    @Accessors(fluent = true)
    @Getter(lazy = true)
    private final CommonFunctions functions = new CommonFunctions();

    @Step("Get test user")
    public static User getUser() {
        return new StaticDataSupplier().getUserWrapped();
    }

    private User getUserWrapped() {
        User foundedUser = users.stream().findFirst().orElse(null);
        Allure.addAttachment("Test user info", AttachmentFormat.TEXT.getAttachmentFormat(), functions().getInfo(foundedUser));
        return foundedUser;
    }
}
