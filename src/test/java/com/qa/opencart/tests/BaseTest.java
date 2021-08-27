package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.ResultsPage;
import com.qa.opencart.pages.ShoppingCartPage;
import com.qa.opencart.pages.WishListPage;

public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	Properties prop;
	SoftAssert softAssert;
	HomePage homePage;
	LoginPage loginPage;
	RegistrationPage regPage;
	AccountPage accountPage;
	ResultsPage resultsPage;
	ProductInfoPage productInfoPage;
	WishListPage wishListPage;
	ShoppingCartPage shoppingCartPage;
	
	@BeforeTest
	public void setUp() {
		df=new DriverFactory();
		prop=df.initProperties();
		driver=df.initDriver(prop);
		softAssert=new SoftAssert();
		homePage=new HomePage(driver);
	}
   
	@AfterTest
	public void tearDown() {
	 driver.quit();	
	}
	
}
