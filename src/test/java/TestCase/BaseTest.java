package TestCase;

import PageObject.HomePage;
import Utilities.ReadConfig;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    protected WebDriverWait wait;

    protected ReadConfig readConfig;

    protected HomePage homePage;

    private String baseUrl;


    public void initializeDriver() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();


        wait = new WebDriverWait(driver, Duration.ofSeconds(6));

    }

    public void setConfiguration() throws IOException {

        readConfig = new ReadConfig();
        baseUrl = readConfig.getBaseUrl();

    }

    public void setZoom(String percentage){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='" + percentage + "%'");

    }

    @BeforeMethod
    public HomePage launchApplication() throws IOException {

        setConfiguration();;

        initializeDriver();
        homePage = new HomePage(driver);
        homePage.goTo(baseUrl);

        return homePage;

    }

    @AfterMethod
    public void tearDown() {

        driver.quit();

    }

}
