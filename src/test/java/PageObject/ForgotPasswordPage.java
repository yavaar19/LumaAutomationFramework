package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage extends BasePage {

    @FindBy(css = "title")
    private WebElement pageTitle;


    public ForgotPasswordPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public String getPageTitle() {

        return pageTitle.getText();

    }


}
