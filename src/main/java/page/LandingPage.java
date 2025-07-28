package page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;

public class LandingPage {

    private final Page page;
    private final Locator btn_signIn;
    private final Locator txt_userName;
    private final Locator txt_password;
    private final Locator btn_login;
    private final Locator dropDown_businessIdentity;
    private final Locator btn_businessPage;
    private final Locator btn_createBusinessPage;
    private final Locator txt_businessPageName;
    private final Locator btn_uploadPic1;
    private final Locator btn_uploadPic2;
    private final Locator btn_uploadPic3;
    private final Locator txt_brandDesc;
    private final Locator btn_save;
    private final Locator btn_confirm;
    private final Locator btn_threeDots;
    private final Locator btn_logout;

    public LandingPage(Page page) {
        this.page = page;
        this.btn_signIn = page.locator("a[href='/signin1']");
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

    public void clickSignInBtn(){
        btn_signIn.click();
    }
    public void enterUserName(String value){
        txt_userName.fill(value);
    }
    public void enterPass(String value){
        txt_password.fill(value);
    }
    public void clickLogInBtn(){
        btn_login.click();
    }
    public void clickBusinessIdentityDropDown(){
        dropDown_businessIdentity.click();
    }
    public void clickBusinessPageBtn(){
        btn_businessPage.click();
    }
    public void clickCreateBusinessPageBtn(){
        btn_createBusinessPage.click();
    }
    public void enterBusinessPageName(String name){
        txt_businessPageName.fill("");
        txt_businessPageName.fill(name);
    }
    public void uploadBusinessPic(){
        btn_uploadPic1.setInputFiles(Paths.get("src/test/java/resources/files/Swiggy1.jpeg"));
        btn_uploadPic2.setInputFiles(Paths.get("src/test/java/resources/files/Swiggy2.jpeg"));
        btn_uploadPic3.setInputFiles(Paths.get("src/test/java/resources/files/Swiggy3.png"));
    }
    public void enterBrandDescription(String desc){
        txt_brandDesc.fill(desc);
    }
    public void clickSaveBtn(){
        btn_save.click();
    }
    public void clickConfirmBtn(){
        btn_confirm.click();
    }
    public void clickThreeDotsBtn(){
        btn_threeDots.click();
    }
    public void clickLogoutBtn(){
        btn_logout.click();
    }
}
