package se.jku.at.handwerkmobileclient.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("service")
public class Service implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private ServiceCategory category;
	private String headline;
	private String detailInfo;
	private int supplierid; //Zirkuläre abhängigkeit kann nicht in json verwandelt werden
	private double price;
	private Date creationDate;
	
	public Service() {
		this.creationDate = new Date();
	}
	
	public Service(ServiceCategory category, String headline,
			String detailInfo, int supplierid, double price) {

		this.category = category;
		this.headline = headline;
		this.detailInfo = detailInfo;
		this.supplierid = supplierid;
		this.price = price;
		this.creationDate = new Date();
	}
	
	public Service(int id,ServiceCategory category, String headline,
			String detailInfo, int supplierid, double price) {
		
		this.id = id;
		this.category = category;
		this.headline = headline;
		this.detailInfo = detailInfo;
		this.supplierid = supplierid;
		this.price = price;
		this.creationDate = new Date();
	}
	
	public int getId() {
		return id;
	}
	

	public void setId(int id) {
		this.id = id;
	}

	public ServiceCategory getCategory() {
		return category;
	}
	public void setCategory(ServiceCategory category) {
		this.category = category;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getDetailInfo() {
		return detailInfo;
	}
	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
	}
	
	public int getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(int supplierid) {
		this.supplierid = supplierid;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	

}
