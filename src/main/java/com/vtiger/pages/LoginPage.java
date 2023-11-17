package com.vtiger.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.vtiger.common.CommonActions;

public class LoginPage {
	
	private WebDriver driver;
	private CommonActions ca;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);			//"this" means, this/current class. we could also use LoginPage.class
		ca = new CommonActions(driver); 	//Since we are calling Common Action Class, By using this ,we dont need to use the ***2 code
		
	}
//NEW TERM - PAGE FACTORY and use the below annotations
	
	@FindBy(name="user_name") 							//By using this ,we dont need to use the below Find Element code > 1***
	WebElement tb_username; 							//this is modifiable as per usage - tb_username, means textbox_username
	
	@FindBy(name="user_password")
	WebElement tb_userpassword;
	
	@FindBy(name="Login")
	WebElement btn_login;
	
	@FindBy(xpath="//img[@src='include/images/vtiger-crm.gif']")
	WebElement img_logo;
	
	@FindBy(xpath="//*[contains (text),'You must specify a valid username and password.']")
	WebElement invalidcred_error;

	
	public void login(String userid, String pwd)		//using this in reation with Common Action class>> we are using 1st as WebElement & 2nd input as String Value
	{
		ca.entervalue(tb_username, userid); 			//WebElement(elm from CA Class) , String Value (from the line above in this class only)
		ca.entervalue(tb_userpassword, pwd);			//WebElement(elm from CA Class) , String Value (from the line above in this class only)
		ca.clickelement(btn_login);						//WebElement(elm from CA Class) , Click function already defined in CA Class
		
	}
	
//above code will give a parameterized command to input userid/pwd.Try/Catch is added to catch any exception and
//have it printed so it can be taken care - exception handling.
	
	public void verifylogo()
	{
		ca.elementexists(img_logo);
	}
	public void VerifyInvalidCredentials()
	{
		ca.elementexists(invalidcred_error);
	}

		
	
	
	
	
	
	
	
	
	
	
	

}


/*
 * 1***===>
public void login(String userid, String pwd)
{
	driver.findElement(By.name("user_name")).clear();
	driver.findElement(By.name("user_name")).sendKeys("admin");
	driver.findElement(By.name("user_password")).clear();
	driver.findElement(By.name("user_password")).sendKeys("admin");
	driver.findElement(By.name("Login")).click();
	
* 2***===>
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOf(tb_username));
		tb_username.clear();
		tb_username.sendKeys(userid);
		
			wait.until(ExpectedConditions.visibilityOf(tb_userpassword));
		tb_userpassword.clear();
		tb_userpassword.sendKeys(pwd);
		
		wait.until(ExpectedConditions.visibilityOf(btn_login));
		btn_login.click();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
}

*/
