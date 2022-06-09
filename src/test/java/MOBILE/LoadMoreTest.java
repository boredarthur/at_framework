package test.java.MOBILE;

import main.java.mobilePageFactory.BO.LoadMoreBO;
import main.java.mobilePageFactory.PO.LoadMorePO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class LoadMoreTest {
    BaseIOSTest baseTest;

    public LoadMoreTest() throws MalformedURLException {}

    @BeforeMethod
    public void setup() throws MalformedURLException {
        baseTest = new BaseIOSTest();
    }

    @Test
    public void loadMoreTest() {
        new LoadMoreBO(baseTest.driver)
                .loadMoreTest();
    }
}
