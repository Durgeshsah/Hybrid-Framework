package com.vtiger.common;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




//this class is created so that we can call this whenever>wherever necessary for most commonly used functions

public class CommonActions {
	
	public WebDriverWait wait;
	public CommonActions (WebDriver driver)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	
	
	public void entervalue(WebElement elm, String Value)
	{
		try
		{
		wait.until(ExpectedConditions.visibilityOf(elm));
		elm.clear();
		elm.sendKeys(Value);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	public void clickelement(WebElement elm)
	{
		try
		{
		wait.until(ExpectedConditions.elementToBeClickable(elm));
		elm.click();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	public void elementexists(WebElement elm)
	{
		try
		{
		wait.until(ExpectedConditions.visibilityOf(elm));
		elm.isDisplayed();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	

}
