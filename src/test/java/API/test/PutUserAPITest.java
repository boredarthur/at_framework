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
import test.java.API.data.UserPOJO;
import test.java.API.data.UserRegisterPOJO;

import java.io.IOException;
import java.util.HashMap;

public class PutUserAPITest extends BaseTest {
    test.java.API.BaseTest baseTest;
    String serviceURL;
    String apiURL;
    String URL;
    RestClient restClient;
    CloseableHttpResponse closableHttpResponse;
    String changeTo;

    @BeforeMethod
    public void setup() throws ClientProtocolException, IOException, JSONException {
        baseTest = new BaseTest();
        serviceURL = prop.getProperty("URL");
        apiURL = prop.getProperty("userRetrieveURL");
        changeTo = "ChangedWithJava";
        URL = serviceURL + apiURL;
    }

    @Test(priority = 4)
    public void PutUserTest() throws ClientProtocolException, IOException, JSONException {
        restClient = new RestClient();
        HashMap<String, String> headermap = new HashMap<String, String>();
        headermap.put("Content-Type", "application/json");
        headermap.put("Authorization", "Bearer " + Variables.TOKEN);
        ObjectMapper mapper = new ObjectMapper();

        UserPOJO newUser = Variables.USER;
        newUser.setFirst_name(changeTo);
        String userJsonString = mapper.writeValueAsString(newUser);

        closableHttpResponse = restClient.PUT(URL, userJsonString, headermap);

        int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, responseStatusCode200, "Status code is 200");

        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
        UserRegisterPOJO userRegisterPOJOResponseObject = mapper.readValue(responseString, UserRegisterPOJO.class);
        System.out.println(userRegisterPOJOResponseObject);
        Assert.assertTrue(userRegisterPOJOResponseObject.getFirst_name().equals(changeTo));
    }
}
