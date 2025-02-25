package com.avaya.smgr.spm.settings.smgr;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class Licenses{
	boolean b;
	static UserAction action =null;
	Properties locator=null;
	Properties input=null;
	public WebDriver driver;
	Boolean Accept;

	
@BeforeClass(alwaysRun=true)
public void setup() throws IOException, InterruptedException{
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
   }
@BeforeMethod(alwaysRun=true)
public void ScreenshptSetup() throws IOException, InterruptedException{
	
}	
@Test(description="Verify that the values are successfully change/override by an administrator and attribute successfully saved",groups={"Sanity"})
public void LicensesUpdate() throws Exception{
	action.RefreshPage();
	//Navigate to Configuration page, setting, SMGR, Agent Management 
	action.ClickLink("Configurations");
	action.ClickLink(locator.getProperty("Configurations"));
	action.ClickLink(locator.getProperty("Settings"));
	action.ClickLink(locator.getProperty("SMGR"));
	action.ClickLink(locator.getProperty("Licenses"));
	action.SwithchFrame("iframe0");
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value from false to true in the Email forward field
	action.ClearText(locator.getProperty("Licences.Usage"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	action.entertext(locator.getProperty("Licences.Usage"), input.getProperty("SingleDigitValue"));
	//Verify that the values are successfully change/override by an administrator and attribute successfully saved
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Licences.Usage"));
	action.VerifyElementValue(locator.getProperty("Licences.Usage"), input.getProperty("SingleDigitValue"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}

@Test(description="Enable of Edit and Done buttons",priority=1)
 public void LicensesEditDoneEnableBtn() throws Exception{
	//Edit and Done buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	Thread.sleep(1000);
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="enable of commit and cancel buttons",priority=2)
public void LicensesCommitCancelEnableBtn() throws Exception{
	//Click on the Edit button
	action.ClickButton(locator.getProperty("Edit"));
	Thread.sleep(2000);
	Accept=action.isAlertPresent();
	action.alert(Accept);
	//Verification of Commit and Cancel buttons should be enable
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	action.ClickButton(locator.getProperty("Done"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Verify the title value")
public void LicensesTitle() throws Exception{
	action.VerifyStringValue(locator.getProperty("Licenses"));
}

@Test(description="Verify that the error message should display when License Usage field is empty",priority=4)
public void LicenseUsageEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value from false to true in the Email forward field
	action.ClearText(locator.getProperty("Licences.Usage"));
	action.ClickButton(locator.getProperty("Commit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("Licences.Usage.msg"));
	action.VerifyElementValue(locator.getProperty("Licences.Usage.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Done"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@Test(description="Verify that the error message should display when License allocation field is empty",priority=5)
public void LicenseAllocationEmpty() throws Exception{
	action.ClickButton(locator.getProperty("Edit"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForTitle(locator.getProperty("Edit_Profile"));
	//update the value from false to true in the Email forward field
	action.ClearText(locator.getProperty("License.Allocation"));
	action.ClickButton(locator.getProperty("Commit"));
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);
	action.WaitForObj(locator.getProperty("License.Allocation.msg"));
	action.VerifyElementValue(locator.getProperty("License.Allocation.msg"), locator.getProperty("Integer_value_is_out_of_range"));
	action.ClickButton(locator.getProperty("Done"));
	Accept=action.isAlertPresent();
	action.alert(Accept);
}
@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws Exception{
	  
	action.Screenshot(result, action);
	boolean Accept=action.isAlertPresent();
	action.alert(Accept);

}
@AfterClass(alwaysRun=true)
	public void Close() throws IOException, InterruptedException{
	action.logout(action);
	}
}
