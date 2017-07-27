package com.templates.testcases;

import com.templates.CommonFunctions;
import com.templates.core.Driver;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class WebTestCase {

    protected final Driver driver = new Driver();

    @Getter(lazy = true)
    @Accessors(fluent = true)
    private final CommonFunctions functions = new CommonFunctions();

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult testResult) {
        if (testResult.getStatus() == (ITestResult.FAILURE))
            functions().makeScreenShot("Fail screenshot", driver.webDriver());
        driver.close();
    }
}
