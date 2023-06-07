package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver; 
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id ="input-email")
	private WebElement emailAddressField; 
	
	
	@FindBy(id ="input-password")
	private WebElement passwordField; 
	
	@FindBy(xpath ="//input[@value='Login']")
	private WebElement loginButton; 
	
	@FindBy(xpath= "//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;
	
	@FindBy(xpath= "//div[contains(@class,'alert-dismissible')]")
	private WebElement noEmailPasswordMatchingText;
	
	public void enterEmail(String email) {
		emailAddressField.sendKeys(email);
	}
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public AccountPage clicLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public AccountPage loginAction(String email, String password) {
		emailAddressField.sendKeys(email);
		passwordField.sendKeys(password);
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String retrieveEmailPasswordNotMatchingText() {
		String warningText = emailPasswordNotMatchingWarning.getText();
		return warningText;
	}
	
	public String noEmailPasswordMatchingText() {
		String warningText = noEmailPasswordMatchingText.getText();
		return warningText;
	}
}
