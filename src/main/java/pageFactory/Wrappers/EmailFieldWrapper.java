package main.java.pageFactory.Wrappers;

import main.java.drivers.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailFieldWrapper extends ElementWrapper {
    public EmailFieldWrapper(WebElement element) {
        super(element);
    }

    public void enterEmail(CharSequence... charSequences) {
        getWrappedElement().sendKeys(charSequences);
        waitFor("//input[@id='continue']");
        clickWithJS();
    }

    public void clickWithJS() {
        JavascriptExecutor executor = (JavascriptExecutor) BrowserFactory.getDriver();
        WebElement submit = BrowserFactory.getDriver().findElement(By.xpath("//input[@id='continue']"));
        executor.executeScript("arguments[0].click();", submit);
    }

    private void waitFor(String xpath) {
        WebDriverWait wait = BrowserFactory.driverWait(5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
}
