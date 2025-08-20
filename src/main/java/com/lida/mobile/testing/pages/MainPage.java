package com.lida.mobile.testing.pages;

import com.codeborne.selenide.appium.SelenideAppiumElement;
import io.appium.java_client.AppiumBy;
import static com.codeborne.selenide.appium.SelenideAppium.$;

public class MainPage extends BasePage{

    private static final SelenideAppiumElement echoBox = $(AppiumBy.accessibilityId("Echo Box"));
    private static final SelenideAppiumElement messageInput = $(AppiumBy.accessibilityId("messageInput"));
    private static final SelenideAppiumElement messageSaveButton = $(AppiumBy.accessibilityId("messageSaveBtn"));
    private static final SelenideAppiumElement listDemo = $(AppiumBy.accessibilityId("List Demo"));

    public void echoBoxClick() {
        echoBox.click();
    }

    public void messageInputSendKeys(String message) {
        messageInput.type(message);
    }

    public void messageSaveButtonClick() {
        messageSaveButton.click();
    }

    public TheAppPage listDemoClick() {
        listDemo.click();
        return new TheAppPage();
    }
}




