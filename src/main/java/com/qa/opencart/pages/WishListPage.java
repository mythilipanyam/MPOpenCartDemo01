package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.JavaScriptUtil;

public class WishListPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsUtil;
	ResultsPage resultsPage;

	// By locators
	private By wishListPageHeader = By.xpath("//div[@id='content']/h2");
	private By removeButton = By.xpath("//a[@data-original-title='Remove']/i");
	private By addtoCartBtn = By.xpath("//button[@data-original-title='Add to Cart']/i");
	private By addedProductName = By.xpath("//tbody/tr/td[2]/a");
	//private By addedProductName2 = By.xpath("(//div[@id=\"content\"]//tbody/tr/td[2]/a");
	private By successWishlistModificationMessage = By.xpath("//div[@id='account-wishlist']/div[@class='alert alert-success alert-dismissible']");
    private By successAdditionToCartMessage=By.xpath("//div[@id=\"account-wishlist\"]/div[1]");
    private By productFromResultsPage=By.linkText("MacBook Pro");
    
	// constructor
	public WishListPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		jsUtil=new JavaScriptUtil(driver);
		resultsPage=new ResultsPage(driver);
	}

	// public methods
	public String getWishListPageUrl() {
		return eleUtil.waitForUrlContains(5, "/wishlist");
	}

	public String getWishListPageHeader() {
		return eleUtil.waitForElementPresence(5, wishListPageHeader).getText();
	}

	public boolean verifyProductNameInWishList() {
		String expectedName = eleUtil.waitForElementPresence(5, addedProductName).getText();
		System.out.println(expectedName);
		String actualName = resultsPage.selectProductFromResults();
		System.out.println(actualName);
		if (expectedName.equalsIgnoreCase(actualName)) {
			return true;
		}
		return false;
	}

	public boolean removeProductFromWishList() {
		eleUtil.doClick(removeButton);
		String successMessage = eleUtil.waitForElementPresence(5, successWishlistModificationMessage).getText();

		if (successMessage.contains("Success: You have modified your wish list!")) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addProductToCart() {
		eleUtil.doClick(addtoCartBtn);
		String successCartAddMessage = eleUtil.waitForElementPresence(5,successAdditionToCartMessage ).getText();

		if (successCartAddMessage.contains("Success: You have added MacBook Pro to your shopping cart!")) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
