package main.java.mobilePageFactory.BO;

import io.appium.java_client.ios.IOSDriver;
import main.java.mobilePageFactory.PO.SearchPO;

public class SearchBO {
    private IOSDriver driver;
    public SearchBO(IOSDriver driver) {
        this.driver = driver;
    }
    public boolean searchTest(String text) {
        new SearchPO(driver)
                .enterSearchableValue(text)
                .validateSearchResults();
        return true;
    }
}
