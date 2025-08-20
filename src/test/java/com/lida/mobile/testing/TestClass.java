package com.lida.mobile.testing;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestClass extends BaseTest {

    @Test
    public void test() {
        mainPage.echoBoxClick();
        mainPage.messageInputSendKeys("hello world");
        mainPage.messageSaveButtonClick();
    }

    @Test
    public void testOpenLearnMoreAboutStratocumulus() {
        String actualText = mainPage.listDemoClick()
                .stratocumulusClick()
                .learnMoreAboutStratocumulusClick()
                .getStratocumulusDescriptionText();

        assertEquals(actualText, "This is a low-level stratocumuliform limited-convective cloud",
                "Текст описания stratocumulus должен соответствовать ожидаемому");
    }
}
