package com.policyTestDemo.testCases;

import com.policyTestDemo.Utilities.Reporting;
import com.policyTestDemo.pageObjects.LoginPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_LoginTest_01 extends BaseClass {
    @Test
    public void LoginTest() throws InterruptedException {

        LoginPage lp = new LoginPage(driver);
        Reporting report = new Reporting();
        logger.info("URL is open");
        Thread.sleep(1000);
        lp.setUserName(username);
        lp.setPassword(password);
        lp.clickLogin();
        if(driver.getTitle().equals("Global IDs")){
            Assert.assertTrue(true);
            logger.info("Login test passed");
        }
        else{
            report.takeScreenshot(driver,"loginTest");
            Assert.assertTrue(false);
            logger.info("Login test failed");        }

    }
}


