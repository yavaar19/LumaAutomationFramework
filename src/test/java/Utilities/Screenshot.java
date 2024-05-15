package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Screenshot {

    public String getScreenshot(String testCase, WebDriver driver) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;

        File src = ts.getScreenshotAs(OutputType.FILE);

        File dest = new File("src/test/java/Reporting/Screenshots/" + testCase + ".png");

        FileUtils.copyFile(src, dest);

        return dest.getAbsolutePath();

    }

}
