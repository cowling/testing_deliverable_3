package myproject;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class TestingAmazon {

/**
 * As a user
 * I would like to see Amazon provides services to users in different ways,
 * so that I can know what services Amazon provides
 */
	// Start at the home page for each test
	static WebDriver driver = new HtmlUnitDriver();
	@Before
	public void setUp() throws Exception{
		driver.get("http://www.amazon.com");
	}

	
	//Given I am on the main page
	//When I view the body
	//Then I can see it contains the words “Most Wished For in movies & TV”
	@Test
	public void testContainText() {
		// Simply check that the web page contains the words "Most Wished For in Movies & TV"
		try {
			WebElement e = driver.findElement(By.id("pageContent"));
			String elementText = e.getText();
			assertTrue(elementText.contains("Most Wished For in Movies & TV"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
		
	}
	
	@Test
	public void testNewPage(){
		//Given I am on the main page
		//When I click the link “see more” 
		//Then I should be redirected to a new page

		// find the "see more" link and click on it
		// The page you go to should include "Amazon.com: Movies: Prime Instant Video" in the title
		
		//click "see more"		
		driver.findElement(By.linkText("See more")).click();
		//get new page title
		String newPageTitle = driver.getTitle();
		assertTrue(newPageTitle.contains("Amazon.com: Movies: Prime Instant Video"));

	}

}
