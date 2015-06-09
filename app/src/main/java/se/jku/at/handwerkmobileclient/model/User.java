package se.jku.at.handwerkmobileclient.model;

/**
 * Created by Martin on 09.06.15.
 */
public class User {

    public static User instance;
    public static User getInstance(String name) {
        if (instance == null)
            instance = new User(name);
        return instance;
    }

    private String name, pass, token;
    private Manufacturer manufacturer;

    private User(String name) {
        this(name, null);
    }

    private User(String name, String password) {
        this.name = name;
        this.pass = password;
    }

    public void logout() {}
    public void login() {}

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public User setToken(String token) {
        this.token = token;
        return this;
    }

    public String getPass() {
        return pass;
    }

    public User setPass(String pass) {
        this.pass = pass;
        return this;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public User setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }
}
