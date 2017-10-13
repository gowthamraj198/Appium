package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Reports;

public class FirstPage extends BasePage {

    private         AndroidDriver   driver;
    private         Reports         reports;
    protected       Logger          log             = Logger.getLogger(HomePage.class);

    @FindBy(id="rs_search_src_text")
    private WebElement searchTextBox;

    public FirstPage(AndroidDriver driver, Reports reports) {
        this.driver = driver;
        this.reports = reports;
        PageFactory.initElements(driver,this);
    }

    public FirstPage enterSerachText() throws Exception {
        try{
            searchTextBox.click();
            searchTextBox.sendKeys("mobile");
            logs_Reports(driver,reports,log,"Search Text entered", "PASS");
            driver.pressKeyCode(AndroidKeyCode.ENTER);
            Thread.sleep(5000);

        }
        catch(Exception e) {
            logs_Reports(driver,reports,log,"Search Text not entered" + e.getMessage(), "FAIL");
            throw new Exception();
        }
        return new FirstPage(driver,reports);
    }

}
