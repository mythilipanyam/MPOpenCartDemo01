package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// By locators:
 private By resultsPageHeader = By.cssSelector("div#content h1");
 private By totalResultsCount = By.cssSelector("div.caption a");
 private By poductPrices = By.xpath("//div[@class='caption']/p[@class='price']");
 private By sortingOptions = By.cssSelector("select#input-sort option");
 private By productFromResultsPage=By.linkText("MacBook Pro");

	// constructor
	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// public methods
	public String verifyResultsPageHeader() {
		return eleUtil.doGetText(resultsPageHeader);
	}
	
	public boolean verifySearchResults(String searchTerm) {
        List<String> resultsList=eleUtil.getLinksTextList(totalResultsCount);
        for(String e:resultsList) {
        	if(e.contains(searchTerm)) {
        		return true;
        	}
        }
		return false;
	}

	public double getAllProductPrices() {
		List<String> pricesList = eleUtil.getLinksTextList(poductPrices);
		Double price = null;
		for (String e : pricesList) {
			String text[] = e.split("Ex Tax:");
			String text1 = text[0].substring(1);

			if (text1.contains(",")) {
				String text2[] = text1.split(",");
				String text3 = text2[0] + text2[1];
				price = Double.parseDouble(text3);
				System.out.println(price);
			} else {
				price = Double.parseDouble(text1);
				System.out.println(price);
			}

		}
		return price;
    }
	
	

	public boolean sortingByPriceFromHighToLow(String value) {
		List<Double> pricesListBeforeSorting = new ArrayList<Double>();
		List<Double> pricesListAfterSorting = new ArrayList<Double>();

		List<String> pricesList = eleUtil.getLinksTextList(poductPrices);

		for (String e : pricesList) {
			String text[] = e.split("Ex Tax:");
			String text1 = text[0].substring(1);

			if (text1.contains(",")) {
				String text2[] = text1.split(",");
				String text3 = text2[0] + text2[1];
				Double price = Double.parseDouble(text3);
				pricesListBeforeSorting.add(price);
			} else {
				Double price = Double.parseDouble(text1);
				pricesListBeforeSorting.add(price);
			}

		}

		System.out.println(pricesListBeforeSorting);// before reversing

		Collections.reverse(pricesListBeforeSorting);// reverses
		

		System.out.println(pricesListBeforeSorting);// after reversing

		eleUtil.clickOnElement(sortingOptions, value);

		pricesList = eleUtil.getLinksTextList(poductPrices);

		for (String e : pricesList) {
			String text[] = e.split("Ex Tax:");
			String text1 = text[0].substring(1);

			if (text1.contains(",")) {
				String text2[] = text1.split(",");
				String text3 = text2[0] + text2[1];
				Double price = Double.parseDouble(text3);
				pricesListAfterSorting.add(price);
			} else {
				Double price = Double.parseDouble(text1);
				pricesListAfterSorting.add(price);
			}

		}

		System.out.println(pricesListAfterSorting);

		if (pricesListAfterSorting.equals(pricesListBeforeSorting)) {
			return true;
		}
		return false;

	}
	
	

	public ProductInfoPage selectProduct(String productName) {
		List<WebElement>totalResults=eleUtil.waitForElementsToBeVisible(5,totalResultsCount); 
		for(WebElement e:totalResults) {
			if(e.getText().equals(productName)) {
				e.click();
			}
		}
		return new ProductInfoPage(driver);
	}
	
	public String selectProductFromResults() {
		String productName=eleUtil.waitForElementPresence(5,productFromResultsPage).getText();
		return productName;
	}

}
