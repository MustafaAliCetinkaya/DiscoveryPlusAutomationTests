package discoveryTeam.tests;

import discoveryTeam.pages.HepsiBuradaPage;
import discoveryTeam.utilities.ConfigurationReader;
import discoveryTeam.utilities.Driver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchForThreeItem {
HepsiBuradaPage pageObject;
    @BeforeMethod
    public void setUp(){
        pageObject=new HepsiBuradaPage();

        //Navigate to the hepsiburada homepage
        Driver.getDriver().get(ConfigurationReader.getProperty("hepsiburada"));

        //If it exists, accept all cookies

        if( pageObject.acceptCookies.isDisplayed() ){
            pageObject.acceptCookies.click();
        }
        Driver.getDriver().navigate().refresh();
    }
    @Test
    public void searchItems() {
        //Navigate any 3 items on the homa page
        pageObject.navigateAnyItemWithGivenQuantity(3);

        //Click on the cart button
        pageObject.shoppingCartButton.click();

        //Verify that last view items are placed at the cart as recommendation list
        pageObject.verifyLastViewedItems();

        Driver.closeDriver();
    }
}
