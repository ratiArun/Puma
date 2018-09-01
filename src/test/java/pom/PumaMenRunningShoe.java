package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import generic.GenericPage;

public class PumaMenRunningShoe extends GenericPage
{
	public PumaMenRunningShoe(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//ul[@class='products-grid products-grid--max-4-col first last odd']/li[4]/a/img")
	private WebElement product;
	
	public String getText()
	{
		String att="";
		WebDriverWait wait=new WebDriverWait(driver, 10);
		try
		{
			wait.until(ExpectedConditions.visibilityOf(product));
			att=product.getAttribute("Alt");
			
		}
		catch(Exception e)
		{
			Reporter.log("product is not present"+e,true);
			Assert.fail();
		}
		return att;
	}
	public void clickProduct() 
	{
		scroll(product);
		WebDriverWait wait= new WebDriverWait(driver, 20);
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(product));
			product.click();
		}
		catch(Exception e)
		{
			Reporter.log("element is not present "+e,true);
			Assert.fail();
		}
		
	}

}
