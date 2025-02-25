package com.avaya.smgr.gls.groups;
/*
 * Test Case related to Groups UI page
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class Groups{
	boolean b=false,match=false;;
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

@Test(description="Verification of buttons states are showing correctly")
public void GLSBtns() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Groups & Roles,Groups
	action.ClickLink(locator.getProperty("Groups_Roles"));
	action.ClickLink(locator.getProperty("Groups"));
	action.SwithchFrame("iframe0");
	//Verify that the title name is showing correctly
	action.VerifyTitle(locator.getProperty("Group_Management"));
	//Verify the button states correctly(Enabled for New and Disabled for Edit, Duplicate,View)
	action.VerifyEnableButton(locator.getProperty("GLS.add"));
	action.VerifyDisableButton(locator.getProperty("GLS.View"));
	action.VerifyDisableButton(locator.getProperty("EditBtn"));
	action.VerifyDisableButton(locator.getProperty("GLS.Duplicate"));
	action.VerifyDisableButton(locator.getProperty("GLS.Delete"));
}
@Test(description="Verification of error message when group name is empty")
public void GLSGroupNameEmpty() throws Exception{
		action.RefreshPage();
		//Navigate to SMGR,Groups & Roles,Groups
		action.ClickLink(locator.getProperty("Groups_Roles"));
		action.ClickLink(locator.getProperty("Groups"));
		action.SwithchFrame("iframe0");
		action.ClickButton(locator.getProperty("GLS.add"));
		//Don't enter Group name
		//Click on the commit button
		action.ClickButton(locator.getProperty("Commit"));
		action.WaitForObj(locator.getProperty("GLS.Error.name"));
		//Verification of error message when group name is empty
		action.VerifyElementValue(locator.getProperty("GLS.Error.name"), "Name cannot be empty.");
	 }	

@Test(description="Verify that the Group should create successfully",priority=1,groups={"Sanity"})
public void GLSGroupadded() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Groups & Roles,Groups
	action.ClickLink(locator.getProperty("Groups_Roles"));
	action.ClickLink(locator.getProperty("Groups"));
	action.SwithchFrame("iframe0");
	//Click on the New button
	action.ClickButton(locator.getProperty("GLS.add"));
	//Enter Group name
	action.entertext(locator.getProperty("GLS.GroupName"), input.getProperty("Uname"));
	//Click on the commit button
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForObj(locator.getProperty("GLS.add"));
	String Actual=input.getProperty("Uname");
	//Verify that the Group is created successfully
	Verify_Add_Secondcolumn(Actual);
}

@Test(description="Verify that the created Group is view successfully",priority=2,groups={"Sanity"})
public void GLSGroupView() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Groups & Roles,Groups
	action.ClickLink(locator.getProperty("Groups_Roles"));
	action.ClickLink(locator.getProperty("Groups"));
	action.SwithchFrame("iframe0");
	String Actual=input.getProperty("Uname");
	//Select the above created group 
	action.SelectChkboxSecColmn(locator.getProperty("Table.Row"),  input.getProperty("Uname"),locator.getProperty("Table1"));
	Thread.sleep(1000);
	//click  on the view button
	action.DoubleClickButton(locator.getProperty("GLS.View")); 
	//System.out.println(action.driver.getTitle());
	action.WaitForTitle("View Group");
	//Verify that the create Group is view successfully
	action.VerifyReadOnlyDisplayed(locator.getProperty("GLS.View.Name"));
	}

@Test(description="Verify that the updated Group successfully",priority=3,groups={"Sanity"})
public void GLSGroupEdit() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Groups & Roles,Groups
	action.ClickLink(locator.getProperty("Groups_Roles"));
	action.ClickLink(locator.getProperty("Groups"));
	action.SwithchFrame("iframe0");
	String Actual=input.getProperty("Uname");
	//Select the Group
	action.SelectChkboxSecColmn(locator.getProperty("Table.Row"),  input.getProperty("Uname"),locator.getProperty("Table1"));
	Thread.sleep(1000);
	//click  on the Edit button
	action.DoubleClickButton(locator.getProperty("EditBtn"));
	action.ClearText(locator.getProperty("GLS.GroupName"));
	//Edit the Updated name
	action.entertext(locator.getProperty("GLS.GroupName"), input.getProperty("UpdatedName"));
	//Click on commit button
	action.ClickButton(locator.getProperty("Commit"));
	action.WaitForObj(locator.getProperty("GLS.add"));
	//Verify that the updated Group successfully
	Verify_Add_Secondcolumn(input.getProperty("UpdatedName"));
 }	
@Test(description="Verify that the duplicate the Group successfully",priority=4,groups={"Sanity"})
public void GLSGroupDuplicate() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Groups & Roles,Groups
	action.ClickLink(locator.getProperty("Groups_Roles"));
	action.ClickLink(locator.getProperty("Groups"));
	action.SwithchFrame("iframe0");
	String Actual=input.getProperty("UpdatedName");
	
	//Select the Group
	action.SelectChkboxSecColmn(locator.getProperty("Table.Row"),  input.getProperty("UpdatedName"),locator.getProperty("Table1"));
	Thread.sleep(1000);
	//click  on the Duplicate button
	action.DoubleClickButton(locator.getProperty("GLS.Duplicate"));
	//Click on the root button
	action.DoubleClickButton(locator.getProperty("GLS.Root"));
	action.WaitForObj(locator.getProperty("GLS.add"));
	//Verify that the Created Duplicate Group successfully
	//It will add "Copy of" in front of Group name
	String Duplicate="Copy of"+" "+Actual;
	Verify_Add_Secondcolumn(Duplicate);

 }	


@Test(description="Verify that the find the group name as equal string by Advance Search",priority=7)
public void GLSGroupAdvanceSearchByName() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Groups & Roles,Groups
	action.ClickLink(locator.getProperty("Groups_Roles"));
	action.ClickLink(locator.getProperty("Groups"));
	action.SwithchFrame("iframe0");
	String Actual=input.getProperty("UpdatedName");
	action.ClickButton(locator.getProperty("AdvanceSearchBtn"));
	action.WaitForObj(locator.getProperty("AdvanceSearchInput"));
	//action.WaitForObj(locator.getProperty("AdvanceSearchInput"));
	action.entertext(locator.getProperty("AdvanceSearchInput"), Actual);
	//Click on the search button
	action.ClickButton(locator.getProperty("AdvanceSearch"));
	//Verify that the find the group name as equal string by Advance Search
	Verify_Add_Secondcolumn(Actual);

 }	
@Test(description="Verification for find the Name starts with string using Advance Search",priority=8)
public void GLSGroupAdvanceSearchByStartswith() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Groups & Roles,Groups
	action.ClickLink(locator.getProperty("Groups_Roles"));
	action.ClickLink(locator.getProperty("Groups"));
	action.SwithchFrame("iframe0");
	String Actual=input.getProperty("UpdatedName");
	action.ClickButton(locator.getProperty("AdvanceSearchBtn"));
	//Select the Starts with 
	action.SelectFromdropDown(locator.getProperty("AdvanceOccurances"), "starts with");
	action.entertext(locator.getProperty("AdvanceSearchInput"), Actual);
	//Click on the search button
	action.ClickButton(locator.getProperty("AdvanceSearch"));
	//Verification for find the Name starts with string using Advance Search
	Verify_Add_Secondcolumn(Actual);
 }	
@Test(description="Verification for find the name contains with string by using using Advance Search",priority=9)
public void GLSGroupAdvanceSearchByContains() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Groups & Roles,Groups
	action.ClickLink(locator.getProperty("Groups_Roles"));
	action.ClickLink(locator.getProperty("Groups"));
	action.SwithchFrame("iframe0");
	String Actual=input.getProperty("UpdatedName");
	action.ClickButton(locator.getProperty("AdvanceSearchBtn"));
	//Select the Starts with 
	action.SelectFromdropDown(locator.getProperty("AdvanceOccurances"), "contains");
	action.entertext(locator.getProperty("AdvanceSearchInput"), Actual);
	//Click on the search button
	action.ClickButton(locator.getProperty("AdvanceSearch"));
	//Verification for find the name contains with string by using using Advance Search
	Verify_Add_Secondcolumn(Actual);
 }	

@Test(description="Verify that Group is Move the selected group to the group that you selected in the Name column. ",priority=10)
public void GLSGroupMove() throws Exception{
	action.RefreshPage();
	//Navigate to SMGR,Groups & Roles,Groups
	action.ClickLink(locator.getProperty("Groups_Roles"));
	action.ClickLink(locator.getProperty("Groups"));
	action.SwithchFrame("iframe0");
	String Actual="Copy of"+" "+input.getProperty("UpdatedName");
	//Select the Group
	action.SelectChkboxSecColmn(locator.getProperty("Table.Row"),  input.getProperty("UpdatedName"),locator.getProperty("Table1"));
	Thread.sleep(1000);
	
	List<WebElement> totalRows = action.driver.findElements(By.xpath(locator.getProperty("Table.Row")));
	//click  on the view button
	action.ClickButton(locator.getProperty("GLS.Drp")); 
	action.WaitForObj(locator.getProperty("GLS.Drp.Mov"));
	action.ClickButton(locator.getProperty("GLS.Drp.Mov")); 
	String Moov=input.getProperty("UpdatedName");
	//Select the above created group 
	List<WebElement> Rows = action.driver.findElements(By.xpath(locator.getProperty("Table.Row")));
	for (int i=0;i<=Rows.size();i++){
		String sValue = null;
		sValue= action.driver.findElement(By.xpath(locator.getProperty("Table1")+"row_[0,"+" "+i+"]']/td[2]/div")).getText();
		
		if(Moov.contains(sValue)){
			WebElement sRowValue= action.driver.findElement(By.xpath(locator.getProperty("Table1")+(i)+"']"));
			sRowValue.click();
			Thread.sleep(1000);
 			break;
		}
	}
	
	action.ClickButton(locator.getProperty("Users.Selectgrpbtn"));
	action.WaitForTitle(locator.getProperty("Group_Management"));
	for (int i=2;i<=totalRows.size();i++){
		String sValue = null;
		sValue= action.driver.findElement(By.xpath(locator.getProperty("Table.Row")+"["+i+"]"+"/td[4]")).getText();
		if(Moov.equals(sValue)){
			Assert.assertTrue(Moov.equals(sValue));	
		}
	}
}	

@Test(description="Verify that the Deleted the Group successfully",priority=11,groups={"Sanity"})
public void GLSGroupDelete() throws Exception{
	action.RefreshPage();
	int flag=0;
	//Navigate to SMGR,Groups & Roles,Groups
	action.ClickLink(locator.getProperty("Groups_Roles"));
	action.ClickLink(locator.getProperty("Groups"));
	action.SwithchFrame("iframe0");
	String Actual=input.getProperty("UpdatedName");
	//Verify that the Group is created successfully
	action.SelectChkboxSecColmn(locator.getProperty("Table.Row"),  input.getProperty("UpdatedName"),locator.getProperty("Table1"));
	Thread.sleep(1000);
	//click  on the Delete button
	action.DoubleClickButton(locator.getProperty("GLS.Delete"));
	action.WaitForTitle("Delete Group Confirmation.");
	//Click on the root button
	action.ClickButton(locator.getProperty("Delete_Cnf"));
	action.WaitForTitle(locator.getProperty("Group_Management"));
	action.VerifyDeleteEntry(locator.getProperty("Table.Row"),input.getProperty("UpdatedName"));
	Thread.sleep(1000);

 }	

public void Verify_Add_Secondcolumn(String Columnvalue) throws Exception
{
	Thread.sleep(1000);
	action.driver.findElement(By.id("table_1_enableFiltering")).click();
	WebDriverWait wait = new WebDriverWait(action.driver,20);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getProperty("Filter.Name"))));
	action.driver.findElement(By.xpath(locator.getProperty("Filter.Name"))).sendKeys(Columnvalue);
	action.driver.findElement(By.xpath(locator.getProperty("Gls.Filer.Name"))).click();
	Thread.sleep(1000);
	List<WebElement> rows = action.driver.findElements(By.name(locator.getProperty("tablebyname")));
	int numberofrows = rows.size();
	int flag=0;
		for(int i=3;i<=numberofrows+2;i++)
		{
			String str1=action.driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[2]")).getText();
			boolean b= str1.equals(Columnvalue);
			if(b)
			{
				Assert.assertTrue(b);
				flag=1;
				break;
			}
   
		}
		if(flag==0)
		{
			Assert.assertTrue(b);
		}
	
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

