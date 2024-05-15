package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

    public static ExtentReports getExtentReport() {

        ExtentSparkReporter reporter = new ExtentSparkReporter("src/test/java/Reporting/Report");

        reporter.config().setReportName("Luma Test Automation Result");
        reporter.config().setReportName("Luma Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);

        extent.setSystemInfo("Tester", "Yavaar Nosimohomed");

        return extent;

    }

}
