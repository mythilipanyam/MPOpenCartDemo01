package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ErrorMessages;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Home Page Features..")
@Story("US 101 & US 102: Verify the links and title of homepage")
public class HomePageTest extends BaseTest {
	
	
	@Description("getHomePageTitleTest")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void getHomePageTitleTest() {
		String title=homePage.getHomePageTitle();
		System.out.println("homepage title is :"+title);
		Assert.assertEquals(title,Constants.HOMEPAGE_TITLE,ErrorMessages.TITLE_MISMATCH_MESSAGE);
	}
	
	@Description("getHomePageHeaderTest")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void getPageHeaderTextTest() {
		String header=homePage.getPageHeaderText();
		System.out.println("homepage header is :"+header);
		Assert.assertEquals(header,Constants.HOMEPAGE_HEADER_TEXT,ErrorMessages.HEADERTEXT_MISMATCH_MESSAGE);
	}
	
	@Description("doesSearchBarExistTest() ")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void doesSearchBarExistTest() {
		Assert.assertTrue(homePage.doesSearchBarExist(), ErrorMessages.ITEM_NOTDISPLAYED_MESSAGE);
	}
	
	@Description("doesSearchButtonExistTest() ")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void doesSearchButtonExistTest() {
		Assert.assertTrue(homePage.doesSearchButtonExist(), ErrorMessages.ITEM_NOTDISPLAYED_MESSAGE);
	}
	
	@Description("doesCurrencyButtonExistTest() ")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=5)
	public void doesCurrencyButtonExistTest() {
		Assert.assertTrue(homePage.doesCurrencyButtonExist(), ErrorMessages.ITEM_NOTDISPLAYED_MESSAGE);
	}
	
	@Description("verifyTopLinksTest() ")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=6,enabled=false)
	public void verifyTopLinksTest() {
		List<String>topLinksList=homePage.verifyTopLinks();
		System.out.println(topLinksList.size());
		Assert.assertEquals(topLinksList.get(0),Constants.TOPLINK1_TEXT,ErrorMessages.LINK_NOTPRESENT_MESSAGE);
		Assert.assertEquals(topLinksList.get(1),Constants.TOPLINK2_TEXT,ErrorMessages.LINK_NOTPRESENT_MESSAGE);
		Assert.assertEquals(topLinksList.get(3),Constants.TOPLINK3_TEXT,ErrorMessages.LINK_NOTPRESENT_MESSAGE);
		Assert.assertEquals(topLinksList.get(4),Constants.TOPLINK4_TEXT,ErrorMessages.LINK_NOTPRESENT_MESSAGE);
		Assert.assertEquals(topLinksList.get(5),Constants.TOPLINK5_TEXT,ErrorMessages.LINK_NOTPRESENT_MESSAGE);
	}
	
	@Description("verifyNavBarLinksTest() ")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=7)
	public void verifyNavBarLinksTest() {
		List<String>navBarLinksList=homePage.verifyNavBarLinks();
		System.out.println(navBarLinksList.size());
		Assert.assertEquals(navBarLinksList,Constants.EXPECTED_NAVBARLINKS_LIST,ErrorMessages.LINK_NOTPRESENT_MESSAGE);
		
	}
	
	@Description("click on login link...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=8)
	public void doClickOnLoginLinkTest() {
		loginPage=homePage.doClickOnLoginLink("Login");
		System.out.println("Login Page url is: "+loginPage.doGetLoginPageUrl());
		Assert.assertTrue(loginPage.doGetLoginPageUrl().contains(Constants.LOGINPAGE_URL_PARTIAL_VALUE),ErrorMessages.URL_MISMATCH_MESSAGE);
	}
	
	@Description("click on register link...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=9)
	public void doClickONRegisterLinkTest() {
		regPage=homePage.doClickOnRegistrationLink("Register");
		Assert.assertTrue(regPage.getRegPageUrl().contains("/register"));
	}
	
	
	

}
