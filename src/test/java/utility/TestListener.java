package utility;

import base.BaseTest;
import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            BaseTest testInstance = (BaseTest) result.getInstance();
            Page page = testInstance.page;
            List<String> logs = testInstance.getConsoleLogs();

            String testName = result.getName();
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String screenshotName = testName + "_" + timestamp + ".png";

            // Capture screenshot as byte array
            byte[] screenshotBytes = page.screenshot(new Page.ScreenshotOptions().setFullPage(false));
            Allure.addAttachment(screenshotName, "image/png", new ByteArrayInputStream(screenshotBytes), ".png");

            // Attach console logs
            System.out.println("🪵 Log count: " + (logs == null ? 0 : logs.size()));
            if (logs != null && !logs.isEmpty()) {
                StringBuilder logBuilder = new StringBuilder();
                for (String line : logs) {
                    logBuilder.append(line).append("\n");
                }
                ByteArrayInputStream logStream = new ByteArrayInputStream(logBuilder.toString().getBytes(StandardCharsets.UTF_8));
                Allure.addAttachment("Browser Console Logs - " + testName, "text/plain", logStream,".log");
            }
            System.out.println("✅ Attached screenshot and console logs for: " + testName);
        } catch (Exception e) {
            System.err.println("❌ Failed to capture screenshot/logs for Allure:");
            e.printStackTrace();
        }
    }
}