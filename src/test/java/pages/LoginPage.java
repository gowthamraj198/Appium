package pages;

import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Reports;

public class LoginPage extends BasePage{

    private AndroidDriver driver;
    private Reports reports;
    protected Logger log             = Logger.getLogger(HomePage.class);

    @FindBy(id="ap_email_login")
    private WebElement username;
    @FindBy(id="ap_password")
    private WebElement pwd;
    @FindBy(id="auth-signin-show-password-checkbox")
    private WebElement rememberPwdCheckBox;
    @FindBy(id="signInSubmit")
    private WebElement signInBtn;

    public LoginPage(AndroidDriver driver, Reports reports) {
        this.driver = driver;
        this.reports = reports;
        PageFactory.initElements(driver,this);
    }

    public void login() throws Exception {
        try {
            username.sendKeys("hello");
            pwd.sendKeys("ser344");
            rememberPwdCheckBox.click();
            signInBtn.click();
            logs_Reports(driver,reports,log,"Logged in Successfully","PASS");
        }
        catch (Exception e)
        {
            logs_Reports(driver,reports,log,"Logged in Failed","FAIL");
            throw new Exception();
        }

    }

}
