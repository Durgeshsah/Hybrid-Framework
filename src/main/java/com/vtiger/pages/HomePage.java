package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.common.CommonActions;

public class HomePage {

	
	private WebDriver driver;
	private CommonActions ca;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);			//"this" means, this/current class. we could also use Homepage.class
		CommonActions ca = new CommonActions(driver); 	//Since we are calling Common Action Class, By using this ,we dont need to use the ***2 code
		
	}
	
	@FindBy (linkText = "Logout")
	WebElement lnk_logout;
	public void verifyLogout()
	{
		ca.elementexists(lnk_logout);
	}
	public void Logout()
	{
		ca.clickelement(lnk_logout);
	}
	
	
	
	
	
}
