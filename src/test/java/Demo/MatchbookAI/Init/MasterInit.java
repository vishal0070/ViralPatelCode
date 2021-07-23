package Demo.MatchbookAI.Init;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import Demo.MatchbookAI.Utility.GetData;
import Demo.MatchbookAI.LoginProcess;
import Demo.MatchbookAI.LoginVerification;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class MasterInit {

	public String suiteName = "";
	public String testName = "";
	public static String testUrl;
	public static String seleniumHub; // Selenium hub IP
	public static String seleniumHubPort; // Selenium hub port
	protected String targetBrowser; // Target browser
	protected static String test_data_folder_path = null;
	public static String currentWindowHandle = "";// Get Current Window handle
	public static String osName = "";
	public static String browserName = "";
	public String HomeDir = "";
	public static String browserVersion = "";
	public static String browseruse = "";
	public static String Testenvironment = "";
	public static String Url = "";
	public static String AuthorName;
	public static String ModuleName;

	protected static String screenshot_folder_path = null;
	public static String currentTest;
	protected static Logger logger = Logger.getLogger("testing");
	protected static WebDriver driver;

	public static com.aventstack.extentreports.ExtentReports extent;
	public static com.aventstack.extentreports.ExtentTest test;
	public static ExtentHtmlReporter htmlReporter;

	// Object Creation
	protected LoginProcess loginProcess;
	protected LoginVerification loginVerification;

	@BeforeSuite
	public void fetchSuite(ITestContext testContext) throws IOException {
		GetData.deletePastScreenshots(System.getProperty("user.dir") + "\\test-output\\screenshots");
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/MBSAI.html"); // Here is
																											// the name
																											// of
																											// project
//		htmlReporter.setAppendExisting(false);
		htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
		extent = new com.aventstack.extentreports.ExtentReports();
		extent.attachReporter(htmlReporter);
		Testenvironment = GetData.getValueFromConfig("config.properties", "TestEnvironment");
		extent.setSystemInfo("Browser", targetBrowser);

		targetBrowser = GetData.getValueFromConfig("config.properties", "Browser");
		browserName = targetBrowser;
		testUrl = GetData.getValueFromConfig("config.properties", "URL");

		try {
			DesiredCapabilities capability = null;
			if (targetBrowser == null || targetBrowser.contains("firefox")
					|| targetBrowser.equalsIgnoreCase("firefox")) {
				File driverpath = new File("Resource/geckodriver.exe");
				String path1 = driverpath.getAbsolutePath();
				System.setProperty("webdriver.gecko.driver", path1);

				capability = DesiredCapabilities.firefox();
				capability.setJavascriptEnabled(true);
				osName = System.getProperty("os.name");
				HomeDir = System.getProperty("user.home");
				capability.setCapability("marionette", true);
				driver = new FirefoxDriver(capability);
			} else if (targetBrowser.contains("chrome") || targetBrowser.equalsIgnoreCase("chrome")) {
//				capability = DesiredCapabilities.chrome();
//				File driverpath = new File("Resource/chromedriver.exe");
//				String path1 = driverpath.getAbsolutePath();
//				System.setProperty("webdriver.chrome.driver", path1);
//				final ChromeOptions chromeOptions = new ChromeOptions();
//				Map<String, Object> prefs = new HashMap<String, Object>();
//				prefs.put("download.default_directory", new File("DownloadData").getAbsolutePath());
//				chromeOptions.setExperimentalOption("prefs", prefs);
//				capability.setBrowserName("chrome");
//				capability.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//				capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
//				osName = capability.getPlatform().name();
//				capability.setJavascriptEnabled(true);
//				osName = capability.getPlatform().name();
//				browserVersion = capability.getVersion();
//				driver = new ChromeDriver(capability);
				
				capability = DesiredCapabilities.chrome();
				File driverpath = new File("Resource/chromedriver.exe");
				String path1 = driverpath.getAbsolutePath();
				System.setProperty("webdriver.chrome.driver",path1);
				final ChromeOptions chromeOptions = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				 prefs.put("download.default_directory",
			                new File("DownloadData").getAbsolutePath());
				chromeOptions.setExperimentalOption("prefs", prefs);
				capability.setBrowserName("chrome");
				capability.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
				//capability.setCapability("disable-popup-blocking", true);
				osName = capability.getPlatform().name();
				capability.setJavascriptEnabled(true);
				osName = capability.getPlatform().name();
				browserVersion = capability.getVersion();
				//driver = new RemoteWebDriver(remote_grid, capability);
				driver= new ChromeDriver(capability);
			}

			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			// driver.get(testUrl);
			suiteName = testContext.getSuite().getName();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeTest(alwaysRun = true)
	public void fetchSuiteConfiguration(ITestContext testContext) throws IOException {
//		targetBrowser = GetData.getValueFromConfig("config.properties", "Browser");
//		testUrl = GetData.getValueFromConfig("config.properties", "URL");
//		
//		try {
//			DesiredCapabilities capability = null;
//			if (targetBrowser == null || targetBrowser.contains("firefox")
//					|| targetBrowser.equalsIgnoreCase("firefox")) {
//				File driverpath = new File("Resource/geckodriver.exe");
//				String path1 = driverpath.getAbsolutePath();
//				System.setProperty("webdriver.gecko.driver", path1);
//
//				capability = DesiredCapabilities.firefox();
//				capability.setJavascriptEnabled(true);
//				osName = System.getProperty("os.name");
//				HomeDir = System.getProperty("user.home");
//				capability.setCapability("marionette", true);
//				driver = new FirefoxDriver(capability);
//			} else if (targetBrowser.contains("chrome") || targetBrowser.equalsIgnoreCase("chrome")) {
//				capability = DesiredCapabilities.chrome();
//				File driverpath = new File("Resource/chromedriver.exe");
//				String path1 = driverpath.getAbsolutePath();
//				System.setProperty("webdriver.chrome.driver", path1);
//				final ChromeOptions chromeOptions = new ChromeOptions();
//				Map<String, Object> prefs = new HashMap<String, Object>();
//				prefs.put("download.default_directory", new File("DownloadData").getAbsolutePath());
//				chromeOptions.setExperimentalOption("prefs", prefs);
//				capability.setBrowserName("chrome");
//				capability.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//				capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
//				osName = capability.getPlatform().name();
//				capability.setJavascriptEnabled(true);
//				osName = capability.getPlatform().name();
//				browserVersion = capability.getVersion();
//				driver = new ChromeDriver(capability);
//			}
//
//			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//			driver.manage().window().maximize();
//			driver.get(testUrl);
//			suiteName = testContext.getSuite().getName();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@Parameters({ "Author", "Module" })
	@BeforeMethod(alwaysRun = true)
	public void setUp(String Author, String Module, Method method, ITestContext testContext, ITestResult testResult)
			throws IOException, InterruptedException {
		AuthorName = Author;
		ModuleName = Module;

		driver.get(testUrl);

//		currentTest = method.getName();

		String SCREENSHOT_FOLDER_NAME = "screenshots";
		String TESTDATA_FOLDER_NAME = "test_data";
		test_data_folder_path = new File(TESTDATA_FOLDER_NAME).getAbsolutePath();
		screenshot_folder_path = new File(SCREENSHOT_FOLDER_NAME).getAbsolutePath();

		// Login
		loginProcess = new LoginProcess(driver);
		loginVerification = new LoginVerification(driver);

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult testResult, ITestContext testContext) {
		String screenshotName = "";
		testName = testResult.getName();

		try {
			screenshotName = Common.getCurrentTimeStampString();
			if (!testResult.isSuccess()) {
				Reporter.setCurrentTestResult(testResult);
				if (testResult.getStatus() == ITestResult.FAILURE) {
					System.out.println("1 message from tear down");
					test.log(Status.FAIL,
							"Please look to the screenshot :- " + Common.makeScreenshot(driver, screenshotName));
					test.log(Status.FAIL, testResult.getThrowable());
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

//	@AfterTest(alwaysRun = true)
//	public void postConfigue() {
//		extent.flush();
//		driver.manage().deleteAllCookies();
//		driver.close();
//		driver.quit();
//	}

	@AfterSuite(alwaysRun = true)
	public void postConfigue() {
		extent.flush();
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
	}

	public void log(String msg) {
		Reporter.log(msg + "</br>");
		test.log(Status.INFO, "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + " " + msg);
		System.out.println(msg);
	}

	public void logStep(int msg1, String msg2) {
		Reporter.log("Step-" + msg1 + " : " + msg2 + "</br>");
		test.log(Status.INFO, "Step-" + msg1 + " : " + msg2);
		System.out.println("Step-" + msg1 + " : " + msg2);
	}

	public void logCase(String msg) {
		System.out.println("Test Case : " + msg);
		test = extent.createTest(msg);
	}

	public static void slog(String msg) {
		Reporter.log(msg + "</br>");
		test.log(Status.INFO, "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + " " + msg);
		System.out.println(msg);
	}

	public void assignAuthor_Module(String AuthorNm, String ModuleNm) {
		test.assignAuthor(AuthorNm);
		test.assignCategory(ModuleNm);
	}

	public void logStatus(final int test_status, String msg) {
		switch (test_status) {
		case TestStatus.PASSED:
			test.log(Status.PASS, "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp " + msg + " ");
			break;
		case TestStatus.FAILED:
			String screenshotName = Common.getCurrentTimeStampString();
			test.log(Status.FAIL, "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + msg
					+ " Please look to the screenshot :- " + Common.makeScreenshot(driver, screenshotName));
			MakeScreenshots();
			break;
		case TestStatus.SKIPPED:
			test.log(Status.SKIP, " " + msg);
			break;
		default:
			break;
		}
	}

	public void MakeScreenshots() {
		String screenshotName = Common.getCurrentTimeStampString() + testName;
		Common.makeScreenshot2(driver, screenshotName);
	}
}
