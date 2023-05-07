package com.buggycarsrating.qa.pages;

import com.buggycarsrating.qa.entities.User;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
public class RegisterUserPage  {


    @FindBy(id = "username")
    protected WebElement userName;

    @FindBy(id = "firstName")
    protected WebElement firstName;

    @FindBy(id = "lastName")
    protected WebElement lastName;

    @FindBy(id = "password")
    protected WebElement password;

    @FindBy(id = "confirmPassword")
    protected WebElement confirmPassword;

    @FindBy(xpath = "//button[contains(text(),'Register')]")
    protected WebElement register;

    @FindBy(xpath = "//a[contains(text(),'Cancel')]")
    protected WebElement cancel;

    @FindBy(xpath = "//div[contains(text(),'Registration is successful')]")
    protected WebElement successMessage;

    @FindBy(xpath = "//div[contains(text(),'UsernameExistsException: User already exists')]")
    protected WebElement errorMessage;


    //initialization
    public RegisterUserPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    //Method to register a new user
    public User registerNewUser() {

        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmssSS"));
        String loginUser = "User_" + dateTime;
        String fName = "FirstName" + dateTime;
        String lName = "LastName" + dateTime;
        String pWord = "passabc@123A";


        User newUser = new User();
        newUser.setUName(loginUser);
        newUser.setFName(fName);
        newUser.setLName(lName);
        newUser.setPWord(pWord);

        this.userName.sendKeys(newUser.getUName());
        this.firstName.sendKeys(newUser.getFName());
        this.lastName.sendKeys(newUser.getLName());
        this.password.sendKeys(newUser.getPWord());
        this.confirmPassword.sendKeys(newUser.getPWord());
        register.click();

        return newUser;

    }

    //Method to register existing user
    public void registerExistingUser(String login, String firstName, String lastName, String password) {

        log.info("Login:" + login);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        this.userName.click();
        this.userName.sendKeys(login);
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.password.sendKeys(password);
        this.confirmPassword.sendKeys(password);
        register.click();


    }


    public String getSuccessMessage() {
        return (successMessage).getText();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public void clickCancel() {
        cancel.click();
    }


}

