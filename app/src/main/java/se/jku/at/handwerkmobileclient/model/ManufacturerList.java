package se.jku.at.handwerkmobileclient.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("manufacturerlist")
public class ManufacturerList {
	
	private List <Manufacturer> manufacturers;

	public List<Manufacturer> getList() {
		return manufacturers;
	}

	public void setList(List<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}
	
	public ManufacturerList(){
		manufacturers = new ArrayList<Manufacturer>();
	}

}
