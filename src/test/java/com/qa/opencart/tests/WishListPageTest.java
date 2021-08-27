package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class WishListPageTest extends BaseTest {
	
	@BeforeClass
	public void WishListPageSetUp() {
		loginPage = homePage.doClickOnLoginLink("Login");
		accountPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
    }
	@Test(priority=1,enabled=false)
	public void verifyWishListPageUrlTest(){
		resultsPage=accountPage.doSearch("MacBook");
		productInfoPage=resultsPage.selectProduct("MacBook Pro");
		wishListPage=productInfoPage.doClickOnWishListLink();
		Assert.assertTrue(wishListPage.getWishListPageUrl().contains("/wishlist"));
	}
	
	@Test(priority=2)
	public void verifyProductNameInWishListTest(){
		resultsPage=accountPage.doSearch("MacBook");
		productInfoPage=resultsPage.selectProduct("MacBook Pro");
		productInfoPage.addToWishList();
		wishListPage=productInfoPage.doClickOnWishListLink();
		Assert.assertTrue(wishListPage.getWishListPageUrl().contains("/wishlist"));
		Assert.assertTrue(wishListPage.verifyProductNameInWishList());	
		Assert.assertTrue(wishListPage.removeProductFromWishList());
	}
	
	@Test(priority=3,enabled=false)
	public void verifyRemoveProductFromWishListTest(){
		resultsPage=accountPage.doSearch("MacBook");
		productInfoPage=resultsPage.selectProduct("MacBook Pro");
		wishListPage=productInfoPage.doClickOnWishListLink();
		Assert.assertTrue(wishListPage.removeProductFromWishList());
	}
	
	
	
		
	

}
