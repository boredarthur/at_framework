package main.java.mobilePageFactory.BO;

import io.appium.java_client.ios.IOSDriver;
import main.java.mobilePageFactory.PO.SearchPO;
import main.java.mobilePageFactory.PO.StackViewPO;

public class StackViewBO {
    private IOSDriver driver;

    public StackViewBO(IOSDriver driver) {
        this.driver = driver;
    }
    public boolean stackViewTest() {
        new StackViewPO(driver)
                .openStackViews()
                .addDetail()
                .validateDetail()
                .addArrangedView()
                .validateArrangedView();
        return true;
    }
}
