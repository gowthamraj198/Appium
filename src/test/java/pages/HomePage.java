package pages;

import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Reports;

public class HomePage extends BasePage{

    private             AndroidDriver     driver;
    private             Reports             reports;
    protected           Logger              log             = Logger.getLogger(HomePage.class);

    @FindBy(id="sign_in_button")
    private WebElement signInBtn;
    @FindBy(id="skip_sign_in_button")
    private WebElement skipSignInBtn;

    public HomePage(AndroidDriver driver, Reports reports) {
        this.driver = driver;
        this.reports = reports;
        PageFactory.initElements(driver,this);
    }

    public LoginPage clickOnSignInButton() throws Exception {
        try {
            signInBtn.click();
            logs_Reports(driver,reports,log,"Signin button clicked","PASS");
        }
        catch (Exception e)
        {
            logs_Reports(driver,reports,log,"Signin button not clicked","FAIL");
            throw new Exception();
        }
        return new LoginPage(driver,reports);
    }

    public FirstPage clickOnSkipLoginButton() throws Exception {
        try {
            Thread.sleep(5000);
            skipSignInBtn.click();
            logs_Reports(driver,reports,log,"Skip signin button clicked","PASS");
        }
        catch (Exception e)
        {
            logs_Reports(driver,reports,log,"Skip signin button clicked","FAIL");
            throw new Exception();
        }
        return new FirstPage(driver,reports);
    }
}
