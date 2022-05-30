package test.java.UI;

import main.java.drivers.BrowserFactory;
import main.java.pageFactory.BO.HomeBO;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class UILoginTest extends BaseTest {
    @Test(priority = 1, description = "Trying to login into account", dataProvider = "browserDataProvider")
    public void loginTest(String browserName) {
        BrowserFactory.initBrowser(browserName);
        HomeBO homeBO = new HomeBO();
        homeBO.logIn();
    }

    @AfterMethod
    void quitBrowser() {
        BrowserFactory.getDriver().quit();
    }
}
