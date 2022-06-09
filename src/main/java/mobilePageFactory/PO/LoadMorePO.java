package main.java.mobilePageFactory.PO;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By.ByXPath;
import org.testng.Assert;

public class LoadMorePO {
    private IOSDriver driver;

    private ByXPath loadMoreButton = new ByXPath("//XCUIElementTypeButton[@name=\"Load more\"]");
    private ByXPath book = new ByXPath("(//XCUIElementTypeStaticText[@name=\"The Popular\"])[2]");

    public LoadMorePO(IOSDriver driver) {
        this.driver = driver;
    }

    public LoadMorePO loadMore() {
        driver.findElement(loadMoreButton).click();
        return this;
    }

    public LoadMorePO validateBook() {
        Assert.assertTrue(driver.findElement(book).isDisplayed());
        return this;
    }
}
