package com.sample.tests;
import com.sample.utilities.driver.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(com.sample.utilities.listener.TestNGListenerImpl.class)
public class BaseTest {

    public static WebDriver getDriver() {
        return WebDriverManager.getWebDriver();
    }

    @BeforeSuite
    public void initTest (){

    }

    @AfterMethod
    public void teardownMethods(){
        WebDriverManager.quitDriver();
    }
}
