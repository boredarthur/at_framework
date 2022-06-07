package main.java.pageFactory.PO;

import main.java.drivers.BrowserFactory;
import main.java.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPO {

    @FindBy(xpath = "//input[contains(@id, 'twotabsearchtextbox')]")
    private WebElement searchInput;

    @FindBy(xpath = "//input[contains(@id, 'nav-search-submit-button')]")
    private WebElement searchSubmitButton;

    @FindBy(xpath = "//img[contains(@alt, 'Lavazza Crema e Aroma - Coffee Beans, 2.2-Pound Bag - Pack of 2')]")
    private WebElement productImage;

    @FindBy(xpath = "//input[contains(@title, 'Add to Shopping Cart')]")
    private WebElement addToCartButton;

    public CartPO() {
        PageFactory.initElements(BrowserFactory.getDriver(), this);
    }

    public CartPO openWebPage() {
        BrowserFactory.getDriver().get(Constants.URL);
        return this;
    }

    public CartPO searchForProduct() {
        searchInput.sendKeys("Lavazza");
        searchSubmitButton.click();
        return this;
    }

    public CartPO openProductPage() {
        productImage.click();
        return this;
    }

    public CartPO addProductToACart() {
        addToCartButton.click();
        return this;
    }

    private void waitFor(String xpath) {
        WebDriverWait wait = BrowserFactory.driverWait(5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
}
