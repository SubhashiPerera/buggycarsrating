import com.buggycarsrating.qa.driver.WebDriverLoader;
import com.buggycarsrating.qa.entities.User;
import com.buggycarsrating.qa.pages.MainPage;
import com.buggycarsrating.qa.pages.RegisterUserPage;
import com.buggycarsrating.qa.util.Functions;
import org.testng.Assert;
import org.testng.annotations.*;
import testbase.TestBase;

public class RegisterUserTest extends TestBase {

    MainPage mainPage;
    RegisterUserPage registerUserPage;


    private RegisterUserTest() {
    }

    @BeforeClass
    public void setup() {
        getWebDriver(Functions.configPropertyReader().getProperty("url"));
        mainPage = new MainPage(driver);
        registerUserPage = new RegisterUserPage(driver);

    }

    @Test(priority = 1, testName = "TC_BUDDYCARSRATING_REGISTER_01", description = "Verify main page tittle")
    public void mainPageTittleTest() {
        mainPage.getHeading();
        Assert.assertEquals(mainPage.getHeading(), "Buggy Rating");

    }

    @Test(priority = 2, testName = "TC_BUDDYCARSRATING_REGISTER_02", description = "Verify registering new user")
    public void registerUserTest() {

        mainPage.clickRegister();
        registerUserPage.registerNewUser();
        Assert.assertEquals(registerUserPage.getSuccessMessage(), "Registration is successful");

    }

    @Test(priority = 3, testName = "TC_BUDDYCARSRATING_REGISTER_03", description = "Verify existing user cannot be registered again")
    public void registerExistingUserTest() {

        mainPage.clickRegister();
        User user = registerUserPage.registerNewUser();
        String oldUName = user.getUName();
        registerUserPage.registerExistingUser(oldUName, "test", "test", "test@123Q");
        Assert.assertEquals(registerUserPage.getErrorMessage(), "UsernameExistsException: User already exists");

    }

    @Test(priority = 4, testName = "TC_BUDDYCARSRATING_REGISTER_04", description = "Verify cancel user register functionality")
    public void cancelRegisterUserTest() {
        mainPage.clickRegister();
        registerUserPage.clickCancel();
        Assert.assertEquals(mainPage.getPopularMake(), "Popular Make");

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

