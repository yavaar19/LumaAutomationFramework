package TestCase.UnitTest;

import PageObject.RegistrationPage;
import PageObject.MyAccountPage;
import TestCase.BaseTest;
import TestCase.Retry;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

import static Utilities.StringGenerator.*;

public class RegistrationPageTest extends BaseTest {

    RegistrationPage registrationPage;

    String firstName = getFirstName();
    String lastName = getLastName();
    String email;
    String password = getPassword();

    // Positive TestCases - Happy Path

    @Test(groups = {"smoke"})
    public void successfulRegistration() throws InterruptedException, IOException {

        email = getEmail();

        registrationPage = homePage.clickCreateAccountHeaderButton();

        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmailPasswordAndConfirmationPassword(email, password, password);
        setZoom("50");
        MyAccountPage accountButton = registrationPage.createAccountButton();

        String successMessage = accountButton.getRegistrationSuccessMessage();

        if (successMessage.equals("Thank you for registering with Main Website Store.")) {

            readConfig.setFirstName(firstName);
            readConfig.setLastName(lastName);
            readConfig.setLoginEmail(email);
            readConfig.setLoginPassword(password);
            readConfig.saveConfig();

        }

        Assert.assertEquals(successMessage, "Thank you for registering with Main Website Store.", "Registration unsuccessful");

    }

    // Negative TestCases

    @Test(dependsOnMethods = "successfulRegistration")
    public void existingEmailRegistration() throws InterruptedException {

        String existingEmail = readConfig.getLoginEmail();

        registrationPage = homePage.clickCreateAccountHeaderButton();

        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmailPasswordAndConfirmationPassword(existingEmail, password, password);
        setZoom("50");
        registrationPage.createAccountButton();

        System.out.println(registrationPage.getTopAlertError());

        Assert.assertEquals(registrationPage.getTopAlertError(), "There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account.",
                "Should not be able to register with an already registered email: " + existingEmail);

    }

    @Test
    public void blankFormRegistration() throws InterruptedException {

        registrationPage = homePage.clickCreateAccountHeaderButton();

        setZoom("50");
        registrationPage.createAccountButton();

        int errorListSize = registrationPage.getInputFieldErrorList().size();

        Assert.assertEquals(errorListSize, 5, "Blank form should have 5 input errors");

    }

    @Test
    public void blankFirstNameRegistration() throws InterruptedException {

        email = getEmail();

        registrationPage = homePage.clickCreateAccountHeaderButton();

        registrationPage.setLastName(lastName);
        registrationPage.setEmailPasswordAndConfirmationPassword(email, password, password);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getFirstNameError();

        Assert.assertEquals(errorMessage, "This is a required field.", "First name should not be blank");

    }

    @Test
    public void firstNameRegistrationWithSpace() throws InterruptedException {

        email = getEmail();

        registrationPage = homePage.clickCreateAccountHeaderButton();

        String firstName = "Jo hn";
        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmailPasswordAndConfirmationPassword(email, password, password);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getTopAlertError();

        Assert.assertEquals(errorMessage, "First Name is not valid!", "First name should not contain whitespace: " + firstName);

    }

    @Test(retryAnalyzer = Retry.class)
    public void firstNameRegistrationWithSpecialCharacter() throws InterruptedException {

        email = getEmail();

        registrationPage = homePage.clickCreateAccountHeaderButton();

        String firstName = "Jo/hn";
        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmailPasswordAndConfirmationPassword(email, password, password);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getTopAlertError();

        Assert.assertEquals(errorMessage, "First Name is not valid!", "First name should not contain special character: " + firstName);

    }

    @Test
    public void firstNameRegistrationWithNumber() throws InterruptedException {

        email = getEmail();

        registrationPage = homePage.clickCreateAccountHeaderButton();

        String firstName = "John1";
        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmailPasswordAndConfirmationPassword(email, password, password);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getTopAlertError();

        Assert.assertEquals(errorMessage, "First Name is not valid!", "First name should not contain number: " + firstName);

    }

    @Test
    public void blankLastNameRegistration() throws InterruptedException {

        email = getEmail();

        registrationPage = homePage.clickCreateAccountHeaderButton();

        registrationPage.setFirstName(firstName);
        registrationPage.setEmailPasswordAndConfirmationPassword(email, password, password);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getLastNameError();

        Assert.assertEquals(errorMessage, "This is a required field.", "Last name should not be blank");

    }

    @Test
    public void lastNameRegistrationWithSpace() throws InterruptedException {

        email = getEmail();

        registrationPage = homePage.clickCreateAccountHeaderButton();

        String lastName = "Smi th";
        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmailPasswordAndConfirmationPassword(email, password, password);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getTopAlertError();

        Assert.assertEquals(errorMessage, "Last Name is not valid!", "Last name should not contain whitespace: " + lastName);

    }

