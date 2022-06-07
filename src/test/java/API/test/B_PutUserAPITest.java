package test.java.API.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.utils.Constants;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
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

public class B_PutUserAPITest extends BaseTest {
    test.java.API.BaseTest baseTest;
    RestClient restClient;
    CloseableHttpResponse closableHttpResponse;
    String changeTo;

    @BeforeMethod
    public void setup() throws ClientProtocolException, IOException, JSONException {
        baseTest = new BaseTest();
        changeTo = "ChangedWithJava";
        restClient = new RestClient();
    }

    @Test(priority = 4)
    public void PutUserTest() throws ClientProtocolException, IOException, JSONException {
        putUser();
        validateNewUserCreated();
    }

    public void putUser() throws IOException {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + Variables.TOKEN);
        HashMap<String, String> headermap = restClient.applyHeaderMap(headers);

        String userJsonString = changeUser();
        closableHttpResponse = restClient.PUT(Constants.userRetrieveURL, userJsonString, headermap);
        int statusCode = restClient.logStatusCode(closableHttpResponse);
        Assert.assertEquals(statusCode, responseStatusCode200, "Status code is 200");

        UserRegisterPOJO user = parseResponse();
        Assert.assertTrue(user.getFirst_name().equals(changeTo));
        System.out.println("PUT ENDED");

    }

    public String changeUser() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        UserPOJO newUser = Variables.USER;
        newUser.setFirst_name(changeTo);
        String userJsonString = mapper.writeValueAsString(newUser);
        return userJsonString;
    }

    public UserRegisterPOJO parseResponse() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
        UserRegisterPOJO userRegisterPOJOResponseObject = mapper.readValue(responseString, UserRegisterPOJO.class);
        return userRegisterPOJOResponseObject;
    }

    public void validateNewUserCreated() throws IOException {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + Variables.TOKEN);
        HashMap<String, String> headermap = restClient.applyHeaderMap(headers);

        closableHttpResponse = restClient.GET(Constants.userRetrieveURL, headermap);
        UserRegisterPOJO user = parseResponse();

        Assert.assertTrue(user.getFirst_name().equals(changeTo));
        System.out.println("RETRIEVE USER ENDED");
    }
}
