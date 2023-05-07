
import com.buggycarsrating.qa.entities.User;
import com.buggycarsrating.qa.pages.MainPage;
import com.buggycarsrating.qa.pages.ModelPage;
import com.buggycarsrating.qa.pages.ProfilePage;
import com.buggycarsrating.qa.pages.RegisterUserPage;
import com.buggycarsrating.qa.util.Functions;
import org.testng.Assert;
import org.testng.annotations.*;
import testbase.TestBase;

public class VoteTest extends TestBase {
    MainPage mainPage;
    ModelPage modelPage;
    ProfilePage profilePage;
    RegisterUserPage registerUserPage;


    private VoteTest() {
    }


    @BeforeClass
    public void setup() {

        getWebDriver(Functions.configPropertyReader().getProperty("url"));
        mainPage = new MainPage(driver);
        modelPage = new ModelPage(driver);
        profilePage = new ProfilePage(driver);
        registerUserPage = new RegisterUserPage(driver);


    }

    @Test(priority = 1,testName = "TC_BUDDYCARSRATING_VOTE_01", description = "Verify voting fucntionality")
    public void voteTest()  {

        mainPage.getHeading();
        Assert.assertEquals(mainPage.getHeading(), "Buggy Rating");
        mainPage.clickRegister();
        User user = registerUserPage.registerNewUser();
        String oldUName = user.getUName();
        String password1 = user.getPWord();
        String fName = user.getFName();
        Assert.assertEquals(registerUserPage.getSuccessMessage(), "Registration is successful");
        mainPage.login(oldUName, password1);
        Assert.assertEquals(profilePage.getHiText(), "Hi," + " " + fName);
        registerUserPage.clickCancel();
        mainPage.clickPopularModel();
        modelPage.vote("test");
        Assert.assertEquals(modelPage.getSuccessMessage(), "Thank you for your vote!");
        modelPage.verifyVoteCount();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
