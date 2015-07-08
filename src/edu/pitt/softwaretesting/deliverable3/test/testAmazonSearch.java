package edu.pitt.softwaretesting.deliverable3.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

//As a user 
//I want to search
//So that I can find what I want to buy

public class testAmazonSearch {

	static private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.firefox.bin", "D:\\Mozilla Firefox\\firefox.exe");  
		driver = new FirefoxDriver();
		driver.get("http://www.amazon.com/");
	}

	//	Scenario1: search for an item
	//	Given the search key words "speedo swim cap"
	//	When enter key words in the search box
	//	Then the name of items containing "speedo swim cap" should appear on the search results

	@Test
	public void testSearchItem(){

		driver.findElement(By.id("twotabsearchtextbox")).clear();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("speedo swim cap");
		driver.findElement(By.cssSelector("input.nav-input")).click();

		try{
			WebElement resultList = driver.findElement(By.id("atfResults"));
			String resultContent = resultList.getText();
			assertTrue(resultContent.contains("Speedo"));
			assertTrue(resultContent.contains("Swim Cap"));
		}catch (NoSuchElementException nseex){
			fail();
		}
	}

	//	Scenario2: use the navigation bar and see different department links
	//	Given that I am on the main page
	//	When I view the navigation bar
	//	Then I see that it contains "Your Amazon.com", "Today's Deals", "Gift Cards" links, and so forth

	@Test
	public void testNavigationBar(){

		try{
			driver.findElement(By.linkText("Your Amazon.com"));
			driver.findElement(By.linkText("Today's Deals"));
			driver.findElement(By.linkText("Gift Cards"));
		}catch (NoSuchElementException nseex){
			fail();
		}

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
