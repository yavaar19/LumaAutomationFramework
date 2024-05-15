package TestCase;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int count = 0;
    int retries = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {

        if (count < retries) {

            count++;
            return true;

        }

        return false;
    }

}
