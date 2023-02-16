package discoveryTeam.tests;

import discoveryTeam.pages.HepsiBuradaPage;
import discoveryTeam.utilities.ConfigurationReader;
import discoveryTeam.utilities.Driver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchForSpecificItem {
    HepsiBuradaPage pageObject;

    @BeforeMethod
    public void setUp() {
        pageObject = new HepsiBuradaPage();

        //Navigate to the hepsiburada homepage
        Driver.getDriver().get(ConfigurationReader.getProperty("hepsiburada"));

        //If it exists, accept all cookies
        if (pageObject.acceptCookies.isDisplayed()) {
            pageObject.acceptCookies.click();
        }

        Driver.getDriver().navigate().refresh();
    }

    @Test
    public void searchItems() {
        //Search for car phone holder. Get every item's comment number and client point. Determine a code fragment to compare them according to client comment' and points.
        pageObject.searchForCarPhone();

        //Driver.closeDriver();
    }

}
