package TestCase;

import PageObject.HomePage;
import Utilities.ReadConfig;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class BaseTest {

    protected WebDriver driver;

    protected ReadConfig readConfig;

    protected HomePage homePage;

    private String baseUrl;

    private String browser;


    public void initializeDriver() {

        if (browser.contains("chrome")) {

            ChromeOptions options = new ChromeOptions();

            if (browser.contains("headless")) options.addArguments("headless");

            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440, 900));

        } else if (browser.contains("firefox")) {

            FirefoxOptions options = new FirefoxOptions();

            if (browser.contains("headless"))  options.addArguments("-headless");

            driver = new FirefoxDriver(options);
            driver.manage().window().setSize(new Dimension(1440, 900));

        } else if (browser.contains("internetexplorer")) {

            InternetExplorerOptions options = new InternetExplorerOptions();

            driver = new InternetExplorerDriver(options);

        }

        driver.manage().window().maximize();

    }

    public void setConfiguration() throws IOException {

        readConfig = new ReadConfig();
        baseUrl = readConfig.getBaseUrl();

    }

    public void setZoom(String percentage){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='" + percentage + "%'");

    }

    @BeforeMethod(alwaysRun = true)
    public HomePage launchApplication() throws IOException {

        setConfiguration();

        browser = System.getProperty("browser") == null ? readConfig.getBrowser().toLowerCase() : System.getProperty("browser").toLowerCase();

        initializeDriver();
        homePage = new HomePage(driver);
        homePage.goTo(baseUrl);

        return homePage;

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        driver.quit();

    }

}
