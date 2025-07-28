package utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private final int maxRetries = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetries) {
            retryCount++;
            System.out.println("Retrying " + result.getName() + " again [count: " + retryCount + "]");
            return true;
        }
        return false;
    }
}
