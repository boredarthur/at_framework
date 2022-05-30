package main.java.pageFactory.PO;

import main.java.drivers.BrowserFactory;
import main.java.utils.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchPO {

    public SearchPO() {
        PageFactory.initElements(BrowserFactory.getDriver(), this);
    }
    public SearchPO openWebPage() {
        BrowserFactory.getDriver().get(Constants.URL);
        return this;
    }
    @FindBy(xpath = "//select[contains(@id, searchDropdownBox)]")
    private WebElement _searchDropdown;

    @FindBy(xpath = "//input[contains(@id, 'twotabsearchtextbox')]")
    private WebElement searchInput;

    @FindBy(xpath = "//input[contains(@id, 'nav-search-submit-button')]")
    private WebElement searchSubmitButton;

    public SearchPO searchForBooks(String searchFor) {
        Select searchDropdown = new Select(_searchDropdown);
        searchDropdown.selectByVisibleText("Books");
        searchInput.sendKeys(searchFor);
        searchSubmitButton.click();
        return this;
    }

}
