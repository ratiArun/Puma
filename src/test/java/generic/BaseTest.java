package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest implements Constants
{
	public WebDriver driver;
	
	
	@BeforeMethod
	public void launch()
	{
			System.setProperty(GECKO_KEY, GECKO_VALUE);
			driver=new FirefoxDriver();
			driver.get("https://in.puma.com/");
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);		
	}
	
	@AfterMethod
	public void close(ITestResult r)
	{
		int status=r.getStatus();
		if(status==2)
		{
			try
			{
				GenericPage.takesScreenshot(driver,PHOTO_PATH);
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
