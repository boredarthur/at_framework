package test.java.MOBILE;

import main.java.mobilePageFactory.BO.StackViewBO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class StackViewTest extends BaseUIKitTest {
    BaseUIKitTest baseTest;

    public StackViewTest() throws MalformedURLException {}

    @BeforeMethod
    public void setup() throws MalformedURLException {
        baseTest = new BaseUIKitTest();
    }

    @Test
    public void stackViewText() {
        new StackViewBO(baseTest.driver)
                .stackViewTest();
    }
}
