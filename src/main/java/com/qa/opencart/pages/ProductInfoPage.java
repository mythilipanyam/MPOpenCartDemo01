package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.JavaScriptUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsUtil;
	
	//private By locators
	private By selectedProductName=By.cssSelector("div#content h1");
	private By productMetaData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By addToCartBtn=By.id("button-cart");
	private By imagesTotalCount=By.cssSelector("ul.thumbnails img");
	private By reviewLink=By.linkText("Reviews (0)");
	private By rating=By.xpath("(//input[@name='rating'])[5]");
	private By continueBtn=By.id("button-review");
	private By reviewSuccess=By.xpath("//div[@class='alert alert-success alert-dismissible']");
	private By nameonReviewTab=By.xpath("//input[@ id='input-name']");
	private By inputReview=By.id("input-review");
	private By succeessMessageForAddToCart=By.cssSelector("div.alert.alert-success.alert-dismissible");
	private By wishListBtn=By.xpath("//button/i[@class='fa fa-heart']");
	private By succeessMessageToWishList=By.xpath("//div[@class='alert alert-success alert-dismissible']");
	private By wishListLink=By.xpath("//a[@id='wishlist-total']/span");
	//private By shoppingCartLink=By.xpath("//a[@title='Shopping Cart']/span");
	private By shoppingCartLink=By.linkText("Shopping Cart");
	
	
	private Map<String,String>productInfoMap;
	
	//constructor
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
		jsUtil=new JavaScriptUtil(driver);
	}
	
	//public methods
	public boolean isSelectedProductDisplayed() {
		return eleUtil.doIsDisplayed(selectedProductName);
	}
	
	public String getProductHeader() {
		jsUtil.clickElementByJS(eleUtil.getElement(selectedProductName));
		 return eleUtil.waitForElementPresence(5,selectedProductName).getText();
	}
	
	public int getImagesCount() {
		return eleUtil.getElements(imagesTotalCount).size();
	}
	
	public Map<String, String> getProductInfo() {
		
		productInfoMap=new HashMap<String,String>();
		productInfoMap.put("Name", getProductHeader());
		
		List<WebElement>metaDataList=eleUtil.getElements(productMetaData);
		System.out.println("metadatalist size is: "+metaDataList.size());
		
		//metadata
		for(WebElement e:metaDataList) {
			String metaData[]=e.getText().split(":");
			productInfoMap.put(metaData[0].trim(), metaData[1].trim());
		}
		
		//pricing data
		List<WebElement>priceDataList=eleUtil.getElements(productPriceData);
		System.out.println("pricelist size is: "+priceDataList.size());
		
		String price=priceDataList.get(0).getText().trim();
		String exTaxPrice=priceDataList.get(1).getText().trim();
		
		productInfoMap.put("price", price);
		productInfoMap.put("extaxprice", exTaxPrice);
		
		return productInfoMap;
		
		}
	
	public String provideReview() {
		eleUtil.doClick(reviewLink);
		eleUtil.waitForElementPresence(5,nameonReviewTab).sendKeys(" ");
		eleUtil.doSendKeys(inputReview, "Test Test Test Test Test Test Test Test Test Test");
		eleUtil.doClick(rating);
		eleUtil.doClick(continueBtn);
		return eleUtil.waitForElementPresence(5,reviewSuccess).getText();
		
	}
	
	public String addToCart() {
		eleUtil.doClick(addToCartBtn);
		return eleUtil.waitForElementPresence(5,succeessMessageForAddToCart).getText();
	}
	
	public String addToWishList() {
		eleUtil.doClick(wishListBtn);
		return eleUtil.waitForElementPresence(5,succeessMessageToWishList).getText();
	}
	
	public WishListPage doClickOnWishListLink() {
		eleUtil.waitForElementPresence(5,wishListLink).click();
		return new WishListPage(driver);
	}
	
	public ShoppingCartPage doClickShoppingCartLink() {
		eleUtil.waitForElementPresence(10,shoppingCartLink).click();
		return new ShoppingCartPage(driver);
	}
	
	
	

}
