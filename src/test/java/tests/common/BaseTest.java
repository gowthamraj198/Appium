package tests.common;

import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import utils.Reports;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class BaseTest {

    protected                               AndroidDriver       driver;
    public                                  Properties          prop_data;
    public                                  InputStream         properties_data;
    public              static final        DateFormat          dateFormat              = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
    public              static              String              testExecutionTimeStamp  = dateFormat.format(new Date());
    protected                               Reports             reports                 = new Reports();
    protected                               Logger              log                     = Logger.getLogger(BaseTest.class);

    protected void prepareAndroidForAppium() throws MalformedURLException {

        try {
            File classpathRoot = new File(System.getProperty("user.dir"));
            File appDir = new File(classpathRoot, getValuesFromPropertyFile("appPath"));
            File app = new File(appDir, getValuesFromPropertyFile("app"));

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("appium-version", "1.0");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("platformVersion", "7.1.1");
            capabilities.setCapability("deviceName", "Nexus 6");
            capabilities.setCapability("avd", "Nexus_6");
            capabilities.setCapability("app", app.getAbsolutePath());
            capabilities.setCapability("appPackage", getValuesFromPropertyFile("appPackage"));
            capabilities.setCapability("appActivity", getValuesFromPropertyFile("appActivity"));
            capabilities.setCapability("fullReset","false");
            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValuesFromPropertyFile(String text) throws IOException {
        prop_data = new Properties();
        properties_data = new FileInputStream("./src/test/resources/config/appium.properties");
        prop_data.load(properties_data);
        return prop_data.getProperty(text);
    }

    @BeforeMethod
    public void setUp() throws Exception {
        PropertyConfigurator.configure("log4j.properties");
        log.info("##################################################################");
        log.info("Test case started");
        prepareAndroidForAppium();
    }

    protected void makeTestCaseFail(Exception e) throws Exception {
        log.error("Test Case Failed :" + e.getMessage());
        reports.reportStep(driver, "Test case Failed", "FAIL");
        throw new Exception();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        try {
            if(RetryAnalyzer.makeReports.equals("FAIL")) {
                reports.reportStep(driver, "TestCase Failed and will execute for one more time!", "ERROR");
                RetryAnalyzer.makeReports="PASS";
            }
        }
        finally {
            log.info("Test case completed");
            log.info("##################################################################");
            reports.reportStep(driver, "Test case completed", "INFO");
            reports.endTest();
            driver.quit();
        }
    }

    @BeforeSuite
    public void beforeSuite()
    {
        reports.startResult();
    }

    @AfterSuite
    public void afterSuite()
    {
        reports.endResult();
    }
}
