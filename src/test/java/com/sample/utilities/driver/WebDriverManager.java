package com.sample.utilities.driver;

import com.sample.utilities.config.Browser;
import com.sample.utilities.config.ConfigReader;
import com.sample.utilities.config.Environment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ThreadGuard;

import java.time.Duration;
import java.util.Properties;

public class WebDriverManager {

    protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger LOGGER = LogManager.getLogger(WebDriverManager.class);
    private static final Environment ENVIRONMENT = Environment.getEnvironment(System.getProperty("test.environment", "local"));
    private static Properties configProperties = ConfigReader.getInstance().getEnvConfigProperties();
    private static Browser BROWSER = Browser.getBrowser(configProperties.getProperty("BROWSER"));

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static WebDriver getWebDriver() {
        LOGGER.info("Creating webdriver for " +  ENVIRONMENT);
        switch (ENVIRONMENT) {
            case LOCAL:
                return setupLocalDriver();
            case GRID:
                return setupRemoteDriver();
            default:
                throw new RuntimeException("The environment "+ ENVIRONMENT + "  is not available.");
        }
    }

    public static WebDriver setupLocalDriver(){
        WebDriver webDriver;
        switch (BROWSER) {
            case CHROME:
                LOGGER.info("Creating chrome browser session");
                driver.set(ThreadGuard.protect(new ChromeDriver(CapabilityManager.getChromeOptions())));
                LOGGER.info("Successfully launched chrome browser");
                getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(
                        Integer.parseInt(configProperties.getProperty("IMPLICIT_WAIT"))));
                getDriver().manage().window().maximize();
                getDriver().get(configProperties.getProperty("URL").toString());
                LOGGER.info("Successfully Opened the default URL- " + configProperties.getProperty("URL") + " in chrome ");
                return getDriver();
            default:
                throw new RuntimeException("The " + BROWSER + " browser is not supported");
        }

    }

    public static WebDriver setupRemoteDriver(){
        return getDriver();
    }

    public static void quitDriver(){
        getDriver().quit();
        driver.remove();
    }
}
