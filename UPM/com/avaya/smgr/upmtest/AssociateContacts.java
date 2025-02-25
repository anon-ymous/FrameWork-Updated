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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.UserAction;


public class AssociateContacts {
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

@Test(description="Verify the Addition of Associate Contact",priority=1)
public void Add_Associate_Contact() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on New Button
	action.DoubleClickButton(locator.getProperty("Users.New"));
	action.WaitForTitle(locator.getProperty("New_User_Profile"));
	//Fill the required details
	action.EntertextUsingKey(locator.getProperty("Lastname"),input.getProperty("UsersLastname1"),Keys.TAB);
	WebDriverWait wait=new WebDriverWait(action.driver,60);
	Thread.sleep(1000);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Lastnameascii")),input.getProperty("UsersLastname1")));
	action.EntertextUsingKey(locator.getProperty("Firstname"),input.getProperty("UsersFirstname"),Keys.TAB);
	Thread.sleep(1000);
	wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(locator.getProperty("Firstnameascii")),input.getProperty("UsersFirstname")));
	Thread.sleep(1000);
	action.EntertextUsingKey(locator.getProperty("Loginname"),input.getProperty("UsersLoginname41"),Keys.TAB);
	Thread.sleep(1000);
	
	//Click on Contact tab
	action.DoubleClickButton(locator.getProperty("Contacts"));
	Thread.sleep(1000);
	//Click on Add Contact button
	action.DoubleClickButton(locator.getProperty("AddContact"));
	action.WaitForTitle(locator.getProperty("Attach_Contacts"));
	//Select User As Associate
	action.SelectCheckBox(locator.getProperty("publicChk0"));
	Thread.sleep(1000);
	//Click on Select button
	action.ClickButton(locator.getProperty("Selectpbliccont"));
	//action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	List<WebElement> rows1 = action.driver.findElements(By.name(locator.getProperty("AssContactchkbox")));
	int noofrows = rows1.size();
	Assert.assertEquals(noofrows, 1);
	Thread.sleep(1000);
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("Users.Commit"));	
	action.WaitForTitle(locator.getProperty("User_Management"));
	Thread.sleep(1000);
	action.Verify_Add_Fifthcolumn(input.getProperty("UsersLoginname41"));
}


@Test(description="Verify the Edit opration of Associate Contact",priority=2)
public void Edit_Associate() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Select User from table
	action.SelectElementByLoginname(input.getProperty("UsersLoginname41"));
	Thread.sleep(1000);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
	//Click on Edit button
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	Thread.sleep(1000);
	//Click on Contact tab
	action.ClickLinkByxpath(locator.getProperty("Contacts"));
	//Verify Remove Button disabled
	action.VerifyDisableButton(locator.getProperty("Removecontact"));
	//Select Contact
	action.SelectCheckBox(locator.getProperty("Contacttablechk0"));
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("EditAssocContact"))));
	//Click on edit button
	action.DoubleClickButton(locator.getProperty("EditAssocContact"));
	action.WaitForTitle(locator.getProperty("Edit_Contact_List_Member"));
	Thread.sleep(500);
	//Verify the Buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Commit"));
	action.VerifyEnableButton(locator.getProperty("Cancel"));
	action.VerifyEnableTextbox(locator.getProperty("Memberlable"));
	action.VerifyEnableTextbox(locator.getProperty("Memberaltlable"));
	action.VerifyEnableCheckbox(locator.getProperty("Presencebuddychk"));
	action.VerifyEnableCheckbox(locator.getProperty("Speeddialchk"));
	//Fill Required Details
	action.ClearText(locator.getProperty("Memberlable"));
	action.entertext(locator.getProperty("Memberlable"), input.getProperty("Lable"));
	Thread.sleep(1000);
	action.SelectCheckBox(locator.getProperty("Presencebuddychk"));
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Contactdetailbtn"))));
	action.ClickButton(locator.getProperty("Contactdetailbtn"));
	Thread.sleep(1000);
	//Click on Commit Button
	action.DoubleClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	Thread.sleep(2000);	
	//Click on Commit button
	action.DoubleClickButton(locator.getProperty("Users.Commit"));
	//action.ClickButton(locator.getProperty("Users.Commit"));
	action.WaitForTitle(locator.getProperty("User_Management"));
}

	
@Test(description="Verify the Edited Associate Contact",priority=3)
public void Verify_Edit_Associate() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Select User
	action.SelectElementByLoginname(input.getProperty("UsersLoginname41"));
	Thread.sleep(1000);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
    //Click on Edit button
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	Thread.sleep(1000);
	//Click on Contact tab
	action.ClickLinkByxpath(locator.getProperty("Contacts"));
	action.VerifyDisableButton(locator.getProperty("Removecontact"));
	//Select Contact
	action.SelectCheckBox(locator.getProperty("Contacttablechk0"));
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("EditAssocContact"))));
	//Click on Edit Button
	action.DoubleClickButton(locator.getProperty("EditAssocContact"));
	action.WaitForTitle(locator.getProperty("Edit_Contact_List_Member"));
	
	//Verify the Check box is Selected
	action.VerifySelectcheckbox(locator.getProperty("Presencebuddychk"));
	//Click on Cancel button
	action.ClickButton(locator.getProperty("Cancel"));
}

@Test(description="Verify the Removal  of Associate Contact",priority=4)
public void Remove_Associate() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Select User
	Thread.sleep(500);
	action.SelectElementByLoginname(input.getProperty("UsersLoginname41"));
	//action.SelectCheckBox(locator.getProperty("Checkbox0"));
	//Click on Edit button
	Thread.sleep(1000);
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Users.Edit"))));
	
	action.DoubleClickButton(locator.getProperty("Users.Edit"));
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	Thread.sleep(1000);
	//Click on Contact tab
	action.ClickLinkByxpath(locator.getProperty("Contacts"));
	action.VerifyDisableButton(locator.getProperty("Removecontact"));
	//Select Contact
	action.SelectCheckBox(locator.getProperty("Contacttablechk0"));
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator.getProperty("Removecontact"))));
	//Click on Remove Button
	Thread.sleep(500);
	action.DoubleClickButton(locator.getProperty("Removecontact"));
	if(action.isAlertPresent()){
        action.driver.switchTo().alert();
        action.driver.switchTo().alert().dismiss();
    	action.SwithchFrame("iframe0");
        Thread.sleep(1000);
        action.DoubleClickButton(locator.getProperty("Removecontact"));
    }
	action.WaitForTitle(locator.getProperty("User_Profile_Edit"));
	
	List<WebElement> rows1 = action.driver.findElements(By.name(locator.getProperty("AssContactchkbox")));
	int noofrows = rows1.size();
	Assert.assertEquals(noofrows, 0);
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
