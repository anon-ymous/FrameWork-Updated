package com.avaya.smgr.gls.roles;
/*
 * Test Case related to Roles UI page
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.UserAction;


public class Createrole{
	boolean b=true,match=false;;
	UserAction action =null;
	Properties locator=null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
	private static final String IDEN_Roles_Grid = "//*[@class='jstree-closed']";
	private static final String IDEN_Roles_Sysadmin = ".//*[@id='node_System_white-space_Administrator']/ins";

@BeforeClass(alwaysRun=true)
public void BeforeClasssetup() throws IOException, InterruptedException{
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
	
 }
@BeforeMethod(alwaysRun=true)
	public void setup() throws IOException, InterruptedException{
		
   }

@Test(description="Verification of buttons states are showing correctly")
public void GLSRolesBtns() throws Exception{
	action.RefreshPage();
	
	//Navigate to SMGR,Groups & Roles,Roles
	action.ClickLink(locator.getProperty("Groups_Roles"));
	action.ClickLink(locator.getProperty("Roles"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("ucm_roles_iframe");
	//Verify that the title name is showing correctly
	action.VerifyTitle(locator.getProperty("Roles"));
	//Verify the button states correctly(Enabled for New and Delete)
	action.VerifyEnableButton(locator.getProperty("Roles.New"));
	action.VerifyEnableButton(locator.getProperty("Roles.Delete"));
}
@Test(description="Verification of error message when Role name is empty")
public void GLSSysRolenameEmpty() throws Exception{
	action.RefreshPage();
	String RoleName=input.getProperty("UpdatedName");
	//Navigate to SMGR,Groups & Roles,Roles
	action.ClickLink(locator.getProperty("Groups_Roles"));
	action.ClickLink(locator.getProperty("Roles"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("ucm_roles_iframe");
	//Select System administrators Role
	action.ClickButton(locator.getProperty("System_Administrator"));
	//Click on the New button
	action.ClickButton(locator.getProperty("Roles.New"));
	action.WaitForObj(locator.getProperty("Roles.Name"));
	//Enter role name and Description
	//action.entertext(locator.getProperty("Roles.Name"), RoleName);
	action.entertext(locator.getProperty("Roles.Desc"), RoleName);
	//Click on the commit and Continue button
	action.ClickButton(locator.getProperty("Roles.CommitContinue"));
	Thread.sleep(2000);
	//Verification of error message when group name is empty
	action.VerifyStringValue("The role name entered is empty");
	Thread.sleep(1000);
}
@Test(description="Verification of error message when Role Description is empty")
public void GLSSysRoleDescEmpty() throws Exception{
	action.RefreshPage();
	String RoleName=input.getProperty("UpdatedName");
	//Navigate to SMGR,Groups & Roles,Roles
	action.ClickLink(locator.getProperty("Groups_Roles"));
	action.ClickLink(locator.getProperty("Roles"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("ucm_roles_iframe");
	//Select System administrators Role
	action.ClickButton(locator.getProperty("System_Administrator"));
	//Click on the New button
	action.ClickButton(locator.getProperty("Roles.New"));
	action.WaitForObj(locator.getProperty("Roles.Name"));
	//Enter role name. Make sure Role Description is empty
	action.entertext(locator.getProperty("Roles.Name"), RoleName);
	//Click on the commit and Continue button
	action.ClickButton(locator.getProperty("Roles.CommitContinue"));
	Thread.sleep(2000);
	//Verification of error message when group name is empty
	action.VerifyStringValue("The role description entered is empty.");
	Thread.sleep(1000);
}

@Test(description="Verify that the Sysadmin Role  is created successfully",priority=1,groups={"Sanity"})
public void GLSASysRoleadded() throws Exception{
	action.RefreshPage();
	int flag=0;
	String RoleName=input.getProperty("UpdatedName");
	//Navigate to SMGR,Groups & Roles,Roles
	action.ClickLink(locator.getProperty("Groups_Roles"));
	action.ClickLink(locator.getProperty("Roles"));
	action.SwithchFrame("iframe0");
	action.driver.switchTo().frame("ucm_roles_iframe");
	//Select System administrators Role
	action.ClickButton(locator.getProperty("System_Administrator"));
	//Click on the New button
	action.ClickButton(locator.getProperty("Roles.New"));
	action.WaitForObj(locator.getProperty("Roles.Name"));
	//Enter role name and Description
	action.entertext(locator.getProperty("Roles.Name"), RoleName);
	action.entertext(locator.getProperty("Roles.Desc"), RoleName);
	//Click on the commit and Continue button
	action.ClickButton(locator.getProperty("Roles.CommitContinue"));
	//Click on the add mappings
	action.ClickButton(locator.getProperty("Roles.Addmap"));
	action.WaitForObj(locator.getProperty("Roles.ElementType"));
	//Select Element as SMGR Core Services
	action.SelectFromdropDown(locator.getProperty("Roles.ElementType"), "users");
	action.SelectFromdropDown(locator.getProperty("Roles.Instance"), "All");
	action.ClickButton(locator.getProperty("Roles.Next"));
	action.WaitForObj(locator.getProperty("Roles.Template"));
	//Select Template for permission
	action.SelectFromdropDown(locator.getProperty("Roles.Template"), "System Administrator");
	//Click on the commit button
	action.ClickButton(locator.getProperty("Roles.commit"));
	action.WaitForObj(locator.getProperty("Roles.commit1"));
	//
	action.ClickButton(locator.getProperty("Roles.Addmap"));
	action.WaitForObj(locator.getProperty("Roles.ElementType"));
	//Select Element as SMGR Core Services
	action.SelectFromdropDown(locator.getProperty("Roles.ElementType"), "SMGR Core Services");
	action.SelectFromdropDown(locator.getProperty("Roles.Instance"), "All");
	action.ClickButton(locator.getProperty("Roles.Next"));
	action.WaitForObj(locator.getProperty("Roles.Template"));
	//Select Template for permission
	action.SelectFromdropDown(locator.getProperty("Roles.Template"), "System Administrator");
	//Click on the commit button
	action.ClickButton(locator.getProperty("Roles.commit"));
	action.WaitForObj(locator.getProperty("Roles.commit1"));
	action.ClickButton(locator.getProperty("Roles.commit1"));
	action.WaitForTitle(locator.getProperty("Roles"));
	//action.ClickButton(locator.getProperty("Roles.SysIcon"));
	//Click on the New button
		List<WebElement> totalRows1 = action.driver.findElements(By.xpath(IDEN_Roles_Grid));
		for (WebElement webElement : totalRows1) { 
			b=webElement.getText().contains(RoleName);
			if(b){
				Assert.assertTrue(b);
				flag=1;
				break;
			}
		}
		if(flag==0){
			Assert.assertTrue(b);
			
		}
		Thread.sleep(1000);
}





	@AfterMethod(alwaysRun=true)
	public void Screenshots(ITestResult result) throws IOException, InterruptedException{
	  
	action.Screenshot(result, action);
	}
	@AfterClass(alwaysRun=true)
	public void close() throws IOException, InterruptedException{
		action.logout(action);
	
	}

}

