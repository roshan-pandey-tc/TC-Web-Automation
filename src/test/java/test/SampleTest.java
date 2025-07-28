package test;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import page.LandingPage;
import utility.Utility;

import static org.testng.Assert.assertTrue;

public class SampleTest extends BaseTest {

    LandingPage landingPage;

    @Test
    @Epic("TFB Web")
    @Feature("Verified Calling")
    @Story("Business Identity")
    @Description("Log in to TFB Web and create a business")
    public void createBusinessPage() throws InterruptedException {
        landingPage = new LandingPage(page);
        Allure.step("Click sign in button");
        landingPage.clickSignInBtn();
        Allure.step("Enter username");
        landingPage.enterUserName(Utility.getTestDataFromJSON("tfbWebTestData","userName").toString());
        Allure.step("Enter password");
        landingPage.enterPass(Utility.getTestDataFromJSON("tfbWebTestData","password").toString());
        Allure.step("Click log in button");
        landingPage.clickLogInBtn();
        /*landingPage.clickBusinessIdentityDropDown();
        landingPage.clickBusinessPageBtn();
        landingPage.clickCreateBusinessPageBtn();
        landingPage.enterBusinessPageName(Utility.getTestDataFromJSON("tfbWebTestData","businessName").toString()+"-"+Utility.getCurrentDateTime());
        landingPage.uploadBusinessPic();
        landingPage.enterBrandDescription(Utility.getTestDataFromJSON("tfbWebTestData","aboutBusiness").toString());
        landingPage.clickSaveBtn();
        landingPage.clickConfirmBtn();*/
        Allure.step("Click three dots button");
        landingPage.clickThreeDotsBtn();
        Allure.step("Click logout button");
        landingPage.clickLogoutBtn();
        Thread.sleep(5000);
        /*page.locator("textarea[name='q']").fill("Playwright Java");
        page.keyboard().press("Enter");
        page.waitForTimeout(3000);
        String title = page.title();
        assertTrue(title.toLowerCase().contains("playwright"), "Title should contain 'Playwright'");*/
    }
}