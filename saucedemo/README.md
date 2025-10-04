**How to Run Selenium Automation Tests**
**1. Setup Prerequisites**

1. Download and install Eclipse IDE – Version: 2022-09 (4.25.0)
2. Verify Maven is available in Eclipse (Help → About Eclipse IDE → Installation Details).
3. Set JAVA Path in your machine (JAVA_HOME should point to JDK 17).
4. Configure JRE in Eclipse:
Go to Window → Preferences → Java → Installed JREs → Execution Environments
Select JavaSE-17.
5. Install TestNG Plugin in Eclipse:
Help → Eclipse Marketplace → Search "TestNG" → Install.

** 2. Run Selenium Tests via Maven (Command Line) **
1. I have configured pom.xml to use the suite file at: **runner-testng-suites/testng.xml**

** Run command: ** mvn clean test

* This will automatically pick **runner-testng-suites/testng.xml** and execute all defined TestNG classes


** 3. Import Project in Eclipse**
1. Clone this repository using Git:
git clone <repo-url>
2. Open Eclipse → File → Import → Maven → Existing Maven Projects → Next → Browse → Finish.
** 3. Run Selenium Tests**
1. Navigate to:
src/test/java/com/TestCases
2. Run the following TestNG classes:
* LoginPageTest
* ProductPageTest
(Run via Right-click → Run As → TestNG Test)



** Manual Testing Task**

1. Manual testing scenarios and cases are documented in the following Excel file: File: **My_Swag_Labs_Test_Cases.xlsx**

**Contents of the Excel File:**
* Workbook 1: Login_Page
* Workbook 2: Product_Page
* Workbook 3: Cart_Page

Each workbook contains detailed Test Scenarios and Test Cases for respective modules.