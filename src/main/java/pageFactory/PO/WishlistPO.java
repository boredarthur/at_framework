package main.java.pageFactory.PO;

import main.java.drivers.BrowserFactory;
import main.java.utils.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WishlistPO {

    @FindBy(xpath = "//img[contains(@alt, 'Electronics')]")
    private WebElement electronicsItem;

    @FindBy(xpath = "//span[contains(text(), 'Headphones')]")
    private WebElement headphonesItem;

    @FindBy(xpath = "//*[@id=\"p_89/Apple\"]")
    private WebElement brandOption;

    @FindBy(xpath = "//img[contains(@alt, 'Apple AirPods (2nd Generation)')]")
    private WebElement airpodsImage;

    @FindBy(xpath = "//input[@id='add-to-wishlist-button-submit']")
    private WebElement addToListButton;

    public WishlistPO() {
        PageFactory.initElements(BrowserFactory.getDriver(), this);
    }

    public WishlistPO openWebPage() {
        BrowserFactory.getDriver().get(Constants.URL);
        return this;
    }

    public WishlistPO openCellPhonesPage() {
        electronicsItem.click();
        headphonesItem.click();
        brandOption.click();
        airpodsImage.click();
        addToListButton.click();
        return this;
    }
}
