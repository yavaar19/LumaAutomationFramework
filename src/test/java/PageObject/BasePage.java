package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    @FindBy(xpath = "(//a[contains(text(),'Sign In')])")
    private WebElement signInHeaderButton;

    @FindBy(xpath = "(//a[contains(text(),'Create an Account')])")
    private WebElement createAccountHeaderButton;

    @FindBy(css = "a")
    private List<WebElement> allLinks;

    protected WebDriver driver;

    private WebDriverWait wait;

    public BasePage(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        PageFactory.initElements(driver, this);

    }

    public RegistrationPage clickCreateAccountHeaderButton() {

        waitForVisibilityOfElement(createAccountHeaderButton);
        createAccountHeaderButton.click();
        return new RegistrationPage(driver);

    }

    public List<WebElement> getallLinks() {

        return allLinks;

    }

    public void waitForElementToBeLocated(By locator) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    public void waitForVisibilityOfElement(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public void waitForElementToBeClickable(WebElement element) {

        wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public void waitForInvisibilityOfElement(WebElement element) {

        wait.until(ExpectedConditions.invisibilityOf(element));

    }

    public boolean isElementPresent(By locator) {

        return !driver.findElements(locator).isEmpty();

    }

}
