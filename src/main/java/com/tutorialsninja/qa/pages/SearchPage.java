package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
WebDriver driver; 
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(linkText = "HP LP3065")
	private WebElement productLink;

	@FindBy(xpath = "//div[@id='content']/p[2]")
	private WebElement noProductText;
	
	public boolean displayStatusOFHpProduct() {
		boolean displayStatus = productLink.isDisplayed();
		return displayStatus;
	}
	
	public boolean checkNoProductTextDisplayed() {
		boolean displayStatus = noProductText.isDisplayed();
		return displayStatus;
	}
	
	public String checkNoProductText() {
		String message = noProductText.getText();
		return message;
	}
}
