package myproject;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;
public class TestingShoppingCart {

	static WebDriver driver = new HtmlUnitDriver();
	@Before
	public void setUp() throws Exception{
		driver.get("http://www.amazon.com/Speedo-Silicone-Solid-Swim-Black/dp/B00070QEAS/ref=sr_1_1?ie=UTF8&qid=1436237668&sr=8-1&keywords=speedo+swim+cap");
	}
	//As a user
	//I want to manage my shopping cart
	//So that I can change items I will buy
	@Test
	public void testAddToCart() throws InterruptedException{
		 
		//add one item into the shopping cart
		//Given the shopping cart is empty and I am on the site of first result searching "speedo swim cap"
		//When I choose black color and one quantity
		//And add it to the cart
		//Then it should be redirected to a new page showing “Added to Cart”
		
		//select black		
		WebElement temp = driver.findElement(By.xpath("//img[@src='http://ecx.images-amazon.com/images/I/512WMATRMYL._SS36_.jpg']"));
		temp.click();
		Thread.sleep(2000);
		//select one item
		new Select(driver.findElement(By.id("quantity"))).selectByVisibleText("1");
		Thread.sleep(2000);
		//add to cart
		driver.findElement(By.name("submit.add-to-cart")).click();
		Thread.sleep(2000);
		//confirm added to cart in new page
		String confirmText = driver.findElement(By.id("cart-page-wrap")).getText();
		assertTrue(confirmText.contains("Added to Cart"));
		
	}
	
	@Test
	public void testDeleteItem() throws InterruptedException{
		// delete the item from the shopping cart
		//Given the item of first result searching "speedo swim cap" is in the shopping cart
		//When I delete this item from the cart
		//Then this item should be removed and show “empty”
		
		//locate to the web site with one item in shopping cart
		WebElement temp = driver.findElement(By.xpath("//img[@src='http://ecx.images-amazon.com/images/I/512WMATRMYL._SS36_.jpg']"));
		temp.click();
		Thread.sleep(2000);
		new Select(driver.findElement(By.id("quantity"))).selectByVisibleText("1");
		Thread.sleep(2000);
		driver.findElement(By.name("submit.add-to-cart")).click();
		Thread.sleep(2000);
		//click view cart
		driver.findElement(By.xpath("//a[starts-with(@id, 'hlb-view-cart')]")).click();
		Thread.sleep(2000);
		//click delete item
		driver.findElement(By.xpath("//input[starts-with(@name, 'submit.delete')]")).click();
		Thread.sleep(2000);
		//show cart empty
		String remove = driver.findElement(By.id("sc-active-cart")).getText();
		assertTrue(remove.contains("empty"));
	}
	

}
