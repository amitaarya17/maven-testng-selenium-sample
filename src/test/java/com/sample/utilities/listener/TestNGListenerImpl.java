package com.sample.utilities.listener;

import com.sample.utilities.config.ConfigReader;
import com.sample.utilities.driver.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class TestNGListenerImpl implements ITestListener {

    private static Properties configProperties = ConfigReader.getInstance().getEnvConfigProperties();
    private static final Logger LOGGER = LogManager.getLogger(TestNGListenerImpl.class);

    public TestNGListenerImpl() {
        super();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            final String newFileNamePath = configProperties.getProperty("SCREENSHOT_DEFAULT_FOLDER") + result.getName() + ".png";
            LOGGER.info("Saving a screenshot- " + newFileNamePath);

            final String base64 = ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
            final File scrFile = OutputType.FILE.convertFromBase64Png(base64);
            FileUtils.copyFile(scrFile, new File(newFileNamePath));

        } catch (IOException e) {
            LOGGER.info("Could not take screenshot for the test '" + result.getName() + "' due to: " + e);
        }
    }
}
