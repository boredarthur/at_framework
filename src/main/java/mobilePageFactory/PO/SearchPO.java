package main.java.mobilePageFactory.PO;

import io.appium.java_client.AppiumBy.ByAccessibilityId;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By.ByXPath;
import org.testng.Assert;

public class SearchPO {
    private IOSDriver driver;
    private ByAccessibilityId searchInput = new ByAccessibilityId("Books, Type, Authors");
    private ByAccessibilityId book = new ByAccessibilityId("The Idiot 3");

    public SearchPO(IOSDriver driver) {
        this.driver = driver;
    }

    public SearchPO enterSearchableValue(String text)  {
        this.driver.findElement(searchInput).sendKeys(text);
        return this;
    }

    public SearchPO validateSearchResults() {
        Assert.assertTrue(driver.findElement(book).isDisplayed());
        return this;
    }

}
