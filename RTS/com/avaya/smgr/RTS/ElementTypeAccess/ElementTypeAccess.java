package com.avaya.smgr.RTS.ElementTypeAccess;
/*
 * Test Case related to Element type access UI page
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class ElementTypeAccess{
	boolean b;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
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
public void Beforemethodsetup() throws IOException, InterruptedException{
}

@Test(description="Verification of title Element type access is showing correctly")
public void ElmtAccessTypeTitle() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,RTS,Element type access
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Element_Type_Access"));
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	action.SwithchFrame("iframe0");
	//Verify that the title name is showing correctly
	action.VerifyTitle(locator.getProperty("Element_Type_Access_Profile_Management.title"));
}

@Test(description="Verify that type dropdown values are correctly displaying as expected")
public void ElmtAccessTypedropdown() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,RTS,Element type access
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Element_Type_Access"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Element_Type_Access_Profile_Management.title"));
	String[] Expected=
	{
		"Select Element Type","Application Enablement Services","Communication Manager","Meeting Exchange and Conferencing 6.0",
		"Engagement Development Platform","ESXi Host","IP Office","Media Gateway","Media Module",
		"Messaging","Conferencing","OneX Portal","Other Applications","Presence Services","Session Manager","System Manager",
		"System Platform","Templates","TN Board","CS 1000 Terminal Proxy Server","Work Assignment","UCMApp","MemberServer",
		"IP Office UCM or IP Office Application Server","Utility Server","Virtual Machine","Voice Mail Pro"
	};
	action.VerifydropDownValues(locator.getProperty("ElementTypeAcess.TypeDrpdwn"),Expected);
	Thread.sleep(1000);
  }

@Test(description="Verification for button states for the Element type access profiles by default")
public void ElmtAccessTypeBtnsStates() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,RTS,Element type access
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Element_Type_Access"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	action.SelectFromdropDown(locator.getProperty("ElementTypeAcess.TypeDrpdwn"),"Communication Manager");
	Thread.sleep(2000);
	//Verification for button states for the Element type access profiles by default
	action.VerifyEnableButton(locator.getProperty("ElementTypeAcess.New"));
	action.VerifyDisableButton(locator.getProperty("ElementTypeAcess.Delete"));
	action.VerifyDisableButton(locator.getProperty("ElementTypeAcess.Edit"));
	action.VerifyDisableButton(locator.getProperty("ElementTypeAcess.View"));
	}
@Test(description="Verify that Protocol drop down values are correctly displaying as expected")
public void ElmtAccessTypeProtocolDrpdwn() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,RTS,Element type access
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Element_Type_Access"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	//Select Communication manager from Element type access drop down
	action.SelectFromdropDown(locator.getProperty("ElementTypeAcess.TypeDrpdwn"),"Communication Manager");
	Thread.sleep(2000);
	//Click on the New button
	action.ClickButton(locator.getProperty("ElementTypeAcess.New"));
	Thread.sleep(1000);
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
    String[] Expected={"Select Protocol","SNMP","SSH"};
    //Verify that Protocol drop down values are correctly displaying as expected
	action.VerifydropDownValues(locator.getProperty("ElementTypeAcess.ProtDrpdown"),Expected);
	Thread.sleep(1000);
	}

@Test(description="Verify that the added Element type access successfully",groups={"Sanity"})
public void ElmtAccessTypeAdd() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,RTS,Element type access
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Element_Type_Access"));
	action.SwithchFrame("iframe0");
	action.SelectFromdropDown(locator.getProperty("ElementTypeAcess.TypeDrpdwn"),"Communication Manager");
	Thread.sleep(2000);
	//Click on the New button
	action.ClickButton(locator.getProperty("ElementTypeAcess.New"));
	action.SelectFromdropDown(locator.getProperty("ElementTypeAcess.ProtDrpdown"),"SSH");
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
	action.entertext(locator.getProperty("ElementTypeAcess.Name"), input.getProperty("Name"));
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
	action.entertext(locator.getProperty("ElementTypeAcess.Log"), input.getProperty("Name"));
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
	action.entertext(locator.getProperty("ElementTypeAcess.Password"), input.getProperty("Password"));
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
	action.entertext(locator.getProperty("ElementTypeAcess.CnfPassword"), input.getProperty("Password"));
	action.WaitForTitle(locator.getProperty("Add_Access_Profile_Entry"));
	action.ClickButton(locator.getProperty("ElementTypeAcess.Commit"));
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	//Verify that the added Element type access successfully
	action.VerifyaddedEntry(locator.getProperty("ElementTypeAcess.Table"),input.getProperty("Name"));
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
}
@Test(description="Verify that the updated Element type access successfully",dependsOnMethods="ElmtAccessTypeAdd",groups={"Sanity"})
public void ElmtAccessTypeUpdate() throws Exception{
	action.RefreshPage();
	//Navigate to configuration, PEM
	action.ClickLink(locator.getProperty("Inventory"));	
	action.ClickLink(locator.getProperty("Element_Type_Access"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	action.SelectFromdropDown(locator.getProperty("ElementTypeAcess.TypeDrpdwn"),"Communication Manager");
	Thread.sleep(2000);
	
	List<WebElement> totalRows = action.driver.findElements(By.xpath(".//*[@id='PageTable']/td/table/tbody/tr/td"));
	//System.out.println("Total Rows"+totalRows.size());

	for (int i=0;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(".//*[@id='viewElementTypeConfigurationTable:"+i+":columnElementTypeAccessName']")).getText();
    	if(sValue.contains(input.getProperty("Name"))){
   			WebElement sRowValue= action.driver.findElement(By.xpath(".//*[@id='viewElementTypeConfigurationTable:"+i+"']"));
   			sRowValue.click();
   			Thread.sleep(1000);
   		break;
    }
	}
	Thread.sleep(1000);

	//Select the Element
	//action.ClickButton(locator.getProperty("ElementTypeAcess.SelectSecond"));
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	action.ClickButton(locator.getProperty("ElementTypeAcess.Edit"));
	Thread.sleep(3000);
	action.ClearText(locator.getProperty("ElementTypeAcess.Name"));
	action.WaitForTitle(locator.getProperty("Modify_Access_Profile_Entry"));
	action.entertext(locator.getProperty("ElementTypeAcess.Name"), input.getProperty("UpdatedName"));
	action.WaitForTitle(locator.getProperty("Modify_Access_Profile_Entry"));
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForTitle(locator.getProperty("Element_Type_Access_Profile_Management.title"));
	//Verify that the updated Element type access successfully
	action.VerifyaddedEntry(locator.getProperty("ElementTypeAcess.Table"),input.getProperty("UpdatedName"));
	action.WaitForTitle(locator.getProperty("Element_Type_Access_Profile_Management.title"));
	}
@Test(description="Verify that the View Element type access and buttons states successfully",dependsOnMethods="ElmtAccessTypeAdd",groups={"Sanity"})
public void ElmtAccessTypeView() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,RTS,Element type access
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Element_Type_Access"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	action.SelectFromdropDown(locator.getProperty("ElementTypeAcess.TypeDrpdwn"),"Communication Manager");
	Thread.sleep(2000);
	List<WebElement> totalRows = action.driver.findElements(By.xpath(".//*[@id='PageTable']/td/table/tbody/tr/td"));
	//System.out.println("Total Rows"+totalRows.size());

	for (int i=0;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(".//*[@id='viewElementTypeConfigurationTable:"+i+":columnElementTypeAccessName']")).getText();
    	if(sValue.contains(input.getProperty("UpdatedName"))){
   			WebElement sRowValue= action.driver.findElement(By.xpath(".//*[@id='viewElementTypeConfigurationTable:"+i+"']"));
   			sRowValue.click();
   			Thread.sleep(2000);
   		break;
    }
	}
	Thread.sleep(3000);

	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	action.ClickButton(locator.getProperty("ElementTypeAcess.View"));
	action.WaitForTitle(locator.getProperty("View_Access_Profile_Entry"));
	action.VerifyEnableButton(locator.getProperty("Edit"));
	action.VerifyEnableButton(locator.getProperty("Done"));
	
	action.WaitForTitle(locator.getProperty("View_Access_Profile_Entry"));
}
@Test(description="Verify that the Deleted Element Access successfully",dependsOnMethods="ElmtAccessTypeUpdate",groups={"Sanity"})
public void ElmtAccessTypeDelete() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,RTS,Element type access
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Element_Type_Access"));
	action.SwithchFrame("iframe0");
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	action.SelectFromdropDown(locator.getProperty("ElementTypeAcess.TypeDrpdwn"),"Communication Manager");
	Thread.sleep(2000);
	List<WebElement> totalRows = action.driver.findElements(By.xpath(".//*[@id='PageTable']/td/table/tbody/tr/td"));
	//System.out.println("Total Rows"+totalRows.size());

	for (int i=0;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(".//*[@id='viewElementTypeConfigurationTable:"+i+":columnElementTypeAccessName']")).getText();
    	if(sValue.contains(input.getProperty("UpdatedName"))){
   			WebElement sRowValue= action.driver.findElement(By.xpath(".//*[@id='viewElementTypeConfigurationTable:"+i+"']"));
   			sRowValue.click();
   			Thread.sleep(1000);
   		break;
    }
	}
	Thread.sleep(1000);
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	action.ClickButton(locator.getProperty("ElementTypeAcess.Delete"));
	action.WaitForTitle(locator.getProperty("Delete_Access_Profile_Entry"));
	//Verify the Delete and Cancel buttons are enabled
	action.VerifyEnableButton(locator.getProperty("Continue_Cnf"));
	action.VerifyEnableButton(locator.getProperty("Cancel_Cnf"));
	action.ClickButton(locator.getProperty("Continue_Cnf"));
	//Verify that the Deleted Element Access successfully
	action.VerifyDeleteEntry(locator.getProperty("ElementTypeAcess.Table"), input.getProperty("UpdatedName"));
	action.WaitForTitle(locator.getProperty("Element_Type_Access"));
	}
	

@AfterMethod(alwaysRun=true)
public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
}

@AfterClass(alwaysRun=true)
	public void Close() throws IOException, InterruptedException{
		action.logout(action);
	}
 }

