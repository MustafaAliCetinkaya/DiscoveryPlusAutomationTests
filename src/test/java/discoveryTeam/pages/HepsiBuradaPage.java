package discoveryTeam.pages;

import discoveryTeam.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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
    @FindBy(css = "div.hb-fznKot.uteQh.siaol1zwfmt")
    public WebElement errorMessage;
    @FindBy(xpath = "//span[contains(text(),'Giriş Yap')]")
    public WebElement homaPageLoginDropdown;
    @FindBy(xpath = "//div[contains(text(),'Üye ol')]")
    public WebElement signupButton;

}
