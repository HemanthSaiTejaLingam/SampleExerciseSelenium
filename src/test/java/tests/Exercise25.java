package tests;

import org.testng.annotations.Test;

import util.DriverUtil;

import org.testng.annotations.BeforeClass;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Exercise25 {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		driver=DriverUtil.getBrowserInstance("chrome");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	@Test
	public void f() {
		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
		Actions a=new Actions(driver);
		a
		.moveToElement(driver.findElement(By.linkText("AboutUs")))
		.moveToElement(driver.findElement(By.xpath("//*[@id=\'menu3\']/li[3]/ul/li/a")))
		.moveToElement(driver.findElement(By.xpath("//*[@id=\'menu3\']/li[3]/ul/li/ul/li[1]/a")))
		.click()
		.build()
		.perform();
		Set<String> w = driver.getWindowHandles();
		driver.switchTo().window(w.toArray()[1].toString());
		driver.switchTo().frame("main_page");
		String s=driver.findElement(By.id("demo3")).getText();
		System.out.println(s);
		Boolean b=s.contains("Chennai");
		Assert.assertTrue(b);
	}


}
