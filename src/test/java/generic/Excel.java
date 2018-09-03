package generic;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.Reporter;

public class Excel 
{
	public static String getData(String path,String key)
	{
		String data="";
		Properties p=new Properties();
		try
		{
			p.load(new FileInputStream(path));
			data=p.getProperty(key);
			
		}
		catch(Exception e)
		{
			Reporter.log("the path is invaid"+e,true);
		}
		
		return data;
	}

}
