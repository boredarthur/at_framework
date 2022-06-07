package test.java.API.client;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hibernate.Session;
import org.json.JSONException;
import test.java.API.Variables;
import test.java.API.data.UserPOJO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {
    public CloseableHttpResponse GET(String url) throws ClientProtocolException, IOException, JSONException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
        return closeableHttpResponse;
    }

    public CloseableHttpResponse GET(String url, HashMap<String, String> headermap) throws ClientProtocolException, IOException, JSONException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        for (Map.Entry<String, String> entry: headermap.entrySet()) {
            httpGet.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
        return closeableHttpResponse;
    }

    public CloseableHttpResponse POST(String url, String entityString, HashMap<String, String> headermap) throws ClientProtocolException, IOException, JSONException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(entityString));

        for (Map.Entry<String, String> entry: headermap.entrySet()) {
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }

        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
        return closeableHttpResponse;
    }

    public CloseableHttpResponse PUT(String url, String entityString, HashMap<String, String> headermap) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        httpPut.setEntity(new StringEntity(entityString));

        for (Map.Entry<String, String> entry: headermap.entrySet()) {
            httpPut.addHeader(entry.getKey(), entry.getValue());
        }

        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPut);
        return closeableHttpResponse;
    }

    public CloseableHttpResponse DELETE(String url, HashMap<String, String> headermap) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);

        for (Map.Entry<String, String> entry: headermap.entrySet()) {
            httpDelete.addHeader(entry.getKey(), entry.getValue());
        }

        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpDelete);
        return closeableHttpResponse;
    }

    public int logStatusCode(CloseableHttpResponse response) {
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Status code " + statusCode);
        return statusCode;
    }

    public void saveUserToDB(Session session, UserPOJO user) {
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    public void setUserData(UserPOJO user, String token) {
        Variables.TOKEN = token;
        user.setToken(token);
        Variables.USER = user;
    }
}
