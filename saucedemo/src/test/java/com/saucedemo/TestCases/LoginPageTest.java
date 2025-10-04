package com.saucedemo.TestCases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.common.utitity.BaseClass;
import com.common.utitity.ExcelUtilities;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.LogoutPage;
import com.saucedemo.pages.ProductPage;



	public class LoginPageTest extends BaseClass  {
		
		
		public LoginPageTest() {
			super();
		}		

//		Logger Log = Logger.getLogger(LoginPageTest.class);
//		PropertyConfigurato.configure("Log4j.properties");
	// =============== Class Ref Variable's
			LoginPage loginPage;
			LogoutPage logoutPage;
			ProductPage productPage;
			ITestResult result;
			
			// String SheetName = "Login";

	
	@BeforeMethod(alwaysRun = true)	
	public void setUp() throws Exception{
		BaseClass.initialization_browser();
		loginPage = new LoginPage();	
		logoutPage = new LogoutPage();
		
//		Thread.sleep(5000);
	}	
	
	
	@Test(priority=1, enabled=true, groups =  { "Smoke",  "Sanity", "Functional", "Regression" } )
	public void loginTest() throws Exception{
// String UserName, String Password
		Reporter.log("**** Verify Login for saucedemo Application ****",true);
		
		// Return productPage object
		productPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password")); 
		
		logoutPage.logout();	
		}
  	
	@Test(priority=2, enabled=false,  dataProvider = "geteducollabLoginTestData", groups = { "Smoke","Functional", "Regression" } )
	public void loginWithExcelTest( String username, String password) throws Exception{
// String UserName, String Password
		Reporter.log("**** Verify login With Excel file Test for saucedemo Application ****",true);
		
		// Return productPage object
		productPage = loginPage.login(username, password); // Return Home Pg object
		
		logoutPage.logout();	
		}
	
	@DataProvider
	  public Object[][] geteducollabLoginTestData() throws Throwable{
	  Object data[][] = ExcelUtilities.getTestData("login");
	  return data;
	  }
	
	
//	@SuppressWarnings("deprecation")
	@AfterMethod(alwaysRun = true)
		public void tearDown(ITestResult result) throws Exception{
		
		if(result.getStatus()==ITestResult.SUCCESS){ 
			Reporter.log("++++++++++++ SUCCESS ++++++++++++++",true);
		}
		if(result.getStatus()==ITestResult.FAILURE){ 
			Reporter.log("++++++++++++ FAILURE ++++++++++++++",true);
			Reporter.log("FAILURE test Case Name "+result.getName(),true);
			
				try {
					//Reporter.log("Failed",true);
					//ActionsClass.takeScreenshotAtEndOfTest(result.getName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		if(result.getStatus()==ITestResult.SKIP){ 
			Reporter.log("++++++++++++ SKIP BLOCKED ++++++++++++++",true);
		}	    
		driver.close();
		Reporter.log("***** Close the Browser *****",true);
		// to close extra Chrome window
//		Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
//		Runtime.getRuntime().exec("taskkill /F /IM chrome.exe /T");
		//Reporter.log(" " );
		}
			
	}
	