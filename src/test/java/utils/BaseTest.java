package utils;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import pages.BrowserUtil;
import pages.DentsplyPage;
import pages.FacebookSearchPage;


public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public FacebookSearchPage facebookSearchPage;
    public DentsplyPage dentsplyPage;
    public BrowserUtil browserUtil;


    public void waitABit(int num) {
        try {
            Thread.sleep(1500);
            Thread.sleep(num);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Before
    public void setUp() {
        // Set the path to the ChromeDriver executable
        System.setProperty("chromedriver", "C:/Users/radam/IdeaProjects/ProjectA/src/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        facebookSearchPage = new FacebookSearchPage(driver, wait);
        dentsplyPage = new DentsplyPage(driver, wait);
        browserUtil = new BrowserUtil(driver, wait);


    }

    @After
    public void tearDown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }
}