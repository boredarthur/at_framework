package test.java.API.data;
import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class UserPOJO {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    Integer id;
    @Column(name = "first_name")
    String first_name;
    @Column(name = "second_name")
    String second_name;
    @Column(name = "street")
    String street;
    @Column(name = "email")
    String email;
    @Column(name = "phone_number")
    String phone_number;
    @Column(name = "token")
    String token;

    public UserPOJO(String first_name, String second_name, String street, String email, String phone_number) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.street = street;
        this.email = email;
        this.phone_number = phone_number;
    }

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
    public Integer getId() {
        return id;
    }

    public void setId(Integer id_address) {
        this.id = id_address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
