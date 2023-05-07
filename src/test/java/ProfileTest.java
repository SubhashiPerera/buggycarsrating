import com.buggycarsrating.qa.entities.User;
import com.buggycarsrating.qa.pages.MainPage;
import com.buggycarsrating.qa.pages.ProfilePage;
import com.buggycarsrating.qa.pages.RegisterUserPage;
import com.buggycarsrating.qa.util.Functions;
import org.testng.Assert;
import org.testng.annotations.*;
import testbase.TestBase;


public class ProfileTest extends TestBase {

    MainPage mainPage;
    ProfilePage profilePage;
    RegisterUserPage registerUserPage;




    @BeforeClass
    public void setup() {
        getWebDriver(Functions.configPropertyReader().getProperty("url"));
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        registerUserPage =new RegisterUserPage(driver);
    }

    @Test(priority = 1,testName = "TC_BUDDYCARSRATING_PROFILE_01", description = "Verify Main Page")
    public void mainPageTittleTest() {
        mainPage.getHeading();
        Assert.assertEquals(mainPage.getHeading(), "Buggy Rating");

    }

    @Test(priority = 2,testName = "TC_BUDDYCARSRATING_PROFILE_02", description = "Verify update additional information in the User Profile")
    public void updateAdditionalInfoTest() {


        mainPage.clickRegister();
        User user = registerUserPage.registerNewUser();
        String oldUName = user.getUName();
        String password1 = user.getPWord();
        String fName = user.getFName();
        Assert.assertEquals(registerUserPage.getSuccessMessage(), "Registration is successful");
        mainPage.login(oldUName, password1);
        Assert.assertEquals(profilePage.getHiText(), "Hi," + " " + fName);
        profilePage.updateData("Female", "16", "teststreet");
        Assert.assertEquals(profilePage.getSuccessMsg(), "The profile has been saved successful");

    }


    @Test(priority = 3,testName = "TC_BUDDYCARSRATING_PROFILE_03", description = "Verify saved additional information in user profile")
    public void verifySavedAdditionalInfoTest() {
        profilePage.clickLogout();
        mainPage.clickRegister();
        User user = registerUserPage.registerNewUser();
        String oldUName = user.getUName();
        String password1 = user.getPWord();
        String fName = user.getFName();
        Assert.assertEquals(registerUserPage.getSuccessMessage(), "Registration is successful");
        mainPage.login(oldUName, password1);
        Assert.assertEquals(profilePage.getHiText(), "Hi," + " " + fName);
        profilePage.updateData("Female", "16", "teststreet");
        Assert.assertEquals(profilePage.getSuccessMsg(), "The profile has been saved successful");
        profilePage.clickProfile();
        profilePage.getGender();
        profilePage.getAge();
        profilePage.getAddress();
        Assert.assertEquals(profilePage.getGender(), "Female");
        Assert.assertEquals(profilePage.getAge(), "16");
        Assert.assertEquals(profilePage.getAddress(), "teststreet");
    }


    @Test(priority = 4,testName = "TC_BUDDYCARSRATING_PROFILE_04", description = "Change old password and verify login from new password")
    public void changePasswordTest() {
        String newPassword = "test123$Q";
        profilePage.clickLogout();
        mainPage.clickRegister();
        User user = registerUserPage.registerNewUser();
        String oldUName = user.getUName();
        String password1 = user.getPWord();
        String fName = user.getFName();
        Assert.assertEquals(registerUserPage.getSuccessMessage(), "Registration is successful");
        mainPage.login(oldUName, password1);
        Assert.assertEquals(profilePage.getHiText(), "Hi," + " " + fName);
        profilePage.updatePassword(password1, newPassword, newPassword);
        Assert.assertEquals(profilePage.getSuccessMsg(), "The profile has been saved successful");
        profilePage.clickLogout();
        mainPage.login(oldUName, newPassword);
        Assert.assertEquals(profilePage.getHiText(), "Hi," + " " + fName);

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}