package tests;

import org.junit.Assert;

import org.junit.Test;
import utils.BaseTest;


// mvn -Dtest=DentsplyTest test
// mvn -Dtest=DentsplyTest#testLogin test

//allure: mvn -Dtest=tests.DentsplyTest test
public class DentsplyTest extends BaseTest {

    @Test
    public void test_A_Login() {
        dentsplyPage.openHomePage();
        dentsplyPage.emailAddress("vietnameseddq123@mail.com");
        dentsplyPage.password("Password1!");
        dentsplyPage.logInButton();
        waitABit(3000);
        dentsplyPage.complianceDashBoard();
        waitABit(3000);
        dentsplyPage.startButton();
        waitABit(10000);
        dentsplyPage.confirmLanguage("English");
        dentsplyPage.assertCompanyInformationLabels();

        Assert.assertEquals("ethiXbase", driver.getTitle());
    }

    @Test
    public void test_B_DashBoard() {
        dentsplyPage.openHomePage();
        dentsplyPage.emailAddress("vietnameseddq123@mail.com");
        dentsplyPage.password("Password1!");
        dentsplyPage.logInButton();
        waitABit(3000);
        dentsplyPage.complianceDashBoard();
        waitABit(3000);
        dentsplyPage.startButton();
        waitABit(10000);
        dentsplyPage.confirmLanguage("English");
        dentsplyPage.assertCompanyInformationLabels();
    }

//    @Test
//    public void test_C_DDQ() {
//        dentsplyPage.complianceDashBoard();
//    }




}
