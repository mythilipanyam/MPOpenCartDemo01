package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest {

	@BeforeClass
	public void loginPageSetUp() {
		loginPage = homePage.doClickOnLoginLink("Login");
	}

	@Test(priority = 1)
	public void doGetUrlTest() {
		Assert.assertTrue(loginPage.doGetLoginPageUrl().contains(Constants.LOGINPAGE_URL_PARTIAL_VALUE));
	}

	@Test(priority = 3)
	public void doGetPageTitleTest() {
		Assert.assertEquals(loginPage.doGetLoginPageTitle(), Constants.LOGINPAGE_TITLE);
	}

	@Test(priority = 4)
	public void doVerifyRHSLinksTest() {
		List<String> rhsLinksValue = loginPage.doVerifyRHSLinks();
		Assert.assertEquals(rhsLinksValue, Constants.EXPECTED_RHSLINKS_LIST);
	}

	@Test(priority = 5)
	public void doesForgotPasswordLinkExistTest() {
		Assert.assertTrue(loginPage.doesForgotPasswordLinkExist());
	}

	@Test(priority=6)
	public void doesReturnCustomerTextExistTest() {
		Assert.assertTrue(loginPage.doesReturnCustomerTextExist());
	}
	
	@DataProvider
	public Object[][] getLoginData() {
		return new Object[][] {
			{"hapanyam@gmail.com","abcdef"},
			{"naveenanimation20@gmail.com","abcdef"},
			{"  ","Selenium12345"},
			{"naveenanimation20@gmail.com","  "},
			{" "," "}
	};
	}
	
	@Test(priority=2,dataProvider="getLoginData")
	public void doLogin_NegativeTest(String username,String password) {
		Assert.assertTrue(loginPage.doLogin_Negative(username, password));
	}

	@Test(priority=7)
	public void doLogin_PositiveTest() {
		AccountPage accountPage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accountPage.doeslogoutBtnPresent());
	}
	
	
	
	}
	

