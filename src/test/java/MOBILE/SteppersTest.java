package test.java.MOBILE;

import main.java.mobilePageFactory.BO.SteppersBO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class SteppersTest extends BaseUIKitTest {
    BaseUIKitTest baseTest;

    public SteppersTest() throws MalformedURLException {}

    @BeforeMethod
    public void setup() throws MalformedURLException {
        baseTest = new BaseUIKitTest();
    }

    @Test
    public void steppersTest() {
        new SteppersBO(baseTest.driver)
                .steppersTest();
    }
}
