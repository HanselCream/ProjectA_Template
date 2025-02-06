package tests;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.JavascriptExecutor;
import utils.BaseTest;



// mvn -Dtest=DentsplyTest test
// mvn -Dtest=DentsplyTest#testLogin test
// allure: mvn -Dtest=tests.DentsplyTest test

//mvn test -Dtest=DentsplyTest#testAssertCompanyInformationLabels
//mvn test -Dtest=DentsplyTest#testB_Organizational

//RUN ON TERMINAL
//Use Maven to run the TestNG suite:
// mvn test -DsuiteXmlFile=testng.xml

//OR, if using JUnit Runner for TestNG, you can also run:
//mvn surefire:test

//6️⃣ View TestNG Reports
//open target/surefire-reports/emailable-report.html
//start target\surefire-reports\index.html


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DentsplyTest extends BaseTest {

    @Test
    public void testA_CompanyInformation() {
        dentsplyPage.openHomePage();
        browserUtil.waitABit(6000);
        dentsplyPage.emailAddress("vietnameseddq123@mail.com");
        dentsplyPage.password("Password1!");
        dentsplyPage.logInButton();
        dentsplyPage.complianceDashBoard();
        dentsplyPage.startButton();
        browserUtil.waitABit(5000);
        dentsplyPage.confirmLanguage("English");
        browserUtil.waitABit(3000);
        //1.11 MultipleChoice
        dentsplyPage.verifyQuestions_CompanyInformation();

        //PRINCIPAL CONTACT PERSON
        dentsplyPage.verifyQuestions_PrincipalContactPerson();

    }

    @Test
    public void test0_AllBlankFieldsAnswer() {
        dentsplyPage.openHomePage();
        browserUtil.waitABit(6000);
//        dentsplyPage.emailAddress("vietnameseddq123@mail.com");
//        dentsplyPage.password("Password1!");

        dentsplyPage.emailAddress("vietnameseddq123@mail.com");
        dentsplyPage.password("Password1!");
        dentsplyPage.logInButton();
        dentsplyPage.complianceDashBoard();
        dentsplyPage.startButton();
        browserUtil.waitABit(5000);
        dentsplyPage.confirmLanguage("English");
        browserUtil.waitABit(3000);
        dentsplyPage.addTextOnBlankFields();

        dentsplyPage.clickOnSaveProgress();
    }

    @Test
    public void testB_Organizational() {
        dentsplyPage.openHomePage();
        browserUtil.waitABit(6000);
        dentsplyPage.emailAddress("vietnameseddq123@mail.com");
        dentsplyPage.password("Password1!");
        dentsplyPage.logInButton();
        dentsplyPage.complianceDashBoard();
        dentsplyPage.startButton();
        browserUtil.waitABit(5000);
        dentsplyPage.confirmLanguage("English");
        browserUtil.waitABit(3000);
        dentsplyPage.rightNavigation("Organizational Structure");

        //dentsplyPage.verifyQuestions_OrganizationStructure(); >Alcon
        //3.2 DropDownSelect
        //3.3 Multiple Choice
        //3.6 ShowCard/File
        //3.7 Multiple Choice
    }


    /**
     * ALCON
     */

    @Test
    public void testC_AlconRussianComplete() {
        dentsplyPage.openHomePage();
        browserUtil.waitABit(6000);
        //dentsplyPage.emailAddress("vietnameseddq123@mail.com");
        dentsplyPage.emailAddress("eb360.polish+v2russian@gmail.com ");
        dentsplyPage.password("Password1!");
        dentsplyPage.logInButton();
        dentsplyPage.complianceDashBoard();
        dentsplyPage.startButton();
        browserUtil.waitABit(5000);
        dentsplyPage.confirmLanguage("Russian");
        browserUtil.waitABit(3000);
        dentsplyPage.rightNavigation("Политики и обучение");
        browserUtil.waitABit(3000);
        dentsplyPage.verifyQuestions_AlconRussian();
        browserUtil.scrollToTop();
        browserUtil.waitABit(2000);
        dentsplyPage.selectAllMultipleChoice("Нет");

        browserUtil.scrollToTop();
        browserUtil.waitABit(8000);


        dentsplyPage.addTextOnBlankFields();
        browserUtil.waitABit(12000);


        browserUtil.waitABit(3000);

        dentsplyPage.clickOnSaveProgress();

    }

    @Test
    public void testC_AlconSpanishComplete() {
        dentsplyPage.openHomePage();
        browserUtil.waitABit(6000);
        //dentsplyPage.emailAddress("vietnameseddq123@mail.com");
        dentsplyPage.emailAddress("eb360.polish+v2spanish@gmail.com");
        dentsplyPage.password("Password1!");
        dentsplyPage.logInButton();
        dentsplyPage.complianceDashBoard();
        dentsplyPage.startButton();
        browserUtil.waitABit(5000);
        dentsplyPage.confirmLanguage("Spanish");
        browserUtil.waitABit(3000);
        dentsplyPage.rightNavigation("Políticas y capacitación");
        browserUtil.waitABit(3000);
        dentsplyPage.verifyQuestions_AlconSpanish();
        browserUtil.scrollToTop();
        browserUtil.waitABit(2000);
        dentsplyPage.selectAllMultipleChoice("No");

        browserUtil.scrollToTop();
        browserUtil.waitABit(8000);


        dentsplyPage.addTextOnBlankFields();
        browserUtil.waitABit(12000);


        browserUtil.waitABit(3000);

        dentsplyPage.clickOnSaveProgress();

    }

    @Test
    public void test1_Alcon_AllMultipleChoice() {
        dentsplyPage.openHomePage();
        browserUtil.waitABit(6000);
        dentsplyPage.emailAddress("eb360.polish+v2russian@gmail.com ");
        dentsplyPage.password("Password1!");

        dentsplyPage.logInButton();
        dentsplyPage.complianceDashBoard();
        dentsplyPage.startButton();
        browserUtil.waitABit(5000);
        dentsplyPage.confirmLanguage("Russian");
        browserUtil.waitABit(3000);

        dentsplyPage.selectAllMultipleChoice("Нет");

        browserUtil.scrollToTop();
        browserUtil.waitABit(8000);


        dentsplyPage.addTextOnBlankFields();
        browserUtil.waitABit(12000);


        browserUtil.waitABit(3000);

        dentsplyPage.clickOnSaveProgress();

    }

    @Test
    public void test0_Alcon_AddTestOnBlankField() {
        dentsplyPage.openHomePage();
        browserUtil.waitABit(6000);
//        dentsplyPage.emailAddress("vietnameseddq123@mail.com");
//        dentsplyPage.password("Password1!");

        dentsplyPage.emailAddress("eb360.polish+v2polish@gmail.com");
        dentsplyPage.password("pASSWORD1!");
        dentsplyPage.logInButton();
        dentsplyPage.complianceDashBoard();
        dentsplyPage.startButton();
        browserUtil.waitABit(5000);
        dentsplyPage.confirmLanguage("Polish");
        browserUtil.waitABit(3000);

        //dentsplyPage.selectAllMultipleChoice("Nie");


        dentsplyPage.addTextOnBlankFields();
        browserUtil.waitABit(12000);

        dentsplyPage.clickOnSaveProgress();
    }




}
