package test.java.API.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.utils.Constants;
import main.java.utils.HibernateUtil;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.hibernate.Session;
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

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class A_PostNewUserAPITest extends BaseTest {
    test.java.API.BaseTest baseTest;
    RestClient restClient;
    CloseableHttpResponse closableHttpResponse;
    private static Session session;

    @BeforeMethod
    public void setup() throws ClientProtocolException, IOException, JSONException {
        baseTest = new BaseTest();
        session = HibernateUtil.getSessionFactory().openSession();
        restClient = new RestClient();
    }

    @Test(priority = 1)
    public void PostUserTest() throws ClientProtocolException, IOException, JSONException {
        postCreateNewUser();
        validateNewUserCreated();
    }

    public void postCreateNewUser() throws IOException {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        HashMap<String, String> headermap = restClient.applyHeaderMap(headers);

        UserPOJO user = createNewUser();
        String userJsonString = mapUser(user);
        closableHttpResponse = restClient.POST(Constants.userCreateServiceURL, userJsonString, headermap);
        int statusCode = restClient.logStatusCode(closableHttpResponse);

        // Validate
        Assert.assertEquals(statusCode, responseStatusCode201, "Status code is 201");

        JSONObject responseJson = parseResponse(user);
        System.out.println("Response JSON from API " + responseJson);
        System.out.println("POST ENDED");
    }

    public void validateNewUserCreated() throws IOException {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + Variables.TOKEN);
        HashMap<String, String> headermap = restClient.applyHeaderMap(headers);

        closableHttpResponse = restClient.GET(Constants.userRetrieveURL, headermap);
        UserRegisterPOJO user = parseResponse();

        Assert.assertTrue(Variables.USER.getEmail().equals(user.getEmail()));
        Assert.assertTrue(Variables.USER.getPhone_number().equals(user.getPhone_number()));
        System.out.println("RETRIEVE USER ENDED");
    }

    public JSONObject parseResponse(UserPOJO user) throws IOException {
        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        restClient.setUserData(user, (String) responseJson.get("token"));
        restClient.saveUserToDB(session, user);
        return responseJson;
    }

    public UserRegisterPOJO parseResponse() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
        UserRegisterPOJO userRegisterPOJOResponseObject = mapper.readValue(responseString, UserRegisterPOJO.class);
        return userRegisterPOJOResponseObject;
    }

    public UserPOJO createNewUser() throws IOException {
        UserPOJO user = new UserPOJO("Automation", "Test", "Placeholder", "automation69@email.com", "+380960060069");
        return user;
    }

    public String mapUser(UserPOJO user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(".\\data\\user.json"), user);
        String userJsonString = mapper.writeValueAsString(user);
        System.out.println(userJsonString);

        return userJsonString;
    }
}
