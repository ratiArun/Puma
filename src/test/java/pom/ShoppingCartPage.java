package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import generic.GenericPage;

public class ShoppingCartPage extends GenericPage
{
	public ShoppingCartPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//tbody/tr[1]/td[@class='product-cart-image']") private WebElement product;
	
	public String gettext()
	{
		String text="";
		WebDriverWait wait=new WebDriverWait(driver, 10);
		try
		{
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(product));
			text=product.getAttribute("alt");
			
		}
		catch(Exception e)
		{
			Reporter.log("product is not added",true);
			Assert.fail();
		}
		return text;
	}
	@FindBy(xpath="(//button[@title='Proceed to Checkout'])[1]") private WebElement checkout;
	
	public void clickCheckout()
	{
		WebDriverWait wait=new WebDriverWait(driver, 10);
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(checkout));
			checkout.click();
		}
		catch(Exception e)
		{
			Reporter.log("checkout element is not present"+e,true);
			Assert.fail();
		}
	}

}
