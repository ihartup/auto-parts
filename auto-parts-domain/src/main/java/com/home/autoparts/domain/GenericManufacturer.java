package com.home.autoparts.domain;

public class GenericManufacturer {
	public String name;
	public String address;
	public String companySite;
	public GenericContact contact;
	public Object mapLink;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCompanySite() {
		return companySite;
	}
	public void setCompanySite(String companySite) {
		this.companySite = companySite;
	}
	public GenericContact getContact() {
		return contact;
	}
	public void setContact(GenericContact contact) {
		this.contact = contact;
	}
	public Object getMapLink() {
		return mapLink;
	}
	public void setMapLink(Object mapLink) {
		this.mapLink = mapLink;
	}
	
}
