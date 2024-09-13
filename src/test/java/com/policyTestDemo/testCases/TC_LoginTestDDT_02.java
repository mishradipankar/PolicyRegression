package com.policyTestDemo.testCases;

import com.policyTestDemo.Utilities.Reporting;
import com.policyTestDemo.Utilities.XLUtils;
import com.policyTestDemo.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginTestDDT_02 extends  BaseClass{

    @Test(dataProvider = "LoginData")
    public void LogintDDT(String user,String pwd) throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        lp.setUserName(user);
        lp.setPassword(pwd);
        lp.clickLogin();
        if(driver.getTitle().equals("Global IDs")){
            Assert.assertTrue(true);
            logger.info("Login test passed");
        }
        else{
            Assert.assertTrue(false);
            logger.info("Login test failed");        }

    }
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException // Add return type 'String[][]'
    {
        String path = System.getProperty("user.dir") + "/src/test/java/com/policyTestDemo/testData/LoginData.xlsx";

        int rownum = XLUtils.getRowCount(path, "Sheet1");
        int colcount = XLUtils.getCellCount(path, "Sheet1", 1);

        String logindata[][] = new String[rownum][colcount];

        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colcount; j++) {
                logindata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j); // Fill 2D array with data
            }
        }

        return logindata; // Return the 2D array
    }


}
