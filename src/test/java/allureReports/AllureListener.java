package allureReports;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.qameta.allure.Attachment;


import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

public class AllureListener extends BaseClass implements ITestListener{
	public ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();// since the method is static we dont neet object to call the method
	ThreadLocal<ExtentTest> threadExtent=new ThreadLocal<ExtentTest>();// for running in parallel mode , add this line
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("I am on teststart"+getTestMethodName(result));
		 test =extent.createTest(result.getMethod().getMethodName());
		 test.log(Status.INFO, result.getInstanceName());
		 test.log(Status.INFO, result.getInstance().getClass().getSimpleName());
		 threadExtent.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("I am on testsucces"+getTestMethodName(result));
		threadExtent.get().log(Status.PASS, "test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("I am in testfailure"+ getTestMethodName(result));
		Object testClass= result.getInstance();
		WebDriver driver =BaseClass.getDriver();
		if (driver instanceof WebDriver) {
			System.out.println("Screenshot captured for test:"+getTestMethodName(result));
		saveFailureScreenShot(driver);}
		saveTextLog(getTestMethodName(result)+"failed screenshot taken");
		
		///  down is for extent report
		threadExtent.get().fail(result.getThrowable().getMessage());// to get the fail log
		
	String methodName =result.getMethod().getMethodName();
	
	try {
		threadExtent.get().addScreenCaptureFromPath(getScreenshot(methodName, driver), methodName);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  //try catch method will auto generate when you hover on getscreenshot() meethod
	}
		
	

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("I am on testskipped"+getTestMethodName(result));
		threadExtent.get().log(Status.SKIP, "Test Skipped");
		
	}



	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("I am in onstart method"+ context.getName());
	  context.setAttribute("WebDriver", BaseClass.getDriver());
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("I am on finish method"+context.getName());
		extent.flush();
	}

	private static String getTestMethodName(ITestResult result) {
		return result.getMethod().getMethodName();
	}
	
	@Attachment
	public byte[] saveFailureScreenShot(WebDriver driver) {
		return((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		
	}
	
	@Attachment(value="{0}", type="text/plain")
	public static String saveTextLog(String message) {
		return message;
	}
	
	
	
}
