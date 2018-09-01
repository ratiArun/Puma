package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import generic.GenericPage;

public class Puma extends GenericPage
{
	public Puma(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//a[contains(.,' MEN ')]") private WebElement men;
	
	public void move()
	{
		mouseHover(men);
		
	}
	
	@FindBy(xpath="//ul/li[2]/a[contains(@href,'/men/shoes/running.html')]") private WebElement running;
	
	public void clickRunning()
	{
		WebDriverWait wait= new WebDriverWait(driver, 10);
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(running));
			running.click();
		}
		catch(Exception e)
		{
			Reporter.log("element is not present "+e,true);
		}
		
	}
	
	

}
