package com.home.autoparts.domain;

import java.util.List;
import java.util.UUID;

public class GenericStore {
	private String storeId  = UUID.randomUUID().toString();
	private String name;
	private String address;
	private String companySite;
	private GenericContact contact;
	private List<GenericProduct> products;
	private Object mapLink;
	
	public GenericStore(GenericStoreBuilder genericStoreBuilder) {
		this.name = genericStoreBuilder.name;
		this.address = genericStoreBuilder.address;
		this.companySite = genericStoreBuilder.companySite;
		this.contact = genericStoreBuilder.contact;
		this.products = genericStoreBuilder.products;
		this.mapLink = genericStoreBuilder.mapLink;
	}
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
	public List<GenericProduct> getProducts() {
		return products;
	}
	public void setProducts(List<GenericProduct> products) {
		this.products = products;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	public static class GenericStoreBuilder {
		private String name;
		private String address;
		private String companySite;
		private GenericContact contact;
		private List<GenericProduct> products;
		private Object mapLink;
		
		public GenericStoreBuilder name(String name) {
			this.name = name;
			return this;
		}
		public GenericStoreBuilder address(String address) {
			this.address = address;
			return this;
		}
		public GenericStoreBuilder companySite(String companySite) {
			this.companySite = companySite;
			return this;
		}
		public GenericStoreBuilder contact(GenericContact contact) {
			this.contact = contact;
			return this;
		}
		public GenericStoreBuilder products(List<GenericProduct> products) {
			this.products = products;
			return this;
		}
		public GenericStoreBuilder mapLink(Object mapLink) {
			this.mapLink = mapLink;
			return this;
		}
		
		public GenericStore build() {
			return new GenericStore(this);
		}
	}
}
