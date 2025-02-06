package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BrowserUtil {

    public WebDriver driver;
    public WebDriverWait wait;

    public BrowserUtil(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // 1. Clicking
    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    // 2. JavaScript Scroll Click
    public void javaScrollClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    // 3. JavaScript Click
    public void javaClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);"); // Scroll to the top of the page

    }

    // 4. Highlight, Scroll, and Click
    public void highlightScrollClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    // 5. Highlight Only
    public void highlight(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid blue'", element);
    }

    // 6. Scroll and Highlight, remove highlight after 3 seconds
    public void scrollHighlight(WebElement element) {
        // Scroll to the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        // Apply green border for highlight
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid green'", element);
        // Wait for 3 seconds
        try {
            Thread.sleep(3000); // 3 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Remove the highlight by resetting the border
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", element);
    }


    // 7. Scroll to View
    public void scrollToView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    // 8. Wait Until Element is Present
    public void waitUntilElementPresent(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementNotPresent(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated((By) element)); // Use existing wait object
    }

    // 9. Scroll and Wait Until Element is Present
    public void scrollWaitUntilElementPresent(WebElement element) {
        scrollToView(element);
        waitUntilElementPresent(element);
    }

    // 10. Scroll to Element
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void logAssertions(List<String> passedAssertions, List<String> failedAssertions) {
        // Log passed assertions
        if (!passedAssertions.isEmpty()) {
            System.out.println("Passed assertions:");
            passedAssertions.forEach(System.out::println);
        }

        // Log failed assertions
        if (!failedAssertions.isEmpty()) {
            System.err.println("Failed assertions:");
            failedAssertions.forEach(System.err::println);
        }

        // Fail the test if there are failed assertions
        if (!failedAssertions.isEmpty()) {
            Assert.fail("Test failed with " + failedAssertions.size() + " assertion(s). See details above.");
        }
    }

    public void waitABit(int num) {
        try {
            Thread.sleep(num);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
