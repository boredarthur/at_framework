package main.java.mobilePageFactory.PO;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.AppiumBy.ByAccessibilityId;
import org.openqa.selenium.By.ByXPath;
import org.testng.Assert;

public class BestsellersDetailsPO {
    private IOSDriver driver;
    private ByXPath book = new ByXPath("(//XCUIElementTypeStaticText[@name=\"The Idiot\"])[1]");
    private ByAccessibilityId detailBook = new ByAccessibilityId("The Idiot 3");
    private ByXPath detailBookTitle = new ByXPath("//XCUIElementTypeStaticText[@name=\"The Idiot 3\"]");

    public BestsellersDetailsPO(IOSDriver driver) {
        this.driver = driver;
    }

    public BestsellersDetailsPO openBook() {
        driver.findElement(book).click();
        return this;
    }

    public BestsellersDetailsPO openDetails() {
        driver.findElement(detailBook).click();
        return this;
    }

    public BestsellersDetailsPO validateTitleVisible() {
        Assert.assertTrue(driver.findElement(detailBookTitle).isDisplayed());
        return this;
    }

}
