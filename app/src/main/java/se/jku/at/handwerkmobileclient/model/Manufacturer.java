package se.jku.at.handwerkmobileclient.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("manufacturer")
public class Manufacturer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public Manufacturer() {
		services = new ServiceList();
	}
	
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
	
	public Manufacturer(int id, String name, String city, String address, int plz,
			String country, String tel, String email, String info) {
		this.id = id;
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
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public boolean addService(Service s) {
		return this.services.getList().add(s);
	}
	
	public boolean deleteService(Service s) {
		return this.services.getList().remove(s);
	}
	
	public String toString(){
		return ""+this.name;
	}
	
	
	// GETTER/ SETTER
	
	

	public String getName() {
		return name;
	}
	public ServiceList getServices() {
		return services;
	}

	public void setServices(ServiceList services) {
		this.services = services;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPlz() {
		return plz;
	}
	public void setPlz(int plz) {
		this.plz = plz;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

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

	public static Manufacturer CreateWithID(String id2) {
		Manufacturer worker = new Manufacturer();
		try{
			worker.setId(Integer.parseInt(id2));
		}catch (Exception e){
			return null;
		}
		return worker;
	}
	
	


}
