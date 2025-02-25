package com.avaya.smgr.upmtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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



public class BulkEdits_Validations {
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
	
	/* Bulk edit test case require at least 12 users in User management table  */
	

	@Test(description="Verify the various elements on user bulk edit page",priority=1)	
public void Verify_Elements() throws Exception
{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.SwithchFrame("iframe0");
		
		//Select Check box
		action.SelectCheckBox(locator.getProperty("Checkbox0"));
		Thread.sleep(1000);
		action.SelectCheckBox(locator.getProperty("Checkbox2"));
		Thread.sleep(1000);
		//Click on More Button
		action.ClickButton(locator.getProperty("Users.More"));
		
		action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
		action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
		action.VerifyTitle(locator.getProperty("User_Bulk_Editor"));
		//Verify the buttons ,Textboxes are enabled
		Thread.sleep(1000);
		action.VerifyEnableButton(locator.getProperty("Users.Runnow"));
		action.VerifyEnableButton(locator.getProperty("Users.Schedule"));
		action.VerifyEnableButton(locator.getProperty("Users.Cancel"));
		Thread.sleep(1000);
		action.VerifyEnableButton(locator.getProperty("Users.Runnowbtm"));
		action.VerifyEnableButton(locator.getProperty("Users.Schedulebtm"));
		action.VerifyEnableButton(locator.getProperty("Users.Cancelbtm"));
		//Click on Edit link
		Thread.sleep(1000);
		action.ClickLink(locator.getProperty("EditLink"));
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(action.driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator.getProperty("CancelLink"))));
		//Verify string value
		action.VerifyStringValue("Confirm Password:");
		//action.ClickLink(locator.getProperty("CancelLink"));
		action.DoubleClickButton(locator.getProperty("Users.Cancel"));
		action.WaitForTitle(locator.getProperty("User_Management"));

}
	@Test(description="Verify the error message on empty Session manager field",priority=3)	
	public void Verify_Error_Msg_EmptySM() throws Exception
	{
			action.driver.navigate().refresh();
			//Click on User Management,Manage Users
			action.ClickLink(locator.getProperty("User_Management"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.VerifyTitle(locator.getProperty("User_Management"));
			action.ClickLink(locator.getProperty("Manage_Users"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.SwithchFrame("iframe0");
		//Select Check box
			action.SelectCheckBox(locator.getProperty("Checkbox0"));
			Thread.sleep(1000);
			action.SelectCheckBox(locator.getProperty("Checkbox2"));
			Thread.sleep(1000);	
			//Click on More Button
			action.ClickButton(locator.getProperty("Users.More"));
			//Click on bulk edit users link
			action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
			action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
			Thread.sleep(200);
			action.SelectFromdropDownbyValue(locator.getProperty("UPR.SIP"), "0");
			Thread.sleep(2000);
			action.DoubleClickButton(locator.getProperty("Editcmf"));
			//Enter com. profile password
			WebDriverWait wait = new WebDriverWait(action.driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("cnfcomprofpass"))));
			action.entertext(locator.getProperty("Upr.comprofilepass"), "Avaya1123$");
			action.entertext(locator.getProperty("Upr.confirmpass"), "Avaya1123$");
			//Click on Communication profile link
			action.ClickLinkByxpath(locator.getProperty("Tent.AdminLink"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.checkbox1"))));
			//Select the SM check box
			action.SelectCheckBox(locator.getProperty("Upr.checkbox1"));
			Thread.sleep(1000);
			//Select new profile check box
			action.SelectCheckBox(locator.getProperty("Newprofilechk"));
			Thread.sleep(4000);
			//Select the location from list
			action.SelectFromdropDownbyValue(locator.getProperty("Upr.smlisthl"), "0");
			Thread.sleep(2000);
			//Click on Run Now button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.Runnow"))));
			action.DoubleClickButton(locator.getProperty("Users.Runnow"));
			//action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
			//Thread.sleep(1000);
			//Verify Error message on empty SM field
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Smerrmsg"))));
			action.VerifyElementValue(locator.getProperty("Smerrmsg"), "A Session Manager selection is required.");	
			
	}
	
	@Test(description="Verify the error message when empty home location field is not selected ",priority=3)	
	public void Verify_Error_Msg_EmptyLoc() throws Exception
	{
			action.driver.navigate().refresh();
			//Click on User Management,Manage Users
			action.ClickLink(locator.getProperty("User_Management"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.ClickLink(locator.getProperty("Manage_Users"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.SwithchFrame("iframe0");
			
			//Select users from table
			action.SelectCheckBox(locator.getProperty("Checkbox0"));
			Thread.sleep(1000);
			action.SelectCheckBox(locator.getProperty("Checkbox2"));
			Thread.sleep(1000);
			//Click on More Button
			action.ClickButton(locator.getProperty("Users.More"));
			//Click on bulk edit users link
			action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
			action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
			Thread.sleep(200);
			action.SelectFromdropDownbyValue(locator.getProperty("UPR.SIP"), "0");
			Thread.sleep(2000);
			action.DoubleClickButton(locator.getProperty("Editcmf"));
			//Enter com. profile password
			WebDriverWait wait = new WebDriverWait(action.driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("cnfcomprofpass"))));
			action.entertext(locator.getProperty("Upr.comprofilepass"), "Avaya1123$");
			action.entertext(locator.getProperty("Upr.confirmpass"), "Avaya1123$");
			//Click on Communication profile link
			action.ClickLinkByxpath(locator.getProperty("Tent.AdminLink"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.checkbox1"))));
			//Select the SM check box
			action.SelectCheckBox(locator.getProperty("Upr.checkbox1"));
			Thread.sleep(1000);
			//Select new profile check box
			action.SelectCheckBox(locator.getProperty("Newprofilechk"));
			Thread.sleep(4000);
			//Select SM from list
			action.SelectFromdropDownbyValue(locator.getProperty("Upr.smlist"), "1");
			Thread.sleep(3000);
			//Click on Run Now button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.Runnow"))));
			action.DoubleClickButton(locator.getProperty("Users.Runnow"));
			//action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
			Thread.sleep(1000);
			//Verify Error message on empty home location
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator.getProperty("Locerrmsg"))));
			action.VerifyElementValue(locator.getProperty("Locerrmsg"), "A Home Location selection is required.");	
					
	}
	
	@Test(description="Verify the error message on empty SIP domain field",priority=3)	
	public void Verify_Error_Msg_EmptySIP() throws Exception
	{
			action.driver.navigate().refresh();
			//Click on User Management,Manage Users
			action.ClickLink(locator.getProperty("User_Management"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.ClickLink(locator.getProperty("Manage_Users"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.SwithchFrame("iframe0");
			
			//Select users from table
			//Select users from table
			action.SelectCheckBox(locator.getProperty("Checkbox0"));
			Thread.sleep(1000);
			action.SelectCheckBox(locator.getProperty("Checkbox2"));
			Thread.sleep(1000);
			//Click on More Button
			action.ClickButton(locator.getProperty("Users.More"));
			//Click on bulk edit users link
			action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
			action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
			action.ClickLinkByxpath(locator.getProperty("Tent.AdminLink"));
			WebDriverWait wait = new WebDriverWait(action.driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.checkbox1"))));
			//Select SM check box
			action.SelectCheckBox(locator.getProperty("Upr.checkbox1"));
			Thread.sleep(1000);
			//Select new profile check box
			action.SelectCheckBox(locator.getProperty("Newprofilechk"));
			Thread.sleep(4000);
			//Select SM from drop down list
			action.SelectFromdropDownbyValue(locator.getProperty("Upr.smlist"), "1");
			Thread.sleep(2000);
			//Select location from List
			action.SelectFromdropDownbyValue(locator.getProperty("Upr.smlisthl"), "0");
			Thread.sleep(2000);
			//Click on Identity link
			action.DoubleClickButton(locator.getProperty("Identity"));
			Thread.sleep(1000);
			//Click on edit link
			action.DoubleClickButton(locator.getProperty("Editcmf"));
			//Enter Com profile password
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("cnfcomprofpass"))));
			action.entertext(locator.getProperty("Upr.comprofilepass"), "Avaya1123$");
			action.entertext(locator.getProperty("Upr.confirmpass"), "Avaya1123$");
			//Click on Run Now button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.Runnow"))));
			action.DoubleClickButton(locator.getProperty("Users.Runnow"));
			//action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
			Thread.sleep(1000);
			//Verify the error message on Empty SIP domain
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Siperrmsg"))));
			action.VerifyElementValue(locator.getProperty("Siperrmsg"), "SIP Domain is required for Session Manager Communication Profile");
			
			Thread.sleep(500);
	}
	@Test(description="Verify the error message when Communication profile is empty",priority=3)	
	public void Verify_Error_Msg_EmptyCP_Password() throws Exception
	{
			action.driver.navigate().refresh();
			//Click on User Management,Manage Users
			action.ClickLink(locator.getProperty("User_Management"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.ClickLink(locator.getProperty("Manage_Users"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.SwithchFrame("iframe0");
			
			//Select users from table
			action.SelectCheckBox(locator.getProperty("Checkbox0"));
			Thread.sleep(1000);
			action.SelectCheckBox(locator.getProperty("Checkbox2"));
			Thread.sleep(1000);
			//Click on More Button
			action.ClickButton(locator.getProperty("Users.More"));
			//Click on bulk edit users link
			action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
			action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
			action.ClickLinkByxpath(locator.getProperty("Tent.AdminLink"));
			WebDriverWait wait = new WebDriverWait(action.driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.checkbox1"))));
			//Select SM check box
			action.SelectCheckBox(locator.getProperty("Upr.checkbox1"));
			Thread.sleep(1000);
			//Select new profile check box
			action.SelectCheckBox(locator.getProperty("Newprofilechk"));
			Thread.sleep(4000);
			//Select SM from drop down list
			action.SelectFromdropDownbyValue(locator.getProperty("Upr.smlist"), "1");
			Thread.sleep(2000);
			//Select location from List
			action.SelectFromdropDownbyValue(locator.getProperty("Upr.smlisthl"), "0");
			Thread.sleep(2000);
			//Click on Identity link
			action.DoubleClickButton(locator.getProperty("Identity"));
			Thread.sleep(1000);
			//Select SIP domain drom the list
			action.SelectFromdropDownbyValue(locator.getProperty("UPR.SIP"), "0");
			Thread.sleep(3000);
			//Click on Run Now button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.Runnow"))));
			action.DoubleClickButton(locator.getProperty("Users.Runnow"));
			action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
			Thread.sleep(1000);
			//Verify Error message on empty com profile password
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getProperty("Cppaswdmsg"))));
			action.VerifyElementValue(locator.getProperty("Cppaswdmsg"), "Communication Profile Password is required for Session Manager Communication Profile");
						
	}

	@Test(description="Verify the error message on empty Communication Manager field",priority=6)	
	public void Verify_ErrMsg_EmptyCM() throws Exception
	{
			action.driver.navigate().refresh();
			//Click on User Management,Manage Users
			action.ClickLink(locator.getProperty("User_Management"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.ClickLink(locator.getProperty("Manage_Users"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.SwithchFrame("iframe0");
			
			//Select Check box
			action.SelectCheckBox(locator.getProperty("Checkbox0"));
			Thread.sleep(1000);
			action.SelectCheckBox(locator.getProperty("Checkbox2"));
			Thread.sleep(1000);
			//Click on More Button
			action.ClickButton(locator.getProperty("Users.More"));
			//Click on bulk edit users link
			action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
			action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
			//Click on Communication profile link
			action.ClickLinkByxpath(locator.getProperty("Tent.AdminLink"));
			//Select the CM Checkbox
			WebDriverWait wait = new WebDriverWait(action.driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.checkbox1"))));
			action.SelectCheckBox(locator.getProperty("Upr.checkbox2"));
			Thread.sleep(1000);
			//Select new profile checkbox
			action.SelectCheckBox(locator.getProperty("Newprofilecmchk"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.cmlist"))));
			//Click on Run Now button
			action.DoubleClickButton(locator.getProperty("Users.Runnow"));
			action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
			Thread.sleep(1000);
			//verify error messages on empty CM Field
			action.VerifyStringValue("Please select a System -it is a mandatory field.");
			action.VerifyStringValue("Please select a Template -it is a mandatory field.");
			Thread.sleep(1000);

			
	}
	
	@Test(description="Verify the error message on empty Template field",priority=7)	
	public void Verify_ErrMsg_EmptyTemplate() throws Exception
	{
			action.driver.navigate().refresh();
			//Click on User Management,Manage Users
			action.ClickLink(locator.getProperty("User_Management"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.ClickLink(locator.getProperty("Manage_Users"));
			action.WaitForTitle(locator.getProperty("User_Management"));
			action.SwithchFrame("iframe0");
			
			//Select Check box
			action.SelectCheckBox(locator.getProperty("Checkbox0"));
			Thread.sleep(1000);
			action.SelectCheckBox(locator.getProperty("Checkbox2"));
			Thread.sleep(1000);
			//Click on More Button
			action.ClickButton(locator.getProperty("Users.More"));
			//Click on bulk edit users link
			action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
			action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
			//Click on Communication profile link
			action.ClickLinkByxpath(locator.getProperty("Tent.AdminLink"));
			//Select CM checkbox
			WebDriverWait wait = new WebDriverWait(action.driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.checkbox1"))));
			action.SelectCheckBox(locator.getProperty("Upr.checkbox2"));
			Thread.sleep(1000);
			//Select new profile checkbox
			action.SelectCheckBox(locator.getProperty("Newprofilecmchk"));
			//Select CM from list
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Upr.cmlist"))));
			action.SelectFromdropDown(locator.getProperty("Upr.cmlist"), input.getProperty("cm29"));
			Thread.sleep(4000);	
			//Click on Run Now button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Users.Runnow"))));
			action.DoubleClickButton(locator.getProperty("Users.Runnow"));
			//action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
			Thread.sleep(500);
			//Verify error message on empty template
			action.VerifyStringValue("Please select a Template -it is a mandatory field.");
			Thread.sleep(1000);

			
	}


	@Test(description="Verify the error message on incorrect date inputs",priority=11)	
	public void Verify_ErrMsg_on_EmptyDate() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select users
		action.SelectCheckBox(locator.getProperty("Checkbox0"));
		Thread.sleep(1000);
		action.SelectCheckBox(locator.getProperty("Checkbox2"));
		Thread.sleep(1000);
		action.ClickButton(locator.getProperty("Users.More"));
        //Click on bulk edit user link
		action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
		action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
		Thread.sleep(1000);
		//Edit the language and time
		action.SelectFromdropDown(locator.getProperty("LangDropdown"), input.getProperty("Danish"));
		action.SelectFromdropDown(locator.getProperty("TimeDropdown"), input.getProperty("Danishtime"));
		//Click on Run now button
		action.DoubleClickButton(locator.getProperty("Users.Schedule"));
		action.WaitForTitle(locator.getProperty("Schedule_Bulk_Edit_Job"));
		//Select previous month from list
		action.SelectFromdropDown(locator.getProperty("Bulk.month"), "March");
		//Click on schedule button
		action.DoubleClickButton(locator.getProperty("Bulk.schedule"));
		//Verify error message
		Thread.sleep(1000);
		action.VerifyStringValue("Start Time - Please provide Start Date/Time greater than current Date/Time");
		//Enter incorrect day
		action.ClearText(locator.getProperty("Bulk.day"));
		action.entertext(locator.getProperty("Bulk.day"), "32");
		//Click on schedule button
		action.DoubleClickButton(locator.getProperty("Bulk.schedule"));
		Thread.sleep(1000);
		//Verify Error message
		action.VerifyStringValue("Invalid date");
		
	}
	
	@Test(description="Verify the error message on incorrect time inputs",priority=12)	
	public void Verify_ErrMsg_on_EmptyTime() throws Exception
	{
		action.driver.navigate().refresh();
		//Click on User Management,Manage Users
		action.ClickLink(locator.getProperty("User_Management"));
		action.WaitForTitle(locator.getProperty("User_Management"));
		action.VerifyTitle(locator.getProperty("User_Management"));
		action.ClickLink(locator.getProperty("Manage_Users"));
		action.SwithchFrame("iframe0");
		//Select users
		action.SelectCheckBox(locator.getProperty("Checkbox0"));
		Thread.sleep(1000);
		action.SelectCheckBox(locator.getProperty("Checkbox2"));
		Thread.sleep(1000);
		action.ClickButton(locator.getProperty("Users.More"));
        //Click on Bulk edit user link
		action.ClickLink(locator.getProperty("Bulk_Edit_Users"));
		action.WaitForTitle(locator.getProperty("User_Bulk_Editor"));
		Thread.sleep(1000);
		//Edit the language and time
		action.SelectFromdropDown(locator.getProperty("LangDropdown"), input.getProperty("Danish"));
		action.SelectFromdropDown(locator.getProperty("TimeDropdown"), input.getProperty("Danishtime"));
		//Click on Schedule button
		action.DoubleClickButton(locator.getProperty("Users.Schedule"));
		action.WaitForTitle(locator.getProperty("Schedule_Bulk_Edit_Job"));
		//Enter incorrect hours in text box
		action.ClearText(locator.getProperty("Bulk.hour"));
		action.entertext(locator.getProperty("Bulk.hour"), "25");
		//Click on schedule button
		action.DoubleClickButton(locator.getProperty("Bulk.schedule"));
		Thread.sleep(1000);
		//Verify error message
		action.VerifyStringValue("Invalid Time; value for hour must be on or between 0 and 23.");
		//Enter incorrect minute in text box
		action.ClearText(locator.getProperty("Bulk.minute"));
		action.entertext(locator.getProperty("Bulk.minute"), "62");
		//Click on Schedule button
		action.DoubleClickButton(locator.getProperty("Bulk.schedule"));
		Thread.sleep(1000);
		//Verify error message
		action.VerifyStringValue("Invalid Time; value for minute(s) must be between 0 and 59.");
		//Verify incorrect seconds
		action.ClearText(locator.getProperty("Bulk.second"));
		action.entertext(locator.getProperty("Bulk.second"), "62");
		//Click on Schedule button
		action.DoubleClickButton(locator.getProperty("Bulk.schedule"));
		Thread.sleep(1000);
		action.VerifyStringValue("Invalid Time; value for second(s) must be between 0 and 59.");
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
