package com.sample.pages;

import com.sample.pages.Search.Category.CategoryDetail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoryNav extends Page {

    private By categoryItem = By.cssSelector("li a.hmenu-item div");
    private By categorySubItem = By.cssSelector("#hmenu-content ul.hmenu-visible li a");
    private By closeNav = By.cssSelector(".nav-sprite.hmenu-close-icon");

    public CategoryNav(WebDriver driver) {
        super(driver);
    }

    public CategoryNav openCategoryItem(String itemName) {
        waitForElementToAppear(closeNav);
        List<WebElement> elements = findElements(categoryItem);
        boolean itemExists = false;
        for (WebElement element : elements) {
            String linkText = element.getText();
            if (linkText.equals(itemName)) {
                WebElement parent = element.findElement(By.xpath(".."));
                itemExists = true;
                parent.click();
                break;
            }
        }
        if (!itemExists) {
            throw new RuntimeException("The subitem " + itemName + " does not exist");
        }
        return this;
    }

    public CategoryDetail openCategorySubItem(String subItemName) {
        List<WebElement> elements = findElements(categorySubItem);
        boolean itemExists = false;
        for (WebElement element : elements) {
            String linkText = element.getText();
            if (linkText.equals(subItemName)) {
                itemExists = true;
                element.click();
                break;
            }
        }
        if (!itemExists) {
            throw new RuntimeException("The subitem " + subItemName + " does not exist");
        }
        return new CategoryDetail(getDriver());
    }
}
