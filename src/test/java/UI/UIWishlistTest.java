package test.java.UI;

import main.java.drivers.BrowserFactory;
import main.java.pageFactory.BO.WishlistBO;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class UIWishlistTest extends BaseTest {

    @Test(priority = 1, description = "Search for specific product", dataProvider = "browserDataProvider")
    public void wishlistTest(String browserName) {
        BrowserFactory.initBrowser(browserName);
        WishlistBO wishlistBO = new WishlistBO();
        wishlistBO.addToWishlist();
    }

    @AfterMethod
    void quitBrowser() {
        BrowserFactory.getDriver().quit();
    }
}
