package com.templates.sutes;

import com.templates.tests.DuckDuckGoTest;
import com.templates.tests.GoogleTest;
import com.templates.tests.YandexTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DuckDuckGoTest.class, GoogleTest.class, YandexTest.class})
public class SearchSuite {
}
