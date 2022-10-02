package com.sample.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

public class Page extends AbstractPage {

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
}
