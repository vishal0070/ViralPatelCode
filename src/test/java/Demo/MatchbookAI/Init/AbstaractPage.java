package Demo.MatchbookAI.Init;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public abstract class AbstaractPage extends MasterInit
{
	public int DRIVER_WAIT = 20;
	
	public AbstaractPage(WebDriver driver) 
	{	
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,DRIVER_WAIT);
		PageFactory.initElements(finder, this);
	}
}