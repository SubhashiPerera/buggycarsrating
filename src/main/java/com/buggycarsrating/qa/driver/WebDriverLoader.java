package com.buggycarsrating.qa.driver;

import com.buggycarsrating.qa.util.Functions;
import com.buggycarsrating.qa.util.WebEventListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

@Log4j2
public class WebDriverLoader {

    protected static WebDriver driver;



    public WebDriver WebDriverLoader(String url) {

        String browserName = Functions.configPropertyReader().getProperty("browser");

        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else {
            throw new RuntimeException("Unsupported browser: " + browserName);
        }

        WebEventListener eventListener = new WebEventListener();
        EventFiringWebDriver eDriver = new EventFiringWebDriver(driver);
        eDriver.register(eventListener);
        driver = eDriver;
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Functions.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Functions.IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.get(url);

        return driver;
    }




}