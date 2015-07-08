package edu.pitt.softwaretesting.deliverable3.test;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

//As a user
//I want to manage my account
//So that I can change my personal information

public class testAmazonManageAccount {

	static private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.firefox.bin", "D:\\Mozilla Firefox\\firefox.exe");  
		driver = new FirefoxDriver();
		driver.get("https://www.amazon.com/ap/signin?_encoding=UTF8&openid.assoc_handle=usflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Fyourstore%2Fhome%3Fie%3DUTF8%26action%3Dsign-out%26path%3D%252Fgp%252Fyourstore%252Fhome%26ref_%3Dnav_youraccount_signout%26signIn%3D1%26useRedirectOnSuccess%3D1");
		Thread.sleep(10000);
		driver.findElement(By.id("ap_email")).clear();
		driver.findElement(By.id("ap_email")).sendKeys("testingcyw@gmail.com");
		driver.findElement(By.id("ap_password")).clear();
		driver.findElement(By.id("ap_password")).sendKeys("wcytesting");
		driver.findElement(By.id("signInSubmit-input")).click();
		driver.findElement(By.linkText("Your Account")).click();

	}

	//	Scenario1: edit my name in the change account settings
	//	Given my new name as "Helen"
	//	When I am logged in and change name to the new name
	//	Then the "success" alert should be shown and the name content should show "Helen"

	@Test
	public void testChangeAccountName() throws InterruptedException{
		
		driver.findElement(By.partialLinkText("Change Account Settings")).click();
		driver.findElement(By.id("auth-cnep-edit-name-button")).click();
		driver.findElement(By.id("ap_customer_name")).clear();
		driver.findElement(By.id("ap_customer_name")).sendKeys("Helen");
		driver.findElement(By.id("cnep_1C_submit_button")).click();

		try{
			String actualMessage = driver.findElement(By.className("a-alert-heading")).getText();
			assertEquals("Success", actualMessage);

			String actualName = driver.findElement(By.className("a-container")).getText();
			assertTrue(actualName.contains("Helen"));

		}catch (NoSuchElementException nseex){
			fail();
		}
	}

	//	Scenario2: delete an item from the wish list
	//  Given there are some items in the wish list
	//	When I am logged in and delete one existing item (eg, pillow) in the wish list
	//	Then this item should not exist in the wish list

	@Test
	public void testWishListDelete(){

		try{
			 driver.findElement(By.linkText("Wish Lists")).click();
			 driver.findElement(By.linkText("Delete item")).click();

			 // Thread.sleep(10000);
			 WebElement outer = driver.findElement(By.id("item-page-wrapper"));
			 WebElement inner = outer.findElement(By.className("g-items-sec-atf")).findElement(By.className("a-box")).findElement(By.className("a-box-inner"));
			 WebElement superInner = inner.findElement(By.className("a-alert-content"));
			 String actualMessage = superInner.getText();
			 assertTrue(actualMessage.contains("Deleted"));
		}catch (NoSuchElementException nseex){
			fail();
		}

	}

	@After
	  public void tearDown() throws Exception {
	    driver.quit();
	 }

}
