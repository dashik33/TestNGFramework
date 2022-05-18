package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class LoginPage extends CommonMethods {
    @FindBy(id = "txtUsername")
    public WebElement usernameBox;

    @FindBy(name = "txtPassword")
    public WebElement passwordBox;

    @FindBy(id = "btnLogin")
    public WebElement loginBtn;

    @FindBy(xpath="//span[text()='Invalid credentials']")
    public WebElement invalidCredentialsErrMsg;

    @FindBy(css="span#spanMessage")
    public WebElement userNameCantBeEmptyErrMessage;

    @FindBy(css="span#spanMessage")
    public WebElement passwordCantBeEmptyErrMsg;

    @FindBy(xpath="//*[@id=divLogo]/img")
    public  WebElement logo;

    @FindBy(id="spanMessage")
    public WebElement errorMessage;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }
    public void loginMethod(String username , String password){
        sendText(usernameBox,username);
        sendText(passwordBox,password);
        loginBtn.click();

    }
}
