package main.java.pageFactory.Wrappers;

import main.java.drivers.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasswordFieldWrapper extends ElementWrapper {
    public PasswordFieldWrapper(WebElement element) {
        super(element);
    }

    public void enterPassword(CharSequence... charSequences) {
        getWrappedElement().sendKeys(charSequences);
        waitFor("//*[@id=\"signInSubmit\"]");
        clickWithJS();
    }

    public void clickWithJS() {
        JavascriptExecutor executor = (JavascriptExecutor) BrowserFactory.getDriver();
        String signInXpath = "//*[@id=\"signInSubmit\"]";
        WebElement submit = BrowserFactory.getDriver().findElement(By.xpath(signInXpath));
        executor.executeScript("arguments[0].click();", submit);
    }

    private void waitFor(String xpath) {
        WebDriverWait wait = BrowserFactory.driverWait(5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
}
