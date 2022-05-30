package test.java.UI;

import main.java.drivers.BrowserFactory;
import main.java.pageFactory.BO.CartBO;
import main.java.pageFactory.BO.HomeBO;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class UIAddToCartTest extends BaseTest {
    @Test(priority = 1, description = "Adding product to a cart", dataProvider = "browserDataProvider")
    public void cartTest(String browserName) {
        BrowserFactory.initBrowser(browserName);
        CartBO cartBO = new CartBO();
        cartBO.addToCart();
    }

    @AfterMethod
    void quitBrowser() {
        BrowserFactory.getDriver().quit();
    }
}
