package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {

	public WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;

	public SearchTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		homePage = new HomePage(driver);
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		searchPage = homePage.searchForProduct(dataProp.getProperty("searchValidProduct"));
		Assert.assertTrue(searchPage.displayStatusOFHpProduct());
	}
	
	//There is no product that matches the search criteria.
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		searchPage = homePage.searchForProduct(dataProp.getProperty("searchInvalidProduct"));
		Assert.assertTrue(
				searchPage.checkNoProductText().contains("There is no product that matches the search criteria."),
				"The Product is Valid");
	}

	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct() {
		searchPage = homePage.clickSearchButton();
		Assert.assertTrue(searchPage.checkNoProductText().contains(dataProp.getProperty("noProductTextinSearchResult")),
				"The Product is Valid");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
