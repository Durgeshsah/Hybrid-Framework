package com.vtiger.test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.vtiger.pages.HomePage;
import com.vtiger.pages.LoginPage;


public class LoginTest extends BaseTest_ReadProperties {
	
//Run this class to Test Login and other actions on home page	
	
	
	public WebDriver driver;				//Since the Before & After class ***3 is of frequent use we will move it to BaseTest Class and call from there
		
	@Test
	public void TC01_InvalidLogin() throws InterruptedException
	{
		LoginPage lp = new LoginPage(driver); 
		lp.login("invalid", "login");
		Thread.sleep(3000);
		lp.VerifyInvalidCredentials();
	}
	
	@Test
	public void TC02_ValidLogin() throws InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.login(prop.getProperty("Userid"), prop.getProperty("Password"));
		HomePage hp = new HomePage(driver);
		Thread.sleep(3000);
		hp.verifyLogout();
		
	}
	

	
	
	
	
	

}




/*
  ***3 ===>
 * @BeforeClass  
	public void launchpage()
	{
		WebDriverManager.chromedriver().setup();   		//no need to give chorme drive functions etc. because now by using this function, it will automatically download all the necessary drivers for the code
		driver = new ChromeDriver();
		driver.get("http://localhost:100");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	}
	
	@AfterClass
	public void closepage()
	{
		driver.quit();
	}
	
 */



