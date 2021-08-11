package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfirmationPage extends BasePage {

    @FindBy(xpath = "//table/tbody/tr")
    private List<WebElement> purchaseDetails;

    @FindBy(xpath = "//h1[contains(text(),'Thank you for your purchase today!')]")
    private WebElement thankYouLabel;


    public ConfirmationPage(WebDriver driver) {
        super(driver, false);
    }

    public String getThankYouLabel() {
        return thankYouLabel.getText();
    }



    public Map<String, String> getPurchaseDetails() {
        Map<String, String> details = new HashMap<>();
        List<WebElement> purchaseDescriptionDetails = purchaseDetails;

        for (WebElement element : purchaseDescriptionDetails) {
            List<WebElement> tds = element.findElements(By.tagName("td"));
            details.put(tds.get(0).getText(), tds.get(1).getText());

        }

        return details;
    }


}
