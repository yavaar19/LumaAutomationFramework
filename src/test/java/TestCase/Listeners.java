package TestCase;

import Utilities.Screenshot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static Utilities.ExtentReport.getExtentReport;

public class Listeners extends Screenshot implements ITestListener {

    private ExtentTest test;

    private ExtentReports extent = getExtentReport();

    private WebDriver driver;

    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);

        test = extent.createTest(result.getMethod().getMethodName());
        test.log(Status.INFO, "Test started");
        threadLocal.set(test);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);

        threadLocal.get().log(Status.PASS, "Test Passed!");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);

        threadLocal.get().fail(result.getThrowable());

        String filePath;

        Object currentClas = result.getInstance();
        driver = ((BaseTest) currentClas).driver;

        try {

            filePath = getScreenshot(result.getMethod().getMethodName(), driver);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

        threadLocal.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);

        extent.flush();

    }

    @Override
    public boolean isEnabled() {
        return ITestListener.super.isEnabled();
    }
}
