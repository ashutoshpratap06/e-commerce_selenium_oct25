package com.saucedemo.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.common.utitity.BaseClass;

public class CartPage extends BaseClass{	
	
	// ========== Page factory
	// XPath for text_CartPage
	@FindBy(xpath = "//div[@class='subheader']") 
	@CacheLookup
	WebElement text_OnCartPage;
	
	// ====== INitiazation the Page Object
		public CartPage() {
			PageFactory.initElements(driver, this);
		}
	
	
	// validate Cart Page Text so confirm we are on cart page 
	public String validateCartPageText() {
		Reporter.log("text on Cart Page...  " +text_OnCartPage.getText(), true);
		return text_OnCartPage.getText();

	 
	}
	
	// validate same Product Name on Cart Page 
	public String isProductInCart(String productName) throws InterruptedException {
//		Thread.sleep(1000);
		String Xpath_productNameOnCart = "//div[@class='inventory_item_name' and text()='"+ productName + "']";
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xpath_productNameOnCart)));		
		
		// get Product Name 
		String productNameOnCart =  driver.findElement(By.xpath(Xpath_productNameOnCart)).getText();		
		Reporter.log("Product Name on Cart Page :: " +productNameOnCart, true);
		
//		Assert.assertEquals(productNameOnCart, productName, "Product Name not matched ");
		return productNameOnCart;	
		
	}


}
