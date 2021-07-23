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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
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
import io.github.bonigarcia.wdm.WebDriverManager;
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

	@SuppressWarnings("deprecation")
	@BeforeSuite(alwaysRun = true)
	public void fetchSuite(ITestContext testContext) throws IOException
	{
		 try
		 {
			 GetData.deletePastScreenshots(System.getProperty("user.dir") +"\\test-output\\screenshots");
		 }catch(Exception e)
		 {}


			testUrl=GetData.getValueFromConfig("config.properties","URL");
			targetBrowser =GetData.getValueFromConfig("config.properties","Browser");
		
		
		browserName=targetBrowser;
		
		//URL remote_grid = new URL("http://" + seleniumHub + ":" + seleniumHubPort + "/wd/hub");
		String SCREENSHOT_FOLDER_NAME = "screenshots";
		String TESTDATA_FOLDER_NAME = "test_data";
		test_data_folder_path = new File(TESTDATA_FOLDER_NAME).getAbsolutePath();
		screenshot_folder_path = new File(SCREENSHOT_FOLDER_NAME).getAbsolutePath();
		try{
		DesiredCapabilities capability = null;		
		if (targetBrowser == null || targetBrowser.contains("firefox") || targetBrowser.equalsIgnoreCase("firefox")) 
		{
			File driverpath = new File("Resource/geckodriver.exe");
			String path1 = driverpath.getAbsolutePath();
			System.setProperty("webdriver.gecko.driver",path1);
			
//			FirefoxProfile profile = new FirefoxProfile();
//			profile.setPreference("browser.download.dir",
//	                new File("DownloadData").getAbsolutePath());
//			profile.setPreference("browser.download.folderList", 2);
//			profile.setPreference("browser.helperApps.alwaysAsk.force",false);
//			Map<String, Object> prefs = new HashMap<String, Object>();
//			profile.setPreference("browser.download.manager.showWhenStarting",false);
//			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
//					"application/zip;application/octet-stream;application/x-zip;application/x-zip-compressed");
//			profile.setPreference("pdfjs.disabled",true);
			
			
			FirefoxOptions options = new FirefoxOptions();
			options.setAcceptInsecureCerts(true);
			
			options.addPreference("browser.download.folderList", 2);
			options.addPreference("browser.helperApps.alwaysAsk.force", false);
			options.addPreference("browser.download.dir", new File("DownloadData").getAbsolutePath()); 
			options.addPreference("browser.download.defaultFolder",new File("DownloadData").getAbsolutePath()); 
			options.addPreference("browser.download.manager.showWhenStarting", false);
			options.addPreference("browser.helperApps.neverAsk.saveToDisk",
		"multipart/x-zip,application/zip,application/x-zip-compressed,application/x-compressed,application/msword,application/csv,text/csv,image/png ,image/jpeg, application/pdf, text/html,text/plain,  application/excel, application/vnd.ms-excel, application/x-excel, application/x-msexcel, application/octet-stream");
			
			capability = DesiredCapabilities.firefox();
						
			capability.setJavascriptEnabled(true);
			osName = System.getProperty("os.name");
			HomeDir=System.getProperty("user.home");
			capability.setCapability("marionette", true);
			driver= new FirefoxDriver(capability);
			driver = new FirefoxDriver(options);
			
			//driver = new RemoteWebDriver(remote_grid, capability);
		}else if (targetBrowser.contains("Edge")||targetBrowser.equalsIgnoreCase("Edge"))
		{
			capability = DesiredCapabilities.internetExplorer();
			WebDriverManager.edgedriver().setup();
			File driverpath = new File("Resource/msedgedriver.exe");
			String path1 = driverpath.getAbsolutePath();
			System.setProperty("webdriver.edge.driver",path1 );
			
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory",
		                new File("DownloadData").getAbsolutePath());
			prefs.put("download.prompt_for_download", false);
			
			EdgeOptions options = new EdgeOptions();
			//options.setExperimentalOption("prefs",prefs);
			
			capability.setBrowserName("Microsoft Edge");
			/*WebDriverManager manager = WebDriverManager.edgedriver();
			manager.config().setEdgeDriverVersion("84.0.522.49");
			manager.setup();*/

			//capability.setJavascriptEnabled(true);
			osName = capability.getPlatform().name();
//			driver= new EdgeDriver();
			driver= new EdgeDriver(options);

			//driver = new RemoteWebDriver(remote_grid, capability);
		}
		else if (targetBrowser.contains("ie11")||targetBrowser.equalsIgnoreCase("ie11"))
		{
			capability = DesiredCapabilities.internetExplorer();
			File driverpath = new File("Resource/IEDriverServer.exe");
			String path1 = driverpath.getAbsolutePath();
			System.setProperty("webdriver.ie.driver",path1 );
			capability.setBrowserName("internet explorer");
			capability.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capability.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capability.setCapability("nativeEvents", false);
			capability.setJavascriptEnabled(true);
			osName = capability.getPlatform().name();
			driver= new InternetExplorerDriver(capability);
			//driver = new RemoteWebDriver(remote_grid, capability);
		}else if (targetBrowser.contains("chrome") || targetBrowser.equalsIgnoreCase("chrome"))
		{
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
		}else if (targetBrowser.contains("safari"))
		{
			capability = DesiredCapabilities.safari();
			capability.setJavascriptEnabled(true);
			capability.setBrowserName("safari");
			//driver = new SafariDriver(capability);
		}
		
		//suiteName = testContext.getSuite().getName();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(testUrl);
		
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@BeforeTest(alwaysRun = true)
	public void fetchSuiteConfiguration(ITestContext testContext) throws IOException 
	{
		/*seleniumHub = testContext.getCurrentXmlTest().getParameter("selenium.host");
		seleniumHubPort = testContext.getCurrentXmlTest().getParameter("selenium.port");*/
		//testUrl=TestData.getValueFromConfig("config.properties","URL");
	}
	/**
	 * WebDriver initialization
	 * @return WebDriver object
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method, ITestContext testContext,ITestResult testResult) throws IOException, InterruptedException 
	{
		
		driver.get(testUrl);
		
		suiteName = testContext.getSuite().getName();
		
		// Login
		loginProcess = new LoginProcess(driver);
		loginVerification = new LoginVerification(driver);
		 
	}
	/**
	 * After Method
	 * 
	 * @param testResult
	 */
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult testResult,ITestContext testContext)
	{
		String screenshotName="";
		testName = testResult.getName();
		
		try 
		{
			screenshotName = Common.getCurrentTimeStampString(); //+ testName;
			if (!testResult.isSuccess()) 
			{
				/* Print test result to Jenkins Console */
				System.out.println();
				System.out.println("TEST FAILED - " + testName);
				System.out.println();
				System.out.println("ERROR MESSAGE: " + testResult.getThrowable());
				System.out.println("\n");
				Reporter.setCurrentTestResult(testResult);
				/* Make a screenshot for test that failed */
				if(testResult.getStatus()==ITestResult.FAILURE)
				{ 
					System.out.println("1 message from tear down");
					log("Please look to the screenshot :- "+ Common.makeScreenshot(driver, screenshotName));
		        }
			}
		}catch (Throwable throwable)
		{
		System.out.println(throwable);
		}
	}
	/**
	 * Log given message to Reporter output.
	 * 
	 * @param msg
	 *            Message/Log to be reported.
	 */
	@AfterSuite(alwaysRun = true)
	public void postConfigue()
	{
		driver.manage().deleteAllCookies();
		driver.close();
	}
	public void log(String msg) 
	{
		Reporter.log(msg + "</br>");
		System.out.println(msg);
	}
	public void logStep(int msg1, String msg2) 
	{
		//Reporter.log("Step-"+msg1+" : "+msg2 + "</br>");
		Reporter.log(msg2 + "</br>");
		System.out.println("Step-"+msg1+" : "+msg2);// for jenkins  
	}
	public void logCase(String msg)
	{
		Reporter.log("Test Case : "+msg+"</br>");
		System.out.println("Test Case : "+msg);
	}
	public static void slog(String msg)
	{
		Reporter.log(msg + "</br>");
		System.out.println(msg);
	}
	
	public void MakeScreenshots()
	{
		String screenshotName = Common.getCurrentTimeStampString() + testName;
		Common.makeScreenshot2(driver, screenshotName);
	}
}
