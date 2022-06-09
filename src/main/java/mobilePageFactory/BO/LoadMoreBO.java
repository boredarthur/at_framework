package main.java.mobilePageFactory.BO;

import io.appium.java_client.ios.IOSDriver;
import main.java.mobilePageFactory.PO.LoadMorePO;
import main.java.mobilePageFactory.PO.SearchPO;

public class LoadMoreBO {
    private IOSDriver driver;

    public LoadMoreBO(IOSDriver driver) {
        this.driver = driver;
    }
    public boolean loadMoreTest() {
        new LoadMorePO(driver)
                .loadMore()
                .validateBook();
        return true;
    }
}