    @Test()
    public void lastNameRegistrationWithSpecialCharacter() throws InterruptedException {

        email = getEmail();

        registrationPage = homePage.clickCreateAccountHeaderButton();

        String lastName = "Smi/th";
        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmailPasswordAndConfirmationPassword(email, password, password);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getTopAlertError();

        Assert.assertEquals(errorMessage, "Last Name is not valid!", "Last name should not contain special character: " + lastName);

    }

    @Test
    public void lastNameRegistrationWithNumber() throws InterruptedException {

        email = getEmail();

        registrationPage = homePage.clickCreateAccountHeaderButton();

        String lastName = "Smith1";
        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmailPasswordAndConfirmationPassword(email, password, password);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getTopAlertError();

        Assert.assertEquals(errorMessage, "Last Name is not valid!", "Last name should not contain number: " + lastName);

    }

    @Test
    public void blankEmailRegistration() throws InterruptedException {

        registrationPage = homePage.clickCreateAccountHeaderButton();

        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setPassword(password);
        registrationPage.setConfirmationPassword(password);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getEmailError();

        Assert.assertEquals(errorMessage, "This is a required field.", "Email should not be blank");

    }

    @Test
    public void emailRegistrationDoesNotAllowSpace() {

        email = getModifiedEmail(" ", "middle", 1);

        registrationPage = homePage.clickCreateAccountHeaderButton();

        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmail(email);
        registrationPage.setPassword(password);
        registrationPage.setConfirmationPassword(password);
        setZoom("50");

        String alteredEmail = registrationPage.getEmailInputField();

        Assert.assertNotEquals(alteredEmail, email, "Email input field should remove the space automatically: " + email);

    }

    @Test
    public void emailRegistrationDoesNotAllowLeadingSpecialCharacter() throws InterruptedException {

        email = getModifiedEmail(".", "leading", 1);

        registrationPage = homePage.clickCreateAccountHeaderButton();

        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmail(email);
        registrationPage.setPassword(password);
        registrationPage.setConfirmationPassword(password);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getEmailError();

        Assert.assertEquals(errorMessage, "Please enter a valid email address (Ex: johndoe@domain.com).", "Email should not contain leading period: " + email);

    }

    @Test
    public void emailRegistrationDoesNotAllowTrailingSpecialCharacter() throws InterruptedException {

        email = getModifiedEmail(".", "trailing", 1);
        registrationPage = homePage.clickCreateAccountHeaderButton();

        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmail(email);
        registrationPage.setPassword(password);
        registrationPage.setConfirmationPassword(password);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getEmailError();

        Assert.assertEquals(errorMessage, "Please enter a valid email address (Ex: johndoe@domain.com).", "Email should not contain trailing period: " + email);

    }

    @Test
    public void emailRegistrationDoesNotAllowConsecutivePeriod() throws InterruptedException {

        email = getModifiedEmail(".", "middle", 4);

        registrationPage = homePage.clickCreateAccountHeaderButton();

        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmail(email);
        registrationPage.setPassword(password);
        registrationPage.setConfirmationPassword(password);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getEmailError();

        Assert.assertEquals(errorMessage, "Please enter a valid email address (Ex: johndoe@domain.com).", "Email should not contain two consecutive periods: " + email);

    }

    @Test
    public void emailRegistrationWithoutAtSign() throws InterruptedException {

        email = getEmail();

        String firstHalf = email.split("@")[0];
        String secondHalf = email.split("@")[1];

        String invalidEmail = firstHalf.concat(secondHalf);

        registrationPage = homePage.clickCreateAccountHeaderButton();

        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmail(invalidEmail);
        registrationPage.setPassword(password);
        registrationPage.setConfirmationPassword(password);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getEmailError();

        Assert.assertEquals(errorMessage, "Please enter a valid email address (Ex: johndoe@domain.com).", "Email should contain the @ sign: " + invalidEmail);

    }

    @Test
    public void emailRegistrationInvalidEmail() throws InterruptedException {

        email = getEmail().concat("sadafd");

        registrationPage = homePage.clickCreateAccountHeaderButton();

        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmail(email);
        registrationPage.setPassword(password);
        registrationPage.setConfirmationPassword(password);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getEmailError();

        Assert.assertEquals(errorMessage, "Please enter a valid email address (Ex: johndoe@domain.com).", "Invalid Email should not be allowed: " + email);

    }

    @Test(retryAnalyzer = Retry.class)
    public void blankPasswordRegistration() throws InterruptedException {

        email = getEmail();

        registrationPage = homePage.clickCreateAccountHeaderButton();

        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmail(email);
        registrationPage.setConfirmationPassword(password);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getPasswordError();

        Assert.assertEquals(errorMessage, "This is a required field.", "Password should not be blank");

    }

