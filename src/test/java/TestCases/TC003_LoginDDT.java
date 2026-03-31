package TestCases;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import TestBase.BaseClass;
import Utilities.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003_LoginDDT extends BaseClass {
    @Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups = {"Datadriven","Master"})
    public void verify_loginDDT(String email,String pwd, String exp) throws InterruptedException {
        logger.info("****Starting TC003_LoginDDT****");
        try {
            //home page
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            //login
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            lp.setpassword(pwd);
            lp.clickLogin();

            //myaccount
            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountPageExists();

            //data is valid - login is success - test pass - logout
            // login failed- test fail
            //data is invalid - login success - test fail - logout
            // login failed - test pass

            if (exp.equalsIgnoreCase("valid")) {
                if (targetPage == true) {
                    macc.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }
            if (exp.equalsIgnoreCase("Invalid")) {
                if (targetPage == true) {
                    macc.clickLogout();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }
        }catch(Exception e)
        {
            Assert.fail();
        }
        Thread.sleep(5000);
        logger.info("****Finished TC003_LoginDDT****");
    }
}
