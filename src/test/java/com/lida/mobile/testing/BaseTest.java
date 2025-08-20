package com.lida.mobile.testing;

import com.lida.mobile.testing.core.driver.DriverManager;
import com.lida.mobile.testing.pages.MainPage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    public static MainPage mainPage = new MainPage();

    @BeforeSuite
    public static void createSession() {
        DriverManager.getDriver();
    }

    @AfterSuite
    public static void closeSession() {
        DriverManager.closeDriver();
        DriverManager.closeAppium();
        DriverManager.closeEmulator();
    }
}
