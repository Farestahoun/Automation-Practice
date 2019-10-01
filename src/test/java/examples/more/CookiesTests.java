package examples.more;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CookiesTests {

	WebDriver driver;

	@BeforeTest
	public void javaScriptExecutorTest() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://the-internet.herokuapp.com/");
	}
	
	@Test
	public void testAddCookie() {
		Cookie cookie = new Cookie.Builder("test", "123").domain("the-internet.herokuapp.com").build();
		driver.manage().addCookie(cookie);
		assertTrue(driver.manage().getCookieNamed(cookie.getName()) != null, "Cookie was not added");
	}
	
	@Test
	public void testDeleteCookie() {
		Cookie cookie = new Cookie.Builder("optimizelyBuckets", "%7B%TD").domain("the-internet.herokuapp.com").build();
		driver.manage().deleteCookie(cookie);
		assertFalse(driver.manage().getCookieNamed(cookie.getName()) != null, "Cookie was not deleted");
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}