package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(id="input-email")
	private WebElement EmailAddressField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement LoginButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotmatchingWarning;
	
	public LoginPage (WebDriver driver) {
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public void enterEmailAddress(String EmailText) {
		EmailAddressField.sendKeys(EmailText);
		
	}
	
	public void enterpassword (String Passwordtext) {
		passwordField.sendKeys(Passwordtext);
	}
	
	public void clickOnloginbutton () {
		
		LoginButton.click();
	}
	
	public String retrieveEmailpasswordNotMatchingWarningMessage () {
		
		String WarningText = emailPasswordNotmatchingWarning.getText();
		return WarningText;
		
	}
}
