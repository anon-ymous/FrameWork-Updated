package com.avaya.smgr.upmtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class AssignRolenGroup {
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
	

	@Test(description="Verify new user creation",priority=1)
	public void New_User() throws Exception
	{
		
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Click on New Button
		action.DoubleClickButton(locator.getProperty("Users.New"));
		//Enter the last name,First name,Login name
		action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("UsersLastname"),Keys.TAB);
		WebDriverWait wait=new WebDriverWait(action.driver,20);
		 Thread.sleep(1000);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Lastnameascii")),input.getProperty("UsersLastname")));
		action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("UsersFirstname"),Keys.TAB);
		 Thread.sleep(1000);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Firstnameascii")),input.getProperty("UsersFirstname")));
		Thread.sleep(1000);
		action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("UsersLoginname12"),Keys.TAB);
		//Click on Commit button
		action.DoubleClickButton(locator.getProperty("Users.Commit"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.Verify_Add_Fifthcolumn(input.getProperty("UsersLoginname12"));
		Thread.sleep(1000);
	}
	
@Test(description="Verify Assignment of role to new user ",priority=2)
	public void Assign_Role() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select User from table
		action.SelectElementByLoginname(input.getProperty("UsersLoginname12"));
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.View"))));
		//Verify the buttons are enabled
		action.VerifyEnableButton(locator.getProperty("Users.View"));
		action.VerifyEnableButton(locator.getProperty("Users.Edit"));
		action.VerifyEnableButton(locator.getProperty("Users.Duplicate"));
		action.VerifyEnableButton(locator.getProperty("Users.Delete"));
		Thread.sleep(3000);
		//Click on More button
		action.ClickButton(locator.getProperty("Users.More"));
		//Click on Assign role link
		Thread.sleep(1000);
		action.ClickLink(locator.getProperty("Assign_Roles"));
		action.WaitForTitle(locator.getProperty("Assign_Roles"));
		action.VerifyTitle(locator.getProperty("Assign_Roles"));
		//Click on Commit button
		action.VerifyDisableButton(locator.getProperty("Users.AssingRole.Commit"));
		action.SelectCheckBox(locator.getProperty("Users.AssingRole.Checkbox0"));
		Thread.sleep(1000);
		//Click on Commit button
		action.VerifyEnableButton(locator.getProperty("Users.AssingRole.Commit"));
		action.DoubleClickButton(locator.getProperty("Users.AssingRole.Commit"));
		action.WaitForTitle(locator.getProperty("User_Management"));
	}

@Test(description="Verify assigned role is added into table",priority=3)
public void Verify_Added_Role() throws Exception
{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select User from table
		action.SelectElementByLoginname(input.getProperty("UsersLoginname12"));
		Thread.sleep(1000);
		//Click on Edit button
		WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
		action.DoubleClickButton(locator.getProperty("Users.Edit"));
		action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
		Thread.sleep(1000);
		//Click on Membership tab
		action.ClickLink(locator.getProperty("Membership_Tab"));
		Thread.sleep(1000);
		//verify the number of rows increases
		List<WebElement> rows1 = action.driver.findElements(By.name(locator.getProperty("RollCheckbox")));
		int noofrows = rows1.size();
		Assert.assertEquals(noofrows, 1);
}

@Test(description="Verify Assigned role to user get disabled",priority=4)
public void Verify_Assigned_Role() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select User
		action.SelectElementByLoginname(input.getProperty("UsersLoginname12"));
		Thread.sleep(1000);
		//Click on More button
		action.ClickButton(locator.getProperty("Users.More"));
		//Click on Assign role link
		action.ClickLink(locator.getProperty("Assign_Roles"));
		action.WaitForTitle(locator.getProperty("Assign_Roles"));
		action.VerifyDisableCheckbox(locator.getProperty("Users.AssingRole.Checkbox0"));
	}
	
	@Test(description="Verify Assignment of group to new user ",priority=5)
	public void Assign_Group() throws Exception
	{

		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select user
		action.SelectElementByLoginname(input.getProperty("UsersLoginname12"));
		Thread.sleep(1000);
		//Click on More button
		action.ClickButton(locator.getProperty("Users.More"));
		//Click on Add to group link
		action.ClickLink(locator.getProperty("Add_To_Group"));
		action.WaitForTitle(locator.getProperty("Assign_Groups"));
		//Click on commit button
		action.VerifyDisableButton(locator.getProperty("Users.AssingRole.Commit"));
		action.SelectCheckBox(locator.getProperty("Groupchk0"));
		Thread.sleep(1000);
		//verify the commit button is enabled
		action.VerifyEnableButton(locator.getProperty("Users.AssingRole.Commit"));
		//Click on Commit button
		action.DoubleClickButton(locator.getProperty("Users.AssingRole.Commit"));
		action.WaitForTitle(locator.getProperty("User_Management"));
	}
	
	@Test(description="Verify assigned role is added into table",priority=6)
	public void Verify_Added_Group() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select User from table
		action.SelectElementByLoginname(input.getProperty("UsersLoginname12"));
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
		action.DoubleClickButton(locator.getProperty("Users.Edit"));
		action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
		Thread.sleep(1000);
		//Click on Membership tab
		action.ClickLink(locator.getProperty("Membership_Tab"));
		Thread.sleep(1000);
		//Verify the number of rows increases
		List<WebElement> rows1 = action.driver.findElements(By.name(locator.getProperty("Groupchkbox")));
		int noofrows = rows1.size();
		Assert.assertEquals(noofrows, 1);
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
