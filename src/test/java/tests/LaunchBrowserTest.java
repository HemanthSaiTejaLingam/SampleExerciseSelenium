package tests;
import util.DriverUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LaunchBrowserTest {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		driver=DriverUtil.getBrowserInstance("chrome");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(200,TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
	@Test
	public void f() {
		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
		Assert.assertEquals(driver.getTitle(),"Home");

		driver.findElement(By.linkText("SignUp")).click();

		driver.findElement(By.id("userName")).sendKeys("ZingaHai123");
		driver.findElement(By.id("firstName")).sendKeys("Test");
		driver.findElement(By.id("lastName")).sendKeys("User");
		driver.findElement(By.id("password")).sendKeys("Pass1234");
		driver.findElement(By.id("pass_confirmation")).sendKeys("Pass1234");
		driver.findElement(By.id("gender")).click();
		driver.findElement(By.id("emailAddress")).sendKeys("testuser@gmail.com");
		driver.findElement(By.id("mobileNumber")).sendKeys("7965476467");
		driver.findElement(By.xpath("//*[@id=\'dob\']")).sendKeys("08/01/1998");
		driver.findElement(By.id("address")).sendKeys("New York,USA");
		Select s=new Select(driver.findElement(By.id("securityQuestion")));
		s.selectByIndex(2);
		driver.findElement(By.id("answer")).sendKeys("pinky");
		driver.findElement(By.cssSelector("input[type='submit']")).click();


		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\'errormsg\'][4]")).getText(),"User successfully registered!!!");
	}
	@Test
	public void login() {
		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
		driver.findElement(By.linkText("SignIn")).click();

		driver.findElement(By.id("userName")).sendKeys("TestUser6676");
		driver.findElement(By.id("password")).sendKeys("Pass1234");
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		driver.findElement(By.cssSelector("ul#menu3 li:nth-of-type(4) a")).click();

		List<WebElement> allRows = driver.findElements(By.tagName("tr"));
		int n=allRows.size();
		if(n==0) {
			System.out.println("Order table empty");
		}
		else {
			System.out.println("Number of Rows is "+n);
			System.out.println(driver.findElement(By.xpath("/html/body/b/section/div/div/div")).getText());
		}
	}
}
