package discoveryTeam.tests;

import discoveryTeam.pages.HepsiBuradaPage;
import discoveryTeam.utilities.BrowserUtils;
import discoveryTeam.utilities.ConfigurationReader;
import discoveryTeam.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HepsiBuradaLoginAttemptWithExistingEmailAccount {

    HepsiBuradaPage pageObject;
    @BeforeMethod
    public void setUp(){
        pageObject=new HepsiBuradaPage();

        //Navigate to the hepsiburada homepage
        Driver.getDriver().get(ConfigurationReader.getProperty("hepsiburada"));

/*        //If it exists, accept all cookies
        if( pageObject.acceptCookies.isDisplayed() ){
            pageObject.acceptCookies.click();
        }*/

        Driver.getDriver().navigate().refresh();
    }
    @Test(priority = 2)
    public void loginWithExistingAccountInfo(){
        //Verify the home page title
        BrowserUtils.verifyTitle("Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com");

        //Click on the Giriş Yap dropdown button
        pageObject.homaPageLoginDropdown.click();

        //Click on the login button at the dropdown
        pageObject.homaPageLoginButton.click();

        //Verify the login page title
        BrowserUtils.verifyTitle("Üye Giriş Sayfası & Üye Ol - Hepsiburada");

        //Write your email address in order to login
        pageObject.signupButton.click();
        pageObject.emailSignupBox.sendKeys(ConfigurationReader.getProperty("email"));

        //Click on the continue button after verifying whether it is enabled or not
        Assert.assertTrue(pageObject.continueSignupButton.isEnabled());
        pageObject.continueSignupButton.click();

        //Verify the error message ("Bu e-posta adresine ait bir hesabınız olduğunu fark ettik.")
        Assert.assertTrue(pageObject.errorMessage.isDisplayed());
        Assert.assertEquals(pageObject.errorMessage.getText(),"Bu e-posta adresine ait bir hesabınız olduğunu fark ettik.");

        Driver.closeDriver();
    }

    @Test(priority = 1)
    public void verifyAllMainMenuLink() {
        pageObject.mainMenuDropdownTest();
    }
}
