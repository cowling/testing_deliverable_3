package edu.pitt.softwaretesting.deliverable3.test;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

//As a user
//I want to log in
//So that I can access my Amazon account

public class testAmazonLogIn {

	static private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.firefox.bin", "D:\\Mozilla Firefox\\firefox.exe");  
		driver = new FirefoxDriver();
		driver.get("https://www.amazon.com/ap/signin?_encoding=UTF8&openid.assoc_handle=usflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Fyourstore%2Fhome%3Fie%3DUTF8%26ref_%3Dnav_ya_signin");
        Thread.sleep(10000); // sleep 10 seconds

	}

	//	Scenario1: log in with correct username and password
	//	Given a correct username 
	//	And an correct password
	//	When I try to log in with those credentials
	//	Then I should succeed logging in my home page and see "hello, sign in" is replaced by "hello, (my username)" on the right of navigation bar

	@Test
	public void testLogInSucceed(){

		driver.findElement(By.id("ap_email")).clear();
		// Fill in your own username and password
		driver.findElement(By.id("ap_email")).sendKeys("testingcyw@gmail.com");
		driver.findElement(By.id("ap_password")).clear();
		driver.findElement(By.id("ap_password")).sendKeys("wcytesting");
		driver.findElement(By.id("signInSubmit-input")).click();

		String newPageUrl = driver.getCurrentUrl();
		assertEquals("https://www.amazon.com/gp/yourstore/home?ie=UTF8&ref_=nav_ya_signin&", newPageUrl);
		
		try{
			String actualText = driver.findElement(By.cssSelector("#nav-link-yourAccount > span.nav-line-1")).getText();
			assertEquals("Hello, Chenyi", actualText);
		} catch(NoSuchElementException nseex){
			fail();
		}


	}

	//	Scenario2: log in with correct username, but incorrect password
	//	Given a correct username
	//	And an incorrect password
	//	When I try to log in with those credentials
	//	Then I should receive an error message with "there was a problem with your request" on it and I am given the opportunity to create a new password

	@Test
	public void testLogInFail() throws InterruptedException{

		driver.findElement(By.id("ap_email")).clear();
		driver.findElement(By.id("ap_email")).sendKeys("abcd@");
		driver.findElement(By.id("ap_password")).clear();
		driver.findElement(By.id("ap_password")).sendKeys("1234");
		Thread.sleep(10000);
		driver.findElement(By.id("signInSubmit-input")).click();

		try{
			String actualMessage = driver.findElement(By.id("message_error")).getText();
			String expectedMessageError = "There was a problem with your request\nThere was an error with your E-Mail/Password combination. Please try again.";
			assertEquals(expectedMessageError, actualMessage);

			WebElement forgotPw = driver.findElement(By.linkText("Forgot your password?"));
			assertTrue(forgotPw.isDisplayed());
			
		} catch(NoSuchElementException nseex){
			fail();
		}

	}
	
	 @After
	  public void tearDown() throws Exception {
	    driver.quit();
	 }
}
