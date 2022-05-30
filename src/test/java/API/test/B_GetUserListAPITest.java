package test.java.API.test;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.API.BaseTest;
import test.java.API.client.RestClient;

import java.io.IOException;

public class B_GetUserListAPITest extends BaseTest {
    BaseTest baseTest;
    String serviceURL;
    String apiURL;
    String URL;
    RestClient restClient;
    CloseableHttpResponse closableHttpResponse;

    @BeforeMethod
    public void setup() throws ClientProtocolException, IOException, JSONException {
        baseTest = new BaseTest();
        serviceURL = prop.getProperty("URL");
        apiURL = prop.getProperty("userListServiceURL");
        URL = serviceURL + apiURL;
    }

    @Test(priority = 2)
    public void GetListUsersTest() throws ClientProtocolException, IOException, JSONException {
        restClient = new RestClient();
        closableHttpResponse = restClient.GET(URL);
        System.out.println("RETRIEVE LIST ENDED");
    }

}
