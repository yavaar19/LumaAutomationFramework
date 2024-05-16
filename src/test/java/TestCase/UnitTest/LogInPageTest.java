package TestCase.UnitTest;

import DataReader.DataReader;
import PageObject.ForgotPasswordPage;
import PageObject.HomePage;
import PageObject.LogInPage;
import TestCase.BaseTest;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public class LogInPageTest extends BaseTest {

    // Positive TestCases - Happy Path

    @Test
    public void successfulLogin() throws InterruptedException {

        LogInPage logInPage = homePage.clickSignInHeaderButton();

        logInPage.setEmail(readConfig.getLoginEmail());
        logInPage.setPassword(readConfig.getLoginPassword());

        HomePage homePage = logInPage.clickSignInButton();

        String actualWelcomeMessage = homePage.getWelcomeMessageHeader();

        String expectedWelcomeMessage = "Welcome, " + readConfig.getFirstName() + " " + readConfig.getLastName() + "!";

        Assert.assertEquals(actualWelcomeMessage, expectedWelcomeMessage);

    }

    // Negative TestCases

    // Data Driven Test

    @Test(dataProvider = "getData")
    public void wrongEmailWrongPasswordLoginDataSet(String email, String password) {

        LogInPage logInPage = homePage.clickSignInHeaderButton();

        logInPage.setEmail(email);
        logInPage.setPassword(password);

        logInPage.clickSignInButton();

        String topAlertError = logInPage.getTopAlertError();

        Assert.assertEquals(topAlertError, "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.");

    }

    @DataProvider
    public String[][] getData() throws IOException, InvalidFormatException {

        DataReader dataReader = new DataReader("src/test/java/Data/UsernamePassword.xlsx");

        return dataReader.getAllData("Credentials");


    }

    @Test
    public void blankCredentialsLogin() throws InterruptedException {

        LogInPage logInPage = homePage.clickSignInHeaderButton();

        logInPage.clickSignInButton();

        String emailError = logInPage.getEmailError();
        String passwordError = logInPage.getPasswordError();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(emailError, "This is a required field.");
        softAssert.assertEquals(passwordError, "This is a required field.");

        softAssert.assertAll();

    }

    @Test
    public void blankEmailWithPasswordLogin() {

        LogInPage logInPage = homePage.clickSignInHeaderButton();

        logInPage.setPassword(readConfig.getLoginPassword());

        logInPage.clickSignInButton();

        String emailError = logInPage.getEmailError();

        Assert.assertEquals(emailError, "This is a required field.");

    }

    @Test
    public void blankPasswordWithEmailLogin() {

        LogInPage logInPage = homePage.clickSignInHeaderButton();

        logInPage.setEmail(readConfig.getLoginEmail());

        logInPage.clickSignInButton();

        String passwordError = logInPage.getPasswordError();

        Assert.assertEquals(passwordError, "This is a required field.");

    }

    @Test
    public void wrongEmailWrongPasswordLogin() {

        LogInPage logInPage = homePage.clickSignInHeaderButton();

        logInPage.setEmail("johtatw@gmail.com");
        logInPage.setPassword("acurs_123#");

        logInPage.clickSignInButton();

        String topAlertError = logInPage.getTopAlertError();

        Assert.assertEquals(topAlertError, "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.");

    }

    @Test
    public void rightEmailWrongPasswordLogin() {

        LogInPage logInPage = homePage.clickSignInHeaderButton();

        logInPage.setEmail(readConfig.getLoginEmail());
        logInPage.setPassword("acurs_123#");

        logInPage.clickSignInButton();

        String topAlertError = logInPage.getTopAlertError();

        Assert.assertEquals(topAlertError, "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.");

    }

    @Test
    public void wrongEmailRightPasswordLogin() {

        LogInPage logInPage = homePage.clickSignInHeaderButton();

        logInPage.setEmail("estradi19@gmail.com");
        logInPage.setPassword(readConfig.getLoginPassword());

        logInPage.clickSignInButton();

        String topAlertError = logInPage.getTopAlertError();

        Assert.assertEquals(topAlertError, "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.");

    }

    @Test
    public void RightEmailDifferentCapitalizationPasswordLogin() {

        LogInPage logInPage = homePage.clickSignInHeaderButton();

        logInPage.setEmail(readConfig.getLoginEmail());
        logInPage.setPassword(readConfig.getLoginPassword().toUpperCase());

        logInPage.clickSignInButton();

        String topAlertError = logInPage.getTopAlertError();

        Assert.assertEquals(topAlertError, "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.");

    }

    @Test
    public void brokenLinksLogin() throws IOException {

        SoftAssert softAssert = new SoftAssert();

        LogInPage logInPage = homePage.clickSignInHeaderButton();

        List<String> filteredLinks = logInPage.getallLinks().stream().map(p -> p.getAttribute("href")).filter(Objects::nonNull).toList();

        for (String url : filteredLinks) {

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setRequestMethod("HEAD");

            connection.connect();

            connection.setConnectTimeout(3000);

            int responseCode = connection.getResponseCode();

            softAssert.assertEquals(responseCode, 200, "Link broken: " + url + " Resp Code: " + responseCode);

        }

        softAssert.assertAll();

    }

    @Test
    public void getConsoleErrorLogsLogin() {

        SoftAssert softAssert = new SoftAssert();

        homePage.clickSignInHeaderButton();

        LogEntries entries = driver.manage().logs().get(LogType.BROWSER);

        for (LogEntry entry : entries) {

            softAssert.assertNotEquals(entry.getLevel(), Level.SEVERE, entry.getMessage());

        }

        softAssert.assertAll();

    }

}
