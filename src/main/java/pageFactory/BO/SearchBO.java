package main.java.pageFactory.BO;


import main.java.pageFactory.PO.SearchPO;

public class SearchBO {
    public boolean search(String searchFor) {
        new SearchPO()
                .openWebPage()
                .searchForBooks(searchFor);
        return true;
    }
}
