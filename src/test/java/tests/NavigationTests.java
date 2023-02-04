package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.StringUtility;
import org.openqa.selenium.WebDriver;

public class NavigationTests {
    public static void main(String[] args) {

        WebDriver driver=BrowserFactory.getDriver("firefox");
        driver.get("https://www.google.com.tr/?hl=tr");
        String googleTitle=driver.getTitle();
        System.out.println(googleTitle);

        driver.get("https://etsy.com");
        String etsyTitle=driver.getTitle();
        System.out.println(etsyTitle);

        driver.navigate().back();
        StringUtility.verifyEquals(driver,googleTitle);

        driver.navigate().forward();
        StringUtility.verifyEquals(driver,etsyTitle);

        driver.quit();

    }
}
