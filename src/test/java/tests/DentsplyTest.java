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
    public void testAssertCompanyInformationLabels() {
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
        dentsplyPage.assertCompanyInformationLabels();
    }

    @Test
    public void test_Organizational() {
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

        dentsplyPage.selectAndCheckQuestionTitle("3.1", "Country of Incorporation or Formation");
        dentsplyPage.selectAndCheckQuestionTitle("3.2", "Date of Incorporation or Formation");
        //DropdownSelect
        dentsplyPage.selectAndCheckQuestionTitle("3.3", "Type of Ownership");
        //Multiple Choice
        dentsplyPage.selectAndCheckQuestionTitle("3.4", "Business Registration or VAT or Tax ID Number");
        dentsplyPage.selectAndCheckQuestionTitle("3.5", "Total Number of Employees");

        //3.6
        //Box img, file
        dentsplyPage.selectAndCheckQuestionTitle("3.7", "Please state the annual sales (in USD) of your company:");
    }

}
