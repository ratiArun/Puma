package scripts;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Property;
import pom.ProductPage;
import pom.Puma;
import pom.PumaMenRunningShoe;
import pom.ShoppingCartPage;

public class AddToCart extends BaseTest
{
	@Test 
	public void testAddToCart()
	{
		String hpTitle=Property.getData(PROPERTYFILE_PATH, "hpTitle");
		String MenRunning=Property.getData(PROPERTYFILE_PATH, "MenRunning");
		String ProductPage=Property.getData(PROPERTYFILE_PATH, "ProductPage");
		String cart=Property.getData(PROPERTYFILE_PATH, "cart");
		
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		Puma p=new Puma(driver);
		p.verifyTitle(hpTitle);
		p.move();
		p.clickRunning();
		
		PumaMenRunningShoe pm=new PumaMenRunningShoe(driver);
		pm.verifyTitle(MenRunning);
		String att = pm.getText();
		pm.clickProduct();
		
		String parent=driver.getWindowHandle();
		Set<String> all_wh = driver.getWindowHandles();
		
		all_wh.remove(parent);
		for(String w:all_wh)
		{
			driver.switchTo().window(w);
		}
		ProductPage pg=new ProductPage(driver);
		pg.verifyTitle(ProductPage);
		
		pg.clicksizeDropdown();
		pg.clickSize();
		
		if(pg.inStock().contains("IN STOCK"))
		{
			pg.clickaddtocart();
			ShoppingCartPage sp=new ShoppingCartPage(driver);
			sp.verifyTitle(cart);
			
			String text = sp.gettext();
			try
			{
				Assert.assertEquals(att, text);
				Reporter.log("product is same",true);
				
			}
			catch(Exception e)
			{
				Reporter.log("eleement is not matching",true);
				Assert.fail();
			}
			
			sp.clickCheckout();
			
		}
		else
		{
			Reporter.log("out of stock",true);
		}
		
		
	}

}
