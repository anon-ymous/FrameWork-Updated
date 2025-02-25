package com.avaya.smgr.upm.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class SharedAddress {
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

	@Test(description="Verify Add Address of user",priority=1)
	public void Add_Address() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.SwithchFrame("iframe0");
		WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.New"))));
		//Select user from table
		action.SelectElementByLoginname(input.getProperty("UserlocalLogname"));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
		//Click on Edit button
		action.DoubleClickButton(locator.getProperty("Users.Edit"));
		action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	
		//Click on identity tab
		action.DoubleClickButton(locator.getProperty("Identity"));
		WebElement LocalNametab=action.driver.findElement(By.xpath(locator.getProperty("AddressTab")));
		LocalNametab.sendKeys(org.openqa.selenium.Keys.CONTROL);
		//Click on Address tab
		action.ClickButton(locator.getProperty("AddressTab"));
		Thread.sleep(1000);

		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("NewContAdd"))));
		//Click on New button
		action.DoubleClickButton(locator.getProperty("NewContAdd"));
		action.WaitForTitle(locator.getProperty("Add_Address"));
		//Enter Address
		action.entertext(locator.getProperty("Addressname"), input.getProperty("Mumbai"));
		//Click on Commit button
		action.DoubleClickButton(locator.getProperty("Commit"));
		action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
		Thread.sleep(500);
		//Verify address added in the table
		setup.Verify_AddedAddress(action,input.getProperty("Mumbai"));
		//Click on Commit button
		action.DoubleClickButton(locator.getProperty("Users.Commit"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		
	}
	
	@Test(description="Verify Edit Shared Address of user",priority=2)
	public void Edit_Address() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
	
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.SwithchFrame("iframe0");
		WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.New"))));
		//Select user from table
		action.SelectElementByLoginname(input.getProperty("UserlocalLogname"));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
		//Click on Edit button
		action.DoubleClickButton(locator.getProperty("Users.Edit"));
		action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
		action.DoubleClickButton(locator.getProperty("Identity"));
		//Click on Address tab
		WebElement LocalNametab=action.driver.findElement(By.xpath(locator.getProperty("AddressTab")));
		LocalNametab.sendKeys(org.openqa.selenium.Keys.CONTROL);
		action.ClickButton(locator.getProperty("AddressTab"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("NewContAdd"))));
		//Select address to edit
		setup.SelectAddress(action, input.getProperty("Mumbai"));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("EditContAdd"))));
		//Click on Edit button
		action.DoubleClickButton(locator.getProperty("EditContAdd"));
		action.WaitForTitle(locator.getProperty("Edit_Address"));
		action.SelectFromdropDown(locator.getProperty("AddressType"), "Home");
		action.DoubleClickButton(locator.getProperty("Commit"));
		action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
		Thread.sleep(500);
		String str=action.driver.findElement(By.xpath(".//*[@id='tableSharedAddress_core_table_content']/tbody/tr[2]/td[3]")).getText();
		Assert.assertEquals(str, "Home");
		action.DoubleClickButton(locator.getProperty("Users.Commit"));
		action.WaitForTitle(locator.getProperty("User_Management"));
	}

	@Test(description="Verify Delete shared address of user",priority=3)
	public void Delete_Address() throws Exception
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
		action.DoubleClickButton(locator.getProperty("Identity"));
		//Click on Address tab
		WebElement LocalNametab=action.driver.findElement(By.xpath(locator.getProperty("AddressTab")));
		LocalNametab.sendKeys(org.openqa.selenium.Keys.CONTROL);
		action.ClickButton(locator.getProperty("AddressTab"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("NewContAdd"))));
		//Select address to delete
		setup.SelectAddress(action, input.getProperty("Mumbai"));
		Thread.sleep(1000);
		//Click on Delete button
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("DeleteContAdd"))));
		action.DoubleClickButton(locator.getProperty("DeleteContAdd"));
		action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("AddressTab"))));
		action.ClickButton(locator.getProperty("AddressTab"));
		//Verify address get deleted from table
		setup.Verifydeleteaddress(action, input.getProperty("Mumbai"));
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
