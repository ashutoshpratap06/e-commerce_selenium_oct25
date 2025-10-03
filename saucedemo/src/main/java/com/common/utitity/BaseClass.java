package com.common.utitity;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.project.utility.ReusableMethods;



public class BaseClass {

	public static WebDriver driver; // WebDriver Object
	public static Properties prop;
	public static JavascriptExecutor js ;
	public static WebDriverWait wait;
	public static EnvironmentSelection sel_env = new EnvironmentSelection();
	
	
	public static void initialization_browser() throws Exception {

		Reporter.log("====== To initialization_browser ===== ", true);
		// Environment Selection
		prop = sel_env.envAndFile();

		String my_browser = prop.getProperty("browser");

		Reporter.log("====== browser name ===== " + my_browser, true);

		// If browser is Firefox, then do this
		if (my_browser.equalsIgnoreCase("firefox")) {
			// setting up the firefox
			
			//driver  = WebDriverManager.firefoxdriver().create();
			//driver  = new FirefoxDriver();
			
			Reporter.log("======  firefox browser open ===== ", true);

			// firefoxOptions
			FirefoxOptions options = new FirefoxOptions();
	        options.addArguments("--start-maximized");
//			options.addArguments("--remote-allow-origins=*");

			// Use headless mode
			// options.addArguments("headless-new");
			// options.addArguments("--disable-notifications");			  

	        // Pass options into FirefoxDriver
	        driver = new FirefoxDriver(options);

//			driver = (WebDriver) new FirefoxOptions(options);

			// If browser is Chrome, then do this
		} else if (my_browser.equalsIgnoreCase("chrome")) {
			// setting up the ChromeDriver
			driver = new ChromeDriver();
			
			Reporter.log("======  Chrome browser open ===== ", true);

			// ChromeOptions to disable the browser notification
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--guest");
			
//			options.addArguments("--disable-popup-blocking");
//			options.addArguments("--disable-notifications");
//			options.addArguments("--disable-infobars");
			// Disable password manager
//			Map<String, Object> prefs = new HashMap<>();
//			prefs.put("credentials_enable_service", false);
//			prefs.put("profile.password_manager_enabled", false);
//			options.setExperimentalOption("prefs", prefs);	
//			options.addArguments("--remote-allow-origins=*");

			// Use headless mode
			//options.addArguments("headless");
			//options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);

			

		}
		// Delete All browser Cookies
		driver.manage().deleteAllCookies();
		//Maximizing the browser window
		driver.manage().window().maximize();
		
		// Creating the JavascriptExecutor interface object by Type casting		
        js = (JavascriptExecutor)driver;	

		// =================>>>> open URL
		driver.navigate().to(prop.getProperty("url")); // url
		ReusableMethods.pageLoadTimeoutWait();
		Reporter.log("====== Application URL Open ===== " + prop.getProperty("url"), true);
		//Reporter.log("====== Application Open ===== ", true);
		
	}

}
