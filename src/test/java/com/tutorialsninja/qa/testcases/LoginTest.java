package com.tutorialsninja.qa.testcases;

//All imports for removing errors
import org.openqa.selenium.By;
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

public class LoginTest extends Base{
	
	public LoginTest () {
		super();
	}
	
	public WebDriver driver;
	
	
    @BeforeMethod
	public void  setup() {
    	
    	
    	//extending base class to initialize until application is opened
    	driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	    HomePage homepage = new HomePage(driver);
	    homepage.clickonMyAccountDropMenu();
	    homepage.selectLoginOption();	
    	//driver.findElement(By.xpath("//span[text()='My Account']")).click();
	    //driver.findElement(By.linkText("Login")).click();
		
	}
    
    
    
	@AfterMethod   
	public void tearDown() {
		
		driver.quit();
	}
	/* Without data provider, only data read from properties file,
	@Test(priority=1)
		public void verifyLoginWithValidCredentials() {
			
			
		    driver.findElement(By.id("input-email")).sendKeys("amotooricap9@gmail.com");
		    driver.findElement(By.id("input-password")).sendKeys("12345");
		    driver.findElement(By.xpath("//input[@value='Login']")).click();
		    
		    Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(), "Edit your account information option is not displayed.");
			//driver.quit();
	} */
	
	
	//Reading data from data provider just below this method( If name of provider not given then use method name.
	@Test(priority=1, dataProvider="validCredentialsprovider")
	public void verifyLoginWithValidCredentials(String email, String password) {
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(email);
	    //driver.findElement(By.id("input-email")).sendKeys(email);
		loginpage.enterpassword(password);
		//driver.findElement(By.id("input-password")).sendKeys(password);
		loginpage.clickOnloginbutton();
	    //driver.findElement(By.xpath("//input[@value='Login']")).click();
	    
		AccountPage accountpage = new AccountPage(driver);
		Assert.assertTrue(accountpage.getDisplayStatusOfEditYourAccountInformationOption());
	    //Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(), "Edit your account information option is not displayed.");
		//driver.quit();
	}
	
		
	//Data provider for priority 1 test script
	/*Reading data from locally multi-dimensional array
	 * @DataProvider(name ="validCredentialsprovider")
	public Object[][] supplyTestData() {
		
		Object [][] data = {
							{"amotooricap9@gmail.com","12345"},
							{"amotooricap9@gmail.com","12345"},
							{"amotooricap3@gmail.com","12345"},
							};
		return data;
	}*/
	//Following data provide reads data from spreadsheet
	@DataProvider(name ="validCredentialsprovider")
	public Object[][] supplyTestData() {
		
		Object [][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=2)
		public void verifyLoginWithInvalidCredentials() {
			
			LoginPage loginpage = new LoginPage(driver);
			loginpage.enterEmailAddress(Utilities.generateEmailWithTimestamp());
		    //driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimestamp());
		    loginpage.enterpassword(prop.getProperty("InvalidPassword"));
			//driver.findElement(By.id("input-password")).sendKeys("1234567890");
		    loginpage.clickOnloginbutton();
		    //driver.findElement(By.xpath("//input[@value='Login']")).click();
		    String actulawarningMessage = loginpage.retrieveEmailpasswordNotMatchingWarningMessage();
		    //String actualwarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText(); 
		    String expectedwarningmessage = "Warning: No match for E-Mail Address and/or Password.";
		    Assert.assertTrue(actulawarningMessage.contains(expectedwarningmessage),"Expected warning message is not displayed");
		    //driver.quit();
	    
	    
	}
	@Test(priority=3)
		public void verifyLoginWithInvalidEmailAndValidPassword() {
			
			LoginPage loginpage = new LoginPage(driver);
		    
			loginpage.enterEmailAddress(Utilities.generateEmailWithTimestamp());
			//driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimestamp());
		    
			//(timestamp function added as after 6 failed attempt with same email locks account and error message changes hence every)
		    //(time due to timestamp new email address is tried so it never makes 6 failed attempts)
		    
			loginpage.enterpassword(prop.getProperty("ValidPassword"));
			//driver.findElement(By.id("input-password")).sendKeys("12345");
		    loginpage.clickOnloginbutton();
			//driver.findElement(By.xpath("//input[@value='Login']")).click();
		    
		    
		    String actulawarningMessage = loginpage.retrieveEmailpasswordNotMatchingWarningMessage();
		    String expectedwarningmessage = "Warning: No match for E-Mail Address and/or Password.";
		    Assert.assertTrue(actulawarningMessage.contains(expectedwarningmessage),"Expected warning message is not displayed");
		    //String actualwarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText(); 
		    //String expectedwarningmessage = "Warning: No match for E-Mail Address and/or Password.";
		    //Assert.assertTrue(actualwarningMessage.contains(expectedwarningmessage),"Expected warning message is not displayed");
		    
		    
		    //driver.quit();
		
	}
	
	@Test(priority=4)
		public void  verifyLoginValidEmailAndInvalidPassowrd() {
			
			
			LoginPage loginpage = new LoginPage(driver);
			loginpage.enterEmailAddress(prop.getProperty("ValidEmail"));
		    //driver.findElement(By.id("input-email")).sendKeys("amotooricap9@gmail.com");
			loginpage.enterpassword(prop.getProperty("InvalidPassword"));
		    //driver.findElement(By.id("input-password")).sendKeys("1234567890");
		    loginpage.clickOnloginbutton();
			//driver.findElement(By.xpath("//input[@value='Login']")).click();
		    String actulawarningMessage = loginpage.retrieveEmailpasswordNotMatchingWarningMessage();
		    String expectedwarningmessage = "Warning: No match for E-Mail Address and/or Password.";
		    Assert.assertTrue(actulawarningMessage.contains(expectedwarningmessage),"Expected warning message is not displayed");
		    //String actualwarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText(); 
		    //String expectedwarningmessage = "Warning: No match for E-Mail Address and/or Password.";
		    //Assert.assertTrue(actualwarningMessage.contains(expectedwarningmessage),"Expected warning message is not displayed");
		    //driver.quit();
	}
	
	@Test(priority=5)
		public void verifyLoginWithoutProvidingCredentials() {
			
			LoginPage loginpage = new LoginPage(driver);
			
		    //driver.findElement(By.id("input-email")).sendKeys(" ");
		    //driver.findElement(By.id("input-password")).sendKeys(" ");
		    loginpage.clickOnloginbutton();
			//driver.findElement(By.xpath("//input[@value='Login']")).click();
		    
		    String actulawarningMessage = loginpage.retrieveEmailpasswordNotMatchingWarningMessage();
		    String expectedwarningmessage = "Warning: No match for E-Mail Address and/or Password.";
		    Assert.assertTrue(actulawarningMessage.contains(expectedwarningmessage),"Expected warning message is not displayed");
		    
		    //String actualwarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText(); 
		    //String expectedwarningmessage = "Warning: No match for E-Mail Address and/or Password.";
		    //Assert.assertTrue(actualwarningMessage.contains(expectedwarningmessage),"Expected warning message is not displayed");
		    //driver.quit();
		}
		
	
	}



