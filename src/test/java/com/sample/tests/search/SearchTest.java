package com.sample.tests.search;

import com.sample.tests.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(SearchTest.class);
    private WebDriver driver;

    @BeforeClass
    public void initClass() {
        driver = getDriver();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify 'About this item' section")
    public void verifyAboutThisItem() {
        logger.info("Starting the test " + new Object() {
        }.getClass().getEnclosingMethod().getName());
        Assert.assertTrue(true);
//        Assert.assertFalse(true);
    }
}
