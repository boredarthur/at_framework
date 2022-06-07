package test.java.API.data;

public class DevicePOJO {
    String name;
    Integer status;
    String storage;

    public DevicePOJO(String name, Integer status, String storage) {
        this.name = name;
        this.status = status;
        this.storage = storage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }
}
