package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class ShoppingCartPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	ResultsPage resultsPage;
	
	//By locators
	private By cartPageHeader=By.xpath("//div[@id='content']/h1");
	private By productInCart=By.linkText("MacBook Pro");
	//private By quantity=By.xpath("//div/input[@name='quantity[730370]']");
	private By quantity=By.xpath("//input[@type='text' and @name='quantity[730378]']");
	private By unitPrice=By.xpath("(//tbody/tr/td[5])[2]");
	private By totalPrice=By.xpath("//tbody/tr/td[6]");
	private By productFromResultsPage=By.linkText("MacBook Pro");
    private By updateButton=By.xpath("//button[@data-original-title='Update']/i");
	private By cartUpdateSuccessMessage=By.xpath("//div[@id='checkout-cart']/div[1]/i");
	
	//constructor
	public  ShoppingCartPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
		resultsPage=new ResultsPage(driver);
	}

	//public methods
	public String getCartPageHeader() {
		return eleUtil.waitForElementPresence(5, cartPageHeader).getText();
	}
	
	public boolean getCartPageUrl() {
		if(eleUtil.getCurrentPageUrl().contains("/cart")){
			return true;
		}else {
			return false;
		}
		 
	}
	
	public boolean verifyProductName() {
		String actualName=eleUtil.waitForElementPresence(5, productInCart).getText();
		System.out.println(actualName);
		String expectedName = resultsPage.selectProductFromResults();
		System.out.println(actualName);
		if (expectedName.equalsIgnoreCase(actualName)) {
			return true;
		}
		return false;
	}  
	
	public boolean verifyPriceCalculation() {
		
		eleUtil.waitForElementPresence(5, quantity).clear();
		eleUtil.waitForElementPresence(5, quantity).sendKeys("10");
		
		eleUtil.doClick(updateButton);
		
		String message=eleUtil.waitForElementPresence(5, cartUpdateSuccessMessage).getText();
		System.out.println(message);
		
		String items=eleUtil.waitForElementPresence(5, quantity).getText();
		int noOfItems=Integer.parseInt(items);
		
		String itemPrice=eleUtil.doGetText(unitPrice);
		double unitprice=Double.parseDouble(itemPrice);
		
		String overallPrice=eleUtil.doGetText(totalPrice);
		double totalprice=Double.parseDouble(overallPrice);
		
		if(totalprice==noOfItems*unitprice) {		
			System.out.println("Total price updated correctly");
					return true;
				}
	
			System.out.println("Total price is not updated correctly");
			return false;
}
	
}
