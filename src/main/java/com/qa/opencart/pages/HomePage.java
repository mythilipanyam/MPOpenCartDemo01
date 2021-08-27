package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class HomePage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// private By locators
	private By searchBar = By.name("search");
	private By searchBtn = By.xpath("//div[@id=\"search\"]/span/button/i");
	private By topLinks = By.xpath("//div[@id=\"top-links\"]/ul/li //span");
	private By logo = By.xpath("//h1/a[text()='Your Store']");
	private By currencyBtn = By.xpath("//button/span[text()='Currency']");
	private By navBarLinks = By.cssSelector("ul.nav.navbar-nav>li>a");
	private By dropdownLinks=By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li/a");
    private By myAccount=By.xpath("(//div[@id='top-links']/ul/li/a)[2]");
    
    
    
	// constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(driver);
	}

	// public page methods: should define behaviour/functionality of the page, no assertions
	
	@Step("getHomePageTitle")
	public String getHomePageTitle() {
		return eleUtil.waitForTitleIs(5, Constants.HOMEPAGE_TITLE);
	}
	
	@Step("getHomePageHeader")
	public String getPageHeaderText() {
		return eleUtil.doGetText(logo);
	}
	
	@Step("doesSearchBarExist")
	public boolean doesSearchBarExist() {
		return eleUtil.doIsDisplayed(searchBar);
	}
	
	@Step("doesSearchButtonExist")
	public boolean doesSearchButtonExist() {
		return eleUtil.doIsDisplayed(searchBtn);
	}
	
	@Step("doesCurrencyButtonExist")
	public boolean doesCurrencyButtonExist() {
		return eleUtil.doIsDisplayed(currencyBtn);
	}
	
    @Step("verifyTopLinks")
	public List<String> verifyTopLinks() {
		return eleUtil.getLinksTextList(topLinks);

	}
    
    @Step("verifyNavBarLinks")
	public List<String> verifyNavBarLinks() {
		return eleUtil.getLinksTextList(navBarLinks);

	}
	@Step("doClickOnLoginLink")
	public LoginPage doClickOnLoginLink(String value) {
		eleUtil.doClick(myAccount);
		eleUtil.clickOnElement(dropdownLinks, value);
		return new LoginPage(driver);
	}
	
	@Step("doClickOnRegistrationLink based on value{0}")
	public RegistrationPage doClickOnRegistrationLink(String value) {
		eleUtil.doClick(myAccount);
		eleUtil.clickOnElement(dropdownLinks, value);
		return new RegistrationPage(driver);
	}
	
	
	

}
