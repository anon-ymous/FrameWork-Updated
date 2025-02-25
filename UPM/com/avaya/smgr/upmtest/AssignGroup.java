package com.avaya.smgr.upmtest;

import java.io.FileInputStream;
import java.io.IOException;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class AssignGroup {
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() throws IOException, InterruptedException
	{
		action = new UserAction();
		locator=new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
		input=new Properties();
		input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
		action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	}

	@Test(description="Verify that the Group should create successfully",priority=1)
	public void Create_Group() throws Exception
	{
		
		//Navigate to SMGR,Groups & Roles,Groups
		action.ClickLink(locator.getProperty("Groups_Roles"));
		action.ClickLink(locator.getProperty("Groups"));
		action.SwithchFrame("iframe0");
		
		//Click on the New button
		action.DoubleClickButton(locator.getProperty("GLS.add"));
		//Enter Group name
		action.entertext(locator.getProperty("GLS.GroupName"), input.getProperty("Groupname"));
		//Click on the commit button
		action.DoubleClickButton(locator.getProperty("Commit"));
		action.WaitForTitle(locator.getProperty("Group_Management"));
		String Actual=input.getProperty("Groupname");
		//Verify that the Group is created successfully
		//action.Verify_Add_Secondcolumn(Actual);
		Thread.sleep(500);

	}
	
	
@Test(description="Verify the Assignment of group to new  User",priority=2)
public void Assign_Group() throws Exception
{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//click on New Button
		action.DoubleClickButton(locator.getProperty("Users.New"));
		action.WaitForTitle(locator.getProperty("New_User_Profile"));
		//Fill the Required details of user creation
		action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("UsersLastname"),Keys.TAB);
		WebDriverWait wait=new WebDriverWait(action.driver,60);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Lastnameascii")),input.getProperty("UsersLastname")));
		action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("UsersFirstname"),Keys.TAB);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Firstnameascii")),input.getProperty("UsersFirstname")));
		Thread.sleep(1000);
		action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("UsersLoginname4"),Keys.TAB);
		Thread.sleep(500);
		//Click on Membership tab
		action.DoubleClickButton(locator.getProperty("Membership"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.Grouplinkbtn"))));
		Thread.sleep(1000);
		action.ClickButton(locator.getProperty("Users.Grouplinkbtn"));
		//Click on Add Group button
		action.DoubleClickButton(locator.getProperty("Users.Addtogroup"));
		action.WaitForTitle(locator.getProperty("Select_Groups"));
		//Verify the buttons are disabled
		action.VerifyDisableButton(locator.getProperty("Users.Selectgrpbtn"));
		//Select Group to assign
		action.SelectCheckBox(locator.getProperty("Groupchk0"));
		//Click on Select Button
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Selectgrpbtn"))));
		action.DoubleClickButton(locator.getProperty("Users.Selectgrpbtn"));
		action.WaitForTitle(locator.getProperty("New_User_Profile"));
		//Click on Commit button
		action.DoubleClickButton(locator.getProperty("Users.Commit"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		
}
	
@Test(description="Verify assigned role is added into table",priority=3)
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
	action.SelectElementByLoginname(input.getProperty("UsersLoginname4"));
	Thread.sleep(500);
	//Click on Edit button
	action.ClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	Thread.sleep(500);
	//Click on Membership tab
	action.DoubleClickButton(locator.getProperty("Membership"));
	
	//Verify rows of table
	List<WebElement> rows1 = action.driver.findElements(By.name(locator.getProperty("Groupchkbox")));
	int noofrows = rows1.size();
	Assert.assertEquals(noofrows, 1);
}
@Test(description="Verify that the user is removed from group",priority=4)
public void Remove_Group() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Select User
	action.SelectElementByLoginname(input.getProperty("UsersLoginname4"));
	Thread.sleep(1000);
	//Click on edit button
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
	action.ClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	Thread.sleep(500);
	//Click on membership button
	action.DoubleClickButton(locator.getProperty("Membership"));
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Grouplinkbtn"))));
	Thread.sleep(500);
	action.ClickButton(locator.getProperty("Users.Grouplinkbtn"));
	action.VerifyDisableButton(locator.getProperty("Users.Removbtn"));
	//Select assigned group
	action.SelectCheckBox(locator.getProperty("Users.Grouptblck1"));
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Removbtn"))));
	//Click on Remove button
	action.DoubleClickButton(locator.getProperty("Users.Removbtn"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	action.VerifyTitle(locator.getProperty("User_Profile_Edit"));
	//Verify Group removed from table
	action.VerifyElementValue(locator.getProperty("Groupsecondrow"), "No Records found");
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	action.WaitForTitle(locator.getProperty("User_Management"));
}

@AfterMethod
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
	action.logout(action);
}


	

}
