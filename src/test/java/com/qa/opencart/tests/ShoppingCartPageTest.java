package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ShoppingCartPageTest extends BaseTest {
	
	@BeforeClass
	public void cartPageSetUp() {
		loginPage = homePage.doClickOnLoginLink("Login");
		accountPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
    }
	@Test(priority=1)
	public void verifyShoppingCartTest(){
		resultsPage=accountPage.doSearch("MacBook");
		productInfoPage=resultsPage.selectProduct("MacBook Pro");
		productInfoPage.addToCart();
		shoppingCartPage=productInfoPage.doClickShoppingCartLink();
		shoppingCartPage.verifyPriceCalculation();

}}
