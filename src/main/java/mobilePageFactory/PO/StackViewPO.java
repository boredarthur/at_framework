package main.java.mobilePageFactory.PO;

import io.appium.java_client.AppiumBy.ByAccessibilityId;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By.ByXPath;
import org.testng.Assert;

public class StackViewPO {
    private IOSDriver driver;

    private ByAccessibilityId stackViewsButton = new ByAccessibilityId("Stack Views");
    private ByXPath detailStepper = new ByXPath("(//XCUIElementTypeButton[@name=\"stepper increment\"])[1]");
    private ByXPath stackViewStepper = new ByXPath("//XCUIElementTypeButton[@name=\"stepper increment\"]");
    private ByAccessibilityId furtherDetail = new ByAccessibilityId("Further Detail");
    private ByXPath subarrangedView = new ByXPath("//XCUIElementTypeOther");

    public StackViewPO(IOSDriver driver) {
        this.driver = driver;
    }

    public StackViewPO openStackViews() {
        driver.findElement(stackViewsButton).click();
        return this;
    }

    public StackViewPO addDetail() {
        driver.findElement(detailStepper).click();
        return this;
    }

    public StackViewPO validateDetail() {
        Assert.assertTrue(driver.findElement(furtherDetail).isDisplayed());
        return this;
    }

    public StackViewPO addArrangedView() {
        driver.findElement(stackViewStepper).click();
        return this;
    }

    public StackViewPO validateArrangedView() {
        Assert.assertTrue(driver.findElement(subarrangedView).isDisplayed());
        return this;
    }
}
