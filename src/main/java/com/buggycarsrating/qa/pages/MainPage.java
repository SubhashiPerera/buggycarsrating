package com.buggycarsrating.qa.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage {


    //Page Factory - Object Repository
    @FindBy(xpath = "//div[1]/a[@class=\"navbar-brand\"]")
    protected WebElement heading;

    @FindBy(xpath = "//div[1]/my-login/div/form/a[@class=\"btn btn-success-outline\"]")
    protected WebElement registerBtn;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    protected WebElement loginBtn;

    @FindBy(name = "login")
    protected WebElement userName;

    @FindBy(name = "password")
    protected WebElement password;

    @FindBy(xpath = "//span[contains(text(),'Hi')]")
    protected WebElement greeting;

    @FindBy(xpath = "//span[contains(text(),'Invalid username/password')]")
    protected WebElement errorMessage;

    @FindBy(xpath = "//a[@href='/overall']")
    protected WebElement overallRating;

    @FindBy(xpath = "//h2[contains(text(),'Popular Make')]")
    protected WebElement popularMaking;

    @FindBy(linkText = "Profile")
    protected WebElement profile;

    @FindBy(xpath = "//*[@title = 'Alfa Romeo']")
    protected WebElement alfaRomeo;

    @FindBy(xpath = "//*[@title = 'Guilia Quadrifoglio']")
    protected WebElement popularModel;


    //Initialization
    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    //Method to get heading
    public String getHeading() {

        return heading.getText();

    }

    //Method to check the login button enabled
    public boolean isLoginButtonAvailable() {
        return loginBtn.isEnabled();
    }

    //Method to login
    public void login(String userName, String password) {
        this.userName.sendKeys(userName);
        this.password.sendKeys(password);
        loginBtn.click();
    }

    //Method to click on Register button
    public void clickRegister() {
        registerBtn.click();
    }

    //Method to click the Popular Model
    public void clickPopularModel() {
        popularModel.click();
    }

    //Method to get the Popular Make title
    public String getPopularMake() {

        return popularMaking.getText();

    }

    //Method to get the login error message
    public String getErrorMessage() {

        return errorMessage.getText();
    }
}
