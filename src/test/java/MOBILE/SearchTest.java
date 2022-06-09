package test.java.MOBILE;

import main.java.mobilePageFactory.BO.SearchBO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.MalformedURLException;

public class SearchTest extends BaseIOSTest{
    BaseIOSTest baseTest;

    public SearchTest() throws MalformedURLException {}

    @BeforeMethod
    public void setup() throws MalformedURLException {
        baseTest = new BaseIOSTest();
    }

    @Test(dataProvider = "searchDataProvider")
    public void searchTest(String text) {
        SearchBO searchBO = new SearchBO(baseTest.driver);
        searchBO.searchTest(text);
    }

}
