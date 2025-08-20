package com.lida.mobile.testing.utils;

import com.codeborne.selenide.commands.TakeScreenshot;
import com.lida.mobile.testing.core.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class TestListener implements ITestListener {

    private static final Logger LOG = LogManager.getRootLogger();

    public void onTestStart(ITestResult result) {
        LOG.info("{} is started", result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        LOG.info("{} is passed", result.getName());
    }

    public void onTestFailure(ITestResult result) {
        LOG.info("{} is failed", result.getName());
        takeScreenshot();
    }

    private void takeScreenshot() {

    }
}
