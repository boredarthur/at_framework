package test.java.UI;

import main.java.drivers.BrowserFactory;
import main.java.pageFactory.BO.ChangeNameBO;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class UIChangeNameTest extends BaseTest {
    @Test(priority = 1, description = "Change name of account", dataProvider = "nameDataProvider")
    public void changeNameTest(String browserName, String nameChangeTo) {
        BrowserFactory.initBrowser(browserName);
        ChangeNameBO changeNameBO = new ChangeNameBO();
        changeNameBO.changeName(nameChangeTo);
    }

    @AfterMethod
    void quitBrowser() {
        BrowserFactory.getDriver().quit();
    }
}
