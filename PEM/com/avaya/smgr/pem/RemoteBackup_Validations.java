package com.avaya.smgr.pem;

/*
 Contains tests for SMGR backup functionality.
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class RemoteBackup_Validations {
	boolean b;
	static UserAction action = null;
	Properties locator = null;
	Properties input = null;
	public WebDriver driver;

	@BeforeClass(alwaysRun=true)
	public void setup() throws IOException, InterruptedException {
		action = new UserAction();
		locator = new Properties();
		input = new Properties();
		locator.load(new FileInputStream(System.getProperty("user.dir")
				+ "\\Third Party\\objectRepository\\OR.properties"));
		input.load(new FileInputStream(System.getProperty("user.dir")
				+ "\\Third Party\\testData\\INPUT.properties"));
		action.login(input.getProperty("UserId"),
				input.getProperty("Password"), action);
	}

	@BeforeMethod(alwaysRun=true)
	public void ScreenshptSetup() throws IOException, InterruptedException {
	}

	@Test(description = "Verification of error message when  file name is empty")
	public void BackFileNameEmpty() throws Exception {
		action.RefreshPage();
		// Navigate to configuration, PEM
		action.ClickLink(locator.getProperty("Backup_and_Restore"));
		action.SwithchFrame("iframe0");
		// Click on the Back up button
		action.ClickButton(locator.getProperty("PEM.Backup"));
		// Click on the now button
		action.ClickButton(locator.getProperty("PEM.Now"));	
		action.WaitForObj(locator.getProperty("PEM.FileName.Error"));
		// Verification for the Back up title is showing correctly.
		action.VerifyElementValue(locator.getProperty("PEM.FileName.Error"),"Mandatory values cannot be empty.");
		Thread.sleep(1000);
	}

	@Test(description="Verification of error message when Scheduled job name is empty")
	public void BackUpScheduleJobEmpty() throws Exception{
		action.RefreshPage();
		//Navigate to configuration, PEM
		action.ClickLink(locator.getProperty("Backup_and_Restore"));
		action.SwithchFrame("iframe0");
		action.ClickButton(locator.getProperty("PEM.Backup"));
		action.WaitForObj(locator.getProperty("PEM.FileName"));
		// Enter Back up file name
		action.entertext(locator.getProperty("PEM.FileName"),input.getProperty("PEM.jobname"));
		//Click on the Schedule button
		action.ClickButton(locator.getProperty("PEM.Scheduled"));
		action.WaitForTitle(locator.getProperty("Schedule_Backup"));
		//Make sure job name is empty
        Calendar cal = Calendar.getInstance();
        int c=cal.get(Calendar.YEAR)+1;  
		action.ClearText(locator.getProperty("PEM.StartDateYear"));
        //Enter calendar year as next year
		action.entertext(locator.getProperty("PEM.StartDateYear"),""+c);
		//Click on the commit button
		action.ClickButton(locator.getProperty("Commit"));
		action.WaitForObj(locator.getProperty("PEM.ScheduleJobName.Error"));
		// Verification for the Back up title is showing correctly.
		action.VerifyElementValue(locator.getProperty("PEM.ScheduleJobName.Error"),"Job Name should not be left blank.");
		Thread.sleep(1000);

	}
	@Test(description="Verification of the error message when time is empty")
	public void BackUpScheduleDateEmpty() throws Exception{
		action.RefreshPage();
		//Navigate to configuration, PEM
		action.ClickLink(locator.getProperty("Backup_and_Restore"));
		action.SwithchFrame("iframe0");
		action.ClickButton(locator.getProperty("PEM.Backup"));
		action.WaitForObj(locator.getProperty("PEM.FileName"));
		// Enter Back up file name
		action.entertext(locator.getProperty("PEM.FileName"),input.getProperty("PEM.jobname"));
		//Click on the Schedule button
		action.ClickButton(locator.getProperty("PEM.Scheduled"));
		action.WaitForTitle(locator.getProperty("Schedule_Backup"));
		//Enter job name
		action.entertext(locator.getProperty("PEM.ScheduleJobName"),input.getProperty("PEM.jobname"));
        Calendar cal = Calendar.getInstance();
        int c=cal.get(Calendar.YEAR)+1;  
		action.ClearText(locator.getProperty("PEM.StartDateYear"));
		Thread.sleep(1000);
        //Enter calendar year as next year
		//action.entertext(locator.getProperty("PEM.StartDateYear"),""+c);
		//Click on the commit button
		action.ClickButton(locator.getProperty("Commit"));
		action.WaitForObj(locator.getProperty("PEM.Invalid"));
		String str=action.driver.findElement(By.xpath(locator.getProperty("PEM.Invalid"))).getText();
		// Verification for the Back up title is showing correctly.
		action.VerifyElementValue(locator.getProperty("PEM.Invalid"),str);
		Thread.sleep(1000);

	}
	@Test(description="Verification error message when Provided start Time greater than current Date/Time.")
	public void BackupMoreTime() throws Exception{
		action.RefreshPage();
		//Navigate to configuration, PEM
		action.ClickLink(locator.getProperty("Backup_and_Restore"));
		action.SwithchFrame("iframe0");
		action.ClickButton(locator.getProperty("PEM.Backup"));
		action.WaitForObj(locator.getProperty("PEM.FileName"));
		// Enter Back up file name
		action.entertext(locator.getProperty("PEM.FileName"),input.getProperty("PEM.jobname"));
		//Click on the Schedule button
		action.ClickButton(locator.getProperty("PEM.Scheduled"));
		action.WaitForTitle(locator.getProperty("Schedule_Backup"));
		//Enter job name
		action.entertext(locator.getProperty("PEM.ScheduleJobName"),input.getProperty("PEM.jobname"));
        Calendar cal = Calendar.getInstance();
        int c=cal.get(Calendar.YEAR)-1;  
		action.ClearText(locator.getProperty("PEM.StartDateYear"));
		action.WaitForObj(locator.getProperty("PEM.StartDateYear"));
        //Enter calendar year as next year
		action.entertext(locator.getProperty("PEM.StartDateYear"),""+c);
		//Click on the commit button
		action.ClickButton(locator.getProperty("Commit"));
		action.WaitForObj(locator.getProperty("PEM.Invalid"));
		action.VerifyElementValue(locator.getProperty("PEM.Invalid"),"Provide start Date/Time greater than current Date/Time.");
		Thread.sleep(1000);

	}
	@Test(description="Verification of error message when occurance is invalid")
	public void BackupRecurrence() throws Exception{
		action.RefreshPage();
		//Navigate to configuration, PEM
		action.ClickLink(locator.getProperty("Backup_and_Restore"));
		action.SwithchFrame("iframe0");
		action.ClickButton(locator.getProperty("PEM.Backup"));
		action.WaitForObj(locator.getProperty("PEM.FileName"));
		// Enter Back up file name
		action.entertext(locator.getProperty("PEM.FileName"),input.getProperty("PEM.jobname"));
		//Click on the Schedule button
		action.ClickButton(locator.getProperty("PEM.Scheduled"));
		action.WaitForTitle(locator.getProperty("Schedule_Backup"));
		//Enter job name
		action.entertext(locator.getProperty("PEM.ScheduleJobName"),input.getProperty("PEM.jobname"));
        Calendar cal = Calendar.getInstance();
        int c=cal.get(Calendar.YEAR)+1;  
		action.ClearText(locator.getProperty("PEM.StartDateYear"));
        //Enter calendar year as next year
		action.entertext(locator.getProperty("PEM.StartDateYear"),""+c);
		//Recurrence value is empty
		action.ClearText(locator.getProperty("PEM.Schedule.Rec"));
		//Click on the commit button
		action.ClickButton(locator.getProperty("Commit"));
		action.WaitForObj(locator.getProperty("PEM.Invalid"));
		action.VerifyElementValue(locator.getProperty("PEM.Invalid"),"Invalid Recurrence value-Enter non zero positive integer.");
		Thread.sleep(1000);
	}
	@Test(description="Verification of error message when range is invalid.")
	public void BackupInvalid() throws Exception{
		action.RefreshPage();
		//Navigate to configuration, PEM
		action.ClickLink(locator.getProperty("Backup_and_Restore"));
		action.SwithchFrame("iframe0");
		action.ClickButton(locator.getProperty("PEM.Backup"));
		action.WaitForObj(locator.getProperty("PEM.FileName"));
		// Enter Back up file name
		action.entertext(locator.getProperty("PEM.FileName"),input.getProperty("PEM.jobname"));
		//Click on the Schedule button
		action.ClickButton(locator.getProperty("PEM.Scheduled"));
		action.WaitForTitle(locator.getProperty("Schedule_Backup"));
		//Enter job name
		action.entertext(locator.getProperty("PEM.ScheduleJobName"),input.getProperty("PEM.jobname"));
        Calendar cal = Calendar.getInstance();
        int c=cal.get(Calendar.YEAR)+1;  
		action.ClearText(locator.getProperty("PEM.StartDateYear"));
		Thread.sleep(1000);
        //Enter calendar year as next year
		action.entertext(locator.getProperty("PEM.StartDateYear"),""+c);
		//Recurrence value is empty
		action.ClearText(locator.getProperty("PEM.Schedule.Rec"));
		//Click on the commit button
		action.ClickButton(locator.getProperty("Commit"));
		action.WaitForObj(locator.getProperty("PEM.Invalid"));
		action.VerifyElementValue(locator.getProperty("PEM.Invalid"),"Invalid Recurrence value-Enter non zero positive integer.");
		Thread.sleep(1000);

	}
	
	@Test(description="Verification of error message when Occurance is out of range")
	public void BackUpOutRange() throws Exception{
		action.RefreshPage();
		//Navigate to configuration, PEM
		action.ClickLink(locator.getProperty("Backup_and_Restore"));
		action.SwithchFrame("iframe0");
		action.ClickButton(locator.getProperty("PEM.Backup"));
		action.WaitForObj(locator.getProperty("PEM.FileName"));
		// Enter Back up file name
		action.entertext(locator.getProperty("PEM.FileName"),input.getProperty("PEM.jobname"));
		//Click on the Schedule button
		action.ClickButton(locator.getProperty("PEM.Scheduled"));
		action.WaitForTitle(locator.getProperty("Schedule_Backup"));
		//Enter job name
		action.entertext(locator.getProperty("PEM.ScheduleJobName"),input.getProperty("PEM.jobname"));
        Calendar cal = Calendar.getInstance();
        int c=cal.get(Calendar.YEAR)+1;  
		action.ClearText(locator.getProperty("PEM.StartDateYear"));
        //Enter calendar year as next year
		action.entertext(locator.getProperty("PEM.StartDateYear"),""+c);
		//Clear default value
		action.ClearText(locator.getProperty("PEM.Schedule.Occurance"));
		action.entertext(locator.getProperty("PEM.Schedule.Occurance"),""+0);

		//Click on the commit button
		action.ClickButton(locator.getProperty("Commit"));
		action.WaitForObj(locator.getProperty("PEM.Invalid"));
		action.VerifyElementValue(locator.getProperty("PEM.Invalid"),"Occurrence value has to be in range of 1 and 999.");
		Thread.sleep(1000);

	}
	@Test(description="Verification error messages when ServerIP is Empty")
	public void RemoteServerIPEmpty() throws Exception{
		action.RefreshPage();
		//Navigate to configuration, PEM
		action.ClickLink(locator.getProperty("Backup_and_Restore"));
		action.SwithchFrame("iframe0");
		//Click on the Back up button
		action.ClickButton(locator.getProperty("PEM.Backup"));
		action.WaitForObj(locator.getProperty("PEM.FileName"));
		//Click on the Remote Radio button
		action.ClickButton(locator.getProperty("PEM.Remote"));
		//Make sure Server IP is empty
		//Fill the Required details
		action.entertext(locator.getProperty("PEM.Remote.UserName"),input.getProperty("Uname"));
		action.entertext(locator.getProperty("PEM.Remote.Password"),input.getProperty("Password"));
		action.entertext(locator.getProperty("PEM.FileName"),input.getProperty("BackupName"));
		//Click on the Commit button
		action.ClickButton(locator.getProperty("PEM.Now"));
		action.WaitForObj(locator.getProperty("PEM.Remote.ipError"));
		action.VerifyElementValue(locator.getProperty("PEM.Remote.ipError"),"Mandatory values cannot be empty.");
		Thread.sleep(1000);

		}
	@Test(description="Verification of error message when Remote UserName is Empty")
	public void RemoteUserNameEmpty() throws Exception{
		action.RefreshPage();
		//Navigate to configuration, PEM
		action.ClickLink(locator.getProperty("Backup_and_Restore"));
		action.SwithchFrame("iframe0");
		//Click on the Back up button
		action.ClickButton(locator.getProperty("PEM.Backup"));
		action.WaitForObj(locator.getProperty("PEM.FileName"));
		//Click on the Remote Radio button
		action.ClickButton(locator.getProperty("PEM.Remote"));
		//Fill the Required details
		//User name is empty
		action.entertext(locator.getProperty("PEM.Remote.ip"),input.getProperty("Ip"));
		action.entertext(locator.getProperty("PEM.Remote.Password"),input.getProperty("Password"));
		action.entertext(locator.getProperty("PEM.FileName"),input.getProperty("BackupName"));
		//Click on the Commit button
		action.ClickButton(locator.getProperty("PEM.Now"));
		action.WaitForObj(locator.getProperty("PEM.Remote.UserNameError"));
		action.VerifyElementValue(locator.getProperty("PEM.Remote.UserNameError"),"Mandatory values cannot be empty.");
		Thread.sleep(1000);
		}
	@Test(description="Verification error message when password is empty")
	public void RemotePasswordEmpty() throws Exception{
		action.RefreshPage();
		//Navigate to configuration, PEM
		action.ClickLink(locator.getProperty("Backup_and_Restore"));
		action.SwithchFrame("iframe0");
		//Click on the Back up button
		action.ClickButton(locator.getProperty("PEM.Backup"));
		action.WaitForObj(locator.getProperty("PEM.FileName"));
		//Click on the Remote Radio button
		action.ClickButton(locator.getProperty("PEM.Remote"));
		//Fill the Required details
		//Password should empty
		action.entertext(locator.getProperty("PEM.Remote.ip"),input.getProperty("Ip"));
		action.entertext(locator.getProperty("PEM.Remote.UserName"),input.getProperty("Uname"));
		action.entertext(locator.getProperty("PEM.FileName"),input.getProperty("BackupName"));
		//Click on the Commit button
		action.ClickButton(locator.getProperty("PEM.Now"));
		action.WaitForObj(locator.getProperty("PEM.Remote.PasswordError"));
		action.VerifyElementValue(locator.getProperty("PEM.Remote.PasswordError"),"Mandatory values cannot be empty.");
		Thread.sleep(1000);

		}
	@Test(description="Verification of error message when back up file name empty")
	public void RemoteFilenameEmpty() throws Exception{
		action.RefreshPage();
		//Navigate to configuration, PEM
		action.ClickLink(locator.getProperty("Backup_and_Restore"));
		action.SwithchFrame("iframe0");
		//Click on the Back up button
		action.ClickButton(locator.getProperty("PEM.Backup"));
		action.WaitForObj(locator.getProperty("PEM.FileName"));
		//Click on the Remote Radio button
		action.ClickButton(locator.getProperty("PEM.Remote"));
		//Fill the Required details
		//Back up should be empty
		action.entertext(locator.getProperty("PEM.Remote.ip"),input.getProperty("Ip"));
		action.entertext(locator.getProperty("PEM.Remote.UserName"),input.getProperty("Uname"));
		action.entertext(locator.getProperty("PEM.Remote.Password"),input.getProperty("Password"));
		//Click on the Commit button
		action.ClickButton(locator.getProperty("PEM.Now"));
		action.WaitForObj(locator.getProperty("PEM.FileName.Error"));
		action.VerifyElementValue(locator.getProperty("PEM.FileName.Error"),"Mandatory values cannot be empty.");
		Thread.sleep(1000);
		}
	@Test(description="Verification of error message when port is empty")
	public void RemoteBackPortEmpty() throws Exception{
		action.RefreshPage();
		//Navigate to configuration, PEM
		action.ClickLink(locator.getProperty("Backup_and_Restore"));
		action.SwithchFrame("iframe0");
		//Click on the Back up button
		action.ClickButton(locator.getProperty("PEM.Backup"));
		action.WaitForObj(locator.getProperty("PEM.FileName"));
		//Click on the Remote Radio button
		action.ClickButton(locator.getProperty("PEM.Remote"));
		//Fill the Required details
		//Port should empty
		action.entertext(locator.getProperty("PEM.Remote.ip"),input.getProperty("Ip"));
		action.entertext(locator.getProperty("PEM.Remote.UserName"),input.getProperty("Uname"));
		action.entertext(locator.getProperty("PEM.Remote.Password"),input.getProperty("Password"));
		action.entertext(locator.getProperty("PEM.FileName"),input.getProperty("BackupName"));
		action.ClearText(locator.getProperty("PEM.Remote.port"));
		//Click on the Commit button
		action.ClickButton(locator.getProperty("PEM.Now"));
		action.WaitForObj(locator.getProperty("PEM.Remote.portError"));
		action.VerifyElementValue(locator.getProperty("PEM.Remote.portError"),"Mandatory values cannot be empty.");
		Thread.sleep(1000);
		}

	@AfterMethod(alwaysRun=true)
	public void Screenshots(ITestResult result) throws IOException,
			InterruptedException {

		action.Screenshot(result, action);
	}

	@AfterClass(alwaysRun=true)
	public void Close() throws IOException, InterruptedException {
		// action logout=new action();
		action.logout(action);
	}
}
