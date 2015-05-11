package se.jku.at.handwerkmobileclient.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("servicelist")
public class ServiceList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Service> services;
	
	public ServiceList(){
		services = new ArrayList<Service>();
	}

	public List<Service> getList() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}
}
