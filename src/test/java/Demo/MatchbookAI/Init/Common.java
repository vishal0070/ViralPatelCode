package Demo.MatchbookAI.Init;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

public class Common {
	Date date = new Date();
	protected static Wait<WebDriver> wait;
	public static String alerttext;
	public static com.aventstack.extentreports.ExtentTest test;

	public static void moveToObjectelement(WebDriver driver, String xpath) {
		driver.switchTo().frame(driver.findElement(By.xpath(xpath)));
	}

	public static void scrollUpToBottom(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void scrollUpToElement(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void scrollToHorizontal(WebDriver driver, WebElement element) {
		Actions dragger = new Actions(driver);
		WebElement draggablePartOfScrollbar = element;
		// drag downwards
		int numberOfPixelsToDragTheScrollbarDown = 50;
		for (int i = 10; i < 500; i = i + numberOfPixelsToDragTheScrollbarDown) {
			try {
				// this causes a gradual drag of the scroll bar, 10 units at a
				// time
				dragger.moveToElement(draggablePartOfScrollbar).clickAndHold()
						.moveByOffset(numberOfPixelsToDragTheScrollbarDown, 0).release().perform();
				Thread.sleep(1000L);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void scrollToVertical(WebDriver driver, WebElement element) {
		Actions dragger = new Actions(driver);
		WebElement draggablePartOfScrollbar = element;
		// drag downwards
		int numberOfPixelsToDragTheScrollbarDown = 50;
		for (int i = 10; i < 500; i = i + numberOfPixelsToDragTheScrollbarDown) {
			try {
				// this causes a gradual drag of the scroll bar, 10 units at a
				// time
				dragger.moveToElement(draggablePartOfScrollbar).clickAndHold()
						.moveByOffset(0, numberOfPixelsToDragTheScrollbarDown).release().perform();
				Thread.sleep(1000L);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void logcase(String msg) {
		System.out.println(msg);
		Reporter.log("<strong> <h3 style=\"color:DarkViolet\"> " + msg + "</h3> </strong>");
	}

	public static void logstep(String msg) {
		System.out.println(msg);
		Reporter.log("<strong>" + msg + "</strong></br>");
	}

	/**
	 * Checks checkbox or toggle element.
	 * 
	 * @param element Checkbox element.
	 */
	public static void checkChkBox(WebElement element) {
		boolean isCheckBoxSet;
		isCheckBoxSet = element.isSelected();
		if (!isCheckBoxSet) {
			element.click();
		}
	}

	public static void movetoalertAndAccept(WebDriver webDriver) {
		try {
			waitForAlert(webDriver);
			Alert alert = webDriver.switchTo().alert();
			alerttext = alert.getText();
			System.out.println("alert----:" + alerttext);
			alert.accept();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getalertText(WebDriver webDriver) {
		try {
			Alert alert = webDriver.switchTo().alert();
			alerttext = alert.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alerttext;
	}

	public static void waitForAlert(WebDriver driver) {
		int i = 0;
		while (i++ < 5) {
			try {
				Alert alert = driver.switchTo().alert();
				break;
			} catch (Exception e) {
				pause(2);
				continue;
			}
		}
	}

	public static void AcceptUnhandledAlert(WebDriver webDriver) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, 2);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = webDriver.switchTo().alert();
			log("Alert Message: " + alert.getText());
			alert.accept();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int randomBetween(int minimum, int maximum) {
		return new Random().nextInt(maximum - minimum + 1) + minimum;
	}

	public static void openMailinator(WebDriver driver, String emailAddress) {
		pause(3);
//		String emailParsed[] = emailAddress.split("@");
//		String url = "http://" + emailParsed[0] + ".mailinator.com";
		String url = "http://www.mailinator.com";
		goToUrl(driver, url);
		driver.findElement(By.xpath(".//input[@id='addOverlay']")).sendKeys(emailAddress);
		driver.findElement(By.xpath(".//button[@id='go-to-public']")).click();
	}

	public static String getCurrentTimeStampString() {
		java.util.Date date = new java.util.Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmssSS");
		TimeZone timeZone = TimeZone.getDefault();
		Calendar cal = Calendar.getInstance(new SimpleTimeZone(timeZone.getOffset(date.getTime()), "IST"));
		sd.setCalendar(cal);
		return sd.format(date);
	}

	public static String getCurrentTimeStampString2() {
		java.util.Date date = new java.util.Date();
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		TimeZone timeZone = TimeZone.getDefault();
		Calendar cal = Calendar.getInstance(new SimpleTimeZone(timeZone.getOffset(date.getTime()), "IST"));
		sd.setCalendar(cal);
		return sd.format(date);
	}
	
	public static String getCurrentDate() {
		java.util.Date date = new java.util.Date();
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		TimeZone timeZone = TimeZone.getDefault();
		Calendar cal = Calendar.getInstance(new SimpleTimeZone(timeZone.getOffset(date.getTime()), "IST"));		
		return sd.format(date);
	}

	public static String makeScreenshot(WebDriver driver, String screenshotName) {
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		/* Take a screenshot */
		File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		String nameWithExtention = screenshotName + ".png";
		/* Copy screenshot to specific folder */
		try {
			String reportFolder = "test-output" + File.separator;
			String screenshotsFolder = "screenshots";
			File screenshotFolder = new File(reportFolder + screenshotsFolder);
			if (!screenshotFolder.getAbsoluteFile().exists()) {
				screenshotFolder.mkdir();
			}
			FileUtils.copyFile(screenshot,
					new File(screenshotFolder + File.separator + nameWithExtention).getAbsoluteFile());
		} catch (IOException e) {
			log("Failed to capture screenshot: " + e.getMessage());
		}
		// log(getScreenshotLink(nameWithExtention, nameWithExtention));
		return getScreenshotLink(nameWithExtention, nameWithExtention);
	}

	public static void makeScreenshot2(WebDriver driver, String screenshotName) {
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		/* Take a screenshot */
		File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		String nameWithExtention = screenshotName + ".png";
		/* Copy screenshot to specific folder */
		try {
			String reportFolder = "test-output" + File.separator;
			String screenshotsFolder = "screenshots";
			File screenshotFolder = new File(reportFolder + screenshotsFolder);
			if (!screenshotFolder.getAbsoluteFile().exists()) {
				screenshotFolder.mkdir();
			}
			FileUtils.copyFile(screenshot,
					new File(screenshotFolder + File.separator + nameWithExtention).getAbsoluteFile());
		} catch (IOException e) {
			new MasterInit();
			// Reporter.log("Failed to capture screenshot: " + e.getMessage());
			MasterInit.test.log(null, "Failed to capture screenshot: " + e.getMessage());
		}
		log("<b>Please look to the screenshot - </b>" + getScreenshotLink2(nameWithExtention, nameWithExtention)
				+ "<br>");
	}

	public static void log(String msg) {
		System.out.println(msg);
		Reporter.log(msg + "</br>");
	}

	public static void logStatus(String Status) {
		System.out.println(Status);
		if (Status.equalsIgnoreCase("Pass")) {
			log("<br><Strong><font color=#008000>Pass</font></strong></br>");
		} else if (Status.equalsIgnoreCase("Fail")) {
			log("<br><Strong><font color=#FF0000>Fail</font></strong></br>");
		}
	}

	public static String getScreenshotLink(String screenshot_name, String link_text) {
		/*
		 * String dataFilePath = "test-output/screenshots"; File datafile = new
		 * File(dataFilePath); String fullpath = datafile.getAbsolutePath();
		 */
		log("<br><Strong><font color=#FF0000>--Failed</font></strong>");
		return "<a href='../test-output/screenshots/" + screenshot_name + "' target='_new'>" + link_text + "</a>";
	}

	public static String getScreenshotLink2(String screenshot_name, String link_text) {
		String dataFilePath = "test-output/screenshots";
		File datafile = new File(dataFilePath);
		String fullpath = datafile.getAbsolutePath();
		return "<a href='" + fullpath + "/" + screenshot_name + "' target='_new'>" + link_text + "</a>";
	}

	public static boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isElementEnabled(WebElement element) {
		try {
			return element.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isElementNotDisplayed(WebElement element) {
		try {
			return !element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean waitForElementIsDisplayed(WebElement by) throws InterruptedException {
		for (int second = 0;; second++) {
			if (second >= 60) {
				break;
			}
			try {
				if (isElementDisplayed(by))
					break;
			} catch (Exception e) {
				e.printStackTrace();
			}
			pause(1);
		}
		return false;
	}

	public static void waitForElement(WebElement webelement, WebDriver driver) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(webelement));
	}

	public static void waitForInvisibility(By locator, WebDriver driver) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public static void PresenceOfElement(By locator, WebDriver driver) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public static void clickableElement(By locator, WebDriver driver) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void clickableElement(WebElement webelement, WebDriver driver) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.elementToBeClickable(webelement));

	}

	public static void waitForIframe(String str, WebDriver driver) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(str))));
	}

	public static String findAndSwitchToSecondWindow(WebDriver driver, String handleCurrentWindow) {
		pause(3);
		Set<String> windows = driver.getWindowHandles();
		String handleSecondWindow = null;
		for (String window : windows) {
			if (!window.contains(handleCurrentWindow)) {
				handleSecondWindow = window;
			}
		}
		// Switch to the second window.
		try {
			pause(4);
			driver.switchTo().window(handleSecondWindow);
		} catch (Throwable failure) {
			// If there is problem in switching
			// window, then re-try.
			pause(3);
			driver.switchTo().window(handleSecondWindow);
		}
		return handleSecondWindow;
	}

	public static void closeOtherWindow(WebDriver driver, String handleCurrentWindow) {
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			if (!window.contains(handleCurrentWindow)) {
				driver.close();
			}
		}
		driver.switchTo().window(handleCurrentWindow);
	}

	public static void selectFromCombo(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public static void selectFromComboByVisibleElement(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	public static void selectFromComboByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public static String selectFromComboByIndexReturnValue(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		Common.pause(1);
		return select.getFirstSelectedOption().getText();
	}

	public static String selectFromComboByIndexReturnValue(WebElement element) {
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public static String getFridayDateOfParticularMonth(String year, String month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year), Integer.parseInt(month), 1);
		cal.add(Calendar.DAY_OF_MONTH, -(cal.get(Calendar.DAY_OF_WEEK) % 7 + 1));
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date d1 = cal.getTime();
		return format.format(d1);
	}

	public static void jsClick(WebDriver driver, WebElement element) {
		highlightElement(driver, element);
		((JavascriptExecutor) driver).executeScript("return arguments[0].click();", element);
		// this.waitForAjax("0");
	}

	public static void clickOn(WebElement element, WebDriver driver) {
		mouseOver(driver, element);
		highlightElement(driver, element);
		element.click();
	}

	public static void mouseOver(WebDriver driver, WebElement element) {
		Actions builder = new Actions(driver);
		builder.moveToElement(element).build().perform();
	}

	public static String getTextJS(WebDriver driver, WebElement element) {
		return (String) ((JavascriptExecutor) driver).executeScript("return jQuery(arguments[0]).text();", element);
	}

	public static void pause(int secs) {
		try {
			Thread.sleep(secs * 1000);

		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
	}

	public static int randomNumericValueGenerate(int length) {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(length);
		return 0;
	}

	public static void type(WebElement webElement, String value) {
		webElement.clear();
		System.out.println("--------->" + value);
		Common.pause(2);
		webElement.sendKeys(value);
	}

	public static void typeAmt(WebElement webElement, String value) {
		webElement.clear();
		System.out.println("--------->" + value);
		Common.pause(2);
		webElement.sendKeys("0");
		webElement.sendKeys(value);
	}

	public static String selectRandomOptionFromCombo(By by, WebDriver driver) throws InterruptedException {
		String selectedOption = "";
		WebElement selectCombo = driver.findElement(by);
		Thread.sleep(2);
		List<WebElement> getAllOption = selectCombo.findElements(By.tagName("option"));
		ArrayList<String> arrayOfAllOption = new ArrayList<String>();
		for (WebElement ele : getAllOption) {
			if (!ele.getText().startsWith("Select")) {
				arrayOfAllOption.add(ele.getText());
			}
		}
		int index = new Random().nextInt(arrayOfAllOption.size());
		if (Integer.signum(index) == -1) {
			index = -index;
			// index=Math.abs(index);
		}
		selectedOption = arrayOfAllOption.get(index);
		return selectedOption;
	}

	public static void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public static void openUrlInNewTab(WebDriver driver, String url) {
		if (System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
			driver.findElement(By.tagName("body")).sendKeys(Keys.COMMAND + "t");
		} else {
			driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL + "t");
		}
		driver.get(url);
	}

	public static void closeCurrentTab(WebDriver driver) {
		if (System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
			driver.findElement(By.tagName("body")).sendKeys(Keys.COMMAND + "w");
		} else {
			driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL + "w");
		}
	}

	public static void mouseHover(WebDriver driver, WebElement ele) {
		Actions action = new Actions(driver);
		action.moveToElement(ele).build().perform();
	}

	public static void FocusOnEle(WebDriver driver, WebElement ele) {
		Actions action = new Actions(driver);
		action.moveToElement(ele).perform();
	}

	public static void mouseHoverUsingJS(WebDriver driver, WebElement ele) {
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		((JavascriptExecutor) driver).executeScript(mouseOverScript, ele);
	}

	public static void jsType(WebDriver driver, WebElement element, String value) {
		String je = "return arguments[0].value='" + value + "';";
		((JavascriptExecutor) driver).executeScript(je, element);
	}

	public static void goToUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public static void goToPreviuosPage(WebDriver driver) {
		driver.navigate().back();
	}

	public static void highlightElement(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border = '3px solid yellow'", element);
		pause(2);
	}

	public static void Log(String msg) {
		// Reporter.log(msg + "</br>");
		test.log(Status.INFO, "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + " " + msg);
	}

	public static void stopPageLoading(WebDriver driver) {
		driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
	}

	public static boolean isElementPresent(WebDriver driver, By identifier) {
		int len = driver.findElements(identifier).size();
		if (len == 0)
			return false;
		else
			return true;
	}

	public static String getAbsolutePathForUpload(String Doc) {
		return new File(Doc).getAbsolutePath();
	}

	public static void SwitchtoTab(WebDriver driver, int tabNumber) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabNumber));
	}

}
