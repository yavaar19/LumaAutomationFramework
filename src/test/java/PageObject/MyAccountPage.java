package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

    @FindBy(xpath = "//div[contains(text(), 'Thank you')]")
    private WebElement registrationSuccessMessage;

    public MyAccountPage(WebDriver driver) {

        super(driver);

    }

    public String getRegistrationSuccessMessage() {

        waitForVisibilityOfElement(registrationSuccessMessage);
        return registrationSuccessMessage.getText();

    }


}
