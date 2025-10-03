package com.project.utility;

import static org.testng.Assert.assertTrue;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.common.utitity.BaseClass;

public class ReusableMethods extends BaseClass {

	public static long PAGE_LOAD_TIMEOUT = 5;
	public static long IMPLICIT_WAIT = 5;
	public static WebDriverWait wait;

	// ======== Implicit Wait
	public static void implicitlyWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
		Reporter.log("Implicitly Wait Executed");
	}

	// ======== Implicit Wait
	public static void pageLoadTimeoutWait() {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
		Reporter.log("Implicitly Wait Executed");
	}

	// ======== Explicit Wait
	public static void visibilityOfElementLocated(By.ByXPath locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

	}

	public static void presenceOfElementLocated(By.ByXPath locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public static void explicitlyWaitElementToBeClickable(String locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
		// wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(x)));
		driver.findElement(By.xpath(locator)).click(); // Reporter.log
		Reporter.log("Explicitly Wait Executed");
	}

	// ======== Thread.Sleep Wait
	public static void threadsleep() throws Exception {
		Thread.sleep(3000);
		Reporter.log("Thread.Sleep Wait Executed");
	}

	// verifyTitleViaAssertStmt
	public static String verifyTitleViaAssertStmt(String title) {

		Reporter.log("Title of the Page is = " + driver.getTitle(), true);

		assertTrue(driver.getTitle().matches(title), "Title not matched");

		Reporter.log("Title Matched via 'Assert' and it's = " + driver.getTitle(), true);

		return driver.getTitle();
	}

}
