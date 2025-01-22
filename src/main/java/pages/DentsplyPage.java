package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DentsplyPage {
    public WebDriver driver;
    public WebDriverWait wait;
    public BrowserUtil browserUtil;


    public DentsplyPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;

    }


    public void openHomePage() {
        driver.get("https://dentsply.ethixcloud.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='input__input-container']//input[@data-test-id])[1]")));
    }


    public void emailAddress(String one) {
        WebElement emailTextBox = driver.findElement(By.xpath("(//div[@class='input__input-container']//input[@data-test-id])[1]"));
        wait.until(ExpectedConditions.visibilityOf(emailTextBox));
        emailTextBox.click();
        emailTextBox.sendKeys(one);
    }


    public void password(String two) {
        WebElement passwordTextBox = driver.findElement(By.xpath("(//div[@class='input__input-container']//input[@data-test-id])[2]"));
        wait.until(ExpectedConditions.visibilityOf(passwordTextBox));
        passwordTextBox.click();
        passwordTextBox.sendKeys(two);
    }

    public void logInButton() {
        WebElement loginButton = driver.findElement(By.xpath("(//button[@class='login__button call-to-action call-to-action--icon-moderate call-to-action--theme-primary login__button'])[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    /**
     * Language Select
     */

    public void selectLanguage(String enterLanguage) {
        WebElement languageDD = driver.findElement(By.xpath("//a[@class='nav-link dropdown-toggle']"));
        languageDD.click();

        WebElement select = driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right show']" +
                "/li/a[text()='"+enterLanguage+"']"));

        wait.until(ExpectedConditions.elementToBeClickable(select));
        select.click();


    }

    /**
     * complianceDashboard
     */

    public void complianceDashBoard() {
        WebElement qpa = driver.findElement((By.xpath("//div[@class='card-body']")));
        wait.until(ExpectedConditions.elementToBeClickable(qpa));
        qpa.click();
    }

    //Edited Continue Button focus
    public void startButton() {
        WebElement button = driver.findElement(By.xpath("(//button[@class='btn btn-outline-primary mr-2'])[2]"));
        wait.until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

    public void confirmLanguage(String expectedLanguage) {
        WebElement languageElement = driver.findElement(By.xpath("//a[@class='nav-link dropdown-toggle']/span"));
        String actualLanguage = languageElement.getText();
        System.out.println("actual Language: " + actualLanguage);

        if (!actualLanguage.equals(expectedLanguage)) {
            WebElement dropDownLanguage = driver.findElement(By.xpath("(//a[@class='nav-link dropdown-toggle'])[1]"));
            dropDownLanguage.click();

            WebElement selectLanguage = driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right show']//li/a[text()='" + expectedLanguage + "']"));
            selectLanguage.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.textToBePresentInElement(languageElement, expectedLanguage));

            actualLanguage = languageElement.getText();

        }

        Assert.assertEquals("Language incorrect", expectedLanguage, actualLanguage);
    }


    /**
     * DDQ Questionnaire
     */

    public void companyInformation() {
        WebElement one_one = driver.findElement(By.xpath("(//label)[1]"));
        String legalName = one_one.getText();
        wait.until(ExpectedConditions.elementToBeClickable(one_one));
        System.out.println(legalName);
        Assert.assertEquals("* 1.1 Legal Name (including type of incorporation)", legalName);
    }

    public void assertCompanyInformationLabels() {
        List<String> expectedLabels = getExpectedLabelsOf_CompanyInformation();
        List<String> passedAssertions = new ArrayList<>();
        List<String> failedAssertions = new ArrayList<>();

        for (int i = 0; i < expectedLabels.size(); i++) {
            String actualLabel = getLabelTextByIndex(i + 1);

            // Normalize both expected and actual text for comparison (trimming, lowercase)
            if (actualLabel.equals(expectedLabels.get(i).trim())) {
                // Log passed assertion
                passedAssertions.add("Passed at index " + (i + 1) + ": '" + actualLabel + "'");
            } else {
                // Log failed assertion
                failedAssertions.add("Failed at index " + (i + 1) + ": Expected: '" + expectedLabels.get(i) + "', Actual: '" + actualLabel + "'");
            }
        }
        browserUtil.logAssertions(passedAssertions, failedAssertions);
    }


    /**
     *COMPANY INFORMATION
     */
    private List<String> getExpectedLabelsOf_CompanyInformation() {
        System.out.println("=======COMPANY INFORMATION=========");
        return Arrays.asList(
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
    }


    private String getLabelTextByIndex(int index) {
        String dynamicXPath = "(//label)[" + index + "]";
        WebElement labelElement = driver.findElement(By.xpath(dynamicXPath));

        // Scroll into view and wait for visibility
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", labelElement);
        wait.until(ExpectedConditions.visibilityOf(labelElement));

        // Return cleaned-up text
        return labelElement.getText().replace("*", "").trim();
    }

    String expectedTitle = "Principal Contact Person";
    String expectedSubtitle = "Duly authorized representative of the Company";
    String expectedDetails_Name = "Contact Complete Name (First name, Middle name, Surname)";
    String expectedDetails_OfficeTelephoneNumber = "Office Telephone Number";
    String expectedDetails_MobileTelephoneNumber = "Mobile Telephone Number";
    String expectedDetails_Email = "Email";
    String expectedDetails_Title = "Title";

    //WebElement subtitle = driver.findElement(By.xpath("//div[@class='collapse show card mt-5']//p[contains(text(),'"+expectedTitle+"')]"));
    //WebElement name = driver.findElement(By.xpath("//thead[@role='rowgroup']/tr/th[text()='"+expectedDetails_Name+"']"));



    public void assertPrincipalContactPersonLabels(String text1) {
        WebElement title = driver.findElement(By.xpath(
                "//div[@class='collapse show card mt-5']//h4[contains(text(),'"+text1+"')]"));
        String actualTitle = title.getText();
        // Scroll into view and wait for visibility
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", title);
        wait.until(ExpectedConditions.visibilityOf(title));

        Assert.assertEquals("NOT EQUAL", expectedTitle, actualTitle);

    }

    /**
     * Principal Contact Person
     *
    */
    private List<String> getExpectedLabelsOf_PrincipalContact(String text1) {
        System.out.println("=======Principal Contact Person=========");
        return Arrays.asList(
                "Principal Contact Person",
                "Duly authorized representative of the Company",
                "Contact Complete Name (First name, Middle name, Surname)",

                "Title",
                "Office Telephone Number",
                "Mobile Telephone Number",
                "Email"
        );
    }

    //public void






}


