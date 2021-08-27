package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoPageSetUp() {
		loginPage = homePage.doClickOnLoginLink("Login");
		accountPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
    
	}
	
	@Test(priority=1)
	public void isSelectedProductDisplayedTest() {
		resultsPage=accountPage.doSearch("Apple");
		productInfoPage=resultsPage.selectProduct("Apple Cinema 30\"");
		Assert.assertTrue(productInfoPage.isSelectedProductDisplayed());
	}
	
	@Test(priority=2)
	public void getProductHeaderTest() {
		resultsPage=accountPage.doSearch("iMac");
		productInfoPage=resultsPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductHeader(),"iMac");
	}
	
	@Test(priority=3)
	public void  getImagesCountTest() {
		resultsPage=accountPage.doSearch("MacBook");
		productInfoPage=resultsPage.selectProduct("MacBook Pro");
		Assert.assertTrue(productInfoPage.getImagesCount()>1) ;
	}
	
	@Test(priority=4)
	public void productInfoTest() {
		resultsPage=accountPage.doSearch("MacBook");
		productInfoPage=resultsPage.selectProduct("MacBook Pro");
		Map<String,String>actProductInfoMap=productInfoPage.getProductInfo();
		softAssert.assertEquals(actProductInfoMap.get("Name"),"MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Brand"),"Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"),"Product 18");
		softAssert.assertEquals(actProductInfoMap.get("price"),"$2,000.00");
		softAssert.assertAll();
	}
	
	@Test(priority=5)
	public void provideReviewTest() {
		resultsPage=accountPage.doSearch("MacBook");
		productInfoPage=resultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.provideReview(),"Thank you for your review. It has been submitted to the webmaster for approval.");
		
	}
	
	@Test(priority=6)
	public void addToCartTest() {
		resultsPage=accountPage.doSearch("MacBook");
		productInfoPage=resultsPage.selectProduct("MacBook Pro");
		Assert.assertTrue(productInfoPage.addToCart().contains("Success: You have added MacBook Pro to your shopping cart!"));
		
	}
	
	@Test(priority=7)
	public void addToWishList() {
		resultsPage=accountPage.doSearch("MacBook");
		productInfoPage=resultsPage.selectProduct("MacBook Pro");
		Assert.assertTrue(productInfoPage.addToWishList().contains("Success: You have added MacBook Pro to your wish list!"));
		
	}
	
	@Test(priority=8)
	public void doClickOnWishListLinkTest(){
		resultsPage=accountPage.doSearch("MacBook");
		productInfoPage=resultsPage.selectProduct("MacBook Pro");
		wishListPage=productInfoPage.doClickOnWishListLink();
		Assert.assertEquals(wishListPage.getWishListPageHeader(),Constants.WISHLISTPAGE_HEADER_TEXT);
	}
	
	@Test(priority=9)
	public void doClickOnShoppingCartLinkTest(){
		resultsPage=accountPage.doSearch("MacBook");
		productInfoPage=resultsPage.selectProduct("MacBook Pro");
		shoppingCartPage=productInfoPage.doClickShoppingCartLink();
		Assert.assertTrue(shoppingCartPage.getCartPageHeader().contains(Constants.CARTPAGE_PARTIAL_HEADER_TEXT));
	}
	

}
