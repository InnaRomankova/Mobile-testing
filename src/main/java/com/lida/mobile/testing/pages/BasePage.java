package com.lida.mobile.testing.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.appium.SelenideAppiumElement;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

import static com.lida.mobile.testing.core.driver.DriverManager.getDriver;

public class BasePage {

    public static void touchDown() {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Interaction moveToStart = finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 520, 1530);
        Interaction pressDown = finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
        Interaction moveToEnd = finger.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), 520, 490);
        Interaction pressUp = finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());

        Sequence swipe = new Sequence(finger, 0);
        swipe.addAction(moveToStart);
        swipe.addAction(pressDown);
        swipe.addAction(moveToEnd);
        swipe.addAction(pressUp);
    }

    public static void scrollToSelenideElement(SelenideAppiumElement element, int maxSwipes) {
        Dimension size = getDriver().manage().window().getSize();
        int screenHeight = size.getHeight();
        int screenWidth = size.getWidth();

        for (int i = 0; i < maxSwipes; i++) {
            if (element.isDisplayed()) {
                System.out.println("Элемент найден после " + i + " свайпов");
                return;
            }

            // Выполняем свайп вверх
            performSwipeUp(getDriver(), screenWidth, screenHeight);

            // Пауза между свайпами
            Selenide.sleep(500);
        }

        throw new RuntimeException("Не удалось найти элемент после " + maxSwipes + " свайпов");
    }

    private static void performSwipeUp(AppiumDriver driver, int screenWidth, int screenHeight) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // Координаты для свайпа снизу вверх
        int startX = screenWidth / 2;
        int startY = (int) (screenHeight * 0.8); // Начинаем с 80% высоты
        int endY = (int) (screenHeight * 0.3);   // Заканчиваем на 30% высоты

        Interaction moveToStart = finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY);
        Interaction pressDown = finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
        Interaction moveToEnd = finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY);
        Interaction pressUp = finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());

        Sequence swipe = new Sequence(finger, 0);
        swipe.addAction(moveToStart);
        swipe.addAction(pressDown);
        swipe.addAction(moveToEnd);
        swipe.addAction(pressUp);

        driver.perform(Collections.singletonList(swipe));
    }
}
