package main.java.mobilePageFactory.BO;

import io.appium.java_client.ios.IOSDriver;
import main.java.mobilePageFactory.PO.SteppersPO;

public class SteppersBO {
    private IOSDriver driver;

    public SteppersBO(IOSDriver driver) {
        this.driver = driver;
    }
    public boolean steppersTest() {
        new SteppersPO(driver)
                .openSteppers()
                .incrementDefault(3)
                .incrementTinted(3)
                .validateSum();
        return true;
    }
}
