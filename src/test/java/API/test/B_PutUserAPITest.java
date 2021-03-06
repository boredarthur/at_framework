package test.java.API.test;

import com.fasterxml.jackson.core.JsonProcessingException;
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

public class B_PutUserAPITest extends BaseTest {
    test.java.API.BaseTest baseTest;
    RestClient restClient;
    CloseableHttpResponse closableHttpResponse;
    String changeTo;
    private static Session session;

    @BeforeMethod
    public void setup() throws ClientProtocolException, IOException, JSONException {
        baseTest = new BaseTest();
        changeTo = "ChangedWithJava";
        session = HibernateUtil.getSessionFactory().openSession();
        restClient = new RestClient();
    }
    @Test(priority = 2)
    public void PutUserTest() throws ClientProtocolException, IOException, JSONException {
        postCreateNewUser();
        putUser();
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

    public JSONObject parseResponse(UserPOJO user) throws IOException {
        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        restClient.setUserData(user, (String) responseJson.get("token"));
        restClient.saveUserToDB(session, user);
        return responseJson;
    }

    public UserPOJO createNewUser() throws IOException {
        UserPOJO user = new UserPOJO("Automation", "Test", "Placeholder", "automation70@email.com", "+380960060070");
        return user;
    }

    public String mapUser(UserPOJO user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(".\\data\\user.json"), user);
        String userJsonString = mapper.writeValueAsString(user);
        System.out.println(userJsonString);

        return userJsonString;
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
