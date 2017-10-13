package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reports;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class BasePage {

    public              Properties          prop_data;
    public              InputStream         properties_data;

    public String getValuesFromPropertyFile(String text) throws IOException {
        prop_data = new Properties();
        properties_data = new FileInputStream("./src/test/resources/config/application-data.properties");
        prop_data.load(properties_data);
        return prop_data.getProperty(text);
    }

    public void logs_Reports(AndroidDriver driver, Reports reports, Logger log, String message, String result)
    {
            log.info(message);
            reports.reportStep(driver, message, result);
    }
}
