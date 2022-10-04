package com.sample.pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.Set;

public class Page extends AbstractPage {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Page.class);

    public Page(WebDriver driver) {
        super(driver);
    }

    protected List<WebElement> findElements(By selector) {
        return getDriver().findElements(selector);
    }

    protected WebElement waitForElementToAppear(By selector) {
        return getWait().until((ExpectedCondition<WebElement>) webDriver -> {
            List<WebElement> elements = findElements(selector);
            for (WebElement element : elements) {
                try {
                    if (element.isDisplayed()) {
                        return element;
                    }
                } catch (StaleElementReferenceException e) {
                    // Do nothing -- stale element reference just means the element
                    // disappeared from the dom before we could check whether it is displayed.
                }
            }
            return null;
        });
    }

    protected void switchWindowHandle() {
        String currentHandle = getDriver().getWindowHandle();
        Set<String> handles = getDriver().getWindowHandles();
        for (String handle : handles) {
            if (!currentHandle.equals(handle)) {
                driver.switchTo().window(handle);
            }
        }
    }

    protected void waitForElementToDisappear(By selector) {
        try {
            getWait().until((ExpectedCondition<Boolean>) webDriver -> {
                try {
                    return findElements(selector).stream().noneMatch(WebElement::isDisplayed);
                } catch (StaleElementReferenceException e) {
                    // Do nothing -- stale element reference just means the element disappeared before
                    // we could check whether it is displayed.
                    return false;
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("Timed out while waiting for '" + selector + "' to disappear");
        }
    }

    protected <T extends Page> T pauseTest(long timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds);
        } catch (InterruptedException ex) {
            LOGGER.error("InterruptedException exception thrown");
        }
        return (T) this;
    }
}
