package test.java.API.test;

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
import test.java.API.data.DevicePOJO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class D_PostNewDeviceAPITest extends BaseTest  {
    BaseTest baseTest;
    RestClient restClient;
    CloseableHttpResponse closableHttpResponse;

    @BeforeMethod
    public void setup() throws ClientProtocolException, IOException, JSONException {
        baseTest = new BaseTest();
        restClient = new RestClient();
    }

    @Test(priority = 4)
    public void PostDeviceTest() throws ClientProtocolException, IOException, JSONException {
        DevicePOJO feeder = postDevice();
        validateDeviceCreated(feeder);
    }

    public DevicePOJO postDevice() throws IOException {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        HashMap<String, String> headermap = restClient.applyHeaderMap(headers);

        DevicePOJO feeder = createDevice();
        String feederJson = mapFeeder(feeder);
        closableHttpResponse = restClient.POST(Constants.deviceCreateURL, feederJson, headermap);
        int statusCode = restClient.logStatusCode(closableHttpResponse);

        // Validate
        Assert.assertEquals(statusCode, responseStatusCode201, "Status code is 201");

        JSONObject responseJson = parseResponse(feeder);
        System.out.println("Response JSON from API " + responseJson);
        System.out.println("POST ENDED");
        return feeder;
    }

    public DevicePOJO createDevice() {
        DevicePOJO newDevice = new DevicePOJO("Xiaomi Feeder 3", 0, "Тараса Шевченка");
        return newDevice;
    }

    public String mapFeeder(DevicePOJO device) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(".\\data\\device.json"), device);
        String deviceJsonString = mapper.writeValueAsString(device);
        System.out.println(deviceJsonString);

        return deviceJsonString;
    }

    public JSONObject parseResponse(DevicePOJO feeder) throws IOException {
        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        return responseJson;
    }

    public String parseResponse() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
        return responseString;
    }

    public void validateDeviceCreated(DevicePOJO device) throws IOException {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        HashMap<String, String> headermap = restClient.applyHeaderMap(headers);

        closableHttpResponse = restClient.GET(Constants.deviceListURL, headermap);
        String response = parseResponse();
        Assert.assertTrue(response.contains(device.getName()) && response.contains(device.getStorage()));
    }

}
