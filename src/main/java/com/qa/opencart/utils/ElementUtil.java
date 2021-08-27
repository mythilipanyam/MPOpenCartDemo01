package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

//'By' is a class in selenium
//all the locators like id(),class(),xpath() etc are methods and they are static in nature

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil=new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		WebElement element=driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight.trim())) {
			jsUtil.flash(element);
		}
		return element;
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public String getCurrentPageUrl() {
		return driver.getCurrentUrl();
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public boolean doIsEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public String doGetAttribute(By locator, String attributeName) {
		return getElement(locator).getAttribute(attributeName);
	}

	public void clickOnElement(By locator, String value) {
		List<WebElement> eleList = driver.findElements(locator);
		System.out.println(eleList.size());
		for (WebElement e : eleList) {
			if (e.getText().equals(value)) {
				e.click();
				break;
			}
		}
	}

	public List<String> getLinksTextList(By locator) {
		List<String> eleTextList = new ArrayList<String>();
		List<WebElement> eleList = getElements(locator);
		System.out.println("Element count: " + eleList.size());
		for (WebElement e : eleList) {
			String text = e.getText();
			if (!text.isEmpty()) {
				eleTextList.add(text);
			}

		}
		return eleTextList;
	}

	public boolean verifyElementPresence(By locator) {
		List<WebElement> eleList = getElements(locator);
		if (eleList.size() > 0) {
			System.out.println("Element is present on the webpage");
			return true;
		}
		System.out.println("Element is not present on the webpage");
		return false;
	}

//	*****************************DropDowns(with select tag)***********************************************
	public void doSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public List<String> doGetDropDownOptionsList(By locator) {
		List<String> optionsValList = new ArrayList<String>();
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();

		System.out.println(optionsList.size());

		for (WebElement e : optionsList) {
			String text = e.getText();
			optionsValList.add(text);
		}
		return optionsValList;
	}

	public void doSelectOptionFromList(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();

		System.out.println(optionsList.size());

		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}

		}

	}
//	*****************************DropDowns(without select tag)***********************************************

	public void chooseDropDownOptionWithOutSelectTag(By locator, String value) {
		List<WebElement> OptionsList = getElements(locator);

		System.out.println(OptionsList.size());

		for (WebElement e : OptionsList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

//	*****************************JQueryDropDowns(without select tag)***********************************************
	public void selectChoice(By locator, String... value) {

		List<WebElement> choicesList = getElements(locator);

		if (!value[0].equalsIgnoreCase("all")) {
			for (WebElement e : choicesList) {
				String text = e.getText();
				System.out.println(text);

				for (int j = 0; j < value.length; j++) {
					if (text.equals(value[j])) {
						e.click();
						break;
					}
				}

			}
		} else {
			// all options selection logic
			try {
				for (WebElement e : choicesList) {
					e.click();
				}
			} catch (Exception e) {

			}
		}

	}

//	*****************************Action class methods***********************************************
	public void twoLevelMenuHandle(By parentMenu, By childMenu) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(0);
		getElement(childMenu).click();
	}

	public void threeLevelMenuHandle(By parentMenu1, By parentMenu2, By childMenu) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu1)).perform();
		Thread.sleep(0);
		act.moveToElement(getElement(parentMenu2)).perform();
		Thread.sleep(0);

		getElement(childMenu).click();
	}

	public void doDragAndDropLongMethod(By sourceEle, By targetEle) {
		Actions act = new Actions(driver);
		act.clickAndHold(getElement(sourceEle)).moveToElement(getElement(targetEle)).release(getElement(targetEle))
				.build().perform();

	}

	public void doDragAndDrop(By sourceEle, By targetEle) {
		Actions act = new Actions(driver);
		act.dragAndDrop(getElement(sourceEle), getElement(targetEle)).perform();
	}

	public void doRightClick(By locator1, By locator2, String value) {
		Actions act = new Actions(driver);
		act.contextClick(getElement(locator1)).perform();
		List<WebElement> optList = getElements(locator2);
		System.out.println(optList.size());
		for (WebElement e : optList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public void doSendKeysByActions(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	public void doClickByActions(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

//	*****************************Pop up handling***********************************************
	public String doGetAlertText(By locator) {
		getElement(locator).click();
		Alert alert = driver.switchTo().alert();// webdriver moves onto alert popup
		String text = alert.getText();
		System.out.println(text);
		alert.accept();
		driver.switchTo().defaultContent();
		return text;

	}

	public void doADismisstTheAlert(By locator) {
		getElement(locator).click();
		Alert alert = driver.switchTo().alert();// webdriver moves onto alert popup
		alert.dismiss();
		driver.switchTo().defaultContent();

	}

//	*****************************Wait Utils***********************************************
	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeOut) {
		String text = waitForAlert(timeOut).getText();
		waitForAlert(timeOut).accept();
		return text;
	}

	public void acceptAlert(int timeOut) {
		waitForAlert(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForAlert(timeOut).dismiss();
		;
	}

	public void doSendKeysOnAlert(int timeOut, String value) {
		waitForAlert(timeOut).sendKeys(value);
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible on the web page
	 * 
	 * @param timeOut
	 * @param locator
	 * @return
	 */

	public WebElement waitForElementPresence(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementPresence(int timeOut, int pollingTime, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut, pollingTime);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param timeOut
	 * @param locator
	 * @return
	 */
	public WebElement waitForElementVisibilityUsingByLocator(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForElementVisibilityUsingWebElement(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}

	public String waitForTitleContains(int timeOut, String titleValue) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		if (wait.until(ExpectedConditions.titleContains(titleValue))) {
			return driver.getTitle();
		}
		return null;
	}

	public String waitForTitleIs(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		if (wait.until(ExpectedConditions.titleIs(title))) {
			return driver.getTitle();
		}
		return null;
	}

	public String waitForUrlContains(int timeOut, String UrlFraction) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		if (wait.until(ExpectedConditions.urlContains(UrlFraction))) {
			return driver.getCurrentUrl();
		}
		return null;
	}

	public String waitForFullUrl(int timeOut, String Url) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		if (wait.until(ExpectedConditions.urlToBe(Url))) {
			return driver.getCurrentUrl();
		}
		return null;
	}

	public void waitForFrameAndSwitchToItByFrameName(int timeOut, String frameName) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}

	public void waitForFrameAndSwitchToItByLocator(int timeOut, By frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public void waitForFrameAndSwitchToItByWebElement(int timeOut, WebElement frameElement) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	public void waitForFrameAndSwitchToItByIndex(int timeOut, int frameIndex) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public List<String> getElementTextList(int timeOut, By locator) {
		List<WebElement> eleList = waitForElementsToBeVisible(timeOut, locator);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			eleTextList.add(e.getText());
		}
		return eleTextList;
	}

	public List<WebElement> waitForElementsToBeVisible(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public WebElement waitForElementWithFluentWait(By locator, int timeOut, long pollingInterval) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(pollingInterval)).ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public WebDriver waitForFrameWithFluentWait(By locator, int timeOut, long pollingInterval) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(pollingInterval)).ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));

	}

	// *****************************Custom wait***********************************************
	public WebElement retryingElement(By locator, int maxAttempts) {
		WebElement element = null;
		int attempts = 0;
		while (attempts < maxAttempts) {
			try {
				element = driver.findElement(locator);
				break;
			} catch (Exception e) {
				try {
					Thread.sleep(1000);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				System.out.println("Element not found in " + (attempts + 1));
			}

			attempts++;

		}
		return element;
	}

}
