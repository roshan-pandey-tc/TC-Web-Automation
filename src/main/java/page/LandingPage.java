package page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import page.utility.WebActions;

public class LandingPage {
    WebActions actions = new WebActions();
    public final Page page;
    public final Locator btn_getInTouch;
    public final Locator btn_signIn;
    public final Locator txt_userName;
    public final Locator txt_password;
    public final Locator btn_login;
    public final Locator dropDown_businessIdentity;
    public final Locator btn_businessPage;
    public final Locator btn_createBusinessPage;
    public final Locator txt_businessPageName;
    public final Locator btn_uploadPic1;
    public final Locator btn_uploadPic2;
    public final Locator btn_uploadPic3;
    public final Locator txt_brandDesc;
    public final Locator btn_save;
    public final Locator btn_confirm;
    public final Locator btn_threeDots;
    public final Locator btn_logout;

    public LandingPage(Page page) {
        this.page = page;
        this.btn_getInTouch = page.locator("//button[@title='Get in touch' and contains(text(),'Get in')]");
        this.btn_signIn = page.locator("a[href='/signin']");
        this.txt_userName = page.locator("//label[@for='input-21']/parent::div//input");
        this.txt_password = page.locator("//label[@for='input-24']/parent::div//input");
        this.btn_login = page.locator("//div[contains(text(),'Login')]");
        this.dropDown_businessIdentity = page.locator("//div[text() = 'Business Identity']");
        this.btn_businessPage = page.locator("//div[@class='v-expansion-panel-content']//div//div[2]");
        this.btn_createBusinessPage = page.locator("//span[text()='Create business page']");
        this.txt_businessPageName = page.locator("//input[@name='name']");
        this.btn_uploadPic1 = page.locator("(//div[contains(@class, 'business-profile-image-upload__item')]//input[@type='file'])[1]");
        this.btn_uploadPic2 = page.locator("(//div[contains(@class, 'business-profile-image-upload__item')]//input[@type='file'])[2]");
        this.btn_uploadPic3 = page.locator("(//div[contains(@class, 'business-profile-image-upload__item')]//input[@type='file'])[3]");
        this.txt_brandDesc = page.locator("//textarea[@name='about']");
        this.btn_save = page.locator("//span[text()='Save']");
        this.btn_confirm = page.locator("//button[@class ='tcbtn__dashboard tcbtn__dashboard--primary' and contains(text(),Confirm)]");
        this.btn_threeDots = page.locator("//button[@type='button']//span//i[@class='v-icon notranslate mdi mdi-dots-horizontal theme--light']");
        this.btn_logout = page.locator("//div[text()='Logout']");
    }
    public boolean isSignInBtnVisible(){
        return actions.isVisible(btn_signIn);
    }
    public void clickSignInBtn(){
        actions.clickElement(btn_signIn);
    }
    public void enterUserName(String value){
        actions.sendKeys(txt_userName,value);
    }
    public void enterPass(String value){
        actions.sendKeys(txt_password,value);
    }
    public void clickLogInBtn(){
       actions.clickElement(btn_login);
    }
    public void clickBusinessIdentityDropDown(){
        actions.clickElement(dropDown_businessIdentity);
    }
    public void clickBusinessPageBtn(){
        actions.clickElement(btn_businessPage);
    }
    public void clickCreateBusinessPageBtn(){
        actions.clickElement(btn_createBusinessPage);
    }
    public void enterBusinessPageName(String name){
       actions.sendKeys(txt_businessPageName,name);
    }

    public void uploadBusinessPic(){
        actions.uploadFiles("src/test/java/resources/files/Swiggy1.jpeg",btn_uploadPic1);
        actions.uploadFiles("src/test/java/resources/files/Swiggy2.jpeg",btn_uploadPic1);
        actions.uploadFiles("src/test/java/resources/files/Swiggy3.png",btn_uploadPic1);
    }
    public void enterBrandDescription(String desc){
        actions.sendKeys(txt_brandDesc,desc);
    }
    public void clickSaveBtn(){
        actions.clickElement(btn_save);
    }
    public void clickConfirmBtn(){
        actions.clickElement(btn_confirm);
    }
    public void clickThreeDotsBtn(){
        actions.clickElement(btn_threeDots);
    }
    public void clickLogoutBtn(){
        actions.clickElement(btn_logout);
    }
}