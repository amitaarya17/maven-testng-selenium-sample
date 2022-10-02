package com.sample.utilities;

import com.sample.utilities.config.ConfigReader;
import com.sample.utilities.driver.CapabilityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.Properties;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger LOGGER = LogManager.getLogger(RetryAnalyzer.class);
    private static Properties configProperties = ConfigReader.getInstance().getEnvConfigProperties();

    /**
     * The internal retry count
     */
    private int retryCount = 0;


    /**
     * Returns true if the test method has to be retried, false otherwise.
     *
     * @param result The result of the test method that just ran.
     * @return true if the test method has to be retried, false otherwise.
     */
    @Override
    public boolean retry(ITestResult result) {
        int maxRetryCount = Integer.getInteger(configProperties.getProperty("RETRY_COUNT"));
        if (retryCount < maxRetryCount) {
            LOGGER.info("Checking for rerun of the test: '" + result.getName() + "' of class "
                    + result.getMethod().getMethodName() + "' for the retry - " + retryCount);
            retryCount++;
            return true;
        }
        return false;
    }
}
