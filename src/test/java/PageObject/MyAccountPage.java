package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

    @FindBy(xpath = "//div[contains(text(), 'Thank you')]")
    private WebElement registrationSuccessMessage;

    @FindBy(xpath = "//span[text()='My Account']")
    private WebElement myAccountHeader;

    @FindBy(xpath = "//div[contains(@class, 'box-information')]/div/p")
    private WebElement firstName;

    @FindBy(xpath = "//div[contains(@class, 'box-information')]/div/p")
    private WebElement lastName;

    @FindBy(xpath = "//span[text()='Edit']")
    private WebElement editLink;

    @FindBy(css = "span.base")
    private WebElement editAccountTitle;

    @FindBy(css = "a.change-password")
    private WebElement changePasswordLink;

    @FindBy(xpath = "//span[@data-title='change-email-password']")
    private WebElement changePasswordTitle;


    public MyAccountPage(WebDriver driver) {

        super(driver);

    }

    public String getRegistrationSuccessMessage() {

        waitForVisibilityOfElement(registrationSuccessMessage);
        return registrationSuccessMessage.getText();

    }

    public String getEditAccountTitle() {

        return editAccountTitle.getText();

    }

    public String getChangePasswordTitle() {

        return changePasswordTitle.getText();

    }

    public void clickEditLink() {

        editLink.click();

    }

    public void clickChangePasswordLink() {

        changePasswordLink.click();

    }

    public String getFirstNameAndEmail() {

        return firstName.getText();

    }

    public String getLastName() {

        return lastName.getText();

    }

}
