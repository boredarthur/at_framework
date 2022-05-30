package main.java.pageFactory.BO;

import main.java.pageFactory.PO.HomePO;

public class HomeBO {
    public boolean logIn() {
        new HomePO()
                .openWebPage()
                .clickSignInButton()
                .enterEmail("boredarthur@gmail.com")
                .enterPassword("Grisford12");
        return true;
    }
}
