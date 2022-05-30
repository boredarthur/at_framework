package test.java.API.test;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.API.BaseTest;
import test.java.API.Variables;
import test.java.API.client.RestClient;

import java.io.IOException;
import java.util.HashMap;

public class DeleteUserAPITest extends BaseTest {
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
        apiURL = prop.getProperty("userRetrieveURL");
        URL = serviceURL + apiURL;
    }

    @Test(priority = 5)
    public void DeleteUserTest() throws ClientProtocolException, IOException, JSONException {
        restClient = new RestClient();
        HashMap<String, String> headermap = new HashMap<String, String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization", "Bearer " + Variables.TOKEN);

        closableHttpResponse = restClient.DELETE(URL, headermap);

        int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, responseStatusCode204);
    }

}
