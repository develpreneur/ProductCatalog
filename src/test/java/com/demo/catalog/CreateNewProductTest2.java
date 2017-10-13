package com.demo.catalog;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class CreateNewProductTest2 {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
//		String geckoPath = "./src/test/resources/geckodriver/geckodriver";
//		System.setProperty("webdriver.firefox.marionette", geckoPath);
//		System.setProperty("webdriver.gecko.driver", geckoPath);
//
//		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//		driver = new FirefoxDriver(capabilities);
		driver = new SafariDriver();
		baseUrl = "http://localhost:9080/catalog/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testCreateNewProduct() throws Exception {
		driver.get(baseUrl + "/catalog/");
		driver.findElement(By.linkText("Product")).click();
		driver.findElement(By.linkText("New Product")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("TestProduct");
		driver.findElement(By.id("summary")).clear();
		driver.findElement(By.id("summary")).sendKeys("Some Product Description");
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys("A more Detailed Description");
		driver.findElement(By.id("price")).clear();
		driver.findElement(By.id("price")).sendKeys("$900.00");
		driver.findElement(By.id("qty")).clear();
		driver.findElement(By.id("qty")).sendKeys("99");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		driver.findElement(By.linkText("<<-- Return to Home")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
