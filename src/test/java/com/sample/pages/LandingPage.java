package com.sample.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends Page {

    private By allNav = By.cssSelector("#nav-hamburger-menu");
    private By signInTooltip = By.cssSelector("#nav-signin-tooltip");

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public CategoryNav openCategoryNav() {
        waitForElementToDisappear(signInTooltip);
        waitForElementToAppear(allNav).click();
        return new CategoryNav(getDriver());
    }
}
