package com.avaya.smgr.geo.configure;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;

import com.avaya.smgr.upmsetup.Upmsetup;

public class Secondarybasics {
	boolean b;
	UserAction action =null;
	Upmsetup setup=null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException
	{
		action = new UserAction();
		locator=new Properties();
		setup=new com.avaya.smgr.upmsetup.Upmsetup();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
		input=new Properties();
		input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
		action.logintosecondary(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

	@Test(description="Verify the Secondary Geo redundancy page",priority=1)
	public void Verify_Page_After_Configure() throws Exception
	{
		action.driver.navigate().refresh();
		
		//Click on Geographic Redundancy
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("GR_Health"));
		action.VerifyTitle(locator.getProperty("GR_Health"));
		Thread.sleep(1000);
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("Geographic_Redundancy"));
		action.SwithchFrame("iframe0");
		action.VerifyEnableButton(locator.getProperty("Georeconfigure"));
		action.VerifyDisableButton(locator.getProperty("Geoenable"));
		action.VerifyDisableButton(locator.getProperty("Geodisable"));
		action.VerifyDisableButton(locator.getProperty("GeoRestore"));
		//verify IP address of primary
		action.VerifyElementValue(locator.getProperty("Geoprimaryip"), input.getProperty("IPadd"));
		Thread.sleep(1000);
		action.VerifyElementValue(locator.getProperty("Geoprimaryfqdn"), input.getProperty("FQDN"));
	}
	
	@Test(description="Verify the Recofigure button on secondary",priority=2)
	public void Verify_Reconfigure() throws Exception
	{
		action.RefreshPage();
		
		//Click on Geographic Redundancy
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("GR_Health"));
		action.VerifyTitle(locator.getProperty("GR_Health"));
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("Geographic_Redundancy"));
		action.VerifyTitle(locator.getProperty("Geographic_Redundancy"));
		action.SwithchFrame("iframe0");
		//Click on Reconfigure button
		action.ClickButton(locator.getProperty("Georeconfigure"));
		Thread.sleep(1000);
		action.driver.switchTo().frame("configurePrimaryServerIframe");
		WebDriverWait wait=new WebDriverWait(action.driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("IPtextbox"))));
		//Verify IP and FQDN of primary
		String str=action.driver.findElement(By.xpath(locator.getProperty("IPtextbox"))).getAttribute("value");
		Assert.assertEquals(str, input.getProperty("IPadd"));
		String str1=action.driver.findElement(By.xpath(locator.getProperty("Fqdntextbox"))).getAttribute("value");
		Assert.assertEquals(str1, input.getProperty("FQDN"));
			
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
