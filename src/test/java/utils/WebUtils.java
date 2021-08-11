package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebUtils {
    private WebUtils() {
    }

    public static synchronized boolean isElementDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public static synchronized boolean isElementDisplayed(WebDriver driver, WebElement webElement, long timeOut) {
        try {
            new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    public static void clearField(WebElement element) {
       element.clear();
    }

    public static void clearFieldAndTypeKeys(WebElement element, String value) {
        clearField(element);
        element.sendKeys(value);
    }


}
