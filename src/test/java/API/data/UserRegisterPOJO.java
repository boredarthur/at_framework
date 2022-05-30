package test.java.API.data;

public class UserRegisterPOJO {
    String first_name;
    String second_name;
    String street;
    String email;
    String phone_number;
    String password;
    int user_type;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public UserRegisterPOJO() {}

    public UserRegisterPOJO(String email, String phone_number, String first_name, String second_name, String street, String password, int user_type) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.street = street;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
        this.user_type = user_type;
    }
}


