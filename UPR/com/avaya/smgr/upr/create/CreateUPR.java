package com.avaya.smgr.upr.create;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.avaya.smgr.Tsetup.Tenantsetup;

import utility.UserAction;


public class CreateUPR {
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Tenantsetup setup=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass
public void setup() throws IOException, InterruptedException
	{
	action = new UserAction();
	setup=new com.avaya.smgr.Tsetup.Tenantsetup();
	locator=new Properties();
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

@Test(description="verify the error message on blank upr name",priority=1)

public void Verify_Error_Msg() throws Exception
{

action.driver.navigate().refresh();
//Click on User Provisioning Rule
action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));

action.SwithchFrame("iframe0");
//Click on New Button
action.ClickButton(locator.getProperty("Users.New"));
action.WaitForTitle(locator.getProperty("New_User_Provisioning_Rule"));

action.DoubleClickButton(locator.getProperty("Commit"));
WebDriverWait wait = new WebDriverWait(action.driver, 60);
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.NameErrmsg"))));
action.VerifyStringValue("User Provisioning Rule Name should not be left blank.");

}


@Test(description="verify the creation of new User Provisioning Rule",priority=2,groups={"Sanity"})
public void New_UPR() throws Exception
{

action.driver.navigate().refresh();
//Click on User Provisioning Rule
action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));

action.SwithchFrame("iframe0");
//Click on New Button
action.ClickButton(locator.getProperty("Users.New"));
action.WaitForTitle(locator.getProperty("New_User_Provisioning_Rule"));

//Fill up the required fields of Upr
action.entertext(locator.getProperty("Uprname"),input.getProperty("uprcmsm2"));
Thread.sleep(1000);
action.SelectFromdropDown(locator.getProperty("LangDropdown"), input.getProperty("Danish"));
action.SelectFromdropDown(locator.getProperty("TimeDropdown"), input.getProperty("Danishtime"));
//Click on Commit Button and Verify the title of the page
action.DoubleClickButton(locator.getProperty("Commit"));
action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
setup.VerifyUprname(action,input.getProperty("uprcmsm2"));
	
}

@Test(description="Verify the Enable Or Disable Buttons",priority=3)
public void Enable_Disable_Test() throws Exception
{
	
	action.driver.navigate().refresh();
	//Click on User Provisioning Rule
	action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
	action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
	
	action.SwithchFrame("iframe0");
	//Verify New Button is enabled
	action.VerifyEnableButton(locator.getProperty("Users.New"));
	//Verify View,Edit,Delete,Duplicate Buttons are disabled
	action.VerifyDisableButton(locator.getProperty("Users.View"));
	action.VerifyDisableButton(locator.getProperty("Users.Edit"));
	action.VerifyDisableButton(locator.getProperty("Users.Delete"));
	action.VerifyDisableButton(locator.getProperty("Users.Duplicate"));
	//Select one Upr
	action.SelectCheckBox(locator.getProperty("Checkbox0"));
	Thread.sleep(1000);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
	
	//Verify View,Edit,Delete,Duplicate Buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Users.View"));
	action.VerifyEnableButton(locator.getProperty("Users.Edit"));
	action.VerifyEnableButton(locator.getProperty("Users.Delete"));
	action.VerifyEnableButton(locator.getProperty("Users.Duplicate"));
	
}

@Test(description="Verify the creation of new User Provisioning Rule with same name already exist",priority=4)
public void Verify_Duplication() throws Exception
{

action.driver.navigate().refresh();
//Click on User Provisioning Rule
action.ClickLink(locator.getProperty("User_Provisioning_Rule"));
action.WaitForTitle(locator.getProperty("User_Provisioning_Rules"));
action.SwithchFrame("iframe0");
//Click on New Button
action.ClickButton(locator.getProperty("Users.New"));
action.WaitForTitle(locator.getProperty("New_User_Provisioning_Rule"));
//Fill the Upr name same as already exist 

action.entertext(locator.getProperty("Uprname"),input.getProperty("uprcmsm2"));
action.SelectFromdropDown(locator.getProperty("LangDropdown"), input.getProperty("Danish"));
action.SelectFromdropDown(locator.getProperty("TimeDropdown"), input.getProperty("Danishtime"));
//Click on Commit button and verify the title of the page
action.DoubleClickButton(locator.getProperty("Commit"));
action.WaitForTitle(locator.getProperty("New_User_Provisioning_Rule"));
Thread.sleep(1000);
//Verify the following Error Message 
action.VerifyStringValue("User Provisioning Rule with this name already exists");

}


@AfterMethod
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}

@AfterClass
public void Close() throws IOException, InterruptedException{
	action.logout(action);
}

}
