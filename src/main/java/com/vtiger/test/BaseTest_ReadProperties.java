package com.vtiger.test;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest_ReadProperties {
	
	public WebDriver driver;
	public Properties prop;
	public Map<String,Map<String,String>> td;
	
	@BeforeSuite (alwaysRun = true)
	public void initiation () throws Exception
	{
		prop = readproperties();
		td = getData(System.getProperty("user.dir")+"/src/main/resources/TestData/Data_HybridFramework.xlsx","Sheet1");
		System.out.println(td);
		System.exit(0);
	}
	
	@BeforeClass  
	public void launchpage()
	{
		if(prop.getProperty("Browser").equals("chrome"))	//Using IF/ELSE function so that it can run on all 3 browsers if necessary
		{
		WebDriverManager.chromedriver().setup();   		//no need to give chorme drive functions etc. because now by using this function, it will automatically download all the necessary drivers for the code
		driver = new ChromeDriver();
		}
		else if(prop.getProperty("Browser").equals("firefox"))
		{
		WebDriverManager.firefoxdriver().setup();   	//no need to give firefox drive functions etc. because now by using this function, it will automatically download all the necessary drivers for the code
		driver = new FirefoxDriver();
		}
		else if(prop.getProperty("Browser").equals("edge"))
		{
		WebDriverManager.edgedriver().setup();   		//no need to give Edge drive functions etc. because now by using this function, it will automatically download all the necessary drivers for the code
		driver = new EdgeDriver();
		}
		driver.get("http://localhost:100");
		driver.manage().window().maximize();
		int time = Integer.parseInt(prop.getProperty("GlobalTimeout")); //data will be read as String, so to convert it to number and read it in seconds, this command is used.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time)); //pass the int>time from above line to read and run
		
	}
	
	@AfterSuite
	public void closepage()
	{
		driver.quit();
	}
	

//Attempting to read the excel using FILLO Method. So add the latest version of dependency and use the below
//We will need to capture all data and put it in a MAP followed by in a LIST to read it it line by line
	
	
	public Map<String,Map<String,String>> getData(String file,String sheet) throws Exception	//Reading the excel filename & required sheet & required data
	{
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(file); 	//called file to read from above line
		String strQuery="Select * from"+sheet;				//called sheet to read from above line
		Recordset recordset=connection.executeQuery(strQuery);
		List<String> collist = recordset.getFieldNames();	//Using Array function to get all the names in that sheet and then just pass the name to pull all data
		Map<String,Map<String,String>> data = new LinkedHashMap<String, Map<String,String>>();
		while(recordset.next()){
			Map<String,String> rowdata = new LinkedHashMap<String,String>(); 
			for(int i=0;i<collist.size();i++)
			{
				String fieldsName = collist.get(i);
				String colValue = recordset.getField(fieldsName);
				rowdata.put(fieldsName, colValue);
			}
			data.put(recordset.getField(collist.get(0)), rowdata);
		System.out.println(recordset.getField("Details"));
		}
		 
		recordset.close();
		connection.close();
		
		return data;
		
	}
	
	public Properties readproperties()
	
	{
		Properties prop = null;
		try 
		{
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/Config/Setting.properties");
		prop.load(fis);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return prop;
		
	}

}
