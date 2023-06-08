package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {

	public WebDriver driver;
	LoginPage loginPage;
	AccountPage accountPage;
	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		loginPage= homePage.navigateToLoginPage();
	}

	@DataProvider(name = "validData")
	public Object[][] getTestData() {
		Object[][] data = Utilities.getTestData("Login");
		return data;
	}

	
	@Test(priority = 1, dataProvider = "validData")
	public void verifyLoginWithValidCredential(String email, String password) {
		AccountPage accountPage = loginPage.loginAction(email, password);
		Assert.assertTrue(accountPage.verifyEditYourAccountInformationOptionDisplayed());
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		accountPage = loginPage.loginAction(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingText().contains(dataProp.getProperty("noMatchWarningEmailPassword")), "Expected wanring is not displayed");
	}

	@Test(priority = 3)
	public void verifyLoginWithoutProvidingCredentials() {
		accountPage = loginPage.loginAction("", "");
		Assert.assertTrue(loginPage.noEmailPasswordMatchingText().contains(dataProp.getProperty("noMatchWarningEmailPassword")), "Expected wanring is not displayed");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
