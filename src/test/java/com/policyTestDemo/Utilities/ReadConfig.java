package com.policyTestDemo.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
    Properties pro;
    public  ReadConfig(){
        File src = new File("./Configuration/config.properties");
        try (FileInputStream fis = new FileInputStream(src)) {
            pro = new Properties();
            pro.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file: " + e.getMessage(), e);
        }
    }
    public String getApplicationUrl(){
        String url = pro.getProperty("url");
        return url;
    }
    public String getUserName(){
        String userName = pro.getProperty("username");
        return userName;
    }
    public String getPassword(){
        String pwd = pro.getProperty("password");
        return pwd;
    }
}
