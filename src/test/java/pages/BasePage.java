package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public abstract class BasePage<T extends BasePage<T>>  {
    public static final int SHORT_TIMEOUT = 10;
    public static final int LONG_TIMEOUT = 30;

    @FindBy(xpath = "//a[@href='index.php']")
    private WebElement travelTheWorldNavigation;

    @FindBy(xpath = "//a[@href='home']")
    private WebElement homeNavigation;

    protected WebDriver driver;

    protected BasePage(WebDriver driver, boolean waitForLoad) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        if (waitForLoad) {
           // waitForLoad();
        }
    }





}
