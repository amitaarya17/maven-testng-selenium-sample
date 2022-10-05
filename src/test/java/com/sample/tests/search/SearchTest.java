package com.sample.tests.search;

import com.sample.pages.LandingPage;
import com.sample.pages.Search.Category.product.ProductDetail;
import com.sample.tests.BaseTest;
import com.sample.utilities.config.ConfigReader;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class SearchTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(SearchTest.class);
    private static Properties dataConfigProperties = ConfigReader.getInstance().getTestDataConfigProperties();
    private String categoryItem = dataConfigProperties.getProperty("CATEGORY_ITEM");
    private String subCategoryItem = dataConfigProperties.getProperty("SUB_CATEGORY_ITEM");
    private String filterType = dataConfigProperties.getProperty("FILTER_TYPE");
    private String sortResultBy = dataConfigProperties.getProperty("SORT_RESULT_BY");
    private String resultNumber = dataConfigProperties.getProperty("RESULT_NUMBER");


    List<String> filterList = Arrays.asList("Samsung");

    private WebDriver driver;

    @BeforeClass
    public void initClass() {
        driver = getDriver();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify 'About this item' section")
    public void verifyAboutThisItem() {

        LOGGER.info("Starting the test " + new Object() {
        }.getClass().getEnclosingMethod().getName());

        ProductDetail productDetail = new LandingPage(driver).openCategoryNav()
                .openCategoryItem(categoryItem)
                .openCategorySubItem(subCategoryItem)
                .selectFilter(filterType, filterList)
                .sortResultsBy(sortResultBy)
                .getItemNumberFromResults(Integer.parseInt(resultNumber));

        Assert.assertTrue(productDetail.verifyAboutSectionExists());
    }
}
