package discoveryTeam.pages;

import discoveryTeam.utilities.BrowserUtils;
import discoveryTeam.utilities.Driver;
import discoveryTeam.utilities.ReusableMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HepsiBuradaPage {
    public HepsiBuradaPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = "button#onetrust-accept-btn-handler")
    public WebElement acceptCookies;
    @FindBy(css = "a#login")
    public WebElement homaPageLoginButton;
    @FindBy(css = "input#txtUserName")
    public WebElement emailBox;
    @FindBy(css = "input#txtEmail")
    public WebElement emailSignupBox;
    @FindBy(css = "button#btnSignUpSubmit")
    public WebElement continueSignupButton;
    @FindBy(css = "button#btnLogin")
    public WebElement loginButton;
    @FindBy(css = "span#shoppingCart")
    public WebElement shoppingCartButton;
    @FindBy(xpath = "//div[contains(text(),'Bu e-posta adresine ait bir hesabınız')]")
    public WebElement errorMessage;
    @FindBy(xpath = "//span[contains(text(),'Giriş Yap')]")
    public WebElement homaPageLoginDropdown;
    @FindBy(xpath = "//div[contains(text(),'Üye ol')]")
    public WebElement signupButton;

    @FindAll(@FindBy(css = "div[data-test-id=\"product-info-wrapper\"] h3"))
    public List<WebElement> allItemsOnTheHomePage;
    @FindAll(@FindBy(css = "div.sf-voltran-body.voltran-body.Recommendation_0 h3"))
    public List<WebElement> lastViewedItems;

    List<String>viewedItemsList=new ArrayList<>();

    public void navigateAnyItemWithGivenQuantity(int ItemQuantity){
        Random random=new Random();
        int index= random.nextInt(allItemsOnTheHomePage.size());
        for (int i = 0; i < ItemQuantity; i++) {
            ReusableMethods.scrollDownToElement(allItemsOnTheHomePage.get(index));
            String itemHeader=allItemsOnTheHomePage.get(index).getText();
            viewedItemsList.add(itemHeader);
            ReusableMethods.jsScrollClick(allItemsOnTheHomePage.get(index));
            Driver.getDriver().navigate().refresh();

            if(Driver.getDriver().getTitle().contains(itemHeader)){
                ReusableMethods.waitFor(2);
                System.out.println("Page verification is done.");
            }else{
                System.out.println("Page verification FAILED");
            }
            Driver.getDriver().navigate().back();
            Driver.getDriver().navigate().refresh();
            Driver.getDriver().navigate().back();
            Driver.getDriver().navigate().refresh();

            ReusableMethods.waitFor(2);
        }
    }

    public void verifyLastViewedItems(){
        System.out.println("All viewed items: "+viewedItemsList);
        for (WebElement eachViewedItem : lastViewedItems) {
            if(viewedItemsList.contains(eachViewedItem.getText())){
                System.out.println("Last view item is seen at the cart recommendation list: "+eachViewedItem.getText());
            }
        }
    }

}
