package com.saucedemo.TestCases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.common.utitity.BaseClass;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.LogoutPage;
import com.saucedemo.pages.ProductPage;

public class ProductPageTest extends BaseClass {
	
	public String CartPageText = "Your Cart";

	

	// =============== Class Ref Variable's
	ITestResult result;
	LoginPage loginPage;
	LogoutPage logoutPage;
	ProductPage productPage;
	CartPage cartPage;

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		// initialization_browser
		BaseClass.initialization_browser();
		// Create Page Objects
		loginPage = new LoginPage();
		logoutPage = new LogoutPage();
		productPage = new ProductPage();
		cartPage = new CartPage();
		// login and login method Return productPage object
		productPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password")); 
		
	}

	// 1. Test method that uses the DataProvider
	@Test(dataProvider = "productData")
	public void addMultipleProductsToCartTest(String productName) throws Throwable {
	
		Reporter.log("**** Verify addProductToCart ****",true);
		// validate Product Page Title
		productPage.validateProductPageTitle();
		// add Product To Cart 
		productPage.addProductToCart(productName);
		// click On Cart and it's Return cartPage object
		cartPage = productPage.clickOnCart();
		// validate Cart Page Text
		String actualText = cartPage.validateCartPageText();
		Assert.assertTrue(actualText.matches(CartPageText), "Cart Text not matched");
		// validate same Product on Cart
		String productNameOnCart =   cartPage.isProductInCart(productName);
		Assert.assertEquals(productNameOnCart, productName, "Product Name not matched ");
		
//		logoutPage.logout();

	}



//2. DataProvider for multiple product inputs
	@DataProvider(name = "productData")
	public Object[][] getProductData() {
		return new Object[][] { { "Sauce Labs Onesie" },
//								{ "Sauce Labs Fleece Jacket" },	
//								{ "Sauce Labs Backpack" },
//								{ "Sauce Labs Bolt T-Shirt" }
								
			};
			
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws Exception {
		// result = Reporter.getCurrentTestResult();
		// result = Reporter.
		if (result.getStatus() == ITestResult.SUCCESS) {
			System.out.println("++++++++++++ SUCCESS ++++++++++++++");
		}
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("++++++++++++ FAILURE ++++++++++++++");
			System.out.println("FAILURE test Case Name " + result.getName());
			try {
				Reporter.log("abc", true);
				// ActionsClass.takeScreenshotAtEndOfTest(result.getName());
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		if (result.getStatus() == ITestResult.SKIP) {
			System.out.println("++++++++++++ SKIP BLOCKED ++++++++++++++");
		}
		driver.close();
		System.out.println("***** Browser Close *****");
		
	}
}
