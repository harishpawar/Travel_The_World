package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utils.PropertiesReader;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(name = "fromPort")
    private WebElement departureCityInput;

    @FindBy(name = "toPort")
    private WebElement destinationCityInput;

    @FindBy(xpath = "//div[@class='container']//input[@value='Find Flights']")
    private WebElement findFlightButton;

    @FindBy(xpath = "//a[@href='vacation.html']")
    private WebElement linkToDestinationOfTheWeek;


    public HomePage(WebDriver driver){
        super(driver, false);
        driver.get(PropertiesReader.getBaseUrl());

    }

    public Vacation checkOutDestinationOfTheWeek(){
        linkToDestinationOfTheWeek.click();
        return vacation(driver);
    }

    public List<WebElement>  getAllDepartureCityOptions(){
       return new Select(departureCityInput).getOptions();
    }

    public List<WebElement>  getAllDestinationCityOptions(){
        return new Select(destinationCityInput).getOptions();
    }

    public HomePage selectDepartureCity(String value) {
        new Select(departureCityInput).selectByVisibleText(value);
        return this;
    }

    public HomePage selectDestinationCity(String value) {
        new Select(destinationCityInput).selectByVisibleText(value);
        return this;
    }

    public FlightListPage findFlights() {
        findFlightButton.click();
        return new FlightListPage(driver);
    }

}
