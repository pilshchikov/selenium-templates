package com.templates.testcases;

import com.templates.core.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class WebTestCase {

    protected final Driver driver = new Driver();

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
