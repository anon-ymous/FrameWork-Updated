package com.avaya.smgr.geo.configure;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;

import com.avaya.smgr.upmsetup.Upmsetup;

public class CofigureGeo {
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
		action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

@Test(description="Verify the Geo redundancy page",priority=1)
	public void Verify_Page() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on Geographic Redundancy
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("Geographic_Redundancy"));
		action.VerifyTitle(locator.getProperty("Geographic_Redundancy"));
		action.SwithchFrame("iframe0");
		//verify configure button is enable
		action.VerifyEnableButton(locator.getProperty("Configure"));
		//Verify no server details
		action.VerifyStringValue("No primary server configured");
		action.VerifyStringValue("No secondary server configured");
		//Verify Help icon
		action.ClickButton(locator.getProperty("Helpicon"));
		WebElement icon=action.driver.findElement(By.xpath(locator.getProperty("Iconbox")));
		icon.sendKeys(org.openqa.selenium.Keys.CONTROL);
	    action.VerifyStringValue("To convert this System Manager into a secondary System Manager.");
	    //Close help notification
	    action.ClickButton(locator.getProperty("Closeicon"));
	}
	
@Test(description="Verify the GR Health page",priority=2)
	public void Verify_GR_Health_Page() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on Geographic Redundancy,GR Health
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("Geographic_Redundancy"));
		//action.ClickButton(locator.getProperty("navigatetree"));
		Thread.sleep(1000);
		action.ClickLink(locator.getProperty("GR_Health"));
		action.WaitForTitle(locator.getProperty("GR_Health"));
		action.VerifyTitle(locator.getProperty("GR_Health"));
		action.SwithchFrame("iframe0");
		//verify Text on the page
		action.VerifyStringValue("No GR Health status");
	}

	@Test(description="Verify the Help link on geo page working properly",priority=3)
	public void Verify_Help_Page() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on Geographic Redundancy
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("Geographic_Redundancy"));
		action.SwithchFrame("iframe0");
		String parentHandle = action.driver.getWindowHandle();
		action.ClickLinkByxpath(locator.getProperty("Helplink"));
		//Switch to help page
		for (String winHandle : action.driver.getWindowHandles())
		{
		  action.driver.switchTo().window(winHandle); 
		}
		Thread.sleep(1000);
	    action.VerifyTitle("Administering Avaya Aura� System Manager");
		//Close help page	
		action.driver.close(); 
		action.driver.switchTo().window(parentHandle);
	}

	@Test(description="Verify the error message on empty username and password",priority=4)
	public void Verify_UserName_Password() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on Geographic Redundancy
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("Geographic_Redundancy"));
		action.VerifyTitle(locator.getProperty("Geographic_Redundancy"));
		action.SwithchFrame("iframe0");
		//Click on Configure button
		action.ClickButton(locator.getProperty("Configure"));
		
		action.driver.switchTo().frame("configurePrimaryServerIframe");
		WebDriverWait wait=new WebDriverWait(action.driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("PrimaryUsername"))));
		//Clear all the text boxes
		action.ClearText(locator.getProperty("PrimaryUsername"));
		action.ClearText(locator.getProperty("IPtextbox"));
		action.ClearText(locator.getProperty("Primarypass"));
		action.ClearText(locator.getProperty("Fqdntextbox"));
		//Enter details except user name
		action.entertext(locator.getProperty("Primarypass"), input.getProperty("Password"));
		action.entertext(locator.getProperty("IPtextbox"), input.getProperty("IPadd"));
		action.entertext(locator.getProperty("Fqdntextbox"), input.getProperty("FQDN"));
		//Click on ok button
		action.DoubleClickButton(locator.getProperty("GeoOk"));
		Thread.sleep(1000);
		//verify error messages
		action.VerifyStringValue("Please enter user name");
		action.ClearText(locator.getProperty("PrimaryUsername"));
		action.ClearText(locator.getProperty("IPtextbox"));
		action.ClearText(locator.getProperty("Primarypass"));
		action.ClearText(locator.getProperty("Fqdntextbox"));
		//Enter details except password
		action.entertext(locator.getProperty("PrimaryUsername"), input.getProperty("UserId"));
		action.entertext(locator.getProperty("IPtextbox"), input.getProperty("IPadd"));
		action.entertext(locator.getProperty("Fqdntextbox"), input.getProperty("FQDN"));
		//Click on Ok button
		action.DoubleClickButton(locator.getProperty("GeoOk"));
		action.VerifyStringValue("Please enter password");
		//Thread.sleep(1000);
	}
	
	@Test(description="Verify the error message on invalid IP address",priority=5)
	public void Verify_IP() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on Geographic Redundancy
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("Geographic_Redundancy"));
		action.VerifyTitle(locator.getProperty("Geographic_Redundancy"));
		action.SwithchFrame("iframe0");
		//Click on Configure button
		action.ClickButton(locator.getProperty("Configure"));
		
		action.driver.switchTo().frame("configurePrimaryServerIframe");
		WebDriverWait wait=new WebDriverWait(action.driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("PrimaryUsername"))));
		//Clear all the text boxes
		action.ClearText(locator.getProperty("PrimaryUsername"));
		action.ClearText(locator.getProperty("IPtextbox"));
		action.ClearText(locator.getProperty("Primarypass"));
		action.ClearText(locator.getProperty("Fqdntextbox"));
		//Enter details except IP
		action.entertext(locator.getProperty("PrimaryUsername"), input.getProperty("UserId"));
		action.entertext(locator.getProperty("Primarypass"), input.getProperty("Password"));
		action.entertext(locator.getProperty("Fqdntextbox"), input.getProperty("FQDN"));
		//Click on Ok button and verify error message
		action.DoubleClickButton(locator.getProperty("GeoOk"));
		action.VerifyStringValue("Please enter IP");
		//Enter Invalid IP
		action.EntertextUsingKey(locator.getProperty("IPtextbox"),"12354", Keys.TAB);
		Thread.sleep(1000);
		//Verify error Message
		action.VerifyStringValue("IP entered is invalid");
		//Thread.sleep(1000);
	}
	
		
	@Test(description="Verify the error message on empty FQDN",priority=6)
	public void Verify_FQDN() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on Geographic Redundancy
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("Geographic_Redundancy"));
		action.VerifyTitle(locator.getProperty("Geographic_Redundancy"));
		action.SwithchFrame("iframe0");
		//Click on Configure button
		action.ClickButton(locator.getProperty("Configure"));
		Thread.sleep(1000);
		action.driver.switchTo().frame("configurePrimaryServerIframe");
		WebDriverWait wait=new WebDriverWait(action.driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("PrimaryUsername"))));
		//Clear all the text boxes
		action.ClearText(locator.getProperty("PrimaryUsername"));
		action.ClearText(locator.getProperty("IPtextbox"));
		action.ClearText(locator.getProperty("Primarypass"));
		action.ClearText(locator.getProperty("Fqdntextbox"));
		//Enter details except FQDN
		action.entertext(locator.getProperty("PrimaryUsername"), input.getProperty("UserId"));
		action.entertext(locator.getProperty("Primarypass"), input.getProperty("Password"));
		action.entertext(locator.getProperty("IPtextbox"), input.getProperty("IPadd"));
		//Click on Ok button
		action.DoubleClickButton(locator.getProperty("GeoOk"));
		//verify error message
		action.VerifyStringValue("Please enter FQDN");
		//Thread.sleep(1000);
	}

	
	@Test(description="Verify the Geo Configuration",priority=7)
	public void Configure_Geo() throws Exception
	{
		action.driver.navigate().refresh();
		action.logout(action);
		action.logintosecondary(input.getProperty("UserId"),input.getProperty("Password"),action);
		//Click on Geographic Redundancy
		action.ClickLink(locator.getProperty("Geographic_Redundancy"));
		action.WaitForTitle(locator.getProperty("Geographic_Redundancy"));
		action.VerifyTitle(locator.getProperty("Geographic_Redundancy"));
		action.SwithchFrame("iframe0");
		Thread.sleep(1000);
		//Click on Configure button
		//action.DoubleClickButton(locator.getProperty("Georeconfigure"));
		action.DoubleClickButton(locator.getProperty("Configure"));
		Thread.sleep(2000);
		action.driver.switchTo().frame("configurePrimaryServerIframe");
		Thread.sleep(5000);
		//WebDriverWait wait = new WebDriverWait(action.driver,240000);
		// wait.until(ExpectedConditions.visibilityOfElementLocated(locator.getProperty("PrimaryUsername")));
		//Clear all the text boxes
		action.ClearText(locator.getProperty("PrimaryUsername"));
		action.ClearText(locator.getProperty("IPtextbox"));
		action.ClearText(locator.getProperty("Primarypass"));
		action.ClearText(locator.getProperty("Fqdntextbox"));
		//Thread.sleep(1000);
		////Enter required details
		action.entertext(locator.getProperty("PrimaryUsername"), input.getProperty("UserId"));
		action.entertext(locator.getProperty("Primarypass"), input.getProperty("Password"));
		action.entertext(locator.getProperty("IPtextbox"), input.getProperty("IPadd"));
		action.entertext(locator.getProperty("Fqdntextbox"), input.getProperty("FQDN"));
		Thread.sleep(500);
		//Click on Ok button
		action.DoubleClickButton(locator.getProperty("GeoOk"));
		//Wait for 30 minutes
		Thread.sleep(1100000);
		action.driver.navigate().refresh();
		Thread.sleep(100000);
		action.driver.navigate().refresh();
		Thread.sleep(100000);
		action.driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(action.driver,240000);
		wait.until(ExpectedConditions.titleContains(locator.getProperty("System_Manager")));
		Thread.sleep(3000);
		WebElement ele=action.driver.findElement(By.xpath(locator.getProperty("ErrorBox")));
		while(ele.isDisplayed())
		{
			action.RefreshPage();
			Thread.sleep(30000);
		}
		action.driver.quit();
	}

	@AfterMethod
	public void Screenshots(ITestResult result) throws IOException, InterruptedException{
		  
		action.Screenshot(result, action);
	}

}
