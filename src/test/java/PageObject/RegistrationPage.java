package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPage extends BasePage {

    @FindBy(id = "firstname")
    private WebElement firstNameInputField;

    @FindBy(id = "lastname")
    private WebElement lastNameInputField;

    @FindBy(id = "email_address")
    private WebElement emailInputField;

    @FindBy(id = "password")
    private WebElement passwordInputField;

    @FindBy(id = "password-confirmation")
    private WebElement confirmPasswordInputField;

    @FindBy(xpath = "//span[text()='Create an Account']")
    private WebElement createAccountButton;

    @FindBy(id = "firstname-error")
    private WebElement firstNameError;

    @FindBy(id = "lastname-error")
    private WebElement lastNameError;

    @FindBy(id = "email_address-error")
    private WebElement emailError;

    @FindBy(id = "password-error")
    private WebElement passwordError;

    @FindBy(id = "password-confirmation-error")
    private WebElement confirmationPasswordError;

    @FindBy(xpath = "//div[text()='This is a required field.']")
    private List<WebElement> inputFieldErrorList;

    @FindBy(xpath = "//div[@role='alert']/div/div")
    private WebElement topAlert;

    private final By emailErrorFindBy = By.id("email_address-error");

    private final By topAlertFindBy = By.xpath("//div[@role='alert']/div/div");

    private final By passwordErrorFindBy = By.id("password-error");

    private final By confirmationPasswordErrorFindBy = By.id("password-confirmation-error");


    public RegistrationPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void setFirstName(String firstName) {

        firstNameInputField.sendKeys(firstName);

    }


    public void setLastName(String lastName) {

        lastNameInputField.sendKeys(lastName);

    }

    public void setFirstAndLastName(String firstName, String lastName) {

        firstNameInputField.sendKeys(firstName);
        lastNameInputField.sendKeys(lastName);

    }

    public void setEmail(String email) {

        emailInputField.sendKeys(email);

    }

    public void setPassword(String password) {

        passwordInputField.sendKeys(password);

    }

    public void setConfirmationPassword(String confirmationPassword) {

        confirmPasswordInputField.sendKeys(confirmationPassword);

    }

    public void setEmailPasswordAndConfirmationPassword(String email, String password, String confirmationPassword) {

        emailInputField.sendKeys(email);
        passwordInputField.sendKeys(password);
        confirmPasswordInputField.sendKeys(confirmationPassword);

    }

    public String getFirstNameError() {

        return firstNameError.getText();

    }

    public String getLastNameError() {

        return lastNameError.getText();

    }

    public String getEmailError() {

        waitForVisibilityOfElement(emailError);
        return emailError.getText();

    }

    public String getPasswordError() {

        return passwordError.getText();

    }

    public String getConfirmationPasswordError() {

        return confirmationPasswordError.getText();

    }

    public String getTopAlertError() {

        waitForVisibilityOfElement(topAlert);
        return topAlert.getText();

    }

    public List<String> getInputFieldErrorList() {

        List<String> errorText = new ArrayList<>();

        inputFieldErrorList.forEach(e -> errorText.add(e.getText()));

        return errorText;

    }

    public String getEmailInputField() {

        return emailInputField.getAttribute("value");

    }

    public MyAccountPage createAccountButton() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", createAccountButton);

        return new MyAccountPage(driver);

    }

    public By getEmailErrorFindBy() {

        return emailErrorFindBy;

    }

    public By getTopAlertFindBy() {

        return topAlertFindBy;

    }

    public By getPasswordErrorFindBy() {

        return passwordErrorFindBy;

    }

    public By getConfirmationPasswordErrorFindBy() {

        return confirmationPasswordErrorFindBy;

    }




}
