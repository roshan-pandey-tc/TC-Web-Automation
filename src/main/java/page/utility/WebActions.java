package page.utility;

import com.microsoft.playwright.Locator;

import java.nio.file.Paths;

public class WebActions {
    public void clickElement(Locator locator){
        locator.waitFor();
        locator.click();
    }
    public void sendKeys(Locator locator, String text){
        locator.waitFor();
        locator.fill("");
        locator.fill(text);
    }
    public boolean isVisible(Locator locator){
        locator.waitFor();
        return locator.isVisible();
    }
    public void uploadFiles(String locationOfFile, Locator locator){
        locator.setInputFiles(Paths.get(locationOfFile));
    }
    public void scrollToElement(Locator locator){
        locator.scrollIntoViewIfNeeded();
    }
}
