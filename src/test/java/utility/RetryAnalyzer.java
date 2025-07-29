/*
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
*/
package utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 2; // Adjust as needed

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("🔁 Retrying test: " + result.getName() + " (Retry #" + retryCount + ")");

            try {
                Thread.sleep(5000); // Wait 5 seconds before retry
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("⚠️ Retry wait interrupted");
            }
            return true;
        }
        return false;
    }
}

