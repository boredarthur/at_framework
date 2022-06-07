package main.java.pageFactory.PO;

import main.java.drivers.BrowserFactory;
import main.java.pageFactory.Wrappers.EmailFieldWrapper;
import main.java.pageFactory.Wrappers.PasswordFieldWrapper;
import main.java.utils.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePO {

    @FindBy(xpath = "//span[text()='Hello, Sign in']")
    private WebElement signInButton;

    @FindBy(id = "ap_email")
    private WebElement emailField;
    @FindBy(xpath = "//*[@id=\"ap_password\"]")
    private WebElement passwordField;

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
        EmailFieldWrapper emailFieldWrapper = new EmailFieldWrapper(emailField);
        emailFieldWrapper.enterEmail(email);
        return this;
    }

    public HomePO enterPassword(String password) {
        PasswordFieldWrapper passwordFieldWrapper = new PasswordFieldWrapper(passwordField);
        passwordFieldWrapper.enterPassword(password);
        return this;
    }

}
