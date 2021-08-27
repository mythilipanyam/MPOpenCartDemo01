package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.google.common.eventbus.Subscribe;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//By locators
	private By firstName=By.id("input-firstname");
	private By lastName=By.id("input-lastname");
	private By emailId=By.id("input-email");
	private By telephone=By.id("input-telephone");
	private By password=By.id("input-password");
	private By confirmPassword=By.id("input-confirm");
	private By subscribeYes=By.xpath("(//label[@class='radio-inline'])[1]/input");
	private By subscribeNo=By.xpath("(//label[@class='radio-inline'])[2]/input");
	private By agreeCheckBox=By.name("agree");
	private By continueBtn=By.xpath("//input[@type='submit' and @value='Continue']");
	private By successMessage=By.cssSelector("div#content h1");
	private By logoutLink=By.linkText("Logout");
	private By registerLink=By.linkText("Register");
	
	
	//constructor
	public  RegistrationPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	
	//public methods
	public String getRegPageUrl() {
		return eleUtil.getCurrentPageUrl();
	}
	
	public boolean accountRegistration(String fstName,String lstName,String email,
			String telephoneno, String passwrd,String subscribe) {
		eleUtil.doSendKeys(firstName, fstName);
		eleUtil.doSendKeys(lastName, lstName);
		eleUtil.doSendKeys(emailId, email);
		eleUtil.doSendKeys(telephone,telephoneno);
		eleUtil.doSendKeys(password,passwrd);
		eleUtil.doSendKeys(confirmPassword, passwrd);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueBtn);
		String succssMessage=eleUtil.waitForElementPresence(5,successMessage ).getText();
		System.out.println(succssMessage);
		if(succssMessage.contains(Constants.REGISTER_SUCCESS_MESSAGE)) {
			eleUtil.waitForElementPresence(5,logoutLink).click();
			eleUtil.waitForElementPresence(5,registerLink).click();
			return true;
		}
		else {
			return false;
		}
		
	}

}
