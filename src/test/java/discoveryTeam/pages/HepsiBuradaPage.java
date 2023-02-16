package discoveryTeam.pages;
import discoveryTeam.utilities.Driver;
import discoveryTeam.utilities.ReusableMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.*;

public class HepsiBuradaPage {
    public HepsiBuradaPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "button#onetrust-accept-btn-handler")
    public WebElement acceptCookies;
    @FindBy(css = "a#login")
    public WebElement homaPageLoginButton;
    @FindBy(css = "input#txtUserName")
    public WebElement emailBox;
    @FindBy(css = "input#txtEmail")
    public WebElement emailSignupBox;
    @FindBy(css = "input#txtUserName")
    public WebElement enterEmailBox;
    @FindBy(css = "button#btnSignUpSubmit")
    public WebElement continueSignupButton;
    @FindBy(css = "button#btnLogin")
    public WebElement loginButton;
    @FindBy(css = "button#txtPassword")
    public WebElement passwordBox;
    @FindBy(css = "button#btnEmailSelect")
    public WebElement submitPasswordBox;
    @FindBy(css = "span#shoppingCart")
    public WebElement shoppingCartButton;
    @FindBy(css = "span.sf-OldMyAccount-sS_G2sunmDtZl9Tld5PR")
    public WebElement usernameText;
    @FindBy(xpath = "//div[contains(text(),'Bu e-posta adresine ait bir hesabınız')]")
    public WebElement errorMessage;
    @FindBy(xpath = "//span[contains(text(),'Giriş Yap')]")
    public WebElement homaPageLoginDropdown;
    @FindBy(xpath = "//div[contains(text(),'Üye ol')]")
    public WebElement signupButton;

    @FindAll(@FindBy(css = "div[data-test-id=\"product-info-wrapper\"] h3"))
    public List<WebElement> allItemsOnTheHomePage;

    @FindAll(@FindBy(css = "ul.sf-MenuItems-MXhuaBP08HFYcFicDzFl li span span"))
    public List<WebElement> mainMenuLinks;
    @FindAll(@FindBy(css = "li.sf-MenuItems-WulWXvlfIAwNiOUGY7FP"))
    public List<WebElement> mainMenuHoverOverDropdowns;
    @FindAll(@FindBy(css = "div.sf-voltran-body.voltran-body.Recommendation_0 h3"))
    public List<WebElement> lastViewedItems;

    List<String> viewedItemsList = new ArrayList<>();

    public void navigateAnyItemWithGivenQuantity(int ItemQuantity) {
        Random random = new Random();
        for (int i = 0; i < ItemQuantity; i++) {
            ReusableMethods.scrollDownToElement(allItemsOnTheHomePage.get(random.nextInt(allItemsOnTheHomePage.size())));
            WebElement randomlySelectedItem = allItemsOnTheHomePage.get(random.nextInt(allItemsOnTheHomePage.size()));
            String itemHeader = randomlySelectedItem.getText();
            ReusableMethods.jsScrollClick(randomlySelectedItem);
            viewedItemsList.add(Driver.getDriver().getTitle());
            Driver.getDriver().navigate().refresh();

            if (Driver.getDriver().getTitle().contains(itemHeader)) {
                ReusableMethods.waitFor(1);
                System.out.println("Page verification is done.");
            } else {
                System.out.println("Page verification FAILED");
            }

            Driver.getDriver().navigate().back();
            Driver.getDriver().navigate().refresh();

            ReusableMethods.waitFor(1);
        }
    }

    public void verifyLastViewedItems() {
        System.out.println("All viewed items: " + viewedItemsList);
        for (WebElement eachViewedItem : lastViewedItems) {
            if (viewedItemsList.contains(eachViewedItem.getText())) {
                System.out.println("Last view item is seen at the cart recommendation list: " + eachViewedItem.getText());
            }
            ReusableMethods.waitFor(2);
        }
    }

    public void mainMenuDropdownTest() {
        for (int i = 0; i < mainMenuLinks.size(); i++) {
            ReusableMethods.hover(mainMenuLinks.get(i));
            Assert.assertEquals(mainMenuLinks.get(i).getText(), mainMenuHoverOverDropdowns.get(i).getText());
        }

    }

    @FindBy(xpath = "//li/a[contains(text(),'Araç İçi Telefon Tutucular ve Araç Şarj Cihazları')]")
    public WebElement carPhoneHolder;
    @FindBy(xpath = "//span/span[contains(text(),'Elektronik')]")
    public WebElement mainPageElectronicDropdown;
    @FindBy(xpath = "//li/a[contains(text(),'Telefon & Telefon Aksesuarları')]")
    public WebElement dropdownTelephoneMenu;
    @FindBy(xpath = "//div/div[contains(text(),'ARA')]")
    public WebElement searchButton;
    @FindBy(css = "div input.theme-IYtZzqYPto8PhOx3ku3c")
    public WebElement searchBox;
    @FindBy(css = "header h1#product-name")
    public WebElement productName;
    @FindBy(css = ".rating-star")
    public WebElement eachItemPoint;
    @FindBy(css = "div#comments-container a span")
    public WebElement eachCommentNumber;
    @FindBy(css = "div[data-test-id=\"price-current-price\"]")
    public List<WebElement> allPrices;
    @FindBy(css = "picture img.product-image")
    public WebElement productPicture;

    public void searchForCarPhone() {
        searchBox.sendKeys("iphone");
        searchButton.click();

        String searchPageHandle=Driver.getDriver().getWindowHandle();

        for (WebElement element : allPrices) {
            element.click();
        }

        Set<String> allWindowHandles = Driver.getDriver().getWindowHandles();
        Map<String,Integer>compareItemsWithAlgorithm=new HashMap<>();
        int count = 1;
        for (String each : allWindowHandles) {
            Driver.getDriver().switchTo().window(each);
            Driver.getDriver().navigate().refresh();
            if(!searchPageHandle.equalsIgnoreCase(Driver.getDriver().getWindowHandle())){
                if(eachItemPoint.isDisplayed()&&eachCommentNumber.isDisplayed()){
                    Assert.assertTrue(productPicture.isDisplayed());
                    System.out.println("Name of the product: "+productName.getText());
                    System.out.println("Star point: "+eachItemPoint.getText());
                    System.out.println("Number of comments: "+eachCommentNumber.getText());
                    System.out.println("* * *");
                    Integer starPoint=(int) Float.parseFloat(eachItemPoint.getText().replace(',','.'));
                    Integer commentNumber=Integer.parseInt(eachCommentNumber.getText());
                    Integer algorithmPoint=starPoint*commentNumber;
                    compareItemsWithAlgorithm.put(productName.getText(),algorithmPoint);
                }else{
                    System.out.println("This item has not star point. Therefore, it will be excluded from the list");
                }
            }
/*            System.out.println(count + ". page title is : " + Driver.getDriver().getTitle() + "\n" +
                    count + ". link is: " + Driver.getDriver().getCurrentUrl());
            count++;*/

            Driver.getDriver().close();
        }

        System.out.println(compareItemsWithAlgorithm);


    }

}
