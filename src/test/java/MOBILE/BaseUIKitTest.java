package test.java.MOBILE;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseUIKitTest {
    public IOSDriver driver;

    public BaseUIKitTest() throws MalformedURLException {
        setup();
    }

    public void setup() throws MalformedURLException {
        this.driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), configureCapabilities());
    }

    public DesiredCapabilities configureCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformVersion", "15.2");
        capabilities.setCapability("appium:deviceName", "iPhone 11");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:platformName", "iOS");
        capabilities.setCapability("appium:commandTimeout", "12000");
        capabilities.setCapability("appium:app", "/Users/artur/IdeaProjects/at-project-framework/src/test/resources/UIKitCatalog.app");
        return capabilities;
    }
}
