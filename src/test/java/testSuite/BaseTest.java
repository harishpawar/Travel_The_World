package testSuite;

import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import utils.WebDriverManager;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void startWebDriver() {
        driver = WebDriverManager.getWebDriver();
    }

    @AfterClass(alwaysRun = true)
    protected void quitWebDriver() {
            driver.quit();
    }

    protected void maximizeBrowser() {
        driver.manage().window().maximize();
    }
}
