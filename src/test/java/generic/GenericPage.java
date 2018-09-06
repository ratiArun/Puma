package generic;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public abstract class GenericPage
{
	public WebDriver driver;
	
	public GenericPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	
	public void verifyTitle(String title)
	{
		WebDriverWait wait=new WebDriverWait(driver, 10);
		try
		{
			wait.until(ExpectedConditions.titleContains(title));
			Reporter.log(title+" is matching",true);
		}
		catch(Exception e)
		{
			Reporter.log(title+" title is not matching", true);
			Assert.fail();
		}
	}
	
	public void mouseHover(WebElement ele)
	{
		Actions act=new Actions(driver);
		act.moveToElement(ele).perform();
		
	}
	
	public void scroll(WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver, 10);
		try 
		{
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			Point p=ele.getLocation();
			int x=p.getX();
			int y=p.getY();
			
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollBy("+x+","+y+")");
		}
		catch(Exception e)
		{
			Reporter.log("element is not present"+e,true);
			Assert.fail();
		}		
	}

	public static void takesScreenshot(WebDriver driver,String path)
	{
		try
		{
			Date d=new Date();
			String s=d.toString();
			String date=s.replaceAll(":", "-");
			TakesScreenshot ts=(TakesScreenshot) driver;
			File src=ts.getScreenshotAs(OutputType.FILE);
			File dst=new File(path+date+".jpeg");
			FileUtils.copyFile(src, dst);
		}
		catch(Exception e)
		{
			Reporter.log("Ohoto path is invalid"+e,true);
			Assert.fail();
		}
	}

}
