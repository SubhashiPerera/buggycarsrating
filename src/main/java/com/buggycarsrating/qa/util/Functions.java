package com.buggycarsrating.qa.util;

import com.buggycarsrating.qa.driver.WebDriverLoader;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


@Log4j2
public class Functions extends WebDriverLoader {

    public static final int PAGE_LOAD_TIMEOUT = 40;
    public static final int IMPLICIT_WAIT = 40;

    private Functions() {
    }


    public static void takeScreenshotAtEndOfTest() {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        try {
            FileUtils.copyFile(scrFile, new File(currentDir + "/src/test/resources/screenshots/" + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {

            log.error(e.getMessage());
        }
    }

    public static Properties configPropertyReader() {

        Properties properties = new Properties();

        try {
            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/"
                    + "buggycarsrating/qa/config/config.properties");
            properties.load(inputStream);
            inputStream.close();

        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return properties;
    }
}
