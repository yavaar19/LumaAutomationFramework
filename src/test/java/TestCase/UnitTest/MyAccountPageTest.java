package TestCase.UnitTest;

import PageObject.LogInPage;
import PageObject.MyAccountPage;
import TestCase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class MyAccountPageTest extends BaseTest {

    @Test
    public void confirmAccountNameAccountPage() {

        MyAccountPage accountPage = getMyAccountPage();

        String name = accountPage.getFirstNameAndEmail().split("\n")[0];

        String expected = readConfig.getFirstName() + " " + readConfig.getLastName();

        Assert.assertEquals(name, expected);

    }

    @Test
    public void confirmAccountEmailAccountPage() {

        MyAccountPage accountPage = getMyAccountPage();

        String email = accountPage.getFirstNameAndEmail().split("\n")[1];

        String expected = readConfig.getLoginEmail();

        Assert.assertEquals(email, expected);

    }

    @Test
    public void confirmEditLinkAccountPage() {

        MyAccountPage accountPage = getMyAccountPage();

        accountPage.clickEditLink();

        String actual = accountPage.getEditAccountTitle();

        Assert.assertEquals(actual, "Edit Account Information");

    }

    @Test
    public void confirmChangePasswordLinkAccountPage() {

        MyAccountPage accountPage = getMyAccountPage();

        accountPage.clickChangePasswordLink();

        String actualAccountTitle = accountPage.getEditAccountTitle();

        String actualChangePasswordTitle = accountPage.getChangePasswordTitle();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actualAccountTitle, "Edit Account Information");
        softAssert.assertEquals(actualChangePasswordTitle, "Change Password");

        softAssert.assertAll();

    }

    private MyAccountPage getMyAccountPage() {

        String email = readConfig.getLoginEmail();
        String password = readConfig.getLoginPassword();

        LogInPage logInPage = homePage.clickSignInHeaderButton();

        logInPage.setEmail(email);
        logInPage.setPassword(password);

        logInPage.clickSignInButton();

        logInPage.performActionClick(logInPage.getDropDownWelcome());

        return logInPage.performActionClick(logInPage.getMyAccountDropDownButton());

    }

}
