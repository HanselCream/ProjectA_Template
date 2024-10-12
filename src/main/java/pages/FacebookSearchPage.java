package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FacebookSearchPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public FacebookSearchPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void waitABit() {
        try {
            Thread.sleep(2000); // Using 2 seconds to avoid long waits
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void openHomePage() {
        waitABit();
        driver.get("https://www.google.com/");
    }

    public void search(String query) {
        WebElement searchBox = driver.findElement(By.name("q"));
        waitABit();
        searchBox.sendKeys(query);
        waitABit();
        searchBox.submit();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isSearchSuccessful(String query) {
        String pageTitle = getPageTitle();
        return pageTitle.toLowerCase().contains(query.toLowerCase());
    }
}
