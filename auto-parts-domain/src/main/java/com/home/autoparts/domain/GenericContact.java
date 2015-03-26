package com.home.autoparts.domain;

import java.util.List;
import java.util.UUID;

public class GenericContact {
	private String contactId = UUID.randomUUID().toString();
	private List<String> phoneList;
	private String fax;
	private String email;
	
	//needed for serialization
	public GenericContact(){
		
	}
	
	private GenericContact(GenericContactBuilder builder) {
		super();
		this.phoneList = builder.phoneList;
		this.fax = builder.fax;
		this.email = builder.email;
	}
	public List<String> getPhoneList() {
		return phoneList;
	}
	public void setPhoneList(List<String> phoneList) {
		this.phoneList = phoneList;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public static class GenericContactBuilder  {
		private List<String> phoneList;
		private String fax;
		private String email;
		
		public GenericContactBuilder phoneList(List<String> phoneList) {
			this.phoneList = phoneList;
			return this;
		}
		
		public GenericContactBuilder fax(String fax) {
			this.fax = fax;
			return this;
		}
		
		public GenericContactBuilder email(String email) {
			this.email = email;
			return this;
		}
		
		public GenericContact build() {
			return new GenericContact(this);
		}
	}
}
