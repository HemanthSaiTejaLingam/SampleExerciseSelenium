package tests;

import org.testng.annotations.Test;

import util.DriverUtil;
import util.getXcelData;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Exercise_26 {
	
	WebDriver driver;
	@Test(dataProvider="xlsheet")
	public void testUsingXcel(String uname,String pass,String bname,
			String bnum,String bpass,String tpass) {
		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
		driver.findElement(By.linkText("SignIn")).click();
		driver.findElement(By.id("userName")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		driver.findElement(By.id("myInput")).sendKeys("Headphone");
		driver.findElement(By.xpath("/html/body/div[1]/form/input")).click();
		driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a"))
		.click();
		driver.findElement(By.partialLinkText("Cart")).click();
		driver.findElement(By.partialLinkText("Checkout")).click();
		driver.findElement(By.cssSelector("input[type='submit'][value='Proceed to Pay']")).click();
		if(bname.equals("Andhra Bank"))
			driver.findElement(By.xpath("//label[contains(text(),'Andhra Bank')]")).click();
		if(bname.equals("HDFC Bank"))
			driver.findElement(By.xpath("//label[contains(text(),'HDFC Bank')]")).click();
		if(bname.equals("IDBI Bank"))
			driver.findElement(By.xpath("//label[contains(text(),'IDBI Bank')]")).click();
		if(bname.equals("UCO Bank"))
			driver.findElement(By.xpath("//label[contains(text(),'UCO Bank')]")).click();
		driver.findElement(By.xpath("//*[@id=\"btn\"]")).click();
		driver.findElement(By.cssSelector("input[type='text'][name='username']")).sendKeys(bnum);
		driver.findElement(By.cssSelector("input[type='password'][name='password']")).sendKeys(bpass);
		driver.findElement(By.cssSelector("input[type='submit'][value='LOGIN']")).click();
		driver.findElement(By.name("transpwd")).sendKeys(tpass);
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		String s=driver.findElement(By.xpath("/html/body/b/section/div/div/div")).getText();
		boolean t=s.contains("Your order has been confirmed");
		driver.findElement(By.linkText("SignOut")).click();
		Assert.assertTrue(t);
	} 
	@BeforeTest
	public void beforeClass() {
		driver=DriverUtil.getBrowserInstance("chrome");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	}

	@AfterTest
	public void afterClass() {
		driver.close();
	}
	@DataProvider(name="xlsheet")
	public Object[][] loginData(){
		return getXcelData.getData
				("C:\\Users\\Training1.WIN-NFDCINOH1RK\\git\\repository2\\src\\test\\resources\\test.xlsx"
						,"testdata");
	}

}
