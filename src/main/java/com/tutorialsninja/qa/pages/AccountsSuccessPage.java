package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountsSuccessPage {
	
	WebDriver driver;
	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement accountsuccessPageHeading;

	
	public AccountsSuccessPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public String retrieveAccountSuccessPageHeading() {
		
		String AccountsuccessPageHeadingtext = accountsuccessPageHeading.getText();
		return AccountsuccessPageHeadingtext;
	}
	
	
}

