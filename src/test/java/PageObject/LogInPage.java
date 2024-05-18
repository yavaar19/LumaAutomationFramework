package PageObject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LogInPage extends BasePage {

    @FindBy(id = "email")
    private WebElement emailInputField;

    @FindBy(id = "pass")
    private WebElement passwordInputField;

    @FindBy(id = "send2")
    private WebElement signInButton;

    @FindBy(id = "email-error")
    private WebElement emailError;

    @FindBy(id = "pass-error")
    private WebElement passwordError;

    @FindBy(xpath = "//div[@role='alert']/div/div")
    public WebElement topAlert;

    @FindBy(css = ".remind")
    public WebElement forgotPasswordLink;

    private By topAlertLocator = By.xpath("//div[@role='alert']/div/div");

    public LogInPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public String getEmailError() {

        waitForVisibilityOfElement(emailError);
        return emailError.getText();

    }

    public String getPasswordError() {

        waitForVisibilityOfElement(passwordError);
        return passwordError.getText();

    }

    public String getTopAlertError() {

        waitForElementToBeLocated(topAlertLocator);
        return topAlert.getText();

    }

    public void setEmail(String email) {

        emailInputField.sendKeys(email);

    }

    public void setPassword(String password) {

        passwordInputField.sendKeys(password);

    }

    public HomePage clickSignInButton() {

        signInButton.click();

        return new HomePage(driver);

    }

    public ForgotPasswordPage clickForgotPasswordLink() {

        forgotPasswordLink.click();

        return new ForgotPasswordPage(driver);

    }

    public By getTopAlertLocator() {

        return topAlertLocator;

    }

}