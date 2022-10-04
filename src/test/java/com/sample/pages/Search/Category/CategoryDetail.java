package com.sample.pages.Search.Category;

import com.sample.pages.Page;
import com.sample.pages.Search.Category.product.ProductDetail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoryDetail extends Page {

    private By itemCards = By.cssSelector(".s-card-container  .s-product-image-container .a-link-normal");
    private By sortDropDown = By.cssSelector(".a-button-text[data-action]");
    private By sortOptions = By.cssSelector(".a-dropdown-link");
    private By sortItemContainer = By.cssSelector(".a-popover-inner");
    private By sortOptionLabel = By.cssSelector(".a-dropdown-prompt");

    public CategoryDetail(WebDriver driver) {
        super(driver);
    }

    public CategoryDetail selectFilter(String filter, List<String> subitems) {
        CategoryFilter categoryFilter = new CategoryFilter(getDriver());
        categoryFilter.selectFilterSubitems(filter, subitems);
        return this;
    }

    public CategoryDetail sortResultsBy(String sortBy) {
        waitForElementToAppear(sortDropDown); //ensure the element does appear on the UI
        pauseTest(2000l); //not sure why the click does not go through sometimes so adding this hard pause
        waitForElementToAppear(sortDropDown).click();
        waitForElementToAppear(sortItemContainer);
        boolean isSorted = false;
        List<WebElement> elements5 = findElements(sortOptions);
        for (WebElement element : elements5) {
            String linkText = element.getText();
            if (linkText.trim().equals(sortBy)) {
                element.click();
                isSorted = true;
                String sortSelection = waitForElementToAppear(sortOptionLabel).getText();
                if (!sortBy.equals(sortSelection)) {
                    throw new RuntimeException("The result sort was not set to '" + sortBy + "' even after clicking");
                }
                break;
            }
        }
        if (!isSorted) {
            throw new RuntimeException("The filter " + sortBy + " to sort the result was not found");
        }
        return this;
    }

    public ProductDetail getItemNumberFromResults(int number) {
        List<WebElement> elements = findElements(itemCards);
        if (elements.size() >= number) {
            elements.get(number - 1).click();
        } else {
            throw new RuntimeException("The product item result number " + number + " does not exist");
        }
        return new ProductDetail(getDriver());
    }
}
