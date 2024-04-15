package tests;

import org.testng.annotations.Test;

import util.DriverUtil;

import org.testng.annotations.BeforeClass;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class NewTest {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		driver=DriverUtil.getBrowserInstance("chrome");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(200,TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	@Test
	public void f() {
		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
		WebElement s = driver.findElement(By.id("myInput"));
		Actions action=new Actions(driver);
		action
		.keyDown(s,Keys.SHIFT)
		.sendKeys(s,"w").pause(3000)
		.sendKeys(s,"e").pause(3000)
		.sendKeys(s,"a").pause(3000)
		.sendKeys(s,"r").pause(3000)
		.build().perform();
		driver.findElement(By.xpath("//*[@id=\'myInputautocomplete-list\']/div[3]")).click();
		driver.findElement(By.xpath("/html/body/div[1]/form/input")).click();
		Set<String> w=driver.getWindowHandles();
		String d=w.toArray()[1].toString();
		driver.switchTo().window(d);
		WebElement act=driver.findElement(
				By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[1]/center[1]"));
		Assert.assertEquals(act.getText(),"Summer wear");
	}

}
