package com.lida.mobile.testing.core.driver;

import com.codeborne.selenide.WebDriverRunner;
import com.lida.mobile.testing.core.configuration.AddressConfigurator;
import com.lida.mobile.testing.core.configuration.CapabilitiesConfigurator;
import com.lida.mobile.testing.core.configuration.ConfigurationReader;
import com.lida.mobile.testing.core.configuration.EnvironmentType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;
import static java.lang.String.format;

public class DriverManager {
    private static final Logger LOG = LogManager.getRootLogger();
    private static final EnvironmentType ENVIRONMENT_TYPE = EnvironmentType.valueOf(ConfigurationReader.get().env().toUpperCase());
    private static AppiumDriver driver;

    private DriverManager() {
    }

    public static AppiumDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    private static AppiumDriver createDriver() {
        switch (ENVIRONMENT_TYPE) {
            case LOCAL:
                driver = new AndroidDriver(AddressConfigurator.getAppiumDriverLocalService
                        (ConfigurationReader.get().appiumPort()), CapabilitiesConfigurator.getLocalCapabilities());
                break;
            default:
                throw new IllegalArgumentException(format("Unexpected environment value: %s", ENVIRONMENT_TYPE));
        }
        LOG.info("Driver created");
        LOG.info("Environment type is: {}", ENVIRONMENT_TYPE);
        WebDriverRunner.setWebDriver(driver);
        return driver;
    }

    public static void closeAppium() {
        AddressConfigurator.stopService();
    }

    public static void closeDriver() {
        Optional.ofNullable(getDriver()).ifPresent(driverInstance -> {
            driverInstance.quit();
            driver = null;
            LOG.info("Driver is closed");
        });
    }

    public static void closeEmulator() {
        try {
            Runtime.getRuntime().exec(format("adb -s %s emu kill", ConfigurationReader.get().udid()));
            LOG.info("AVD is closed");
        } catch (IOException e) {
            LOG.info("AVD isn't closed, message {}", e.getMessage());
        }
    }
}