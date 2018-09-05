package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import generic.GenericPage;

public class WomenProductPage extends GenericPage
{
	public WomenProductPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//div[@class='product-size-click-btn']") private WebElement sizedropdown;
	public void clickSizeDropdown()
	{
		WebDriverWait wait=new WebDriverWait(driver, 25);
		try 
		{
			wait.until(ExpectedConditions.elementToBeClickable(sizedropdown));
			sizedropdown.click();
			
		} catch (Exception e)
		{
			Reporter.log("sizedropdown is not clickable "+e,true);
			Assert.fail();
		}
	}
	@FindBy(xpath="//span[@id='180~229']") private WebElement wsize;
	public void clickWSize()
	{
		WebDriverWait wait=new WebDriverWait(driver, 25);
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(wsize));
			wsize.click();
		} catch (Exception e) 
		{
			Reporter.log("wsize is not clickable "+e,true);
			Assert.fail();
		}
	}
	@FindBy(xpath="//span[@class='value']") private WebElement instock;
	public String getInstock()
	{
		String s="";
		WebDriverWait wait=new WebDriverWait(driver, 25);
		try 
		{
			wait.until(ExpectedConditions.visibilityOf(instock));
			s=instock.getText();
		} catch (Exception e) 
		{
			Reporter.log("in stock is not visible "+e,true);
			Assert.fail();
		}
		
		return s;
	}
	@FindBy(xpath="//button[@title='Add to Cart']") private WebElement addtocart;
	public void clickAddtocart()
	{
		WebDriverWait wait=new WebDriverWait(driver, 25);
		try 
		{
			wait.until(ExpectedConditions.elementToBeClickable(addtocart));
			addtocart.click();
			
		} catch (Exception e)
		{
			Reporter.log("add to cart is disabled "+e,true);
		}
	}

}
