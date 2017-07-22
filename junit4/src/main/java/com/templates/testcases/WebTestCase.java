package com.templates.testcases;

import com.templates.core.Driver;
import org.junit.After;
import org.junit.Before;

public class WebTestCase {

    protected final Driver driver = new Driver();

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        if (driver != null) driver.close();
    }
}
