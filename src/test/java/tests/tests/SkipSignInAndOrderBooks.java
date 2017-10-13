package tests.tests;

import org.testng.annotations.Test;
import pages.HomePage;
import tests.common.BaseTest;

public class SkipSignInAndOrderBooks extends BaseTest{

    @Test
    public void login() throws Exception {
        try {
            reports.startTestCase("Skip Login and order books", "Skip Login and order books");
            new HomePage(driver,reports)
                    .clickOnSkipLoginButton()
                    .enterSerachText();
        }
        catch(Exception e)
        {
            makeTestCaseFail(e);
        }

    }
}
