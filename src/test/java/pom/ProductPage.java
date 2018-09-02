package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import generic.GenericPage;

public class ProductPage extends GenericPage
{
	public ProductPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//div[@class='product-size-click-btn']") private WebElement sizeDropdown;
	
	public void clicksizeDropdown()
	{
		WebDriverWait wait=new WebDriverWait(driver, 10);
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(sizeDropdown));
			sizeDropdown.click();
		}
		catch(Exception e)
		{
			Reporter.log("the element is not present "+e,true);
			Assert.fail();
		}
	}
	
	@FindBy(xpath="//span[@id='180~72']") private WebElement size;
	
	public void clickSize()
	{
		WebDriverWait wait=new WebDriverWait(driver, 10);
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(size));
			size.click();
		}
		catch(Exception e)
		{
			Reporter.log("the element is not present "+e,true);
		}
	}
	
	@FindBy(xpath="//span[@class='value']") private WebElement instock;
	
	public String inStock()
	{
		String text="";
		try
		{
			text=instock.getText();
		}
		catch(Exception e)
		{
			Reporter.log("instock in not displayed",true);
		}
		return text;
	}
	
	@FindBy(xpath="//button[@title='Add to Cart']") private WebElement addtocart;
	
	public void clickaddtocart()
	{
		WebDriverWait wait=new WebDriverWait(driver, 10);
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(addtocart));
			addtocart.click();
		}
		catch(Exception e)
		{
			Reporter.log("element is not present "+e,true);
			Assert.fail();
		}
	}

}
