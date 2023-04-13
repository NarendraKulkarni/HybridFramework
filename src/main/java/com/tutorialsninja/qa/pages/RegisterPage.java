package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstnamefield;
	
	@FindBy(id="input-lastname")
	private WebElement LastNamefield;
	
	@FindBy(id="input-email")
	private WebElement Emailfield;
	
	@FindBy(id="input-telephone")
	private WebElement telephonefield;
	
	@FindBy(id="input-password")
	private WebElement passwordfield;
	
	@FindBy(id="input-confirm")
	private WebElement passwordconfirmfield;
	
	@FindBy(name="agree")
	private WebElement privacypolicyagreefield;

	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continuebutton;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement newsletterfield;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailWarining;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div" )
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement LastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement Emailwarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephonewarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordwarning;
	
	public  RegisterPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	
	public String retrievepassowordwarning () {
		String passwordwarningtext = passwordwarning.getText();
		return passwordwarningtext;
	}
	
	public String retrievetelephonewarning () {
		String telephonewarningtext = telephonewarning.getText();
		return telephonewarningtext;
	}
	
	public String retrieveemailwarning () {
		String emailwarningtext = Emailwarning.getText();
		return emailwarningtext;
	}
	
	public String retrievelastnamewaning () {
		String lastnamewarningtext = LastNameWarning.getText();
		return lastnamewarningtext;
	}
	public String retrievefirstnamewarning () {
		
		String firstnamewarningtext = firstNameWarning.getText();
		return firstnamewarningtext;
	}
	
	
	
	public void enterfirstname(String firstnametext) {
		
		firstnamefield.sendKeys(firstnametext);
	}
	
	public void enterlastname(String lastnametext) {
	
		LastNamefield.sendKeys(lastnametext);
	}
	
	public void enteremailfield ( String emailtext) {
		Emailfield.sendKeys(emailtext);
	}
	
	public void entertelephonefield (String telephoneFieldtext) {
		telephonefield.sendKeys(telephoneFieldtext);
	}
	
	public void enterpasswordfield ( String passwordtext) {
		passwordfield.sendKeys(passwordtext);
		
	}
	
	public void enterpasswordagain ( String passowordtextconfirm) {
		passwordconfirmfield.sendKeys(passowordtextconfirm);
		
	}
	
	public void selectprivacypolicyagreefield () {
		
		privacypolicyagreefield.click();
	}
	
	public void clickcontinuebutton () {
		
		continuebutton.click();
	}
	
	public void selectnewsletteroption () {
		newsletterfield.click();
	}
	
	public String retrieveduplicateEmailAddressWarning () {
		String duplicateemailaddressWarningtext = duplicateEmailWarining.getText();
		return duplicateemailaddressWarningtext;
	}
}
