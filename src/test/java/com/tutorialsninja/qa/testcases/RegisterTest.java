package com.tutorialsninja.qa.testcases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountsSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base{
	
	public RegisterTest () {
		super();
	}
	
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homepage = new HomePage(driver);
		homepage.clickonMyAccountDropMenu();
		//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		homepage.clickonRegisterOption();
		//driver.findElement(By.linkText("Register")).click();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterfirstname(dataprop.getProperty("FirstName"));
		//driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("FirstName"));
		registerpage.enterlastname(dataprop.getProperty("LastName"));
		//driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("LastName"));
		registerpage.enteremailfield(Utilities.generateEmailWithTimestamp());
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimestamp());
		registerpage.entertelephonefield(prop.getProperty("InvalidPassword"));
		//driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		registerpage.enterpasswordfield(prop.getProperty("ValidPassword"));
		//driver.findElement(By.id("input-password")).sendKeys("12345");
		registerpage.enterpasswordagain(prop.getProperty("ValidPassword"));
		//driver.findElement(By.id("input-confirm")).sendKeys("12345");
		registerpage.selectprivacypolicyagreefield();
		//driver.findElement(By.name("agree")).click();
		registerpage.clickcontinuebutton();
		//driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		AccountsSuccessPage accountsuccesspage = new AccountsSuccessPage(driver);
		String actualSuccessHeading = accountsuccesspage.retrieveAccountSuccessPageHeading();
		//String actualSuccessHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		
		Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!","Account Success Page is not displayed");
		
		//driver.quit();
	}
	@Test(priority=2)
	public void verifyRegisteringAccountByProvidingAllFields() {
		
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterfirstname(dataprop.getProperty("FirstName"));
		//driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		registerpage.enterlastname(dataprop.getProperty("LastName"));
		//driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
		registerpage.enteremailfield(Utilities.generateEmailWithTimestamp());
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimestamp());
		registerpage.entertelephonefield(prop.getProperty("InvalidPassword"));
		//driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		registerpage.enterpasswordfield(prop.getProperty("ValidPassword"));
		//driver.findElement(By.id("input-password")).sendKeys("12345");
		registerpage.enterpasswordagain(prop.getProperty("ValidPassword"));
		//driver.findElement(By.id("input-confirm")).sendKeys("12345");
		registerpage.selectnewsletteroption();
		//driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		registerpage.selectprivacypolicyagreefield();
		//driver.findElement(By.name("agree")).click();
		registerpage.clickcontinuebutton();
		//driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		AccountsSuccessPage accountsuccesspage = new AccountsSuccessPage(driver);
		String actualSuccessHeading = accountsuccesspage.retrieveAccountSuccessPageHeading();
		//String actualSuccessHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		
		Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!","Account Success Page is not displayed");
		
		//String actualSuccessHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		//Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!","Account Success Page is not displayed");
		
		//driver.quit();
		
	}
	 @Test(priority=3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {
		
		 RegisterPage registerpage = new RegisterPage(driver);
		 
			registerpage.enterfirstname(dataprop.getProperty("FirstName"));
			registerpage.enterlastname(dataprop.getProperty("LastName"));
			
		 	//driver.findElement(By.id("input-firstname")).sendKeys("Arun");
			//driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
			registerpage.enteremailfield(prop.getProperty("ValidEmail"));
			//driver.findElement(By.id("input-email")).sendKeys("amotooricap9@gmail.com");
			
			registerpage.entertelephonefield(prop.getProperty("InvalidPassword"));
			registerpage.enterpasswordfield(prop.getProperty("ValidPassword"));
			registerpage.enterpasswordagain(prop.getProperty("ValidPassword"));
			registerpage.selectnewsletteroption();
			registerpage.selectprivacypolicyagreefield();
			registerpage.clickcontinuebutton(); 			
			
			/*driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
			driver.findElement(By.id("input-password")).sendKeys("12345");
			driver.findElement(By.id("input-confirm")).sendKeys("12345");
			driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
			driver.findElement(By.name("agree")).click();
			driver.findElement(By.xpath("//input[@value='Continue']")).click();
			*/
			
			String actualWarning = registerpage.retrieveduplicateEmailAddressWarning();
			//String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
			Assert.assertTrue(actualWarning.contains("Warning: E-Mail Address is already registered!"),"Warning message regarding duplicate email address is not displayed.");
			
			//driver.quit();
		 
	}
	 
	 @Test(priority=4)
	 public void verifyRegisteringAccountWithoutFillingAnyDetails() {
		 
		 	RegisterPage registerpage = new RegisterPage(driver);
		 	registerpage.clickcontinuebutton();
			//driver.findElement(By.xpath("//input[@value='Continue']")).click();
			
		 	String actualPrivacyPolicyWarning = registerpage.retrieveduplicateEmailAddressWarning();
			//String actualPrivacyPolicyWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
			Assert.assertTrue(actualPrivacyPolicyWarning.contains("Warning: You must agree to the Privacy Policy!"),"Privacy Policy warning message is not displayed");
			
			String actualFirstNameWarning = registerpage.retrievefirstnamewarning();
			//String actualFirstNameWarning = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
			Assert.assertEquals(actualFirstNameWarning,"First Name must be between 1 and 32 characters!","First Name warning message is not displayed");
			
			String actualLastNameWarning = registerpage.retrievelastnamewaning(); 
			//String actualLastNameWarning = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
			Assert.assertEquals(actualLastNameWarning,"Last Name must be between 1 and 32 characters!","Last Name warning message is not displayed");
			
			String actualEmailWarning = registerpage.retrieveemailwarning();
			//String actualEmailWarning = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
			Assert.assertEquals(actualEmailWarning,"E-Mail Address does not appear to be valid!","Email warning message is not displayed");
			
			String actualTelephoneWarning = registerpage.retrievetelephonewarning();
			//String actualTelephoneWarning = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
			Assert.assertEquals(actualTelephoneWarning,"Telephone must be between 3 and 32 characters!","Telephone warning message is not displayed");
			
			String actualPasswordWarning = registerpage.retrievepassowordwarning();
			//String actualPasswordWarning = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
			Assert.assertEquals(actualPasswordWarning,"Password must be between 4 and 20 characters!","Password warning message is not displayed");
			
			//driver.quit();
	 }
}
