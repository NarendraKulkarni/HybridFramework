package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	//@FindBy option is given by pagefactory While moving from one page to other page stale element exception
	// is thrown hence to solve it, pagefactory design pattern is implemented
	
	//Objects
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginoption;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	//Constructor 
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		//For stale element initialize driver and class; hence "this" keyword used
		//After initialization the webwlements will be linked with locator in @FindBy due to pagefactory
		//to support page object model design pattern we selenium has given pagefactory design pattern
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public void clickonMyAccountDropMenu() {
		
		myAccountDropMenu.click();
	}
	
	public void selectLoginOption() {
		loginoption.click();
	}
	
	public void clickonRegisterOption() {
		registerOption.click();
	}
}
