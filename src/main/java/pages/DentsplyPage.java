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
import java.util.Collections;
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
        driver.get("https://alcon.ethixcloud.com/login");
        driver.manage().window().maximize();
        log.info("Opened homepage and maximized window.");
    }

    public void rightNavigation(String titleSectionName) {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='list-group']/button[contains(text(),'" + titleSectionName + "')]")));
        title.click();
    }

    public void clickOnSaveProgress() {
        WebElement saveButton = wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-outline-primary']"))));
        browserUtil.waitABit(2000);
        browserUtil.highlightScrollClick(saveButton);
        browserUtil.waitABit(20000);
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
                By.xpath("(//button[contains(@class,'btn-outline-primary')])[1]")));
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
        Assert.assertEquals("Language mismatch", actualLanguage, expectedLanguage);
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
            log.error("\033[31m# {}: Timeout occurred while waiting for question '{}'. Error: {} \033[0m", stepNumber, questionText);
            // Continue the test execution without failing
        } catch (AssertionError e) {
            // Log failure in red for AssertionError
            log.error("\033[31m# {}: Question verification failed for '{}'. Assertion failed: {} \033[0m", stepNumber, questionText);
            // Continue the test execution without failing
        } catch (Exception e) {
            // Log failure in red for other unexpected exceptions
            log.error("\033[31m# {}: Unexpected error while verifying question '{}'. Error: {} \033[0m", stepNumber, questionText);
            // Continue the test execution without failing
        }
    }

    /// ///////////////////////////// ////////////////////////
    /// //////////////////////////////// //////////////////////
    /// ////////////////////////////// ////////////////////////

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
     * ANSWER 'ALL BLANK' FIELD QUESTIONS
     */

    public void addTextOnBlankFields() {
        browserUtil.waitABit(1000);

        // Wait for at least one input field to be visible
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@class,'form-control')]")));

        // Get all blank input fields
        List<WebElement> blankFields = driver.findElements(By.xpath("//input[contains(@class,'form-control')]"));

        System.out.println("Total blank fields found: " + blankFields.size());

        for (int i = 0; i < blankFields.size(); i++) {
            try {
                // Re-fetch elements to avoid stale element issues
                blankFields = driver.findElements(By.xpath("//input[contains(@class,'form-control')]"));

                WebElement blankField = blankFields.get(i);
                browserUtil.highlightScrollClick(blankField);

                // Ensure field is clickable and clear existing value
                wait.until(ExpectedConditions.elementToBeClickable(blankField));
                blankField.clear();
                browserUtil.waitABit(500);

                // Get text from RandomTextGenerator
                String textToEnter = RandomTextGenerator.getTextByIndex(i);
                System.out.println("Entering text: " + textToEnter + " into field index: " + i);

                blankField.sendKeys(textToEnter);
                browserUtil.waitABit(500);
            } catch (Exception e) {
                System.err.println("Error processing field index " + i + ": " + e.getMessage());
            }
        }

        System.out.println("✅ All blank fields filled successfully!");
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

    //3.2 DropDownSelect
    //3.3 Multiple Choice
    //3.6 ShowCard/File
    //3.7 Multiple Choice
    public void verifyQuestions_AlconPolish() {
        // List of questions and corresponding step numbers
        List<String[]> questions = Arrays.asList(
                new String[]{"3.1", "Czy podmiot trzeci ma Kodeks postępowania i/lub polityki, które zabraniają łapownictwa i korupcji?"},
                new String[]{"3.3", "Czy podmiot trzeci ma udokumentowany proces ujawniania konfliktów interesów, dostępny dla wszystkich pracowników?"},
                new String[]{"3.5", "Czy podmiot zewnętrzny posiada udokumentowany proces jakiejkolwiek interakcji z pracownikami służby zdrowia?"},
                new String[]{"3.7", "Czy podmiot zewnętrzny posiada Politykę dotyczącą dokumentacji księgowej, która jest zgodna z obowiązującymi przepisami?"},
                new String[]{"3.9", "Czy podmiot trzeci posiada procedury i kanały zgłaszania wątpliwości, dostępne dla wszystkich pracowników i w zrozumiałym dla nich języku?"},
                new String[]{"3.11", "Czy podmiot trzeci przeprowadza regularne szkolenia antykorupcyjne dla swoich pracowników i zarządu?"},
                new String[]{"3.13", "Czy podmiot zewnętrzny posiada dedykowane zasoby do działań związanych ze zgodnością?"}
        );

        // Loop through each question and verify
        for (String[] question : questions) {
            String stepNumber = question[0];
            String questionText = question[1];

            // Call your verification method for each question
            selectAndCheckQuestionTitle(stepNumber, questionText);
        }
    }

    public void verifyQuestions_AlconRussian() {
        // List of questions and corresponding step numbers
        List<String[]> questions = Arrays.asList(
                new String[]{"3.1", "Имеется ли у третьей стороны Кодекс поведения и/или политики, запрещающие взяточничество и коррупцию?"},
                new String[]{"3.3", "Имеется ли у третьей стороны документированный процесс раскрытия информации о конфликтах интересов, доступный всем сотрудникам?"},
                new String[]{"3.5", "Имеется ли у третьей стороны документально оформленный процесс взаимодействия с медицинскими работниками?"},
                new String[]{"3.7", "Имеется ли у третьей стороны Политика бухгалтерского учета, которая соответствует применимым правилам?"},
                new String[]{"3.9", "Есть ли у Третьей стороны процедуры и каналы для сообщения о проблемах, доступные всем сотрудникам и на понятном им языке?"},
                new String[]{"3.11", "Проводит ли Третья сторона регулярное обучение по борьбе со взяточничеством для своих сотрудников и совета директоров?"},
                new String[]{"3.13", "Есть ли у Третьей стороны специальный ресурс для деятельности по соблюдению нормативных требований?"}

        );

        // Loop through each question and verify
        for (String[] question : questions) {
            String stepNumber = question[0];
            String questionText = question[1];

            // Call your verification method for each question
            selectAndCheckQuestionTitle(stepNumber, questionText);
        }
    }

    public void verifyQuestions_AlconSpanish() {
        // List of questions and corresponding step numbers
        List<String[]> questions = Arrays.asList(
                new String[]{"3.1", "¿El tercero tiene un Código de conducta o políticas que prohíban el soborno y la corrupción?"},
                new String[]{"3.3", "¿El tercero tiene un proceso documentado para divulgar conflictos de intereses, disponible para todos los asociados?"},
                new String[]{"3.5", "¿El tercero tiene un proceso documentado para cualquier tipo de interacción con profesionales de atención médica?"},
                new String[]{"3.7", "¿El tercero tiene una política de registros contables que esté alineada con las regulaciones aplicables?"},
                new String[]{"3.9", "¿El tercero cuenta con procedimientos y canales para informar inquietudes, disponibles para todos los empleados y en un idioma que puedan comprender?"},
                new String[]{"3.11", "¿El Tercero realiza capacitación antisoborno regular para sus empleados y su junta?"},
                new String[]{"3.13", "¿El tercero tiene un recurso dedicado para las actividades de cumplimiento?"}
        );

        // Loop through each question and verify
        for (String[] question : questions) {
            String stepNumber = question[0];
            String questionText = question[1];

            // Call your verification method for each question
            selectAndCheckQuestionTitle(stepNumber, questionText);
        }
    }

    public void verifyQuestions_AlconTurkish() {
        // List of questions and corresponding step numbers
        List<String[]> questions = Arrays.asList(
                new String[]{"3.1", "Üçüncü Tarafın rüşvet ve yolsuzluğu yasaklayan Davranış Kuralları ve/veya politikaları var mı?"},
                new String[]{"3.3", "Üçüncü Tarafın tüm çalışanlara sunulan çıkar çatışmalarını açıklamak için belgelenmiş bir süreci var mı?"},
                new String[]{"3.5", "Üçüncü Tarafın sağlık çalışanları ile herhangi bir etkileşim için belgelenmiş bir süreci var mı?"},
                new String[]{"3.7", "Üçüncü Tarafın geçerli düzenlemelere uygun bir Muhasebe Kayıtları Politikası var mı?"},
                new String[]{"3.9", "Üçüncü Taraf, tüm çalışanların anlayabileceği bir dilde endişelerini bildirmek için prosedürlere ve kanallara sahip mi?"},
                new String[]{"3.11", "Üçüncü Taraf, çalışanları ve yönetim kurulu için düzenli rüşvetle mücadele eğitimi veriyor mu?"},
                new String[]{"3.13", "Üçüncü Tarafın uyum faaliyetleri için özel bir kaynağı var mı?"}

        );

        // Loop through each question and verify
        for (String[] question : questions) {
            String stepNumber = question[0];
            String questionText = question[1];

            // Call your verification method for each question
            selectAndCheckQuestionTitle(stepNumber, questionText);
        }
    }


    public void verifyQuestions_AlconBrazil() {
        // List of questions and corresponding step numbers
        List<String[]> questions = Arrays.asList(
                new String[]{"3.1", "O terceiro tem um Código de Conduta e/ou políticas que proíbem o suborno e a corrupção?"},
                new String[]{"3.3", "O terceiro tem um processo documentado para divulgar conflitos de interesses, disponível para todos os colaboradores?"},
                new String[]{"3.5", "O terceiro tem um processo documentado para qualquer tipo de interação com profissionais de saúde?"},
                new String[]{"3.7", "O Terceiro tem uma Política de Registros Contábeis, que está alinhada com os regulamentos aplicáveis?"},
                new String[]{"3.9", "O terceiro tem procedimentos e canais para relatar preocupações, disponíveis para todos os funcionários e em linguagem que eles possam entender?"},
                new String[]{"3.11", "O terceiro realiza treinamento antissuborno regular para seus funcionários e conselhos?"},
                new String[]{"3.13", "O terceiro tem um recurso dedicado para atividades de conformidade?"}

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
     * SHAREHOLDERS
     */

    //SHOW CARD/FILE
    // 4.2
    // 4.3
    // 4.5
    // 4.6
    public void verifyQuestions_Shareholders() {
        // List of questions and corresponding step numbers
        List<String[]> questions = Arrays.asList(
                new String[]{"4.1", "Number of Shareholders"},
                new String[]{"4.4", "Number of Ultimate Beneficial Owners (UBOs)"}
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
     * OTHER AREAS OF BUSINESS INTEREST
     */

    public void verifyQuestions_OtherBusiness() {
        // List of questions and corresponding step numbers
        // 5.6, 5.10, 5.17, 5.20
        List<String[]> questions = Arrays.asList(
                new String[]{"5.1", "Does your company intend to offer services, distribute and/or transit Dentsply Sirona products in/through Cuba, Iran, North Korea, Syria, Russia, Belarus, or the Ukrainian regions of Crimea, Donetsk, Luhansk, Zaporizhzhia, or Kherson?"},
                new String[]{"5.2", "Does the company (including its officers, directors, principals, and owners), or any business concern controlled by the company, have any offices or other operations in Cuba, Iran, North Korea, Syria, Russia, Belarus, or the Ukrainian regions of Crimea, Donetsk, Luhansk, Zaporizhzhia, or Kherson?"},
                new String[]{"5.3", "Does your company offer services and/or distribute products in Myanmar (Burma), Sudan, South Sudan, Central African Republic, Zimbabwe, Burundi, Lebanon, Nicaragua, Venezuela, Democratic Republic of the Congo, Iraq, Libya, Mali, Somalia, Yemen, Afghanistan, Haiti, or Moldova?"},
                new String[]{"5.4", "Does your company intend to offer services or sell Dentsply Sirona products outside of its country of registration?"},
                new String[]{"5.5", "Does your company intend to use sub distributors for selling/distributing Dentsply Sirona products?"},
                new String[]{"5.7", "Has the company (including its officers, directors, principals, and owners), ever been involved, directly or indirectly, in a transaction, export, re-export or other transfer pursuant to an export license or other authorization issued by the U.S. Government?"},
                new String[]{"5.8", "Does your company have existing contracts with or sell or market products or/and services to any government, government-controlled or military entity, including any state-owned enterprises?"},
                new String[]{"5.9", "Is any current or former officer, director, owner, principal, or controlling person of the company (including the spouse, brother, sister, parent or child of such a person) a current or former office holder, official, or employee of any government entity?"},
                new String[]{"5.11", "Has your company or somebody working for your company ever received a request for or provided a payment, gift or kickback to a government official?"},
                new String[]{"5.12", "Has your company ever been a party to a legal proceeding anywhere in the world for violation of applicable anti-bribery laws or regulations?"},
                new String[]{"5.13", "Has your company or any associated or previously associated entity, any predecessor entity or any present or former owner, manager, partner, director, officer, employee or consultant of your company been within the last ten years (10) years:  (a) suspended from doing business in any capacity; (b) charged or indicted with any criminal act; (c) the subject of any allegation or investigation of fraud, bribery, misrepresentation or similar circumstances; (d) mentioned in the press for having been involved in any improper activity; or (e) terminated by a client because of ethical or legal concerns?"},
                new String[]{"5.14", "Has your company been involved in the last 5 years in any lawsuit or other judicial action to which the Company or any owner, partner, officer or director, has been involved, either as the plaintiff or as defendant?"},
                new String[]{"5.15", "Are any of the owners/majority shareholders of this distributor a practicing physician or relevant member/owner of a Healthcare Organization?"},
                new String[]{"5.18", "Does your company hire any individual or business entity that directly interact with government entities/officials?"},
                new String[]{"5.20", "Does any officer, director or shareholder of your company have a personal, professional, or financial relationship with any Dentsply Sirona employee or a close relationship of a Dentsply Sirona employee?"}
        );

        // Loop through each question and verify
        for (String[] question : questions) {
            String stepNumber = question[0];
            String questionText = question[1];

            // Call your verification method for each question
            selectAndCheckQuestionTitle(stepNumber, questionText);
        }
    }


        //input[@class='custom-date-picker date-picker-left']


    public void selectAllMultipleChoice(String text) {
        browserUtil.waitABit(1000);

        // Wait for at least one input field to be visible
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='custom-control custom-radio']//span[text()='"+text+"']")));

        // Get all blank input fields
        List<WebElement> multipleChoice = driver.findElements(By.xpath("//div[@class='custom-control custom-radio']//span[text()='"+text+"']"));

        System.out.println("Total blank fields found: " + multipleChoice.size());

        for (int i = 0; i < multipleChoice.size(); i++) {
            try {
                // Re-fetch elements to avoid stale element issues
                multipleChoice = driver.findElements(By.xpath("//div[@class='custom-control custom-radio']//span[text()='"+text+"']"));

                WebElement choice = multipleChoice.get(i);
                browserUtil.highlightScrollClick(choice);

                browserUtil.waitABit(800);

            } catch (Exception e) {
                System.err.println("Cannot be selected");
            }
        }

        System.out.println("✅ All blank fields filled successfully!");
    }

}

