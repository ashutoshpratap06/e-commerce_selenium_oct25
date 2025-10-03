package com.common.utitity;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.testng.Reporter;


public class EnvironmentSelection {

	public static Properties prop;

	public Properties envAndFile() {

		prop = new Properties();
		String environment;
		if (System.getenv("ENV") != null) {
			environment = System.getenv("ENV").toLowerCase();
		} else {
			Reporter.log("Testing environment varible not set and default RUN on QA", true);
			environment = "qa";
		}

		try {			
			Reporter.log("Testing environment is : " + environment, true);
			File path = new File(System.getProperty("user.dir") + "/environment_files/" 	+ environment + ".properties");
			
			if (path.exists()) {
				FileInputStream EnvFile = new FileInputStream(path);
				prop.load(EnvFile);
			} else {
				Reporter.log(" Testing environment file not found ", true);
				
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		 return prop;
		

	}

	public static String envAndFileWithKey(String key) {

		prop = new Properties();
		String environment;
		if (System.getenv("ENV") != null) {
			environment = System.getenv("ENV").toLowerCase();
		} else {
			// System.out.println("Testing environment varible not set in Environment file
			// and default RUN on QA");
			Reporter.log("Testing environment varible not set and default RUN on QA", true);
			environment = "qa";

		}

		try {
			// System.out.println("Testing environment is :" +environment);
			Reporter.log("Testing environment is : " + environment, true);
			File path = new File(System.getProperty("user.dir") + "/environment_files/" 	+ environment + ".properties");
			if (path.exists()) {
				FileInputStream EnvFile = new FileInputStream(path);
				prop.load(EnvFile);
			} else {
				Reporter.log("Testing environment file not found ", true);
				// System.out.println(" Testing environment file not found ");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		

		return prop.getProperty(key);

	}

}
