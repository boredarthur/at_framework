package test.java.MOBILE;

import main.java.mobilePageFactory.BO.BestsellersDetailsBO;
import main.java.mobilePageFactory.BO.SearchBO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class BestsellersDetailTest extends BaseIOSTest {
    BaseIOSTest baseTest;

    public BestsellersDetailTest() throws MalformedURLException {}

    @BeforeMethod
    public void setup() throws MalformedURLException {
        baseTest = new BaseIOSTest();
    }

    @Test
    public void bestsellersDetailsTest() {
        new BestsellersDetailsBO(baseTest.driver)
                .detailsTest();
    }
}
