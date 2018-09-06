package scripts;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;
import pom.Puma;
import pom.PumaWomenRunningShoe;
import pom.ShoppingCartPage;
import pom.WomenProductPage;

public class WomenRunningShoe extends BaseTest
{
	@Test
	public void testWomenRunningShoe()
	{
		driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
		Puma p=new Puma(driver);
		p.moveToWomen();
		p.clickWrunning();
		
		PumaWomenRunningShoe pw=new PumaWomenRunningShoe(driver);
		String att=pw.getText();
		pw.scrolltoProduct();
		pw.clickWomenProduct();
		
		String parent=driver.getWindowHandle();
		Set<String> all = driver.getWindowHandles();
		all.remove(parent);
		for(String w:all)
		{
			driver.switchTo().window(w);
		}
		WomenProductPage wp=new WomenProductPage(driver);
		wp.clickSizeDropdown();
		wp.clickWSize();
		if(wp.getInstock().equals("IN STOCK"))
		{
			wp.clickAddtocart();
			ShoppingCartPage sp=new ShoppingCartPage(driver);
			String txt=sp.gettext();
			Assert.assertEquals(att, txt);
			sp.clickCheckout();
		}
		else
		{
			Reporter.log("out os stock",true);
		}

	}

}
