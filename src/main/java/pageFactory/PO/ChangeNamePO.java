package main.java.pageFactory.PO;

import main.java.drivers.BrowserFactory;
import main.java.utils.Constants;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;

public class ChangeNamePO {

    @FindBy(xpath = "//span[text()='Account & Lists']")
    private WebElement profileButton;

    @FindBy(xpath = "//div[contains(@data-card-identifier, 'SignInAndSecurity')]")
    private WebElement securityCard;

    @FindBy(xpath = "//input[contains(@id, 'auth-cnep-edit-name-button')]")
    private WebElement changeNameButton;

    @FindBy(xpath = "//input[contains(@id, 'ap_customer_name')]")
    private WebElement nameInput;

    @FindBy(xpath = "//input[contains(@id, 'cnep_1C_submit_button')]")
    private WebElement submitButton;

    public ChangeNamePO() {
        PageFactory.initElements(BrowserFactory.getDriver(), this);
    }

    public ChangeNamePO openWebPage() {
        BrowserFactory.getDriver().get(Constants.URL);
        return this;
    }

    public ChangeNamePO openChangeNamePage() {
        profileButton.click();
        securityCard.click();
        changeNameButton.click();
        return this;
    }

    public ChangeNamePO performChangeName(String nameChangeTo) {
        nameInput.sendKeys(Keys.COMMAND + "a");
        nameInput.sendKeys(Keys.DELETE);
        nameInput.sendKeys(nameChangeTo);
        submitButton.click();
        return this;
    }

}
