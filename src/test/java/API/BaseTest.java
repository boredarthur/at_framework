package test.java.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    //Properties
    public Properties prop;
    public int responseStatusCode200 = 200;
    public int responseStatusCode201 = 201;
    public int responseStatusCode204 = 204;

    public BaseTest() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/API/config/" + "config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException el) {
            el.printStackTrace();
        }
    }
}

