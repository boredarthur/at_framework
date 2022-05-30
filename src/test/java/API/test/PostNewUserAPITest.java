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

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PostNewUserAPITest extends BaseTest {
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
        apiURL = prop.getProperty("userCreateServiceURL");
        URL = serviceURL + apiURL;
    }

    @Test(priority = 1)
    public void PostUserTest() throws ClientProtocolException, IOException, JSONException {
        restClient = new RestClient();
        HashMap<String, String> headermap = new HashMap<String, String>();
        headermap.put("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();

        UserPOJO user = new UserPOJO("Automation", "Test", "Placeholder", "automation44@email.com", "+380960060044");
        mapper.writeValue(new File(".\\data\\user.json"), user);

        String userJsonString = mapper.writeValueAsString(user);
        System.out.println(userJsonString);

        closableHttpResponse = restClient.POST(URL, userJsonString, headermap);

        // Fetch status code
        int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code " + statusCode);

        // Validate
        Assert.assertEquals(statusCode, responseStatusCode201, "Status code is 201");

        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        Variables.TOKEN = (String) responseJson.get("token");
        Variables.USER = user;
        System.out.println("Response JSON from API " + responseJson);
    }
}
