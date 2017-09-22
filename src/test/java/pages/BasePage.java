package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BasePage {

    public              Properties          prop_data;
    public              InputStream         properties_data;

    public String getValuesFromPropertyFile(String text) throws IOException {
        prop_data = new Properties();
        properties_data = new FileInputStream("./src/test/resources/config/application-data.properties");
        prop_data.load(properties_data);
        return prop_data.getProperty(text);
    }
}
