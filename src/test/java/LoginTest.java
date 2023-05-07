import com.buggycarsrating.qa.entities.User;
import com.buggycarsrating.qa.pages.MainPage;
import com.buggycarsrating.qa.pages.ProfilePage;
import com.buggycarsrating.qa.pages.RegisterUserPage;
import com.buggycarsrating.qa.util.Functions;
import org.testng.Assert;
import org.testng.annotations.*;
import testbase.TestBase;

public class LoginTest extends TestBase {

    MainPage mainPage;
    ProfilePage profilePage;

    RegisterUserPage registerUserPage;

    private LoginTest() {
    }


    @BeforeClass
    public void setup() {
        getWebDriver(Functions.configPropertyReader().getProperty("url"));
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        registerUserPage =new RegisterUserPage(driver);
    }

    @Test(priority = 1, testName = "TC_BUDDYCARSRATING_LOGIN_01", description = "Verify Main Page Title")
    public void mainPageTittleTest() {
        mainPage.getHeading();
        Assert.assertEquals(mainPage.getHeading(), "Buggy Rating");
    }

    @Test(priority = 2,testName = "TC_BUDDYCARSRATING_LOGIN_02", description = "Verify login with valid user name and valid password")
    public void loginWithValidUserNameAndPasswordTest() {
        mainPage.clickRegister();
        User user = registerUserPage.registerNewUser();
        String oldUName = user.getUName();
        String password1 = user.getPWord();
        String fName = user.getFName();
        Assert.assertEquals(registerUserPage.getSuccessMessage(), "Registration is successful");
        mainPage.login(oldUName, password1);
        Assert.assertEquals(profilePage.getHiText(), "Hi," + " " + fName);

    }


    @Test(priority = 3, dependsOnMethods = "loginWithValidUserNameAndPasswordTest",testName = "TC_BUDDYCARSRATING_LOGIN_03", description = "Verify logout User")
    public void logoutUserTest() {
        profilePage.clickLogout();
        mainPage.isLoginButtonAvailable();
    }


    @Test(priority = 4, dependsOnMethods = "logoutUserTest",testName = "TC_BUDDYCARSRATING_LOGIN_04", description = "Verify login with invalid username and invalid password")
    public void loginWithInvalidUserNameAndPasswordTest() {
        mainPage.login("randomuser", "1234@45");
        Assert.assertEquals(mainPage.getErrorMessage(), "Invalid username/password");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}