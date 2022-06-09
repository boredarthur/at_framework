package main.java.mobilePageFactory.BO;

import io.appium.java_client.ios.IOSDriver;
import main.java.mobilePageFactory.PO.BestsellersDetailsPO;

public class BestsellersDetailsBO {
    private IOSDriver driver;

    public BestsellersDetailsBO(IOSDriver driver) {
        this.driver = driver;
    }

    public boolean detailsTest() {
        new BestsellersDetailsPO(driver)
                .openBook()
                .openDetails()
                .validateTitleVisible();
        return true;
    }
}
