package com.avaya.smgr.geo.elements;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;

import com.avaya.smgr.GeoSetup.Geosetup;

public class SecondaryrestoreCM {
	boolean b;
	UserAction action =null;
	Geosetup setup=null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException
	{
		action = new UserAction();
		locator=new Properties();
		setup=new com.avaya.smgr.GeoSetup.Geosetup();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
		input=new Properties();
		input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
		action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

	@Test(description="Restore Secondary server to primary",priority=1)
	public void Restore() throws Exception
	{

		//Navigate to Geographic_Redundancy
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("Geographic_Redundancy"));
		action.SwithchFrame("iframe0");
		//Click on Restore button
		action.ClickButton(locator.getProperty("GeoRestore"));
		Thread.sleep(3000);
		//Focus on Popup window
		WebElement Details=action.driver.findElement(By.xpath(locator.getProperty("RecoveryWindow")));
		Details.sendKeys(org.openqa.selenium.Keys.CONTROL);
		//Click on Recondary sever button
		action.ClickButton(locator.getProperty("RecoverSecondary"));
		WebElement recovercnfm=action.driver.findElement(By.xpath(locator.getProperty("RecoveryWindowcnfrm")));
		recovercnfm.sendKeys(org.openqa.selenium.Keys.CONTROL);
		//Click on OK button to confirm
		action.ClickButton(locator.getProperty("Restorecnfrm"));
		//Wait for some time to handle pop up
		Thread.sleep(550000);
		//handle alert 
		if(action.isAlertPresent())
		{
	        action.driver.switchTo().alert();
	        action.driver.switchTo().alert().accept();	
	    }
		//Wait for 15 minutes
		action.RefreshPage();
		Thread.sleep(150000);
		action.RefreshPage();
		 WebDriverWait wait = new WebDriverWait(action.driver,240000);
		 wait.until(ExpectedConditions.titleContains(locator.getProperty("System_Manager")));
		action.RefreshPage();
		Thread.sleep(1000);
		WebElement ele=action.driver.findElement(By.xpath(locator.getProperty("ErrorBox")));
		while(ele.isDisplayed())
		{
			action.RefreshPage();
			Thread.sleep(60000);
		}
		
		action.driver.quit();
		action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
		Thread.sleep(1000);
		action.RefreshPage();
	}

	@Test(description="Verify the staus is 'Unmanaged' after restore complete",priority=2)
	public void Verify_Status_after_Restore() throws Exception
	{
		 action.driver.navigate().refresh();
		 //Click on Inventory,Manage Elements 
		 action.ClickLink(locator.getProperty("Inventory"));
		 action.WaitForTitle(locator.getProperty("Inventory"));
		 action.ClickLink(locator.getProperty("Manage_Elements"));
		 action.WaitForTitle(locator.getProperty("Manage_Elements"));
		 action.VerifyTitle(locator.getProperty("Manage_Elements"));
		 action.SwithchFrame("iframe0");
		 Thread.sleep(2000);
		 action.driver.switchTo().frame("appTableIframe");
		 Thread.sleep(2000);
		 //Select CM Element
		 setup.SelectCMElement(action,input.getProperty("cm29"));
		 Thread.sleep(1000);
		 WebDriverWait wait = new WebDriverWait(action.driver, 60);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Currentstatus"))));
		 //Click on Get current status button
		 action.ClickButton(locator.getProperty("Currentstatus"));
		 action.WaitForTitle(locator.getProperty("Manage_Elements"));
		 Thread.sleep(90000);
		 //Verify the status
		 action.VerifyElementValue(locator.getProperty("Mangedby"), "Primary");
		 Thread.sleep(2000);	
	}

	@Test(description="Manage CM Element in primary",priority=3)
	public void Manage_CM() throws Exception
	{
		 action.driver.navigate().refresh();
		 //Click on Inventory,Manage Elements 
		 action.ClickLink(locator.getProperty("Inventory"));
		 action.WaitForTitle(locator.getProperty("Inventory"));
		 action.ClickLink(locator.getProperty("Manage_Elements"));
		 action.WaitForTitle(locator.getProperty("Manage_Elements"));
		 action.VerifyTitle(locator.getProperty("Manage_Elements"));
		 action.SwithchFrame("iframe0");
		 Thread.sleep(2000);
		 action.driver.switchTo().frame("appTableIframe");
		 Thread.sleep(2000);
		 //Select the CM Element
		 setup.SelectCMElement(action,input.getProperty("cm29"));
		 Thread.sleep(1000);
		 //Click on More button
		 action.ClickButton(locator.getProperty("Users.More"));
		 Thread.sleep(1000);
	     //Select manage link
		 action.ClickLink(locator.getProperty("Manage"));
		 Thread.sleep(60000);
		 action.VerifyElementValue(locator.getProperty("Mangedby"), "Primary");
		 Thread.sleep(2000);
	}
	
	@AfterMethod
	public void Screenshots(ITestResult result) throws IOException, InterruptedException{
		  
		action.Screenshot(result, action);
	}

	@AfterClass
	  public void Close() throws IOException, InterruptedException
	  {
		action.logout(action);
	  }
	

}
