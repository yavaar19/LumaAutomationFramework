package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    @FindBy(css = "span.logged-in")
    private WebElement welcomeMessageHeader;

    @FindBy(css = "button.action.switch")
    private WebElement dropDownWelcome;

    @FindBy(xpath = "//a[text()='My Account']")
    private WebElement myAccountDropDownButton;

    @FindBy(css = "a:not([rel='nofollow noopener sponsored'])")
    private List<WebElement> allLinks;

    protected WebDriver driver;

    private final WebDriverWait wait;

    private final Actions actions;

    private final By welcomeMessageHeaderLocator = By.cssSelector("span.logged-in");


    public BasePage(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);

    }

    public RegistrationPage clickCreateAccountHeaderButton() {

        waitForVisibilityOfElement(createAccountHeaderButton);
        createAccountHeaderButton.click();
        return new RegistrationPage(driver);

    }

    public LogInPage clickSignInHeaderButton() {

        waitForVisibilityOfElement(signInHeaderButton);
        signInHeaderButton.click();
        return new LogInPage(driver);

    }

    public String getWelcomeMessageHeader() {

        waitForElementToBeLocated(welcomeMessageHeaderLocator);
        waitForVisibilityOfElement(welcomeMessageHeader);
        waitForElementToBeClickable(welcomeMessageHeader);
        return welcomeMessageHeader.getText();

    }

    public MyAccountPage performActionClick(WebElement element) {

        waitForElementToBeClickable(element);
        actions.moveToElement(element).click().build().perform();

        return new MyAccountPage(driver);

    }

    public List<WebElement> getallLinks() {

        return allLinks;

    }

    public WebElement getDropDownWelcome() {

        return dropDownWelcome;

    }

    public WebElement getMyAccountDropDownButton() {

        return myAccountDropDownButton;

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
