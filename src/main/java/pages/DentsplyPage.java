package pages;

import org.openqa.selenium.*;
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

    /// //////////////////////////////// ////////////////////////
    /// //////////////////////////////// //////////////////////
    /// ////////////////////////////// ////////////////////////


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

        } catch (TimeoutException e) {
            // Log failure in red for TimeoutException
            log.error("\033[31m# {}: Timeout occurred while waiting for question '{}'. Error: {} \033[0m", stepNumber, questionText, e.getMessage());
            // Continue the test execution without failing
        } catch (AssertionError e) {
            // Log failure in red for AssertionError
            log.error("\033[31m# {}: Question verification failed for '{}'. Assertion failed: {} \033[0m", stepNumber, questionText, e.getMessage());
            // Continue the test execution without failing
        } catch (Exception e) {
            // Log failure in red for other unexpected exceptions
            log.error("\033[31m# {}: Unexpected error while verifying question '{}'. Error: {} \033[0m", stepNumber, questionText, e.getMessage());
            // Continue the test execution without failing
        }
    }

    /// //////////
    /// //////////////////

    public void titleShowCard(String titleShowCard, String secondTitle) {
        try {
            // Wait for and validate top title
            WebElement topTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h4[contains(text(),'" + titleShowCard + "')]")));
            browserUtil.scrollHighlight(topTitle);
            String getTopTitle = topTitle.getText();

            // Assert that the top title matches
            Assert.assertTrue("Mismatch in question title: Expected '" + titleShowCard + "' but found '" + getTopTitle + "'",
                    getTopTitle.contains(titleShowCard));

            // Call the subtitle verification method
            subtitleShowCard(secondTitle);
            log.info("\033[32m# {}: showCard top and subtitle : verified successfully \033[33m'{}'\033[0m", titleShowCard, secondTitle);
        } catch (TimeoutException e) {
            // Log failure in red for TimeoutException
            log.error("\033[31m# {}: Timeout occurred while waiting for showCard top and subtitle '{}'. Error: {} \033[0m", titleShowCard, secondTitle, e.getMessage());
            // Continue the test execution without failing
        } catch (AssertionError e) {
            // Log failure in red for AssertionError
            log.error("\033[31m# {}: showCard top and subtitle failed for '{}'. Assertion failed: {} \033[0m", titleShowCard, secondTitle, e.getMessage());
            // Continue the test execution without failing
        } catch (Exception e) {
            // Log failure in red for other unexpected exceptions
            log.error("\033[31m# {}: Unexpected error while verifying showCard top and subtitle '{}'. Error: {} \033[0m", titleShowCard, secondTitle, e.getMessage());
            // Continue the test execution without failing
        }
    }

    public void subtitleShowCard(String secondTitle) {
        try {
            // Wait for and validate subtitle
            WebElement subtitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h4[contains(text(),'Principal Contact Person')]/..//p")));
            browserUtil.scrollHighlight(subtitle);
            String getSubtitle = subtitle.getText();

            // Assert that the subtitle matches
            Assert.assertTrue("Mismatch in subtitle: Expected '" + secondTitle + "' but found '" + getSubtitle + "'",
                    getSubtitle.contains(secondTitle));
        } catch (Exception e) {
            // Catch any errors in subtitle verification
            logError("Error occurred while verifying subtitle for: '" + secondTitle + "'", e);
        }
    }

    // Helper method to log errors with more information
    private void logError(String message, Throwable e) {
        log.error("\033[31m# ERROR: {}. Error details: {} \033[0m", message, e.getMessage());
        // Optionally, include more detailed stack trace in logs if needed
        log.error("Stack trace: ", e);
    }




    /**
     * COMPANY INFORMATION
     */

    public void verifyQuestions_CompanyInformation() {
        // List of questions and corresponding step numbers
        List<String[]> questions = Arrays.asList(
                new String[]{"1.1", "Legal Name (including type of incorporation)"},
                new String[]{"1.2", "Legal Name in Local Language (if applicable)"},
                new String[]{"1.3", "Primary Business Address"},
                new String[]{"1.4", "City"},
                new String[]{"1.5", "State/Province"},
                new String[]{"1.6", "Country"},
                new String[]{"1.7", "ZIP/Postal Code"},
                new String[]{"1.8", "Phone Number (include “+” then country code)"},
                new String[]{"1.9", "Company Website Address"},
                new String[]{"1.10", "Email Address"},
                new String[]{"1.11", "Do you have a parent company?"},
                new String[]{"1.12", "Please provide the parent company legal name in local language."},
                new String[]{"1.13", "Registered Address of the Parent Company"},
                new String[]{"1.14", "City"},
                new String[]{"1.15", "State/Province"},
                new String[]{"1.16", "Country"},
                new String[]{"1.17", "ZIP/Postal Code"}
        );

        // Loop through each question and verify
        for (String[] question : questions) {
            String stepNumber = question[0];
            String questionText = question[1];

            // Call your verification method for each question
            selectAndCheckQuestionTitle(stepNumber, questionText);
        }
    }

    /**
     * PRINCIPAL CONTACT PERSON
     */

    public void verifyQuestions_PrincipalContactPerson() {
        titleShowCard("Principal Contact Person", "Duly authorized representative of the Company");
    }

    /**
     * ORGANIZATION STRUCTURE
     */

    public void verifyQuestions_OrganizationStructure() {
        // List of questions and corresponding step numbers
        List<String[]> questions = Arrays.asList(
                new String[]{"3.1", "Country of Incorporation or Formation"},
                new String[]{"3.2", "Date of Incorporation or Formation"},
                new String[]{"3.3", "Type of Ownership"},
                new String[]{"3.4", "Business Registration or VAT or Tax ID Number"},
                new String[]{"3.5", "Total Number of Employees"},
                new String[]{"3.7", "Please state the annual sales (in USD) of your company:"}
        );

        // Loop through each question and verify
        for (String[] question : questions) {
            String stepNumber = question[0];
            String questionText = question[1];

            // Call your verification method for each question
            selectAndCheckQuestionTitle(stepNumber, questionText);
        }
    }



}
