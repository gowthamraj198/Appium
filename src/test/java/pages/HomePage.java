package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Reports;

public class HomePage extends BasePage{

    private             RemoteWebDriver     driver;
    private             Reports             reports;
    protected           Logger              log             = Logger.getLogger(HomePage.class);

    @FindBy(id="sign_in_button")
    private WebElement signInBtn;

    public HomePage(RemoteWebDriver driver, Reports reports) {
        this.driver = driver;
        this.reports = reports;
        PageFactory.initElements(driver,this);
    }

    public LoginPage clickOnSignInButton() throws Exception {
        try {
            signInBtn.click();
            log.info("Signin button clicked");
            reports.reportStep(driver, "Signin button clicked", "PASS");
        }
        catch (Exception e)
        {
            log.info("Signin button not clicked");
            reports.reportStep(driver, "Signin button not clicked", "FAIL");
            throw new Exception();
        }
        return new LoginPage(driver,reports);
    }
}
