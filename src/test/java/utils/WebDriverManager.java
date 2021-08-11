package utils;


import Entities.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {

    public static WebDriver getWebDriver() {
        Browser browser = PropertiesReader.getBrowser();
        WebDriver driver = getWebDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        return driver;
    }

    private static WebDriver getWebDriver(Browser browser) {
        switch (browser) {

            case FIREFOX:
                String geckoDriverLocation = FileUtils.copyResourceToTempFolder("geckodriver.exe");
                System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, geckoDriverLocation);

                String neverAskSaveToDiskAndOpenFileValues = "application/octet-stream, application/x-zip-compressed, " +
                        "application/zip-compressed, application/zip, multipart/x-zip, application/x-compressed, " +
                        "application/msword, text/plain, image/gif, image/png, application/pdf, application/excel, " +
                        "application/vnd.ms-excel, application/x-excel, application/x-msexcel, text/csv";
                FirefoxOptions firefoxOptions = new FirefoxOptions()
                        // Disable “Auto update firefox”
                        .addPreference("app.update.enabled", false)
                        // File download configuration
                        .addPreference("browser.download.folderList", 2)
                        .addPreference("browser.download.dir", FileUtils.getDownloadDirectory())
                        .addPreference("browser.helperApps.alwaysAsk.force", false)
                        .addPreference("browser.download.manager.showWhenStarting", false)
                        .addPreference("browser.helperApps.neverAsk.saveToDisk", neverAskSaveToDiskAndOpenFileValues)
                        .addPreference("browser.helperApps.neverAsk.openFile", neverAskSaveToDiskAndOpenFileValues)
                        .addPreference("browser.download.panel.shown", true)
                        .addPreference("intl.accept_languages", "en-US");

                return new FirefoxDriver(firefoxOptions);

            case CHROME:
                String chromeDriverLocation;
                chromeDriverLocation = FileUtils.copyResourceToTempFolder("chromedriver.exe");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, chromeDriverLocation);

                // File download configuration
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("download.prompt_for_download", "false");
                prefs.put("download.default_directory", FileUtils.getDownloadDirectory());
                prefs.put("intl.accept_languages", "en-US");
                ChromeOptions chromeOptions = new ChromeOptions()
                        .setExperimentalOption("prefs", prefs)
                        .setExperimentalOption("w3c", false)
                        .addArguments("disable-infobars");

                return new ChromeDriver(chromeOptions);


            default:
                throw new UnsupportedOperationException("Attempt to start invalid browser " + browser);
        }

    }
}
