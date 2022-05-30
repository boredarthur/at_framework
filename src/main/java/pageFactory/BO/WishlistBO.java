package main.java.pageFactory.BO;

import main.java.pageFactory.PO.WishlistPO;

public class WishlistBO {
    public boolean addToWishlist() {
        new HomeBO()
                .logIn();

        new WishlistPO()
                .openWebPage()
                .openCellPhonesPage();
        return true;
    }
}
