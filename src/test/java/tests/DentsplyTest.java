package tests;

import org.junit.Test;
import utils.BaseTest;
import org.testng.annotations.Test;

// mvn -Dtest=DentsplyTest test
// mvn -Dtest=DentsplyTest#testLogin test
// allure: mvn -Dtest=tests.DentsplyTest test

//mvn test -Dtest=DentsplyTest#testAssertCompanyInformationLabels
//mvn test -Dtest=DentsplyTest#testB_Organizational

//RUN ON TERMINA
//Use Maven to run the TestNG suite:
// mvn test -DsuiteXmlFile=testng.xml

//OR, if using JUnit Runner for TestNG, you can also run:
//mvn surefire:test

//6️⃣ View TestNG Reports
//open target/surefire-reports/emailable-report.html
//start target\surefire-reports\index.html



public class DentsplyTest extends BaseTest {

    @Test(priority = 1)
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

    @Test(priority = 1)
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

        dentsplyPage.verifyQuestions_OrganizationStructure();
        //3.2 DropDownSelect
        //3.3 Multiple Choice
        //3.6 ShowCard/File
        //3.7 Multiple Choice
    }













}
