import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;     
import java.io.FileInputStream;     
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import jxl.Cell;     
import jxl.Sheet;     
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class WebDriverDemo {
	
	private WebDriver driver;
	private String baseUrl;
	
	@Before
	public void setUp() throws Exception{
		System.setProperty("webdriver.firefox.bin","E:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"); 
		driver = new FirefoxDriver();
		baseUrl = "http://www.ncfxy.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
	}
	@Test
	public void test() throws Exception{
		
		FileInputStream fis = new FileInputStream("G:/test.xls");
	//	InputStream fis = new FileInputStream("G:/info.xls");
	    jxl.Workbook wb = Workbook.getWorkbook(fis);
	      
	 //   Sheet[] sheet = wb.getSheets();
	    Sheet rs = wb.getSheet(0);
	    for(int i = 0; i < rs.getRows(); i++)
	    {
	    	Cell cellNum = rs.getCell(0, i);
	    	String num = cellNum.getContents();
	    	Cell cellMail = rs.getCell(1, i);
	    	String mail = cellMail.getContents();
	    	String password = num.substring(4, 10);
	    	
	    	
	    	driver.get(baseUrl);
			WebElement name = driver.findElement(By.id("name"));
			name.sendKeys(num);
			WebElement pwd = driver.findElement(By.id("pwd")); 
			pwd.sendKeys(password);
			WebElement submit = driver.findElement(By.id("submit"));
			submit.click();
			
			WebElement element = driver.findElement(By.xpath("//td[2]"));
			String mailByWeb = element.getText();
            assertEquals(mail,mailByWeb);
	    	
	    }
		fis.close();
		driver.quit();
		
	}

}
