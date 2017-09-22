package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Reports;

public class LoginPage {

    private RemoteWebDriver driver;
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

    public LoginPage(RemoteWebDriver driver, Reports reports) {
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
            log.info("logged in successfully");
            reports.reportStep(driver, "logged in successfully", "PASS");
        }
        catch (Exception e)
        {
            log.info("error in login");
            reports.reportStep(driver, "error in login", "FAIL");
            throw new Exception();
        }

    }

}
