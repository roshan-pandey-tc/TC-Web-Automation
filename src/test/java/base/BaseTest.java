package base;

import com.microsoft.playwright.*;

import io.qameta.allure.testng.AllureTestNg;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utility.RetryTransformer;
import utility.TestListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/*
This is added to run the listeners by default, if it's not added the screenshot and console log won't be capture
 if the test is run via IDE UI
*/
@Listeners({
        //io.qameta.allure.testng.AllureTestNg.class,
        //utility.TestListener.class,
        TestListener.class,
        AllureTestNg.class,
        RetryTransformer.class
})
public class BaseTest {

    public Playwright playwright;
    public Browser browser;
    public BrowserContext context;
    public Page page;
    public final List<String> consoleLogs = new ArrayList<>();

    public String fetchDataFromConfigFile(String key) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/java/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        playwright = Playwright.create();
        String browserName = fetchDataFromConfigFile("browser").toLowerCase();
        String moduleName = fetchDataFromConfigFile("module").toLowerCase();
        boolean isHeadless = Boolean.parseBoolean(fetchDataFromConfigFile("setHeadless"));

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(isHeadless);
        browser = switch (browserName) {
            case "firefox" -> playwright.firefox().launch(options);
            case "webkit", "safari" -> playwright.webkit().launch(options);
            case "chrome", "chromium" -> playwright.chromium().launch(options);
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
        };

        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1280, 720));
        page = context.newPage();

        page.onConsoleMessage(msg -> {
            String log = msg.type() + ": " + msg.text();
            System.out.println("🪵 Console Log Captured: " + log); // optional debug
            consoleLogs.add(log);
        });

        Map<String, String> moduleUrlMap = Map.of(
                "tfb", fetchDataFromConfigFile("urlTfbWeb"),
                "ads", fetchDataFromConfigFile("urlAdsWeb")
        );

        String url = moduleUrlMap.get(moduleName);
        if (url == null) throw new IllegalArgumentException("Unsupported module: " + moduleName);
        page.navigate(url);

        //page.onConsoleMessage(msg -> consoleLogs.add(msg.type() + ": " + msg.text()));
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @AfterSuite(alwaysRun = true)
    public void generateAllureReport() {
        try {
            File resultsFolder = new File("target/allure-results");
            if (!resultsFolder.exists()) {
                System.err.println("Allure results folder not found.");
                return;
            }

            ProcessBuilder builder = new ProcessBuilder(
                    "allure", "generate", "--clean", "target/allure-results", "-o", "target/allure-report"
            );
            builder.inheritIO();
            Process process = builder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("✅ Allure report generated.");
            } else {
                System.err.println("❌ Failed to generate Allure report. Exit code: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<String> getConsoleLogs() {
        return consoleLogs;
    }
}