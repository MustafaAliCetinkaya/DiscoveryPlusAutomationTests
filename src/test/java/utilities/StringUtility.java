package com.cbt.utilities;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class StringUtility {
    public static void verifyEquals(WebDriver driver, String expectedTitle){
        String actualTitle = driver.getTitle();
        if(actualTitle.equals(expectedTitle)){
            System.out.println("Test is PASSED");
        }else{
            System.out.println("Test is FAILED");
        }
    }
}
