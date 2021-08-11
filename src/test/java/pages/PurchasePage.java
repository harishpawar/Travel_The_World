package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WebUtils;

import java.util.List;

public class PurchasePage extends BasePage{

    @FindBy(xpath = "//p[contains(text(),'Flight Number:')]")
    private WebElement flightNumberLabel;

    @FindBy(xpath = "//p[contains(text(),'Price:')]")
    private WebElement flightPriceLabel;

    @FindBy(xpath = "//p[contains(text(),'Arbitrary Fees and Taxes:')]")
    private WebElement flightFeeAndTaxesLabel;

    @FindBy(xpath = "//p[contains(text(),'Total Cost: ')]/em")
    private WebElement flightTotalPriceLabel;

    @FindBy(xpath = "//h2[contains(text(),'Your flight from')]")
    private WebElement flightReserveDetailsLabel;

    @FindBy(id = "inputName")
    private WebElement nameInput;

    @FindBy(id="address")
    private WebElement addressInput;

    @FindBy(xpath="//input[@type='submit' and @value='Purchase Flight']")
    private WebElement purchaseFlightInput;



    public PurchasePage(WebDriver driver){
        super(driver, false);
    }

    public PurchasePage enterFullName(String fullName){
        WebUtils.clearFieldAndTypeKeys(nameInput, fullName);
        return this;
    }

    public PurchasePage enterAddress(String address){
        WebUtils.clearFieldAndTypeKeys(addressInput, address);
        return this;
    }

    public ConfirmationPage purchaseFlight(){
        purchaseFlightInput.click();
        return new ConfirmationPage(driver);
    }



    public boolean validateFlightReserveDetailsLabel(String flightFrom, String flightTo){
        String expectedLabel = String.format("Your flight from %s to %s has been reserved.",flightFrom,flightTo);
        return flightReserveDetailsLabel.getText().equals(expectedLabel);
    }

    public String getFlightNumber(){
        return flightNumberLabel.getText();
    }

    public String getFlightPrice(){
        return flightPriceLabel.getText();
    }

    public String getFlightFeesAndTaxes(){
        return flightFeeAndTaxesLabel.getText();
    }

    public String getFlightTotalPrice(){
        return flightTotalPriceLabel.getText();
    }

}
