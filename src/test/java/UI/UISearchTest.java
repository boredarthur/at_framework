package test.java.UI;

import main.java.drivers.BrowserFactory;
import main.java.pageFactory.BO.SearchBO;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class UISearchTest extends BaseTest {

    @Test(priority = 1, description = "Search for books", dataProvider = "searchDataProvider")
    public void searchTest(String browserName, String searchFor) {
        BrowserFactory.initBrowser(browserName);
        SearchBO searchBO = new SearchBO();
        searchBO.search(searchFor);
    }

    @AfterMethod
    void quitBrowser() {
        BrowserFactory.getDriver().quit();
    }
}
