package Demo.MatchbookAI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Demo.MatchbookAI.Init.AbstaractPage;
import Demo.MatchbookAI.Init.Common;

public class LoginProcess extends AbstaractPage {
	public LoginProcess(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "EmailAddressInput")
	WebElement Username;

	public LoginVerification adddata(String username) {
		Common.clickableElement(Username, driver);
		Username.sendKeys(username);
		return new LoginVerification(driver);
	}

	@FindBy(id = "btnLoginCheckSubmit")
	WebElement Next;

	public LoginVerification clickonnextbtn() {
		Common.clickOn(Next, driver);
		return new LoginVerification(driver);
	}

	@FindBy(id = "Password")
	WebElement password;

	public LoginVerification PWd(String paword) {
		// ((JavascriptExecutor) driver).executeScript("scroll(0,250);");
		Common.scrollUpToElement(driver, password);
		Common.clickableElement(password, driver);
		password.sendKeys(paword);
		return new LoginVerification(driver);
	}

	@FindBy(id = "btnLoginsbt")
	WebElement Login;

	public LoginVerification loginbutton() throws InterruptedException {

		Common.clickOn(Login, driver);
		return new LoginVerification(driver);
	}

	@FindBy(id = "SecurityAnswer")
	WebElement SecurityAnswer;

	public LoginVerification SecurityAns(String Answer) {
		Common.clickableElement(SecurityAnswer, driver);
		SecurityAnswer.sendKeys(Answer);
		return new LoginVerification(driver);
	}

	

	@FindBy(id = "btnSecurityAnswerSubmit")
	WebElement Submitbtn;

	public LoginVerification Clickonsubmitbtn() {
		Common.clickOn(Submitbtn, driver);
		return new LoginVerification(driver);
	}

	

}
