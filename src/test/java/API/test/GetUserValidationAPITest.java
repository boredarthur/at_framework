package test.java.API.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.API.BaseTest;
import test.java.API.Variables;
import test.java.API.client.RestClient;
import test.java.API.data.UserRegisterPOJO;

import java.io.IOException;
import java.util.HashMap;

public class GetUserValidationAPITest extends BaseTest {
    test.java.API.BaseTest baseTest;
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

    @Test(priority = 3)
    public void GetUserValidationTest() throws ClientProtocolException, IOException, JSONException {
        restClient = new RestClient();
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> headermap = new HashMap<String, String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization", "Bearer " + Variables.TOKEN);
        closableHttpResponse = restClient.GET(URL, headermap);
        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");

        UserRegisterPOJO userRegisterPOJOResponseObject = mapper.readValue(responseString, UserRegisterPOJO.class);
        System.out.println(Variables.USER.getEmail());
        System.out.println(userRegisterPOJOResponseObject.getEmail());
        Assert.assertTrue(Variables.USER.getEmail().equals(userRegisterPOJOResponseObject.getEmail()));
        Assert.assertTrue(Variables.USER.getPhone_number().equals(userRegisterPOJOResponseObject.getPhone_number()));
    }
}
