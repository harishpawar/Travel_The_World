package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.PropertiesReader;

public class Vacation extends BasePage{

    @FindBy(xpath = "//div[@class='container']/img")
    private WebElement vacationImage;

    public Vacation(WebDriver driver){
        super(driver, false);
    }
}
