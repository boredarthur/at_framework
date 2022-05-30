package main.java.pageFactory.BO;

import main.java.pageFactory.PO.CartPO;

public class CartBO {
    public boolean addToCart() {
        new HomeBO()
                .logIn();

        new CartPO()
                .openWebPage()
                .searchForProduct()
                .openProductPage()
                .addProductToACart();
        return true;
    }
}
