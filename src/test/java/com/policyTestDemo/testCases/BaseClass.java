package com.policyTestDemo.testCases;
import com.policyTestDemo.Utilities.ReadConfig;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    protected  WebDriver driver;
    ReadConfig readConfig = new ReadConfig();
    protected  String baseUrl = readConfig.getApplicationUrl();
    protected String username = readConfig.getUserName();
    protected String password = readConfig.getPassword();

    public static Logger logger;

    @Parameters("browser")
    @BeforeClass
    public  void setUp(String br) {
        logger =Logger.getLogger(BaseClass.class);
        PropertyConfigurator.configure("log4j.properties");
        if(br.equals("chrome")){
            driver = new ChromeDriver();
        }
        else if((br.equals("firefox"))){
            driver = new FirefoxDriver();
        }
        else if((br.equals("ie"))){
            driver = new InternetExplorerDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
