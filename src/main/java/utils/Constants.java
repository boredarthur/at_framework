package main.java.utils;

public interface Constants {
    String URL = "https://www.amazon.com/";
    String BeURL = "http://localhost:8000/";
    String userCreateServiceURL = BeURL + "/auth-api/registration/";
    String userRetrieveURL = BeURL + "/auth-api/user/";
    String deviceCreateURL = BeURL + "/orders-api/device/create/";
    String deviceListURL = BeURL + "/orders-api/device/";
    String orderCreateURL = BeURL + "/orders-api/create/";
    String orderListURL = BeURL + "/orders-api/";
}
