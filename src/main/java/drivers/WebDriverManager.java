package main.java.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class WebDriverManager {

    public static WebDriver setupChrome() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver");
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public static WebDriver setupFirefox() {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "geckodriver");
        WebDriver driver = new FirefoxDriver();
        return driver;
    }

}
