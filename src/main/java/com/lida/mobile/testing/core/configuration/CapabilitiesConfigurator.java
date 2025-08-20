package com.lida.mobile.testing.core.configuration;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class CapabilitiesConfigurator {

    private CapabilitiesConfigurator() {

    }

    public static DesiredCapabilities getLocalCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", ConfigurationReader.get().platformName());
        desiredCapabilities.setCapability("appium:udid", ConfigurationReader.get().udid());
        desiredCapabilities.setCapability("appium:avd", ConfigurationReader.get().localDeviceName());
        desiredCapabilities.setCapability("appium:app.package", ConfigurationReader.get().appPackage());
        desiredCapabilities.setCapability("appium:app.activity", ConfigurationReader.get().appActivity());
        desiredCapabilities.setCapability("appium:automationName", ConfigurationReader.get().automationName());
        desiredCapabilities.setCapability("appium:app", new File(ConfigurationReader.get().appPath()).getAbsolutePath());
        return desiredCapabilities;
    }
}
