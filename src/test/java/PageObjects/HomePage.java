package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    WebDriver driver;

    //constructor
    public HomePage(WebDriver driver)
    {
        super(driver);
    }
    //locators
    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement InkMyAccount;
    @FindBy(xpath = "(//a[normalize-space()='Register'])[1]")
    WebElement InkRegister;
    @FindBy(xpath = "(//a[normalize-space()='Login'])[1]")
    WebElement InkLogin;

    //action elements
    public void clickMyAccount()
    {
        InkMyAccount.click();
    }
    public void clickRegister()
    {
        InkRegister.click();
    }
    public void clickLogin()
    {
        InkLogin.click();
    }
}
