package com.avaya.smgr.upr.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class NewUserwithUPR {
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass
public void setup() throws IOException, InterruptedException
	{
	action = new UserAction();
	locator=new Properties();
	locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
	input=new Properties();
	input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}


@Test(description="Verify UPR By creating new user", priority=1,enabled=false)
public void New_User_With_UPR() throws Exception
{

action.driver.navigate().refresh();

//Click on User Management,Manage Users
action.ClickLink(locator.getProperty("User_Management"));
action.WaitForTitle(locator.getProperty("User_Management"));
action.ClickLink(locator.getProperty("Manage_Users"));
action.SwithchFrame("iframe0");
//Click on New button
action.ClickButton(locator.getProperty("Users.New"));
action.WaitForTitle(locator.getProperty("New_User_Profile"));

//Select the upr from upr list
action.SelectFromdropDown(locator.getProperty("Users.Uprdropdown"), input.getProperty("uprcmsm2"));
Thread.sleep(500);
action.ClickButton(locator.getProperty("Users.Uprchangeok"));
Thread.sleep(1000);
//Fill up the required details
action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("UsersLastname"),Keys.TAB);
WebDriverWait wait=new WebDriverWait(action.driver,20);
wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Lastnameascii")),input.getProperty("UsersLastname")));
action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("UsersFirstname"),Keys.TAB);
wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Firstnameascii")),input.getProperty("UsersFirstname")));
Thread.sleep(1000);
action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("UsersLoginname"),Keys.TAB);
Thread.sleep(1000);
//Verify the language and time for specified upr is correct
String str1=new Select(action.driver.findElement(By.xpath(locator.getProperty("LangDropdown")))).getFirstSelectedOption().getText();
Assert.assertEquals(str1,input.getProperty("Korean"));
Thread.sleep(1000);
String str2=new Select(action.driver.findElement(By.xpath(locator.getProperty("TimeDropdown")))).getFirstSelectedOption().getText();
Assert.assertEquals(input.getProperty("Ktime"), str2);
action.ClickButton(locator.getProperty("Users.Commit"));
action.WaitForTitle(locator.getProperty("User_Management"));
Thread.sleep(1000);
action.Verify_Add_Fifthcolumn(input.getProperty("UsersLoginname"));
Thread.sleep(1000);
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
