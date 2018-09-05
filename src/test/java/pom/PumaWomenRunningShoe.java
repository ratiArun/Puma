package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import generic.GenericPage;

public class PumaWomenRunningShoe extends GenericPage
{
	public PumaWomenRunningShoe(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//ul[@class='products-grid products-grid--max-4-col first last odd']/li[4]//img")
	private WebElement product;
	public String getText()
	{
		String att="";
		WebDriverWait wait=new WebDriverWait(driver, 25);
		try 
		{  wait.until(ExpectedConditions.visibilityOf(product));
		   att=product.getAttribute("alt");
			
		} catch (Exception e) 
		{
			Reporter.log("product is not visible "+e,true);
		}
		return att;
	}
	public void scrolltoProduct()
	{
		scroll(product);
	}
	public void clickWomenProduct()
	{
		WebDriverWait wait=new WebDriverWait(driver, 25);
		try 
		{
			wait.until(ExpectedConditions.elementToBeClickable(product));
			product.click();
		} catch (Exception e)
		{
			Reporter.log("product is not clickable "+e,true);
			Assert.fail();
		}
	}
	
}

