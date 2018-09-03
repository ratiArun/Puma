package generic;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest implements Constants
{
	public WebDriver driver;
	
	@Parameters({"browser"})
	@BeforeMethod
	public void launch(String browser)
	{
		if(browser.equals("firefox"))
		{
			System.setProperty(GECKO_KEY, GECKO_VALUE);
			driver=new FirefoxDriver();
			driver.get("https://in.puma.com/");
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}
		else
		{
			System.setProperty(CHROME_KEY, CHROME_VALUE);
			driver=new ChromeDriver();
			driver.get("https://in.puma.com/");
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}
		
	}
	
	@AfterMethod
	public void close(ITestResult r)
	{
		int status=r.getStatus();
		
		if(status==2)
		{
			try
			{
				Date d=new Date();
				String s=d.toString();
				String date=s.replaceAll(":", "-");
				TakesScreenshot ts=(TakesScreenshot) driver;
				File src=ts.getScreenshotAs(OutputType.FILE);
				File dst=new File(PHOTO_PATH+date+".jpeg");
				FileUtils.copyFile(src, dst);
			}
			catch(Exception e)
			{
				Reporter.log("photo path is invalid",true);
				Assert.fail();
			}
			
		}
		driver.quit();
		
	}
}
