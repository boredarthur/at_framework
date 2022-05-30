package main.java.pageFactory.PO;

import main.java.drivers.BrowserFactory;
import main.java.utils.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;

public class HomePO {

    @FindBy(xpath = "//span[text()='Hello, Sign in']")
    private WebElement signInButton;

    @FindBy(id = "ap_email")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id=\"continue\"]")
    private WebElement continueButton;

    @FindBy(xpath = "//*[@id=\"ap_password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"signInSubmit\"]")
    private WebElement signInSubmitButton;

    public HomePO() {
        PageFactory.initElements(BrowserFactory.getDriver(), this);
    }

    public HomePO openWebPage() {
        BrowserFactory.getDriver().get(Constants.URL);
        return this;
    }

    public HomePO clickSignInButton() {
        signInButton.click();
        return this;
    }

    public HomePO enterEmail(String email) {
        emailField.sendKeys(email);
        continueButton.click();
        return this;
    }

    public HomePO enterPassword(String password) {
        passwordField.sendKeys(password);
        signInSubmitButton.click();
        return this;
    }

}
