package main.java.pageFactory.BO;

import main.java.pageFactory.PO.ChangeNamePO;
import main.java.pageFactory.PO.WishlistPO;

public class ChangeNameBO {
    public boolean changeName(String nameChangeTo) {
        new HomeBO()
                .logIn();

        new ChangeNamePO()
                .openWebPage()
                .openChangeNamePage()
                .performChangeName(nameChangeTo);
        return true;
    }
}
