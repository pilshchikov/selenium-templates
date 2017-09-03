package com.templates.testcases;

import com.templates.models.User;
import com.templates.testdata.StaticDataSupplier;
import org.junit.Before;

import java.util.Random;

public class TwitterTestCase extends WebTestCase {

    protected String getRandomDigits() {
        return String.valueOf(new Random().nextInt(9999));
    }

    protected User user = User.builder().build();

    @Before
    public void setUp() {
        user = StaticDataSupplier.getUser();
    }
}
