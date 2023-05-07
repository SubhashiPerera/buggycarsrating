package testbase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.buggycarsrating.qa.driver.WebDriverLoader;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Log4j2
public class TestBase {


    protected static WebDriver driver;
    private int passed = 0;
    private int failed = 0;
    private int skipped = 0;


    protected static void getWebDriver(String url)
    {

        WebDriverLoader webDriverLoader=new WebDriverLoader();
        driver=webDriverLoader.WebDriverLoader(url);

    }


    private static ExtentTest extentTest;
    private static ExtentReports extentReports;


    @BeforeSuite(alwaysRun = true)
    public void setExtent() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/src/test/resources/reports/" + dtf.format(now) + " Buggy_Car_Demo_Test_Report.html");
        extentReports = new ExtentReports();
        extentReports.setSystemInfo("Environment ", "Buggy Demo Test ");
        extentReports.attachReporter(htmlReporter);
    }

    private void printAllResults(Collection<ITestResult> results) {

        for (ITestResult result : results) {
            String parameter = "";
            if (result.getParameters().length != 0) {
                parameter = " - email [" + result.getParameters()[0] + "]";
            }

            if (result.getStatus() == ITestResult.FAILURE) {
                extentTest = extentReports.createTest(result.getName(), result.getMethod().getDescription() + parameter);
                extentTest.log(Status.FAIL, "Test Case FAILED is " + result.getMethod().getDescription() + parameter);
                extentTest.fail("Test Failure Reason is     " + result.getThrowable());
                extentTest.info("Test Priority is           " + result.getMethod().getPriority());
                failed++;


            } else if (result.getStatus() == ITestResult.SKIP) {
                extentTest = extentReports.createTest(result.getName(), result.getMethod().getDescription() + parameter);
                extentTest.log(Status.SKIP, "Test Case SKIPPED is " + result.getMethod().getDescription() + parameter);
                extentTest.info("Test Method  is       " + result.getMethod().getMethodName());
                extentTest.info("Test Priority is      " + result.getMethod().getPriority());
                skipped++;

            } else if (result.getStatus() == ITestResult.SUCCESS) {
                extentTest = extentReports.createTest(result.getName(), result.getMethod().getDescription() + parameter);
                extentTest.log(Status.PASS, "Test Case Passed is " + result.getMethod().getDescription() + parameter);
                extentTest.info("Test Method  is       " + result.getMethod().getMethodName());
                extentTest.info("Test Priority is      " + result.getMethod().getPriority());
                passed++;

            }
        }

    }


    private void printAllResults1(ITestContext context) {
        printAllResults(context.getPassedTests().getAllResults());
        printAllResults(context.getFailedTests().getAllResults());
        printAllResults(context.getSkippedTests().getAllResults());
    }


    private void printSuiteResults(ISuite suite) {
        Collection<ISuiteResult> suiteResults = suite.getResults().values();
        for (ISuiteResult suiteResult : suiteResults) {
            printAllResults1(suiteResult.getTestContext());
        }
    }


    @AfterSuite
    public void afterSuite(ITestContext context) {
        /* Generating the report */
        printSuiteResults(context.getSuite());

        /* Generating the Extent test report */
        extentReports.flush();
    }




}
