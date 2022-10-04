package com.sample.pages.Search.Category.product;

import com.sample.pages.Page;
import com.sample.pages.Search.Category.CategoryFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class ProductDetail extends Page {

    private static final Logger LOGGER = LogManager.getLogger(CategoryFilter.class);

    private By aboutSection = By.cssSelector("#feature-bullets .a-size-base-plus");

    public ProductDetail(WebDriver driver) {
        super(driver);
    }

    public boolean verifyAboutSectionExists() {
        switchWindowHandle();
        try {
            waitForElementToAppear(aboutSection);
            return true;
        } catch (TimeoutException e) {
            LOGGER.error("The about section did not appear! " + e);
            return false;
        }

    }
}
