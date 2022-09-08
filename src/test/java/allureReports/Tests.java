package allureReports;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;


import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;



public class Tests extends BaseClass{
 public WebDriver driver;


@BeforeClass
public void setup() {
	driver=setupDriver();
	driver.get("https://demo.nopcommerce.com/");
	driver.manage().window().maximize();
	
	
}
@Test(priority=1, description ="verify logo presence in HomePage")
@Description("verify logo presence in HomePage....")
@Epic("EP001")
@Feature("Feature1: logo verification")
@Story("Logo Presence")
@Step("Verification")
@Severity(SeverityLevel.MINOR)
public void logoPresenceTest() {
	boolean status=driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).isDisplayed();
Assert.assertTrue(status);
}


@Test(priority=2, description ="Login Verification")
@Description("verify login....")
@Epic("EP002")
@Feature("Feature2: login verification")
@Story("Login Verify")
@Step("Verification")
@Severity(SeverityLevel.BLOCKER)
public void loginTest() {
	driver.findElement(By.className("ico-login")).click();
	driver.findElement(By.id("Email")).sendKeys("haynsk93@gmail.com");
	driver.findElement(By.id("Password")).sendKeys("Test@123");
	driver.findElement(By.cssSelector("button[class*='ogin-button']")).click();
	String expected="Login was unsuccessful. Please correct the errors and try again.";
	String actual=driver.findElement(By.cssSelector("div[class*='summary-errors']")).getText();
	Assert.assertEquals(actual, expected);
	Assert.assertEquals(driver.getTitle(), "gghfh");
			
	
}
@Test(priority=3, description="verify user registration")
@Description("verify Registration details....")
@Epic("EP003")
@Feature("Feature3: Registration verification")
@Story("Registration Presence")
@Step("Verification")
@Severity(SeverityLevel.NORMAL)

public void registrationTest() {
	throw new SkipException("Skipping the test");
	
}
@AfterClass
public void teardown() {
	driver.quit();
}

}
