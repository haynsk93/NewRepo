package allureReports;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {
	
	public  static String folderName;
	static ExtentReports extent;
	public static ExtentReports getReportObject() { //since its static, we dont need an oject to call the method
		
		Date d = new Date();
		folderName=d.toString().replaceAll(":", "-");
		String path=System.getProperty("user.dir")+"\\reports\\"+folderName+"\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("Extent Reports");
		reporter.config().setTheme(Theme.DARK);
		
		
		 extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Hayns Kurian");
		return extent;
	}

}
