package tests;

import org.junit.Test;
import utils.BaseTest;

// mvn -Dtest=DentsplyTest test
// mvn -Dtest=DentsplyTest#testLogin test
// allure: mvn -Dtest=tests.DentsplyTest test

//mvn test -Dtest=DentsplyTest#testAssertCompanyInformationLabels
//mvn test -Dtest=DentsplyTest#testDashBoard

//RUN MAVEN TEST SUITE
//mvn test -DsuiteXmlFile=testng.xml


//LOCATE AT target/surefire-reports/
//OPEN FILE AFTER RUN
//start target\surefire-reports\TEST-DentsplyTest.html

//cat, less, or more
//HTML FILE
//cat target/surefire-reports/TEST-DentsplyTest.html


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
    public void testB_PrincipalContactPerson() {
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




    }


    @Test
    public void testC_Organizational() {
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
