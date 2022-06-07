package test.java.API.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.utils.Constants;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.API.BaseTest;
import test.java.API.client.RestClient;
import test.java.API.data.UserPOJO;
import test.java.API.data.UserRegisterPOJO;

import java.io.IOException;
import java.util.HashMap;

public class E_PostUnauthorizedOrderAPITest extends BaseTest {
    BaseTest baseTest;
    RestClient restClient;
    CloseableHttpResponse closableHttpResponse;
    String token;
    UserPOJO client;
    String dateOrder = "2022-05-26T22:44:49Z";

    @BeforeMethod
    public void setup() throws ClientProtocolException, IOException, JSONException {
        baseTest = new BaseTest();
        restClient = new RestClient();
    }

    @Test(priority = 5)
    public void PostUnauthorizedOrderTest() throws ClientProtocolException, IOException, JSONException {
        makeOrder();
        validateUser(token);
        validateOrder();
    }

    public void makeOrder() throws IOException {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        HashMap<String, String> headermap = restClient.applyHeaderMap(headers);

        client = createUser();
        String json = mapOrder(client);
        makeRequest(json, headermap);
    }

    public void makeRequest(String json, HashMap<String, String> headermap) throws IOException {
        closableHttpResponse = restClient.POST(Constants.orderCreateURL, json, headermap);
        int statusCode = restClient.logStatusCode(closableHttpResponse);

        // Validate
        Assert.assertEquals(statusCode, responseStatusCode201, "Status code is 201");
        JSONObject responseJson = parseResponseWithToken();
        System.out.println("Response JSON from API " + responseJson);
        System.out.println("POST ENDED");
    }

    public UserPOJO createUser() {
        UserPOJO user = new UserPOJO("Automation",
                "Test",
                "Placeholder",
                "automation79@email.com",
                "+380960060079");
        return user;
    }

    public String mapOrder(UserPOJO user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject clientJson = new JSONObject(user);
        String json = String.format("{" +
                "\"street\":\"Street 2\"," +
                "\"status\":\"0\"," +
                "\"priority\":\"0\"," +
                "\"device\":\"Xiaomi Feeder 2\"," +
                "\"duration\":\"7\"," +
                "\"date_order\": \"%s\"," +
                "\"date_start\":\"2022-06-20T22:44:49Z\"," +
                "\"client\":" + clientJson.toString()
                + "}", dateOrder);
        return json;
    }

    public JSONObject parseResponseWithToken() throws IOException {
        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        token = (String) responseJson.get("user_token");
        return responseJson;
    }

    public JSONObject parseResponse() throws IOException {
        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        return responseJson;
    }

    public UserRegisterPOJO parseUserResponse() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
        UserRegisterPOJO userRegisterPOJOResponseObject = mapper.readValue(responseString, UserRegisterPOJO.class);
        return userRegisterPOJOResponseObject;
    }

    public void validateUser(String token) throws IOException {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + token);
        HashMap<String, String> headermap = restClient.applyHeaderMap(headers);

        closableHttpResponse = restClient.GET(Constants.userRetrieveURL, headermap);
        UserRegisterPOJO user = parseUserResponse();

        Assert.assertTrue(client.getEmail().equals(user.getEmail()));
        Assert.assertTrue(client.getPhone_number().equals(user.getPhone_number()));
        System.out.println("RETRIEVE USER ENDED");
    }

    public void validateOrder() throws IOException {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        HashMap<String, String> headermap = restClient.applyHeaderMap(headers);

        closableHttpResponse = restClient.GET(Constants.orderListURL, headermap);
        JSONObject response = parseResponse();

        Assert.assertTrue(response.toString().contains(client.getEmail()) && response.toString().contains(dateOrder));
        System.out.println("VALIDATE ORDER SUCCESS");
    }

}
