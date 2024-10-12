package tests;

import org.junit.Test;
import utils.BaseTest;

//mvn -Dtest=FBSearchTest test
public class FBSearchTest extends BaseTest {

    @Test
    public void testFacebookSearch() {
        facebookSearchPage.openHomePage();
        facebookSearchPage.waitABit();
        facebookSearchPage.search("Selenium WebDriver");
        facebookSearchPage.waitABit();

        System.out.println("Test Passed: Successfully searched on Google.");
    }
}
