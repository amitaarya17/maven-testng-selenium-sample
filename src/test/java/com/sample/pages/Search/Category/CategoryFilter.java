package com.sample.pages.Search.Category;

import com.sample.pages.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoryFilter extends Page {

    private static final Logger LOGGER = LogManager.getLogger(CategoryFilter.class);
    private By filterLabel = By.cssSelector(".a-text-bold");
    private By filterName = By.cssSelector(".a-column.apb-browse-left-nav .apb-browse-refinements>.a-section");
    private By filterSubitem = By.cssSelector("li a");
    private By filterSubitemLabel = By.cssSelector("span.a-size-base");

    public CategoryFilter(WebDriver driver) {
        super(driver);
    }

    private WebElement getFilterItem(String filter) {
        List<WebElement> elements = findElements(filterName);
        for (int count = 0; count < 20; count++) {
            if (elements.size() < 4) {
                pauseTest(500l); // necessary to ensure enough time is given to the list to load completely
                elements = findElements(filterName);
            }
            LOGGER.info(elements.size());
            for (WebElement element : elements) {
                String linkText = element.findElement(filterLabel).getText();
                if (linkText.equals(filter)) {
                    return element;
                }
            }
        }
        throw new RuntimeException("The filter item " + filter + " does not exist");
    }

    public CategoryFilter selectFilterSubitems(String filter, List<String> subitems) {
        List<WebElement> elements = getFilterItem(filter).findElements(filterSubitem);
        for (String subitem : subitems) {
            boolean subitemExists = false;
            for (WebElement element : elements) {
                String optionText = element.findElement(filterSubitemLabel).getText();
                if (optionText.equals(subitem)) {
                    element.findElement(By.cssSelector("i")).click();
                    subitemExists = true;
                    break;
                }
            }
            if (!subitemExists) {
                throw new RuntimeException("The subitem " + subitem + " does not exist");
            }
        }
        return this;
    }
}