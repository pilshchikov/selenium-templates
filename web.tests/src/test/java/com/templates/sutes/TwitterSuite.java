package com.templates.sutes;

import com.templates.tests.twitter.LoginTest;
import com.templates.tests.twitter.TweetTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({LoginTest.class, TweetTest.class})
public class TwitterSuite {
}
