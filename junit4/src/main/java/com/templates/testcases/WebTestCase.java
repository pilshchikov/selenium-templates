package com.templates.testcases;

import com.templates.Driver;
import org.junit.After;
import org.junit.Before;

public class WebTestCase {

    protected Driver driver;

    @Before
    public void setUp() {
        driver = new Driver();
    }

    @After
    public void tearDown() {
        if (driver != null) driver.close();
    }
}
