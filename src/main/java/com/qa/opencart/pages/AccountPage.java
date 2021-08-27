package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//private By locator
	private By logoutBtn=By.xpath("//aside[@id='column-right']/div/a[13]");
    private By accountLinks=By.xpath("//div[@id='content']/h2");
    private By searchBar = By.name("search");
	private By searchBtn = By.xpath("//div[@id=\"search\"]/span/button/i");
    
	
	//constructor
	public AccountPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	
	//public methods
	public boolean doeslogoutBtnPresent() {
		return eleUtil.doIsDisplayed(logoutBtn);
	}
	
	public List<String> verifyAccountLinks() {
		return eleUtil.getLinksTextList(accountLinks);
	}
	
	public String verifyAccountPageTitle() {
		return eleUtil.waitForTitleIs(5,Constants.ACCOUNTPAGE_TITLE);
	}
	
	public String verifyAccountPageUrl() {
		return eleUtil.waitForUrlContains(5,Constants.ACCOUNTPAGE_URL_PARTIAL_VALUE);
	}
	
	public ResultsPage doSearch(String productName) {
		System.out.println("Searching for: "+productName);
		eleUtil.doSendKeys(searchBar, productName);
		eleUtil.doClick(searchBtn);
		return new ResultsPage(driver);
	}
	
	
	
	

}
