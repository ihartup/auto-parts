package com.home.autoparts.domain;

import java.util.Date;

public interface GenericProduct {
	public String getId();
	public String getName();
	public String getProductId();
	public double getPrice();
	
	public Long getStock();
	public String getManufacturer();
	public Date getOfferDate();
}
