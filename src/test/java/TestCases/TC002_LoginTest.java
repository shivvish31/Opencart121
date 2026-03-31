package TestCases;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_LoginTest extends BaseClass {
    @Test(groups = {"Sanity","Master"})
    public void verify_login(){
        logger.info("**** Starting TC002_LoginTest****");
        try {
            //home page
            HomePage hp = new HomePage(driver);

            hp.clickMyAccount();
            hp.clickLogin();
            //login page
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(p.getProperty("email"));
            lp.setpassword(p.getProperty("password"));
            lp.clickLogin();

            //my account page
            MyAccountPage mac = new MyAccountPage(driver);
            boolean targetpage = mac.isMyAccountPageExists();
            Assert.assertTrue(targetpage);
        }
        catch(Exception e){
            Assert.fail();
        }

        logger.info("*** Finished TC002_LoginTest***");
    }
}
