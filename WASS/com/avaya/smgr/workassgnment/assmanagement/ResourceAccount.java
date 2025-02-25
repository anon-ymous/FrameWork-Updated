package com.avaya.smgr.workassgnment.assmanagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
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

import com.avaya.smgr.Worksetup.WorkSetup;

public class ResourceAccount {
	boolean b=false,match=false;;
	UserAction action =null;
	Properties locator=null;
	WorkSetup setup=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass
public void setup() throws IOException, InterruptedException{
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
   	setup=new WorkSetup();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
   }

@Test(description="Verify the elements of Resource account page")
public void Resource_Account_Page() throws Exception
{
action.RefreshPage();
//Navigate to work assignment
action.ClickLink(locator.getProperty("Work_Assignment"));
action.WaitForTitle(locator.getProperty("Work_Assignment"));
action.ClickLink(locator.getProperty("Assignment_Management"));
//verify page title
action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
action.SwithchFrame("iframe0");

//verify resource button selected
action.SelectCheckBox(locator.getProperty("Assign.resourceAccount"));
Thread.sleep(1000);
//Verify buttons 
action.VerifyDisableButton(locator.getProperty("RNext"));
action.VerifyEnableButton(locator.getProperty("Upgrade.Cancel"));
action.VerifydeSelectcheckbox(locator.getProperty("ResourceChk"));
action.VerifyNoElementdisplay(locator.getProperty("ResourceAccChk"));

action.SelectRadioButton(locator.getProperty("ResourceChk"));
Thread.sleep(1000);
action.VerifyElementDisplay(locator.getProperty("ResourceAccChk"));

}


@Test(description="Verify the Account attribute tab of Resource account page")
public void Verify_Account_AttributeTab() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Assignment_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
	action.SwithchFrame("iframe0");
	//verify resource button selected
	action.SelectCheckBox(locator.getProperty("Assign.resourceAccount"));
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("ResourceChk"))));
	action.SelectRadioButton(locator.getProperty("ResourceChk"));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("ResourceAccChk"))));
	action.SelectCheckBox(locator.getProperty("ResourceAccChk"));
	action.WaitToClick(locator.getProperty("RNext"));
	action.DoubleClickButton(locator.getProperty("RNext"));
	action.WaitForTitle(locator.getProperty("Account_Assignment_Details"));
	action.VerifyTitle(locator.getProperty("Account_Assignment_Details"));
	//verify Buttons
	action.VerifyEnableButton(locator.getProperty("Acc.AttrCommitContbtn"));
	action.VerifyEnableButton(locator.getProperty("Acc.AttrCommitbtn"));
	action.VerifyEnableButton(locator.getProperty("Acc.AttrCancelbtn"));
	Thread.sleep(1000);
	//Verify Commit and continue button
	action.SelectFromdropDown(locator.getProperty("AccAttributelist"),"Chat");
	Thread.sleep(500);
	action.DoubleClickButton(locator.getProperty("Acc.AttrCommitContbtn"));
	Thread.sleep(1000);
	action.VerifyStringValue("Attributes are assigned successfully.");
	action.VerifyTitle(locator.getProperty("Account_Assignment_Details"));
	//verify Commit button
	action.SelectFromdropDown(locator.getProperty("AccAttributelist"),"SMS");
	action.ClickButton(locator.getProperty("Acc.AttrCommitbtn"));
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
	//Click on Next Button
	action.DoubleClickButton(locator.getProperty("RNext"));
	action.WaitForTitle(locator.getProperty("Account_Assignment_Details"));
	String str=new Select(action.driver.findElement(By.xpath(locator.getProperty("AccAttributelist")))).getFirstSelectedOption().getText();
	Assert.assertEquals(str, "SMS");
	Thread.sleep(1000);
	//Verify Cancel button
	action.ClickButton(locator.getProperty("Acc.AttrCancelbtn"));
	//landing page  gets launched
	action.VerifyTitle(locator.getProperty("Assignment_ManagementDocument"));
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));

}


@Test(description="Verify the Account property tab of Resource account page")
public void Verify_Account_PropertyTab() throws Exception
{
	action.RefreshPage();
	//Navigate to work assignment
	action.ClickLink(locator.getProperty("Work_Assignment"));
	action.WaitForTitle(locator.getProperty("Work_Assignment"));
	action.ClickLink(locator.getProperty("Assignment_Management"));
	//verify page title
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
	action.SwithchFrame("iframe0");
	//verify resource button selected
	action.SelectCheckBox(locator.getProperty("Assign.resourceAccount"));
	WebDriverWait wait = new WebDriverWait(action.driver, 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("ResourceChk"))));
	//Select Resource radio button
	action.SelectRadioButton(locator.getProperty("ResourceChk"));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("ResourceAccChk"))));
	//Select Resource account radio button
	action.SelectCheckBox(locator.getProperty("ResourceAccChk"));
	//Click on Next button
	action.WaitToClick(locator.getProperty("RNext"));
	action.DoubleClickButton(locator.getProperty("RNext"));
	//Verify Title of page
	action.WaitForTitle(locator.getProperty("Account_Assignment_Details"));
	action.VerifyTitle(locator.getProperty("Account_Assignment_Details"));
	//Click on Property tab
	action.ClickButton(locator.getProperty("Propertytab"));
	Thread.sleep(1000);
	action.VerifyEnableButton(locator.getProperty("Acc.PropCommitContbtn"));
	action.VerifyEnableButton(locator.getProperty("Acc.PropCommitbtn"));
	action.VerifyEnableButton(locator.getProperty("Acc.PropCancelbtn"));
	//verify commit and continue button
	action.SelectCheckBox(locator.getProperty("Acc.Propchk1"));
	wait.until(ExpectedConditions.elementToBeSelected(By.xpath(locator.getProperty("Acc.Propchk1"))));
	action.entertext(locator.getProperty("Acc.Defaulttext"), "2");
	action.ClickButton(locator.getProperty("Acc.PropCommitContbtn"));
	Thread.sleep(1000);
	//action.VerifyStringValue("Commit Done");
	action.VerifyTitle(locator.getProperty("Account_Assignment_Details"));
	//Verify Commit button
	//Clear default value and enter new one
	action.ClearText(locator.getProperty("Acc.Defaulttext"));
	action.entertext(locator.getProperty("Acc.Defaulttext"), "3");
	//Click on Commit button
	action.ClickButton(locator.getProperty("Acc.PropCommitbtn"));
	action.WaitForTitle(locator.getProperty("Assignment_ManagementDocument"));
	//Click on Next button
	action.WaitToClick(locator.getProperty("RNext"));
	action.DoubleClickButton(locator.getProperty("RNext"));
	action.WaitForTitle(locator.getProperty("Account_Assignment_Details"));
	//Click on Property tab
	action.ClickButton(locator.getProperty("Propertytab"));
	//Verify Committed Value
	String str=action.driver.findElement(By.xpath(locator.getProperty("Acc.Defaulttext"))).getAttribute("value");
	Assert.assertEquals("3", str);	
	action.VerifySelectcheckbox(locator.getProperty("Acc.Propchk1"));
	Thread.sleep(1000);
	
		
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
