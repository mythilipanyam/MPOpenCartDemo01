package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	// Private By locators
  private By emailId = By.id("input-email");
  private By password = By.id("input-password");
  private By loginBtn = By.xpath("//input[@value='Login']");
  private By returnCstmr = By.xpath("//h2[text()='Returning Customer']");
  private By forgotpwrd=By.xpath("//div[@class='form-group']/a[text()='Forgotten Password']");
  private By rhsLinksList=By.xpath("//div[@class='list-group']/a");
  private By unsuccessfulLoginMsg=By.xpath("//div[@id='account-login']/div[1]");
    
	// constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(driver);
	}

	// public methods
	public String doGetLoginPageUrl() {
		return eleUtil.waitForUrlContains(5,Constants.LOGINPAGE_URL_PARTIAL_VALUE);
    }
	
	public String doGetLoginPageTitle() {
		return eleUtil.waitForTitleIs(5, Constants.LOGINPAGE_TITLE);
    }
	
	public List<String> doVerifyRHSLinks() {
		return eleUtil.getLinksTextList(rhsLinksList);
	}
	
	public boolean doesForgotPasswordLinkExist() {
		return eleUtil.doIsDisplayed(forgotpwrd);
	}
	
	public boolean doesReturnCustomerTextExist() {
		return eleUtil.doIsDisplayed(returnCstmr);
	}
	
	public AccountPage doLogin(String un,String pwrd) {
		System.out.println("========"+un+":"+pwrd+"========");
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwrd);
		eleUtil.doClick(loginBtn);
		return new AccountPage(driver);
	}
	
	public boolean doLogin_Negative(String un,String pwrd) {
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwrd);
		eleUtil.doClick(loginBtn);
		return driver.findElement(unsuccessfulLoginMsg).isDisplayed();
	}
	
	
	
	
}