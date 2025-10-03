package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.common.utitity.BaseClass;
import com.project.utility.ReusableMethods;


public class LogoutPage extends BaseClass {

	// ============== Page Factory
	@FindBy(xpath = "//button[normalize-space()='Open Menu']")
	@CacheLookup
	WebElement menu_Icon;

	@FindBy(xpath = "//a[@id='logout_sidebar_link']")
	@CacheLookup
	WebElement Link_Logout;

	// ====== Constructor Initiazation the Page Object
	public LogoutPage() {
		PageFactory.initElements(driver, this);
	}

	// ========== Method
	public void logout() throws Exception {

		ReusableMethods.implicitlyWait();
		menu_Icon.click();
		Reporter.log("Click on menu_Icon ", true);
		Thread.sleep(1000);
		ReusableMethods.implicitlyWait();
		Link_Logout.click();
		Reporter.log("===== Click on Logout =====", true);
		

	}

	
}
