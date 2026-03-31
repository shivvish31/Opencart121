package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

    WebDriver driver;

    //constructor
    public AccountRegistrationPage(WebDriver driver)
    {
        super(driver);
    }


    //locators
    @FindBy(xpath="//input[@id='input-firstname']")
    WebElement txt_Firstname;

    @FindBy(xpath="//input[@id='input-lastname']")
    WebElement txt_lastName;

    @FindBy(xpath="//input[@id='input-email']")
    WebElement txt_Email;

    @FindBy(xpath="//input[@id='input-telephone']")
    WebElement txt_telephone;

    @FindBy(xpath="//input[@id='input-password']")
    WebElement txt_password;

    @FindBy(xpath="//input[@id='input-confirm']")
    WebElement txt_passwordConfirm;

    @FindBy(xpath="//input[@name='agree']")
    WebElement chk_agree;

    @FindBy(xpath="//input[@value='Continue']")
    WebElement btn_continue;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created']")
    WebElement msgConfirmation;

    //Action elements
    public void setFirstName(String fname){
        txt_Firstname.sendKeys(fname);
    }
    public void setLastName(String lname) {
        txt_lastName.sendKeys(lname);
    }
    public void setEmail(String email){
        txt_Email.sendKeys(email);
    }
    public  void setTelephone(String tel){
        txt_telephone.sendKeys(tel);
    }
    public  void setPassword(String pwd){
        txt_password.sendKeys(pwd);
    }
    public  void setConfirmPassword(String pwd){
        txt_passwordConfirm.sendKeys(pwd);
    }
    public void setPrivacyPolicy(){
        chk_agree.click();
    }
    public void clickContinue(){
        btn_continue.click();
    }
    public String getconfirmationMsg(){
        try{
            return (msgConfirmation.getText());
        }catch(Exception e){
            return (e.getMessage());
        }
    }

}
