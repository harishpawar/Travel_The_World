package testSuite;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ConfirmationPage;
import pages.FlightListPage;
import pages.HomePage;
import pages.PurchasePage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class travelTheWorldTest extends BaseTest {

    private HomePage homePage;

    @DataProvider
    public Object[][] testdeta(){
        return new Object[][]{{"Philadelphia", "Berlin", "43", "Flight Number: UA954", "harish", "ylp", "PendingCapture"}
                , {"Paris", "New York", "234", "Flight Number: UA954", "harish", "ylp", "PendingCapture"}
                , {"San Diego", "London", "9696", "Flight Number: UA954", "harish", "ylp", "PendingCapture"}
        //can also add incorrect data and check flight details arfe not correct but web page is static and place holder is created
                // Example without card details flights can be reserved ?
        };
    }

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        homePage = new HomePage(driver);
    }


    @Test(dataProvider = "testdeta", groups = {"endToEnd"}, description = "Verify user is able to book/purchase flight ")
    public void validateUserIsAbleToPurchaseFlights(String from, String to, String flight, String flightNum, String fullName, String address, String status) {
        FlightListPage flightListPage = homePage.selectDepartureCity(from)
                .selectDestinationCity(to)
                .findFlights();
        Assert.assertTrue(flightListPage.validateFlightDetailLabel(from, to));

        PurchasePage purchasePage = flightListPage.chooseFlight(flight);
        Assert.assertEquals(purchasePage.getFlightNumber(), flightNum);

        ConfirmationPage confirmationPage = purchasePage
                .enterFullName(fullName)
                .enterAddress(address)
                .purchaseFlight();
        Assert.assertEquals(confirmationPage.getThankYouLabel(), "Thank you for your purchase today!");

        Map<String,String> confirmationDetails = confirmationPage.getPurchaseDetails();
        Assert.assertEquals(confirmationDetails.get("Status"), "PendingCapture");


    }


    @Test(groups = {"Componant"}, description = "Verify user is able to see listed expected cities in departure & destination ")
    public void validateCitiesOnHomePage(){
        List<String> expectedDepartureCities = Arrays.asList("Paris", "Philadelphia", "Boston", "Portland", "San Diego", "Mexico City", "São Paolo" );
        List<WebElement> elements = homePage.getAllDepartureCityOptions();
        List<String> actualDepartureCities = elements.stream().map(s->s.getText()).collect(Collectors.toList());

        Assert.assertTrue(actualDepartureCities.containsAll(expectedDepartureCities));


        List<String> expectedDestinationCities = Arrays.asList("Buenos Aires", "Rome", "London", "Berlin", "New York", "Dublin", "Cairo" );
        elements = homePage.getAllDestinationCityOptions();
        List<String> actualDestinationCities = elements.stream().map(s->s.getText()).collect(Collectors.toList());

        Assert.assertTrue(actualDestinationCities.containsAll(expectedDestinationCities));
    }


}
