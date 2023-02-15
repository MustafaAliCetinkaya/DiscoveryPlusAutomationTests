package discoveryTeam.tests;

import discoveryTeam.pages.HepsiBuradaPage;
import discoveryTeam.utilities.BrowserUtils;
import discoveryTeam.utilities.ConfigurationReader;
import discoveryTeam.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HepsiBuradaLoginWithValidAccount {

    HepsiBuradaPage pageObject;

    @BeforeMethod
    public void setUp() {
        pageObject = new HepsiBuradaPage();

        //Navigate to the hepsiburada homepage
        Driver.getDriver().get(ConfigurationReader.getProperty("hepsiburada"));

        /*//If it exists, accept all cookies
        if (pageObject.acceptCookies.isDisplayed()) {
            pageObject.acceptCookies.click();
        }*/

        Driver.getDriver().navigate().refresh();
    }

    @Test
    public void loginWithExistingAccount() {
        //Verify the home page title
        BrowserUtils.verifyTitle("Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com");

        //Click on the Giriş Yap dropdown button
        pageObject.homaPageLoginDropdown.click();

        //Click on the login button at the dropdown
        pageObject.homaPageLoginButton.click();

        //Verify the login page title
        BrowserUtils.verifyTitle("Üye Giriş Sayfası & Üye Ol - Hepsiburada");

        //Write your email address in order to login
        pageObject.enterEmailBox.sendKeys(ConfigurationReader.getProperty("email"));

        //Click on the continue button
        pageObject.loginButton.click();

        //Write your password to the related box
        pageObject.passwordBox.sendKeys(ConfigurationReader.getProperty("pass"));

        //Click on the continue button
        pageObject.submitPasswordBox.click();

        //Verify username visibility and check the username text is true or not
        Assert.assertTrue(pageObject.usernameText.isDisplayed());
        String expectedUsername = "Hayu QA";
        Assert.assertEquals(pageObject.usernameText.getText(), expectedUsername);
        System.out.println("Username verification is passed");

        Driver.closeDriver();

    }

    @Test
    public void verifyAllMainMenuLink() {
        pageObject.mainMenuDropdownTest();
    }
}
