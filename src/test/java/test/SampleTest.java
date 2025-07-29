package test;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import page.LandingPage;
import utility.Utility;

public class SampleTest extends BaseTest {

    LandingPage landingPage;

    @Test
    @Epic("TFB Web")
    @Feature("Verified Calling")
    @Story("Business Identity")
    @Description("Log in to TFB Web and create a business")
    public void createBusinessPage() throws InterruptedException {

        landingPage = new LandingPage(page);
        Utility.allureStepAssert(landingPage.isSignInBtnVisible(),true);
        landingPage.clickSignInBtn();
        landingPage.enterUserName(Utility.getTestDataFromJSON("tfbWebTestData","userName").toString());
        landingPage.enterPass(Utility.getTestDataFromJSON("tfbWebTestData","password").toString());
        landingPage.clickLogInBtn();
        Utility.allureStepAssert("Signin | Truecaller for Business",page.title());
        landingPage.clickBusinessIdentityDropDown();
        landingPage.clickBusinessPageBtn();
        landingPage.clickCreateBusinessPageBtn();
        landingPage.enterBusinessPageName(Utility.getTestDataFromJSON("tfbWebTestData","businessName").toString()+"-"+Utility.getCurrentDateTime());
        landingPage.uploadBusinessPic();
        landingPage.enterBrandDescription(Utility.getTestDataFromJSON("tfbWebTestData","aboutBusiness").toString());
        landingPage.clickSaveBtn();
        landingPage.clickConfirmBtn();
        landingPage.clickThreeDotsBtn();
        landingPage.clickLogoutBtn();
        Utility.allureStepAssert(landingPage.isSignInBtnVisible(),true);
        Thread.sleep(5000);
    }
}