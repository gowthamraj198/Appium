package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import tests.common.BaseTest;

public class Login extends BaseTest {

    @Test
    public void login() throws Exception {
        try {
            reports.startTestCase("login", "login");
            new HomePage(driver,reports)
                    .clickOnSignInButton()
                    .login();
        }
        catch(Exception e)
        {
            makeTestCaseFail(e);
        }

    }
}
