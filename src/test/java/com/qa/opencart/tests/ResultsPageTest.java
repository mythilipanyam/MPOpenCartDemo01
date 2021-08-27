package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ResultsPageTest extends BaseTest {

	@BeforeClass
	public void resultsPageSetUp() {
		loginPage = homePage.doClickOnLoginLink("Login");
		accountPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());

	}
	
	@DataProvider
	public Object[][] getSearchDataAndProductSelectData() {
		return new Object[][]{	{"Macbook","MacBook Pro"}, 
			                  {"Apple","Apple Cinema 30\""},
			               {"Samsung","Samsung Galaxy Tab 10.1"} };
	}
	
	@Test(priority=1,dataProvider="getSearchDataAndProductSelectData")
	public void doSearchAndSelectProduct(String searchTerm,String selectedProduct) {
		resultsPage=accountPage.doSearch(searchTerm);
		String resultsHeader=resultsPage.verifyResultsPageHeader();
		System.out.println("results page header is: "+resultsHeader);
		Assert.assertTrue(resultsHeader.contains(searchTerm));
		productInfoPage=resultsPage.selectProduct(selectedProduct);
		Assert.assertTrue(productInfoPage.isSelectedProductDisplayed());
	}
  
   @Test(priority=2)
   public void getallpricesTest() {
	  	resultsPage=accountPage.doSearch("macbook");
	  	Double productPrice=resultsPage.getAllProductPrices();
	  	Assert.assertTrue(productPrice.toString().contains(".0"));
	}
   
   @Test(priority=3)
  public void sortingByPriceTest() {
	   resultsPage=accountPage.doSearch("macbook");
	  Assert.assertTrue(resultsPage. sortingByPriceFromHighToLow("Price (High > Low)"));
   }
   
  @Test(priority=4)
  public void  verifySearchResultsTest() {
	  resultsPage=accountPage.doSearch("MacBook");
	  Assert.assertTrue(resultsPage.verifySearchResults("MacBook"));
   }
   
 }