    @Test
    public void threeCharacterPasswordRegistration() throws InterruptedException {

        email = getEmail();
        String customPassword = "aaa";

        registrationPage = homePage.clickCreateAccountHeaderButton();

        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmail(email);
        registrationPage.setPassword(customPassword);
        registrationPage.setConfirmationPassword(customPassword);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getPasswordError();

        Assert.assertEquals(errorMessage, "Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.", "Password should contains 8 or more characters! Password: " + customPassword);

    }

    @Test
    public void twoDifferentClassesPasswordRegistration() throws InterruptedException {

        email = getEmail();
        String customPassword = "aaaDDDaaa";

        registrationPage = homePage.clickCreateAccountHeaderButton();

        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmail(email);
        registrationPage.setPassword(customPassword);
        registrationPage.setConfirmationPassword(customPassword);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getPasswordError();

        Assert.assertEquals(errorMessage, "Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters.", "Password should contains three or more difference classes! Password: " + customPassword);

    }

    @Test
    public void threeDifferentClassesPasswordRegistration() throws InterruptedException {

        email = getEmail();
        String customPassword = "aaaDDD111";

        registrationPage = homePage.clickCreateAccountHeaderButton();

        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmail(email);
        registrationPage.setPassword(customPassword);
        registrationPage.setConfirmationPassword(customPassword);
        setZoom("50");
        MyAccountPage accountPage = registrationPage.createAccountButton();

        String registrationSuccessMessage = accountPage.getRegistrationSuccessMessage();

        Assert.assertEquals(registrationSuccessMessage, "Thank you for registering with Main Website Store.",
                "Should be able to create account with valid password: " + customPassword);

    }

    @Test
    public void blankConfirmationPasswordRegistration() throws InterruptedException {

        email = getEmail();

        registrationPage = homePage.clickCreateAccountHeaderButton();

        registrationPage.setPassword(password);
        registrationPage.setEmail(email);
        registrationPage.setFirstAndLastName(firstName, lastName);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getConfirmationPasswordError();

        Assert.assertEquals(errorMessage, "This is a required field.", "Confirmation password should not be blank");

    }

    @Test
    public void mismatchPasswordRegistration() throws InterruptedException {

        email = getEmail();
        String confirmationPassword = password.concat("A_1");

        registrationPage = homePage.clickCreateAccountHeaderButton();

        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmail(email);
        registrationPage.setPassword(password);
        registrationPage.setConfirmationPassword(confirmationPassword);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getConfirmationPasswordError();

        Assert.assertEquals(errorMessage, "Please enter the same value again.",
                "Password and confirmation password should match! Password: " + password + " Confirmation Password: " + confirmationPassword);

    }

    @Test
    public void samePasswordWithDifferentCapitalizationPasswordRegistration() throws InterruptedException {

        email = getEmail();
        String confirmationPassword = password.toLowerCase();

        registrationPage = homePage.clickCreateAccountHeaderButton();

        registrationPage.setFirstAndLastName(firstName, lastName);
        registrationPage.setEmail(email);
        registrationPage.setPassword(password);
        registrationPage.setConfirmationPassword(confirmationPassword);
        setZoom("50");
        registrationPage.createAccountButton();

        String errorMessage = registrationPage.getConfirmationPasswordError();

        Assert.assertEquals(errorMessage, "Please enter the same value again.", "Password and confirmation password should match! Password: " + password + " Confirmation Password: " + confirmationPassword);

    }

    @Test
    public void brokenLinksRegistration() throws IOException {

        SoftAssert softAssert = new SoftAssert();

        registrationPage = homePage.clickCreateAccountHeaderButton();

        List<String> filteredLinks = registrationPage.getallLinks().stream()
                .map(p -> p.getAttribute("href")).filter(Objects::nonNull).toList();

        for (String url : filteredLinks) {

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setRequestMethod("HEAD");
            connection.connect();

            connection.setConnectTimeout(3000);

            int responseCode = connection.getResponseCode();

            softAssert.assertTrue(responseCode == 200, "Link broken: " + url + " Resp Code: " + responseCode);

        }

        softAssert.assertAll();

    }

    @Test
    public void consoleErrorLogsRegistration() {

        SoftAssert softAssert = new SoftAssert();

        registrationPage = homePage.clickCreateAccountHeaderButton();

        LogEntries entries = driver.manage().logs().get(LogType.BROWSER);

        for (LogEntry entry : entries) {

            softAssert.assertNotEquals(entry.getLevel(), Level.SEVERE, entry.getMessage());

        }

        softAssert.assertAll();

    }

}
