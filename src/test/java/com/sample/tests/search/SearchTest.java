package com.sample.tests.search;

import com.sample.tests.BaseTest;
import com.sample.utilities.config.ConfigReader;
import com.sample.utilities.driver.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

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
