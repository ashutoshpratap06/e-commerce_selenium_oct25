package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.common.utitity.BaseClass;
import com.project.utility.ReusableMethods;

public class LoginPage extends BaseClass {

	// ========== String
	public static String un;
	public static String pw;

	// ============== Page Factory
	@FindBy(id = "user-name") // (xpath = "//input[@id='user-name']")
	@CacheLookup
	WebElement txt_User_Name;

	@FindBy(id = "password") // (xpath = "//input[@id='password']")
	@CacheLookup
	WebElement txt_Password;

	@FindBy(xpath = "//input[@id='login-button']") // (xpath = "//input[@id='login-button']")
	@CacheLookup
	WebElement btn_submit;

	public String PgTitle = "Swag Labs";

	// ====== Initiazation the Page Object
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public ProductPage login(String un, String pw) throws InterruptedException {

		// Cleaning previously filled Password
		txt_User_Name.clear();
		// Entering txt_User_Name
		if (System.getenv("USERNAME1") == null) {
			Reporter.log("Enter user name via file : " + un, true);
			txt_User_Name.sendKeys(un);
		} else {
			//Reporter.log("Enter user name via environment varible : " + System.getenv("USERNAME"), true);
			txt_User_Name.sendKeys(System.getenv("USERNAME1"));
			Reporter.log("Enter user name via environment varible ", true);
		}
		// Cleaning previously filled Password
		txt_Password.clear();
		// Entering Password
		if (System.getenv("PASSWORD") == null) {
			Reporter.log("Enter password via file : " , true);
			txt_Password.sendKeys(pw);
		} else {
			// Reporter.log("Enter user password via environment varible : "
			// +System.getenv("PASSWORD"), true);
			txt_Password.sendKeys(System.getenv("PASSWORD"));
			Reporter.log("Enter password via environment varible ", true);
		}

		ReusableMethods.pageLoadTimeoutWait();
		btn_submit.click();
		ReusableMethods.pageLoadTimeoutWait();
		Reporter.log("Click on Submit Button", true);
		Thread.sleep(1000);
		return new ProductPage();

	} // Login Method

} // Main Class
