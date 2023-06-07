package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {

	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage; 
	
	public RegisterTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
	}

	@Test(priority = 1)
	public void verifyRegisteringAccountWithMandatoryFields() {
		accountSuccessPage = registerPage.registerWithoutNewsLetter(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("telephone"), prop.getProperty("validPassword"));
		Assert.assertEquals(accountSuccessPage.getAccountSuccessPageHeading(), dataProp.getProperty("accountCreatedsuccessfuly"),
				"Account Success Page is not displayed");
	}

	@Test(priority = 2)
	public void verifyRegisteringAccountByProvidingAllFields() {
		accountSuccessPage = registerPage.registerWithoutNewsLetter(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("telephone"), prop.getProperty("validPassword"));
		Assert.assertEquals(accountSuccessPage.getAccountSuccessPageHeading(), "Your Account Has Been Created!",
				"Account Success Page is not displayed");
	}

	@Test(priority = 3)
	public void verifyRegisteringAccountWtihExistingEmailAddress() {

		accountSuccessPage = registerPage.registerWithoutNewsLetter(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validEmail"),dataProp.getProperty("telephone"), prop.getProperty("validPassword"));
		Assert.assertTrue(registerPage.getDuplicateEmailWarning().contains(dataProp.getProperty("duplicateAccountWarning")),
				"Warning for duplicate email not displayed");
	}

	@Test(priority = 4)
	public void verifyRegisteringAccountWtihoutFillingDetails() {

		registerPage.clickContinueButton();
		Assert.assertTrue(registerPage.getPrivacyWarning().contains(dataProp.getProperty("privacyWarning")),
				"Privacy warning message not displayed");
		Assert.assertTrue(registerPage.getFirstNameWarning().contains(dataProp.getProperty("firstNameWarning")),
				"Firstname warning message not displayed");
		Assert.assertTrue(registerPage.getLastNameWarning().contains(dataProp.getProperty("lastNameWarning")),
				"Lastname warning message not displayed");
		Assert.assertTrue(registerPage.getEmailWarning().contains(dataProp.getProperty("emailWarning")),
				"Email warning message not displayed");
		Assert.assertTrue(registerPage.getTelephoneWarning().contains(dataProp.getProperty("telephoneWarning")),
				"Tel warning message not displayed");
		Assert.assertTrue(registerPage.getPasswordWarning().contains(dataProp.getProperty("passwordWarning")),
				"Password warning message not displayed");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}