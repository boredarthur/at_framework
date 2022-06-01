package test.java.UI;
import org.testng.annotations.DataProvider;

public class BaseTest {
    @DataProvider(parallel = true, name = "browserDataProvider")
    public static Object[][] browserDataProvider() {
        return new Object[][]{{"chrome"}};
    }

    @DataProvider(parallel = true, name = "parallelBrowserDataProvider")
    public static Object[][] parallelBrowserDataProvider() { return new Object[][]{{"chrome"}, {"firefox"}};}

    @DataProvider(parallel = true, name = "nameDataProvider")
    public static Object[][] nameDataProvider() { return new Object[][]{{"chrome", "AutomationTest"}};}

    @DataProvider(parallel = true, name = "searchDataProvider")
    public static Object[][] searchDataProvider() { return new Object[][]{{"chrome", "Sherlock Holmes"}};}
}
