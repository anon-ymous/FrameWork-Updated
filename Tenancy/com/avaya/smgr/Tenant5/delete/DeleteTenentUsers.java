package com.avaya.smgr.Tenant5.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
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


public class DeleteTenentUsers {
	boolean b=false,match=false;;
	UserAction action =null;
	Properties locator=null;
	com.avaya.smgr.Tenant1.setup.TenantSetUp setup= null;
	Properties read=null;
	Properties input=null;
	public WebDriver driver;
@BeforeClass(alwaysRun=true)
public void setup() throws IOException, InterruptedException{
	setup = new com.avaya.smgr.Tenant1.setup.TenantSetUp();
	action = new UserAction();
	locator=new Properties();
   	input=new Properties();
    locator.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\objectRepository\\OR.properties"));
    input.load(new FileInputStream(System.getProperty("user.dir") + "\\Third Party\\testData\\INPUT.properties"));
	action.login(input.getProperty("UserId"),input.getProperty("Password"),action);
}
@Test(description="Verify Delete user from table",priority=1)
public void DeleteUser() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Select Tenant
	List<WebElement> totalRows =action.driver.findElements(By.xpath(locator.getProperty("Tenant.UpmTotal")));
	//System.out.println("Total Rows"+10);

	for (int i=1;i<=totalRows.size();i++){
    	String sValue = null;
    	sValue = action.driver.findElement(By.xpath(locator.getProperty("Tenant.UpmTotal")+"["+i+"]/a")).getText();
    	if(sValue.contains(input.getProperty("UpdateTname"))){
   			WebElement sRowValue=action.driver.findElement(By.xpath(locator.getProperty("Tenant.UpmTotal")+"["+i+"]/a/ins[1]"));
   			sRowValue.click();
   			Thread.sleep(3000);
   			break;
    	}
	}
	
	action.ClickButton(locator.getProperty("Users.Refresh"));
		Thread.sleep(5000);
		action.ClickButton(locator.getProperty("Users.all"));
		Thread.sleep(2000);
	//Click on delete Button
	action.DoubleClickButton(locator.getProperty("Users.Delete"));
	action.WaitForTitle(locator.getProperty("User_Delete_Confirmation"));
	//Click on Delete Button
	action.DoubleClickButton(locator.getProperty("Delete_Cnf"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyDeleteEntry(locator.getProperty("GSNMP.Table"), input.getProperty("User.Loginname"));
	action.VerifyDeleteEntry(locator.getProperty("GSNMP.Table"), input.getProperty("Tenant.Login"));
	Thread.sleep(2000);
}

@Test(description="Verify Deleted user is showing in the soft delete user table",priority=2)

public void verifyDeletedUser1() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on More button
	action.ClickButton(locator.getProperty("Users.More"));
	//Click on Show Deleted user link
	action.ClickLink(locator.getProperty("Show_Deleted_Users"));
	action.WaitForTitle(locator.getProperty("Deleted_Users"));
	//Verify the Deleted user added in table
	action.Verify_Add_Fifthcolumnwithoutfilter(input.getProperty("Tenant.Login"));
	action.Verify_Add_Fifthcolumnwithoutfilter(input.getProperty("User.Loginname"));
	Thread.sleep(2000);
}

@Test(description="Verify Deleted user",priority=3)
public void verifyDeletedUser() throws Exception
{
	action.driver.navigate().refresh();
	//Click on User Management,Manage Users
	action.ClickLink(locator.getProperty("User_Management"));
	action.WaitForTitle(locator.getProperty("User_Management"));
	action.VerifyTitle(locator.getProperty("User_Management"));
	action.ClickLink(locator.getProperty("Manage_Users"));
	action.SwithchFrame("iframe0");
	//Click on More button
	action.ClickButton(locator.getProperty("Users.More"));
	//Click on Show Deleted user link
	action.ClickLink(locator.getProperty("Show_Deleted_Users"));
	action.WaitForTitle(locator.getProperty("Deleted_Users"));
	Thread.sleep(3000);
	//Verify the Deleted user added in table
	action.DoubleClickButton(locator.getProperty("Users.all"));
	Thread.sleep(300);
	action.WaitToClick(locator.getProperty("Delete_Cnf"));
	//Click on delete button
	action.DoubleClickButton(locator.getProperty("Delete_Cnf"));
	action.WaitForTitle(locator.getProperty("User_Delete_Confirmation"));	
	action.DoubleClickButton(locator.getProperty("Delete_Cnf"));
	Thread.sleep(2000);

	action.WaitForTitle(locator.getProperty("User_Management"));
	Thread.sleep(2000);
}
public void Verify_Add_Fifthcolumnwithoutfilter(String Columnvalue) throws Exception
{
  	List<WebElement> rows = driver.findElements(By.name(locator.getProperty("tablebyname")));
	int numberofrows = rows.size();
	int flag=0;
		for(int i=2;i<=numberofrows+1;i++)
		{
			String str1=driver.findElement(By.xpath(".//*[@id='table_1_core_table_content']/tbody/tr["+i+"]/td[5]")).getText();
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
public void logout() throws IOException, InterruptedException{

	action.logout(action);

}

}
