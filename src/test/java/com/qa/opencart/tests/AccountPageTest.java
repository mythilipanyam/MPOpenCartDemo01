package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountPageTest extends BaseTest {
	
	@BeforeClass
	public void accountPageSetUp() {
		loginPage = homePage.doClickOnLoginLink("Login");
		accountPage=loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		//accountPage=loginPage.doLogin(System.getProperty("username"),System.getProperty("password"));
	}
	
	@Test(priority=1)
	public void doeslogoutBtnPresentTest() {
		Assert.assertTrue(accountPage.doeslogoutBtnPresent());
	}
	
	@Test(priority=2)
	public void verifyAccountLinksTest() {
		List<String>actPageLinksValueList=accountPage.verifyAccountLinks();
		Assert.assertEquals(actPageLinksValueList,Constants.EXPECTED_ACCOUNTPAGE_LINKS_LIST);
	}
	
	@Test(priority=3)
	public void verifyAccountPageTitleTest() {
		Assert.assertEquals(accountPage.verifyAccountPageTitle(),Constants.ACCOUNTPAGE_TITLE);
	}
	
	@Test(priority=4)
	public void verifyAccountPageUrlTest() {
		Assert.assertTrue(accountPage.verifyAccountPageUrl().contains(Constants.ACCOUNTPAGE_URL_PARTIAL_VALUE));
	}
	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][]{	{"Macbook"}, {"Apple"},{"Samsung"} };
	}
	
	@Test(priority=5,dataProvider="getSearchData")
	public void doSearchTest(String searchTerm) {
		resultsPage=accountPage.doSearch(searchTerm);
		String resultsHeader=resultsPage.verifyResultsPageHeader();
		System.out.println("results page header is: "+resultsHeader);
		Assert.assertTrue(resultsHeader.contains(searchTerm));
	}

}
