package com.avaya.smgr.upm.basic;

import com.avaya.smgr.upmsetup.Upmsetup;

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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class LocalizeName {
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

	@Test(description="Verify the elements of Local Name tab")
	public void Verify_Lacal_Name_Tab() throws Exception
	{
		action.RefreshPage();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Click on New button
		action.DoubleClickButton(locator.getProperty("Users.New"));
		action.WaitForTitle(locator.getProperty("New_User_Profile"));
		WebElement LocalNametab=action.driver.findElement(By.xpath(locator.getProperty("LoacalTab")));
		LocalNametab.sendKeys(org.openqa.selenium.Keys.CONTROL);
		//Click on localized name tab 
		action.ClickButton(locator.getProperty("LoacalTab"));
		WebDriverWait wait=new WebDriverWait(action.driver,20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("LocalNameNew"))));
		//verify the buttons are enabled and disabled
		action.VerifyEnableButton(locator.getProperty("LocalNameNew"));
		action.VerifyDisableButton(locator.getProperty("LocalEdit"));
		action.VerifyDisableButton(locator.getProperty("LocalDelete"));
		//Click on New button
		action.ClickButton(locator.getProperty("LocalNameNew"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("AddlocalNL"))));
		//verify the buttons are enabled
		action.VerifyEnableButton(locator.getProperty("AddlocalNL"));
		action.VerifyEnableButton(locator.getProperty("CancelLocalNM"));
	}
	
	@Test(description="Verify the error message on empty Name field")
	public void Verify_Error_Message() throws Exception
	{
		action.RefreshPage();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Click on New button
		action.DoubleClickButton(locator.getProperty("Users.New"));
		action.WaitForTitle(locator.getProperty("New_User_Profile"));
		WebElement LocalNametab=action.driver.findElement(By.xpath(locator.getProperty("LoacalTab")));
		LocalNametab.sendKeys(org.openqa.selenium.Keys.CONTROL);
        //Click on Localized name tab
		action.ClickButton(locator.getProperty("LoacalTab"));
		WebDriverWait wait=new WebDriverWait(action.driver,20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("LocalNameNew"))));
		//Click on New Button
		action.DoubleClickButton(locator.getProperty("LocalNameNew"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("AddlocalNL"))));
		//Click on Add button
		action.ClickButton(locator.getProperty("AddlocalNL"));
		action.WaitForObj(locator.getProperty("LocalnameErrmsg"));
		//verify error message on empty name field.
		action.VerifyStringValue("Name is required.");
		
	}
	@Test(description="Verify Add Localized name to user",priority=1)
	public void Add_Local_Name() throws Exception
	{
		action.driver.navigate().refresh();
		WebDriverWait wait=new WebDriverWait(action.driver,20);
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
	    //Click on New Button
		action.DoubleClickButton(locator.getProperty("Users.New"));
		action.WaitForTitle(locator.getProperty("New_User_Profile"));
		//Fill up the required fields
		action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("FName"),Keys.TAB);
		Thread.sleep(1000);	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Firstname"))));
		//Fill up the required fields
		action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("UsersFirstname"),Keys.TAB);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Firstnameascii")),input.getProperty("UsersFirstname")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Loginname"))));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Loginname"))));
		action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("UserlocalLogname"),Keys.TAB);
		//Click on Localized Name tab
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("LoacalTab"))));
		WebElement LocalNametab=action.driver.findElement(By.xpath(locator.getProperty("LoacalTab")));
		LocalNametab.sendKeys(org.openqa.selenium.Keys.CONTROL);
		Thread.sleep(500);
		//Click on Localized name tab
		action.ClickButton(locator.getProperty("LoacalTab"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("LocalNameNew"))));
		//Click on New button
		action.DoubleClickButton(locator.getProperty("LocalNameNew"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("LocalLanglist"))));
		//Select language
		action.SelectFromdropDown(locator.getProperty("LocalLanglist"), input.getProperty("Danish"));
		Thread.sleep(1000);
		WebElement LocalNM=action.driver.findElement(By.xpath(locator.getProperty("LocalDisplayNM")));
		LocalNM.sendKeys(org.openqa.selenium.Keys.CONTROL);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("LocalDisplayNM"))));
		//Enter Display name
		//action.driver.findElement(By.xpath(locator.getProperty("LocalDisplayNM"))).sendKeys("DSP");
		action.entertext(locator.getProperty("LocalDisplayNM"), "DSP");
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("LocalDisplayNM")),"DSP"));
		//Click on Add button
		Thread.sleep(1000);
		action.DoubleClickButton(locator.getProperty("AddlocalNL"));
		Thread.sleep(1000);
		//Verify Local name added successfully
		setup.Verify_AddedLocalname(action, input.getProperty("Danish"));
		action.DoubleClickButton(locator.getProperty("Users.Commit"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		
	}
	
	@Test(description="Verify Edit Localized name of user",priority=2)
	public void Edit_Local_Name() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select user from table
		action.SelectElementByLoginname(input.getProperty("UserlocalLogname"));
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
		//Click on Edit button
		action.DoubleClickButton(locator.getProperty("Users.Edit"));
		action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
		//Click on Identity tab
		action.DoubleClickButton(locator.getProperty("Identity"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("LoacalTab"))));
		WebElement LocalNametab=action.driver.findElement(By.xpath(locator.getProperty("LoacalTab")));
		LocalNametab.sendKeys(org.openqa.selenium.Keys.CONTROL);
		//Click on Localizesd name tab
		action.ClickButton(locator.getProperty("LoacalTab"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("LocalEdit"))));
		//Select local name
		setup.SelectLocalName(action, input.getProperty("Danish"));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("LocalEdit"))));
		//Click on Edit button
		action.DoubleClickButton(locator.getProperty("LocalEdit"));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("LocalDisplayNM"))));
		//Edit Display name
		action.ClearText(locator.getProperty("LocalDisplayNM"));
		action.entertext(locator.getProperty("LocalDisplayNM"), "DSP2");
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("LocalDisplayNM")),"DSP2"));
		//Click on Add button
		action.DoubleClickButton(locator.getProperty("AddlocalNL"));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Commit"))));
		//Click on commit button
		action.DoubleClickButton(locator.getProperty("Users.Commit"));
		action.WaitForTitle(locator.getProperty("User_Management"));
	}
	
	@Test(description="Verify Delete Localized name of user",priority=3)
	public void DeleteLocalName() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.SwithchFrame("iframe0");
		//Select user from table
		action.SelectElementByLoginname(input.getProperty("UserlocalLogname"));
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
		//Click on Edit button
		action.DoubleClickButton(locator.getProperty("Users.Edit"));
		action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
		//Click on Identity tab
		Thread.sleep(500);
		action.DoubleClickButton(locator.getProperty("Identity"));
        //Click on Localized name tab
		WebElement LocalNametab=action.driver.findElement(By.xpath(locator.getProperty("LoacalTab")));
		LocalNametab.sendKeys(org.openqa.selenium.Keys.CONTROL);
		action.ClickButton(locator.getProperty("LoacalTab"));
		Thread.sleep(1000);
		//Select local name from table
		setup.SelectLocalName(action, input.getProperty("Danish"));
		Thread.sleep(1000);
		//Click on Delete  button
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("LocalDelete"))));
		action.ClickButton(locator.getProperty("LocalDelete"));
		Thread.sleep(1000);
		//Verify the local name deleted from table
		setup.Verifydeletelocalname(action, input.getProperty("Danish"));
		Thread.sleep(500);
		//Click on Commit button
		action.DoubleClickButton(locator.getProperty("Users.Commit"));
		action.WaitForTitle(locator.getProperty("User_Management"));
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
