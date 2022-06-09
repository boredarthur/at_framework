package test.java.MOBILE;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.DataProvider;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseIOSTest {
    public IOSDriver driver;

    public BaseIOSTest() throws MalformedURLException {
        setup();
    }

    public void setup() throws MalformedURLException {
        this.driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), configureCapabilities());
    }

    public DesiredCapabilities configureCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformVersion", "15.2");
        capabilities.setCapability("appium:deviceName", "iPhone 11");
        capabilities.setCapability("appium:automationName","XCUITest");
        capabilities.setCapability("appium:platformName", "iOS");
        capabilities.setCapability("appium:commandTimeout", "12000");
        capabilities.setCapability("appium:app", "/Users/artur/IdeaProjects/at-project-framework/src/test/resources/relay-book-app.app");
        return capabilities;
    }

    @DataProvider(name = "searchDataProvider")
    public static Object[][] searchDataProvider() {
        return new Object[][]{{"idiot 3"}};
    }
}
