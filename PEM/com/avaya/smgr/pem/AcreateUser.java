package com.avaya.smgr.pem;

/*
 Contains tests for SMGR backup functionality.
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class AcreateUser {
	boolean b,d;
	static UserAction action = null;
	Properties locator = null;
	Properties input = null;
	public WebDriver driver;
	String tr="/tr",td="/td";

	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException {
		
	}

	@BeforeMethod(alwaysRun=true)
	public void ScreenshptSetup() throws IOException, InterruptedException {
		action = new UserAction();
		locator = new Properties();
		input = new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir")
				+ "\\Third Party\\objectRepository\\OR.properties"));
		input.load(new FileInputStream(System.getProperty("user.dir")
				+ "\\Third Party\\testData\\INPUT.properties"));
		action.login(input.getProperty("UserId"),
				input.getProperty("Password"), action);
	}

	@Test(description="Verify that user is created by using Role successfully",priority=1,groups={"Sanity"})
	public void GLSBUserCreate() throws Exception{
		action.RefreshPage();
		action.ClickLink(locator.getProperty("User_Management"));
		Thread.sleep(2000);
		action.ClickLink(locator.getProperty("Manage_Users"));
		Thread.sleep(2000);
		action.SwithchFrame("iframe0");
		//Click on New button
		action.ClickButton(locator.getProperty("Users.New"));
		Thread.sleep(3000);
		action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("FName"),Keys.TAB);
		Thread.sleep(1000);	
		WebElement Firstname=action.driver.findElement(By.xpath(locator.getProperty("Firstname")));
		Firstname.sendKeys(org.openqa.selenium.Keys.CONTROL);
		Thread.sleep(2000);
		action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("LName"),Keys.TAB);
		Thread.sleep(1000);
		action.entertext(locator.getProperty("Loginname"),input.getProperty("PEM.Loginname"));
		Thread.sleep(3000);
		action.DoubleClickButton(locator.getProperty("Users.Commit"));
		action.WaitForTitle(locator.getProperty("User_Management"));

		
	}

	@AfterMethod(alwaysRun=true)
	public void Screenshots(ITestResult result) throws IOException,
			InterruptedException {

		action.Screenshot(result, action);
		action.logout(action);
	}

	@AfterClass(alwaysRun=true)
	public void Close() throws IOException, InterruptedException {
		// action logout=new action();
		
	}
}

