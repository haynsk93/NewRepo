package allureReports;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {
	public WebDriver driver;
	public static ThreadLocal<WebDriver> tdriver= new ThreadLocal<WebDriver>();
	
	public WebDriver setupDriver() {
		WebDriverManager.chromedriver().setup();// get chromedriver from github
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
		tdriver.set(driver);
		return getDriver();
	}
	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}
public String getScreenshot(String methodName, WebDriver driver) throws IOException {
		
		Date d = new Date();
		//String folderName=d.toString().replaceAll(":", "-");// if yo give like this, it will create seperate folder for screen shot.
		                                                      //screenshots are made at different time. so diff folders
		String folderName = ExtentReporterNG.folderName;
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String file= System.getProperty("user.dir")+"\\reports\\"+folderName+"\\"+methodName+".png";
		FileUtils.copyFile(src,new File( file));
		//apache commons IO is needed for screenshots
		
		//File f = new File(file);
		//f.mkdir();
	
		return file;
		
	}
}
