package com.templates.testcases;

import com.templates.core.Driver;
import com.templates.rules.TestWatcherRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

public class WebTestCase {

    protected final Driver driver = new Driver();

    @Rule
    public final TestWatcherRule watcher = new TestWatcherRule(driver);

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
