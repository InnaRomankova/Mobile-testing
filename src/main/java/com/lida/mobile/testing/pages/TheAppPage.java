package com.lida.mobile.testing.pages;

import com.codeborne.selenide.appium.SelenideAppiumElement;
import io.appium.java_client.AppiumBy;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;

public class TheAppPage extends BasePage {
    private static final SelenideAppiumElement polarStratocumulus = $(AppiumBy.accessibilityId("Stratocumulus"));
    private static final SelenideAppiumElement learnMoreAboutStratocumulus = $(AppiumBy.id("android:id/button3"));
    private static final SelenideAppiumElement messageText = $(AppiumBy.id("android:id/message"));

    public TheAppPage stratocumulusClick() {
        scrollToSelenideElement(polarStratocumulus, 3);
        polarStratocumulus.click();

        return this;
    }

    public TheAppPage learnMoreAboutStratocumulusClick() {
        learnMoreAboutStratocumulus.click();

        return this;
    }

    public String getStratocumulusDescriptionText() {
        return messageText
                .shouldBe(visible)
                .getText();
    }
}
