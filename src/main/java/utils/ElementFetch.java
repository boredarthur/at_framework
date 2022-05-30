package main.java.utils;

import main.java.drivers.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementFetch {
    public WebElement getWebElement(String identifierType, String identifierValue) {
        switch (identifierType) {
            case "ID":
                return BrowserFactory.getDriver().findElement(By.id(identifierValue));
            case "CSS":
                return BrowserFactory.getDriver().findElement(By.cssSelector(identifierValue));
            case "TAGNAME":
                return BrowserFactory.getDriver().findElement(By.tagName(identifierValue));
            case "XPATH":
                return BrowserFactory.getDriver().findElement(By.xpath(identifierValue));
            default:
                return null;
        }
    }

    public List<WebElement> getListWebElements(String identifierType, String identifierValue) {
        switch (identifierType) {
            case "ID":
                return BrowserFactory.getDriver().findElements(By.id(identifierValue));
            case "CSS":
                return BrowserFactory.getDriver().findElements(By.cssSelector(identifierValue));
            case "TAGNAME":
                return BrowserFactory.getDriver().findElements(By.tagName(identifierValue));
            case "XPATH":
                return BrowserFactory.getDriver().findElements(By.xpath(identifierValue));
            default:
                return null;
        }
    }
}
