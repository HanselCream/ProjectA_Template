package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DentsplyPage {
    private static final Logger log = LoggerFactory.getLogger(DentsplyPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    public BrowserUtil browserUtil;

    public DentsplyPage(WebDriver driver, WebDriverWait wait, BrowserUtil browserUtil) {
        this.driver = driver;
        this.wait = wait;
        this.browserUtil = browserUtil;
    }

    public void openHomePage() {
        driver.get("https://dentsply.ethixcloud.com/login");
        driver.manage().window().maximize();
        log.info("Opened homepage and maximized window.");
    }

    public void rightNavigation(String titleSectionName) {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='list-group']/button[contains(text(),'"+titleSectionName+"')]")));
        title.click();
    }


    public void waitLoadPage() {
        try {
            WebElement loader = driver.findElement(By.xpath("//div[@class='loader-container text-center']"));
            wait.until(ExpectedConditions.invisibilityOf(loader));
            log.info("Page loader is no longer visible.");
        } catch (Exception e) {
            log.warn("Page loader not found or already invisible: {}", e.getMessage());
        }
    }

    public void emailAddress(String email) {
        WebElement emailTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@class='input__input-container']//input[@data-test-id])[1]")));
        emailTextBox.clear();
        emailTextBox.sendKeys(email);
        log.info("Entered email: {}", email);
    }

    public void password(String password) {
        WebElement passwordTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@class='input__input-container']//input[@data-test-id])[2]")));
        passwordTextBox.clear();
        passwordTextBox.sendKeys(password);
        log.info("Entered password.");
    }

    public void logInButton() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[contains(@class,'login__button')])[1]")));
        loginButton.click();
        log.info("Clicked on the login button.");
    }

    public void complianceDashBoard() {
        WebElement dashboardButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='card-body']")));
        dashboardButton.click();
        log.info("Navigated to compliance dashboard.");
    }

    public void startButton() {
        WebElement startButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[contains(@class,'btn-outline-primary')])[2]")));
        startButton.click();
        log.info("Clicked on the start button.");
    }

    public void confirmLanguage(String expectedLanguage) {
        WebElement languageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@class='nav-link dropdown-toggle']/span")));
        String actualLanguage = languageElement.getText().trim();

        if (!actualLanguage.equals(expectedLanguage)) {
            WebElement languageDropdown = driver.findElement(By.xpath("//a[@class='nav-link dropdown-toggle']"));
            languageDropdown.click();

            WebElement selectedLanguage = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//ul[@class='dropdown-menu dropdown-menu-right show']//a[text()='" + expectedLanguage + "']")));
            selectedLanguage.click();

            wait.until(ExpectedConditions.textToBePresentInElement(languageElement, expectedLanguage));
            actualLanguage = languageElement.getText().trim();
        }
        Assert.assertEquals("Language mismatch",  actualLanguage, expectedLanguage);
        log.info("Language confirmed as: {}", expectedLanguage);
    }

    public void assertCompanyInformationLabels() {
        List<String> expectedLabels = Arrays.asList(
                "1.1 Legal Name (including type of incorporation)",
                "1.2 Legal Name in Local Language (if applicable)",
                "1.3 Primary Business Address",
                "1.4 City",
                "1.5 State/Province",
                "1.6 Country",
                "1.7 ZIP/Postal Code",
                "1.8 Phone Number (include “+” then country code)",
                "1.9 Company Website Address",
                "1.10 Email Address",
                "1.11 Do you have a parent company?",
                "Yes",
                "No",
                "1.12 Please provide the parent company legal name in local language.",
                "1.13 Registered Address of the Parent Company",
                "1.14 City",
                "1.15 State/Province",
                "1.16 Country",
                "1.17 ZIP/Postal Code"
        );

        for (int i = 1; i <= expectedLabels.size(); i++) {
            WebElement labelElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("(//label)[" + i + "]")));
            String actualLabel = labelElement.getText().replace("*", "").trim();
            Assert.assertEquals(actualLabel, expectedLabels.get(i - 1), "Label mismatch at index: " + i);
        }
        log.info("Company information labels verified successfully.");
    }

    public void selectAndCheckQuestionTitle(String stepNumber, String questionText) {
        try {
            WebElement questionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[contains(text(),'" + questionText + "')]")));

            String actualText = questionElement.getText().trim();
            browserUtil.scrollHighlight(questionElement);

            // Assert if the actual text matches the expected text
            Assert.assertTrue("Mismatch in question title.", actualText.contains(questionText));

            // Log success in green
            log.info("\033[32m# {}: Question : verified successfully !! \033[33m'{}'\033[0m", stepNumber, questionText);
        } catch (AssertionError e) {
            // Log failure in red
            log.error("\033[31m# {}: Question verification failed for '{}'. Error: {} \033[0m", stepNumber, questionText, e.getMessage());
        }
    }


}
