package Demo.MatchbookAI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import Demo.MatchbookAI.Init.AbstaractPage;





public class LoginVerification extends AbstaractPage{
	
	public LoginVerification(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//img[@id='ClientLogo']")
	WebElement WebsiteLogo;
	@FindBy(xpath="//h4[text()='Matchbook Services, Inc.']")
	WebElement Websitename;
	
	public boolean verifyhomepage() {
		if(WebsiteLogo.isDisplayed())
			return true;
		else
			return false;
	}
	
	@FindBy(xpath=".//div[@class='btn-header transparent']//a[@title='Sign Out']")
	WebElement logoutbtn;
	@FindBy(xpath="//span[contains(text(),'Viral Patel')]")
	WebElement username;
	
	
	
	public boolean verifydashboard(String name) {
		if(username.isDisplayed() && (username.getText().trim().contains(name)))
			return true;
		else
			return false;
	}
	
}