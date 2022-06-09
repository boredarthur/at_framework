package main.java.mobilePageFactory.PO;

import io.appium.java_client.AppiumBy.ByAccessibilityId;
import org.openqa.selenium.By.ByXPath;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SteppersPO {
    private IOSDriver driver;

    private ByAccessibilityId steppersTitle = new ByAccessibilityId("Steppers");
    private ByXPath defaultIncrement = new ByXPath("(//XCUIElementTypeButton[@name=\"Increment\"])[1]");
    private ByXPath tintedIncrement = new ByXPath("(//XCUIElementTypeButton[@name=\"Increment\"])[2]");
    private ByXPath defaultNumber = new ByXPath("(//XCUIElementTypeStaticText[@name=\"3\"])[1]\n");
    private ByXPath tintedNumber = new ByXPath("(//XCUIElementTypeStaticText[@name=\"3\"])[2]\n");

    public SteppersPO(IOSDriver driver) {
        this.driver = driver;
    }

    public SteppersPO openSteppers() {
        driver.findElement(steppersTitle).click();
        return this;
    }

    public SteppersPO incrementDefault(Integer times) {
        for (int i = 0; i < times; i++) {
            driver.findElement(defaultIncrement).click();
        }
        return this;
    }

    public SteppersPO incrementTinted(Integer times) {
        for (int i = 0; i < times; i++) {
            driver.findElement(tintedIncrement).click();
        }
        return this;
    }

    public SteppersPO validateSum() {
        WebElement defaultN = driver.findElement(defaultNumber);
        WebElement tintedN = driver.findElement(tintedNumber);
        String defaultValue = defaultN.getAttribute("value");
        String tintedValue = tintedN.getAttribute("value");
        Assert.assertEquals(Integer.parseInt(defaultValue) + Integer.parseInt(tintedValue), 6);
        return this;
    }
}
