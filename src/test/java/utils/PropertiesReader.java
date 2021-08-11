package utils;

import Entities.Browser;
import com.google.common.io.Resources;
import org.openqa.selenium.WebDriverException;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static final Properties PROPERTIES = getProperties();

    private PropertiesReader(){

    }

    private static synchronized Properties getProperties(){
        String propertiesFilePath = "environment.properties";
        String path = Resources.getResource(propertiesFilePath).getPath();

        try(InputStream inputStream = new BufferedInputStream(new FileInputStream(path))){
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        }catch (IOException e){
            throw new RuntimeException("Failed to read the property file " + propertiesFilePath, e);
        }

    }

    public static String getBaseUrl(){
        return PROPERTIES.getProperty("host");
    }

    public static Browser getBrowser(){
        Browser browser;
        try {
            browser =  Browser.valueOf(PROPERTIES.getProperty("browser").toUpperCase());
        }catch (IllegalArgumentException e){
            throw new WebDriverException("Incorrect browser value passed", e);
        }
        return browser;
    }

}
