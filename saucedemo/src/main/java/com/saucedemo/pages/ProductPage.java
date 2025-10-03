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
import com.project.utility.ReusableMethods;

public class ProductPage extends BaseClass {

	public String ProductPageTitle = "Swag Labs";

	// ========== Page factory
	// XPath for all product titles
	@FindBy(css = ".inventory_item_name")
	@CacheLookup
	WebElement ALL_PRODUCT_TITLES;

	// XPath for CartBtn
	@FindBy(xpath = "//*[name()='path' and contains(@fill,'currentCol')]")
	@CacheLookup
	WebElement CartBtn;

	// XPath for "Next Page" button, for pagination
//	@FindBy(id =  "next-page-button") 
//	@CacheLookup
//	WebElement NEXT_PAGE_BUTTON;
	private static final By NEXT_PAGE_BUTTON = By.id("next-page-button");
//	private static final By CartBtn = By.xpath("//*[name()='path' and contains(@fill,'currentCol')]");	
//	private static final By ALL_PRODUCT_TITLES = By.cssSelector(".inventory_item_name");

	// %s will be replaced by the product name at runtime.
	private static final String ADD_TO_CART_BUTTON_XPATH_TEMPLATE = "//div[@class='inventory_item_name' and text()='%s']"
			+ "/ancestor::div[contains(@class, 'inventory_item')]//button[text()='ADD TO CART']";

// ====== Initiazation the Page Object
	public ProductPage() {
		PageFactory.initElements(driver, this);
	}

	// validateProductPageTitle
	public String validateProductPageTitle() {
		return ReusableMethods.verifyTitleViaAssertStmt(ProductPageTitle);
	}

	/**
	 * Helper method to generate the dynamic By locator for a specific product's
	 * 'Add to cart' button.
	 */
	private By getAddToCartButtonLocator(String productName) {

		/**
		 * String ADD_TO_CART_BUTTON_XPATH_TEMPLATE =
		 * "//div[@class='inventory_item_name' and text()='" + productName +
		 * "']//ancestor::div[contains(@class, 'inventory_item')]//button[text()='ADD TO CART']"
		 * ; System.out.println(ADD_TO_CART_BUTTON_XPATH_TEMPLATE); By xpath =
		 * By.xpath(ADD_TO_CART_BUTTON_XPATH_TEMPLATE);
		 */

		// Via String method
		String ADD_TO_CART = String.format(ADD_TO_CART_BUTTON_XPATH_TEMPLATE, productName);
//      System.out.println("Xpath :: " +ADD_TO_CART);
		Reporter.log("Xpath :: " + ADD_TO_CART, true);
		By xpath = By.xpath(ADD_TO_CART);
		return xpath;
	}

	/**
	 * Helper method to check if the product is present on the current page.
	 * productName The name of the product. return true if the product is found,
	 * false otherwise.
	 */
	private boolean CheckProductIsVisible(String productName) {
		try {
			// Wait for the product element (Add to Cart button) to be present
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated(getAddToCartButtonLocator(productName)));
			return driver.findElement(getAddToCartButtonLocator(productName)).isDisplayed();
		} catch (Exception e) {
			// If the element is not found
			return false;
		}
	}

	/**
	 * Selects a product and adds it to the cart. Loop structure to handle future
	 * pagination. productName The name of the product to add
	 */
	public void addProductToCart(String productName) throws Throwable {

		// --- PAGINATION HANDLING
		while (true) {

			// 1. Check if the product is visible on the current page
			System.out.println(CheckProductIsVisible(productName));
			if (CheckProductIsVisible(productName)) {

				// Product is found! Add it to the cart.
				By buttonLocator = getAddToCartButtonLocator(productName);
				WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(buttonLocator));
				addToCartButton.click();
//				System.out.println("SUCCESS: Added product '" + productName + "' to the cart.");
				Reporter.log("SUCCESS: Added product '" + productName + "' to the cart.", true);
				break; // Exit the loop as the product is added.
			}

			// 2. If product not found, check for a 'Next Page' button
			if (driver.findElements(NEXT_PAGE_BUTTON).isEmpty()) {
//				System.out.println("FAILURE: Product '" + productName + "' not found on any page.");
				Reporter.log("FAILURE: Product '" + productName + "' not found on any page.", true);
				break; // No more pages to check, exit the loop.
			} else {
				// Click 'Next Page' button and continue the loop
				driver.findElement(NEXT_PAGE_BUTTON).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated((By) ALL_PRODUCT_TITLES));
//				System.out.println("Moving to the next page...");
				Reporter.log("Moving to the next page...", true);
			}
		} // --- PAGINATION END ---

	}

	public CartPage clickOnCart() throws InterruptedException {
		CartBtn.click();
		Reporter.log("Click on Cart :: ", true);
		
		Thread.sleep(1000);
		return new CartPage();
	}

}
