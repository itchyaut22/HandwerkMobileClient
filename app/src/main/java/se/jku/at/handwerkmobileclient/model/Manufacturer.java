package se.jku.at.handwerkmobileclient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Diese Klasse stellt einen Menufacturer mit all seinen Properties dar.
 *
 */
@JsonTypeName("manufacturer")
@JsonIgnoreProperties({"userCategory"})
public class Manufacturer {

    private int id;
    private String name;
    private String city;
    private String address;
    private int plz;
    private String country;
    private String tel;
    private String email;
    private String info;
    private ServiceList services;


    @JsonIgnore
    private String password;

    private UserCategory userCategory;

    /**
     * Default constructor.
     */
    public Manufacturer() {
        services = new ServiceList();
        userCategory = UserCategory.USER;
    }

    /**
     * Initialisiert einen neuen Manufacturer mit den gegebenen Parametern
     * @param name
     * @param city
     * @param address
     * @param plz
     * @param country
     * @param tel
     * @param email
     * @param info
     */
    public Manufacturer(String name, String city, String address, int plz,
                        String country, String tel, String email, String info) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.plz = plz;
        this.country = country;
        this.tel = tel;
        this.email = email;
        this.info = info;
        this.services = new ServiceList();
    }

    /**
     * Initialisiert einen neuen Manufacturer mit den gegebenen Parametern
     * @param id
     * @param name
     * @param city
     * @param address
     * @param plz
     * @param country
     * @param tel
     * @param email
     * @param info
     */
    public Manufacturer(int id, String name, String city, String address,
                        int plz, String country, String tel, String email, String info, String password, UserCategory category) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.plz = plz;
        this.country = country;
        this.tel = tel;
        this.email = email;
        this.info = info;
        this.password = password;
        this.userCategory = category;
        this.services = new ServiceList();
    }


    // GETTER/ SETTER

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + plz;
        result = prime * result + ((tel == null) ? 0 : tel.hashCode());
        return result;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     *            the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the plz
     */
    public int getPlz() {
        return plz;
    }

    /**
     * @param plz
     *            the plz to set
     */
    public void setPlz(int plz) {
        this.plz = plz;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     *            the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     *            the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info
     *            the info to set
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * @return the services
     */
    public ServiceList getServices() {
        return services;
    }

    /**
     * @param services
     *            the services to set
     */
    public void setServices(ServiceList services) {
        this.services = services;
    }

    /**
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * @param id
     * 			the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the password
     */
    //@JsonIgnore
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the userCategory
     */
    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }

    /**

     * Weißt dem Manufacturer einen Service zu
     * @param s Service to set
     * @return true, falls ok.
     */
    public boolean addService(Service s) {
        return this.services.getList().add(s);
    }

    /**
     * Entzieht dem Manufacturer einen Service
     * @param s Service to delete
     * @return true, falls ok.
     */
    public boolean deleteService(Service s) {
        return this.services.getList().remove(s);
    }


    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Manufacturer other = (Manufacturer) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (plz != other.plz)
            return false;
        if (tel == null) {
            if (other.tel != null)
                return false;
        } else if (!tel.equals(other.tel))
            return false;
        return true;
    }
}
