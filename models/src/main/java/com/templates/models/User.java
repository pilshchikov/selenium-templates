package com.templates.models;

import com.templates.annotations.Description;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@Accessors(fluent = true)
@Description("Test user")
public class User {

    @Description("Email")
    private String email;

    @Description("Password")
    private String password;

    @Description("Name")
    private String name;
}
