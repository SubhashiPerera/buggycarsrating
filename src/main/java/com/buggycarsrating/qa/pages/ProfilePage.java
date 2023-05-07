package com.buggycarsrating.qa.pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProfilePage  {

    private static final String VALUE = "value";

    //Page Factory - Object Repository
    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    protected WebElement logoutBtn;

    @FindBy(xpath = "//a[contains(text(),'Profile')]")
    protected WebElement profile;

    @FindBy(xpath = "//span[contains(text(),'Hi')]")
    protected WebElement hi;

    @FindBy(id = "gender")
    protected WebElement gender;

    @FindBy(id = "age")
    protected WebElement age;

    @FindBy(id = "address")
    protected WebElement address;

    @FindBy(id = "currentPassword")
    protected WebElement currentPassword;

    @FindBy(id = "newPassword")
    protected WebElement newPassword;

    @FindBy(id = "newPasswordConfirmation")
    protected WebElement confirmPassword;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    protected WebElement save;

    @FindBy(xpath = "//div[contains(text(),'The profile has been saved successful')]")
    protected WebElement successMessage;

    //initialization
    public ProfilePage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    //Method to update user information
    public void updateData(String gender, String age, String address) {
        profile.click();
        this.gender.sendKeys(gender);
        this.age.sendKeys(age);
        this.address.sendKeys(address);
        save.click();

    }

    //Method to update user information
    public void updatePassword(String currentPassword, String newPassword, String confirmPassword) {
        profile.click();
        this.currentPassword.sendKeys(currentPassword);
        this.newPassword.sendKeys(newPassword);
        this.confirmPassword.sendKeys(confirmPassword);
        save.click();

    }

    //Method to get "Hi" text
    public String getHiText() {
        hi.getText();
        return hi.getText();
    }

    //Method to click the profile
    public void clickProfile() {
        profile.click();
    }

    //Method to click logout button
    public void clickLogout() {
        logoutBtn.click();
    }

    //Method to get gender information
    public String getGender() {
        return gender.getAttribute(VALUE);
    }

    //Method to get age information
    public String getAge() {
        return age.getAttribute(VALUE);
    }

    //Method to get address information
    public String getAddress() {
        return address.getAttribute(VALUE);
    }

    //Method to get success message
    public String getSuccessMsg() {
        return successMessage.getText();

    }
}