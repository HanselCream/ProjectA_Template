package utils;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import pages.FacebookSearchPage;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public FacebookSearchPage facebookSearchPage;

    @Before
    public void setUp() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:/Users/radam/IdeaProjects/ProjectA/src/drivers/chromedriver.exe");

        // Initialize the ChromeDriver
        driver = new ChromeDriver();

        // Initialize WebDriverWait with 10 seconds timeout
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Initialize FacebookSearchPage with the WebDriver instance
        facebookSearchPage = new FacebookSearchPage(driver, wait);
    }

    @After
    public void tearDown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }
}
