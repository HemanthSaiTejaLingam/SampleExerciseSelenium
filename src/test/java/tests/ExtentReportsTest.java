package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import util.DriverUtil;

public class ExtentReportsTest {
	WebDriver driver;
	ExtentSparkReporter sparkReporter;
	ExtentReports extent;
	ExtentTest logger;

	@BeforeTest
	public void beforeTest() {
		driver=DriverUtil.getBrowserInstance("chrome");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);

		SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-ms");
		String path=System.getProperty("user.dir")+"/extent-reports/"+d.format(new Date())+".html";

		sparkReporter=new ExtentSparkReporter(path);
		extent=new ExtentReports();

		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Stream", "QE");
		extent.setSystemInfo("Location", "PDC5A");
		extent.setSystemInfo("Name", "Hemanth");
		
		sparkReporter.config().setDocumentTitle("Sample Test Report");
		sparkReporter.config().setReportName("Extent Report");;
		sparkReporter.config().setTheme(Theme.DARK);;
	}

	@Test(priority=1)
	public void testHome() {
		logger=extent.createTest("Testing Home Page");
		logger.log(Status.INFO,MarkupHelper.createLabel("Running test Home",ExtentColor.GREY));
		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
		Assert.assertEquals(driver.getTitle(),"Home");

	}
	@Test(priority=2)
	public void testregister(){
		logger=extent.createTest("Testing Register Page");
		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
		logger.log(Status.INFO,MarkupHelper.createLabel("Running test register",ExtentColor.GREY));
		driver.findElement(By.linkText("SignUp")).click();
		Assert.assertEquals(driver.getTitle(),"Register");
	}
	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS) {
			logger.log(Status.PASS,
					MarkupHelper.createLabel("Test Home run Successfully", ExtentColor.GREEN));
		}
		else if(result.getStatus()==ITestResult.FAILURE) {
			logger.log(Status.FAIL,MarkupHelper.createLabel("Test Register Failed", ExtentColor.RED));
			TakesScreenshot ss=(TakesScreenshot) driver; 
			String path= System.getProperty("user.dir")+
					"/extent-reports/screenshots/"+result.getMethod().getMethodName()+".png";
			File source=ss.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(source, new File(path));
				logger.log(Status.FAIL,result.getThrowable().getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.addScreenCaptureFromPath(path,result.getMethod().getMethodName());
		}
	}
	@AfterTest
	public void afterTest() {
		driver.close();
		extent.flush();
	}
}
