package com.avaya.smgr.RTS.ManageElements;
/*
 * Manage Elements page UI
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


public class ManageElements{
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

@Test(description="Verification of title Manage Elements is showing correctly")
public void ManageElmtsTitle() throws Exception{
	action.RefreshPage();
	//Navigate to configuration, RTS,Manage Elements
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Elements"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("appTableIframe");
	//Verify that the title Manage Elements is showing correctly
	action.VerifyTitle(locator.getProperty("Manage_Elements"));
}
@Test(description="Verification for enabled for New buttton and disabled for  View, Delete Buttons by default")
public void ManageElmtsBtnStates() throws Exception{
	action.RefreshPage();
	//Navigate to configuration, RTS,Manage Elements
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Elements"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("appTableIframe");
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	//Verification for New button is enabled
	//Verification for View and Delete Buttons are disabled "
	action.VerifyEnableButton(locator.getProperty("RTS.New"));
	action.VerifyDisableButton(locator.getProperty("RTS.View"));
	action.VerifyDisableButton(locator.getProperty("RTS.Delete"));
	}

@Test(description="Verify that type dropdown values are correctly displaying as expected")
public void ManageElmtsTypeDropdown() throws Exception{
	action.RefreshPage();
	//Navigate to configuration, RTS,Manage Elements
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Elements"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("appTableIframe");
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	//Click on the New button
	action.ClickButton(locator.getProperty("RTS.New"));
	String[] Typedropdown={
		"Select Type","Application Enablement Services",
		"CS 1000 Terminal Proxy Server",
		"Communication Manager","Conferencing","Engagement Development Platform","IP Office","IP Office UCM or IP Office Application Server",
		"Meeting Exchange and Conferencing 6.0","Messaging","Other Applications","Presence Services",
		"Session Manager","System Platform","Utility Server","Work Assignment"
	};
	action.VerifydropDownValues(locator.getProperty("RTS.Typedropdown"),Typedropdown);
	action.VerifyDisableButton(locator.getProperty("Commit"));
	action.VerifyEnableButton(locator.getProperty("Cancel"));
	}

@Test(description="Verification for the commit, Clear and Cancel buttons are enabled by default")
public void ManageElmtsBtnsStates() throws Exception{
	action.RefreshPage();
	//Navigate to configuration, RTS,Manage Elements
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Elements"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("appTableIframe");
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	//Click on the New button
	action.ClickButton(locator.getProperty("RTS.New"));
	//Select Communication Manager
	action.SelectFromdropDown(locator.getProperty("RTS.Typedropdown"),"Communication Manager");
	action.VerifyDisableButton(locator.getProperty("Commit"));
	action.VerifyEnableButton(locator.getProperty("Cancel"));
	}
@Test(description="Verify that the added CM successfully",groups={"Sanity"})
public void ManageElementsAdd() throws Exception{
	action.RefreshPage();
	//Navigate to configuration, RTS,Manage Elements
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Elements"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("appTableIframe");
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	//Click on the New button
	action.ClickButton(locator.getProperty("RTS.New"));
	//Select Communication Manager
	action.SelectFromdropDown(locator.getProperty("RTS.Typedropdown"),"Communication Manager");
	action.VerifyTitle(locator.getProperty("Manage_Elements"));
	//Fill the Required details
	action.entertext(locator.getProperty("Manage.Name"), input.getProperty("Name"));
	action.entertext(locator.getProperty("Manage.Ip"), input.getProperty("Ip"));
	action.entertext(locator.getProperty("Manage.login"), input.getProperty("Uname"));
	action.entertext(locator.getProperty("Manage.Password"), input.getProperty("Password"));
	action.entertext(locator.getProperty("Manage.CnfPassword"), input.getProperty("Password"));
	action.ClickButton(locator.getProperty("RTS.Commit"));
	//Verify that the added CM successfully
	action.VerifyaddedEntry(locator.getProperty("Manage.table"),input.getProperty("Name"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
}
@Test(description="Verify that the Updated CM name successfully",dependsOnMethods="ManageElementsAdd",groups={"Sanity"})
public void ManageElementsUpdate() throws Exception{
	action.RefreshPage();
	//Navigate to configuration, RTS,Manage Elements
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Elements"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("appTableIframe");
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	List<WebElement> totalRows = action.driver.findElements(By.xpath(".//*[@id='PageTable']/td/table/tbody/tr/td"));
	//System.out.println("Total Rows"+totalRows.size());

	for (int i=2;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(".//*[@id='table_2_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
    	if(sValue.contains(input.getProperty("Name"))){
   			WebElement sRowValue= action.driver.findElement(By.xpath(".//*[@id='table_2:"+(i-2)+"']"));
   			sRowValue.click();
   			Thread.sleep(1000);
   		break;
    }
	}
	//Click on the Edit button
	action.ClickButton(locator.getProperty("RTS.Edit"));
	action.ClearText(locator.getProperty("Manage.Name"));
	action.entertext(locator.getProperty("Manage.Name"), input.getProperty("UpdatedName"));
	action.ClickButton(locator.getProperty("RTS.Commit"));
	action.WaitForObj(locator.getProperty("Manage.table"));

	//Verify that the Updated CM name successfully
	action.VerifyaddedEntry(locator.getProperty("Manage.table"),input.getProperty("UpdatedName"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
}

@Test(description="Verify that the home page is disaplaying when click on the cancel button on delete page",dependsOnMethods="ManageElementView")
public void ManageElementDeleteCancel() throws Exception{
	action.RefreshPage();
	//Navigate to configuration, RTS,Manage Elements
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Elements"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("appTableIframe");
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	//Select First created element
	List<WebElement> totalRows = action.driver.findElements(By.xpath(".//*[@id='PageTable']/td/table/tbody/tr/td"));
	//System.out.println("Total Rows"+totalRows.size());

	for (int i=2;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(".//*[@id='table_2_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
    	if(sValue.contains(input.getProperty("UpdatedName"))){
   			WebElement sRowValue= action.driver.findElement(By.xpath(".//*[@id='table_2:"+(i-2)+"']"));
   			sRowValue.click();
   			Thread.sleep(1000);
   		break;
    }
	}
	//Click on the delete button
	action.ClickButton(locator.getProperty("RTS.Delete"));
	action.WaitForObj(locator.getProperty("Cancel_Cnf"));
	//Click on the Cancel button
	action.ClickButton(locator.getProperty("Cancel_Cnf"));
	action.VerifyTitle("Manage Elements");
}
@Test(description="Verify that the Deleted CM successfully",dependsOnMethods="ManageElementDeleteCancel",groups={"Sanity"})
public void ManageElementDelete() throws Exception{
	action.RefreshPage();
	//Navigate to configuration, RTS,Manage Elements
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Elements"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("appTableIframe");
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	//Select First created element
	List<WebElement> totalRows = action.driver.findElements(By.xpath(".//*[@id='PageTable']/td/table/tbody/tr/td"));
	//System.out.println("Total Rows"+totalRows.size());

	for (int i=2;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(".//*[@id='table_2_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
    	if(sValue.contains(input.getProperty("UpdatedName"))){
   			WebElement sRowValue= action.driver.findElement(By.xpath(".//*[@id='table_2:"+(i-2)+"']"));
   			sRowValue.click();
   			Thread.sleep(1000);
   		break;
    }
	}
	//Click on the delete button
	action.ClickButton(locator.getProperty("RTS.Delete"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	action.VerifyStringValue("Delete Communication Manager Confirmation");
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	action.ClickButton(locator.getProperty("Delete_Cnf"));
	action.WaitForObj(locator.getProperty("Manage.table"));
	//Verify that the Deleted CM successfully
	action.VerifyDeleteEntry(locator.getProperty("Manage.table"), input.getProperty("UpdatedName"));
}
@Test(description="Verify that the Edit and Done buttons are displaying correctly",dependsOnMethods="ManageElementsUpdate",groups={"Sanity"})
public void ManageElementView() throws Exception{
	action.RefreshPage();
	//Navigate to configuration, RTS,Manage Elements
	action.ClickLink(locator.getProperty("Inventory"));
	action.ClickLink(locator.getProperty("Manage_Elements"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("appTableIframe");
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	//Select the first element
	List<WebElement> totalRows = action.driver.findElements(By.xpath(".//*[@id='PageTable']/td/table/tbody/tr/td"));
	//System.out.println("Total Rows"+totalRows.size());

	for (int i=2;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(".//*[@id='table_2_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
    	if(sValue.contains(input.getProperty("UpdatedName"))){
   			WebElement sRowValue= action.driver.findElement(By.xpath(".//*[@id='table_2:"+(i-2)+"']"));
   			sRowValue.click();
   			Thread.sleep(1000);
   		break;
    }
	}
	//Click on the View button
	action.ClickButton(locator.getProperty("RTS.View"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
	//Verify that the Edit and Done buttons are displaying correctly
	action.VerifyEnableButton(locator.getProperty("RTS.View.Edit"));
	action.VerifyEnableButton(locator.getProperty("RTS.View.Done"));
	action.WaitForTitle(locator.getProperty("Manage_Elements"));
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

