package main.java.drivers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserFactory {
    private static final ThreadLocal<WebDriver> DRIVER_INSTANCE = new InheritableThreadLocal<>();

    public static WebDriver getDriver() {
        if(DRIVER_INSTANCE.get() == null)
            throw new RuntimeException("please call .initBrowser(name) method before using");
        return DRIVER_INSTANCE.get();
    }

    public static void initBrowser(String name) {
        switch (name) {
            case "chrome" -> {
                DRIVER_INSTANCE.set(WebDriverManager.setupChrome());
            }
            case "firefox" -> {
                DRIVER_INSTANCE.set(WebDriverManager.setupFirefox());
            }
            default -> throw new RuntimeException("invalid browser " + name);
        }
    }

    public static WebDriverWait driverWait(long timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(DRIVER_INSTANCE.get(), Duration.ofSeconds(timeInSeconds));
        return wait;
    }
}
