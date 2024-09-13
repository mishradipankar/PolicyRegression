package com.policyTestDemo.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporting extends TestListenerAdapter {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static String screenshotFolder = System.getProperty("user.dir") + "/Screenshots/";

    // Initialize the report
    public void setupReport() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReport.html");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Create screenshots folder if not exists
            new File(screenshotFolder).mkdirs();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        setupReport();
        test = extent.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());

        // Capture screenshot
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("WebDriver");
        if (driver != null) {
            String screenshotPath = takeScreenshot(driver, result.getName());
            test.addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();  // Finalize the report
        }
    }

    // Method to capture screenshot
    public String takeScreenshot(WebDriver driver, String testName) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = screenshotFolder + testName + "_" + timeStamp + ".png";
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(srcFile, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }

}
