package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

public class FlightListPage extends BasePage{

    @FindBy(xpath = "//table[@class='table']/tbody/tr")
    private List<WebElement> flightListTable;

    @FindBy(xpath = "//h3")
    private WebElement flightDetailsLabel;

    public FlightListPage(WebDriver driver){
        super(driver, false);
    }

    public boolean validateFlightDetailLabel(String flightFrom, String flightTo){
        String expectedLabel = String.format("Flights from %s to %s:",flightFrom,flightTo);
        return flightDetailsLabel.getText().equals(expectedLabel);
    }

    public PurchasePage chooseFlight(String flightId){

        getFlightDetails(flightId).findElement(By.xpath("//input[@type='submit']")).click();
        return new PurchasePage(driver);
    }

    public WebElement getFlightDetails(String flightId){

        for(WebElement element: flightListTable){
            Optional<WebElement> flightRow = element.findElements(By.xpath("//td")).stream().filter(s->s.getText().equals(flightId)).findAny();

            if(flightRow.isPresent()){
                return element;
            }
        }

        return null;
    }



}
