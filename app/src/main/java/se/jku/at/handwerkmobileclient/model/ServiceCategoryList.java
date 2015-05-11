package se.jku.at.handwerkmobileclient.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("servicecategorylist")
public class ServiceCategoryList {

	private List <ServiceCategory> categories;

	public List<ServiceCategory> getList() {
		return categories;
	}

	public void setList(List<ServiceCategory> categories) {
		this.categories = categories;
	}
	
	public ServiceCategoryList(){
		categories = new ArrayList<ServiceCategory>();
	}
	
}
