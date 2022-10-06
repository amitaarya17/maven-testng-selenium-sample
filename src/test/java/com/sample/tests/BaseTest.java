package com.sample.tests;

import com.sample.utilities.config.ConfigReader;
import com.sample.utilities.driver.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@Listeners(com.sample.utilities.listener.TestNGListenerImpl.class)
public class BaseTest {

    private static Properties configProperties = ConfigReader.getInstance().getEnvConfigProperties();

    public static WebDriver getDriver() {
        return WebDriverManager.getWebDriver();
    }

    @BeforeSuite
    public void initTest() {
        if (System.getProperty("test.environment") == null) {
            System.setProperty("test.environment", "local");
        }
    }

    @AfterMethod
    public void teardownMethod(ITestResult testResult) {
        if (System.getProperty("test.environment").equals("local")) {
            if (!testResult.isSuccess()) {
                final String newFileNamePath = configProperties.getProperty("SCREENSHOT_DEFAULT_FOLDER") + testResult.getName() + ".png";
                try {
                    Allure.addAttachment("Failure Screenshot", "image/png", Files.newInputStream(Paths.get(newFileNamePath)), "png");
                } catch (IOException e) {
                    throw new RuntimeException("Could not add the failure atatchment to failure report for " + testResult.getName());
                }
            }
        }
        WebDriverManager.quitDriver();
    }
}
