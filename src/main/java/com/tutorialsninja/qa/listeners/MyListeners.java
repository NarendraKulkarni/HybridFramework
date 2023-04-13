package com.tutorialsninja.qa.listeners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener {
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context) {
		
		System.out.println("============================================");
		System.out.println("Message: Execution of Project Test started.");
		System.out.println("=============================================");
		
		//Got report from Utulities package
		 extentReport = ExtentReporter.generateExtentReport();
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		String testName = result.getName();
		System.out.println("============================================");
		System.out.println("Message: "+testName+" started executing.");
		 extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO,testName+"started executing.");
		System.out.println("============================================");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		System.out.println("============================================");
		System.out.println("Message: "+testName+"got successfully passed.");
		extentTest.log(Status.PASS, testName+"got successfully passed.");
		System.out.println("============================================");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String testName = result.getName();
		//Geting driver instance here
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Taking Screenshot here
		//Below two lines will be replaced if method is called from Utilities
		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//watch video from 8:30:00
		//String destinationScreenshotPath1 = Utilities.captureScreenshot(driver, testName); // Read line 71 above
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
	
		System.out.println("============================================");
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+"got failed.");
		System.out.println("Message: "+testName+"got failed.");
		
		System.out.println("============================================");
		System.out.println(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		String testName = result.getName();
		
	
		System.out.println("============================================");
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+ "got skipped.");
		System.out.println("Message: "+testName+ "got skipped.");
		System.out.println("============================================");
		System.out.println(result.getThrowable());
	}

	

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		System.out.println("============================================");
		System.out.println("Message: Finished executing Project Tests.");
		System.out.println("============================================");
		
	}

}
