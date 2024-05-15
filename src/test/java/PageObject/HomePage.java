package PageObject;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {

        super(driver);

    }

    public void goTo(String baseUrl) {

        driver.get(baseUrl);

    }

}
