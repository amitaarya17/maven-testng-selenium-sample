package com.sample.utilities.config;

import com.sample.tests.search.SearchTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Logger LOGGER = LogManager.getLogger(ConfigReader.class);
    private static ConfigReader instance = new ConfigReader();

    public static ConfigReader getInstance() {
        return instance;
    }

    public Properties readProperties(String fileName) {
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        try {
            LOGGER.info("Accessing config file " + fileName);
            prop.load(inputStream);
            LOGGER.info("Config file '" + fileName + "' successfully read");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public Properties getEnvConfigProperties() {
        return this.readProperties("application-config.properties");
    }

    public Properties getTestDataConfigProperties() {
        return this.readProperties("testData-config.properties");
    }
}
