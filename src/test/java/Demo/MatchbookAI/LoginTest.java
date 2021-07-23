package Demo.MatchbookAI;
import org.testng.Assert;
import org.testng.annotations.Test;

import Demo.MatchbookAI.Init.MasterInit;





public class LoginTest extends MasterInit{

	int numOfFailure = 0;
	int step = 1;
	String username = "vpatel@matchbookservices.com";
	String paword = "Admin@123";
	String name = "Viral Patel";
	String Answer="viral";
	
	
	@Test
	public void login() throws InterruptedException {
		
		logCase("Login");
		assignAuthor_Module(AuthorName, ModuleName);
		logStep(step++,"Open URL : "+testUrl);
		
		if(loginVerification.verifyhomepage()) {
			log("Website open Successfully.");
		}
		else
		{
			log("Website not open.");
		}
		
		logStep(step++,"Enter Username : "+ username);
		loginProcess.adddata(username);
		
		logStep(step++, "Click on next button.");
		loginProcess.clickonnextbtn();
		
		logStep(step++,"Enter password : "+ paword);
		loginProcess.PWd(paword);
		
		logStep(step++, "Click on login button.");
		loginProcess.loginbutton();
		
		logStep(step++,"Security Answer : "+ Answer);
		loginProcess.SecurityAns(Answer);
		
		logStep(step++, "Click on submit button.");
		loginProcess.Clickonsubmitbtn();
		
		
		if(loginVerification.verifydashboard(name))
		{
			log("User is logged in successfully and navigate to Dashboard.");
		}
		else
		{
			log("User is not login successfully.");
		}
	
		
		

	if (numOfFailure > 0) {
		Assert.assertTrue(false);
	}
}
}

