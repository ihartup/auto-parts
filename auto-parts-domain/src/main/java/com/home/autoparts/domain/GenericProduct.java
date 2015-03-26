package com.home.autoparts.domain;

import java.math.BigDecimal;
import java.util.Date;

public class GenericProduct {
	private String name;
	private String productId;
	private BigDecimal price;
	private Long stock;
	private String manufacturer;
	private Date offerDate;
	
	//needed for serialization
	public GenericProduct() {
		super();
	}
	
	private GenericProduct(GenericProductBuilder genericProductBuilder) {
		this.name = genericProductBuilder.name;
		this.productId = genericProductBuilder.productId;
		this.price = genericProductBuilder.price;
		this.stock = genericProductBuilder.stock;
		this.manufacturer = genericProductBuilder.manufacturer;
		this.offerDate = genericProductBuilder.offerDate;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Date getOfferDate() {
		return offerDate;
	}
	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}
	
	public static class GenericProductBuilder{
		private String name;
		private String productId;
		private BigDecimal price;
		private Long stock;
		private String manufacturer;
		private Date offerDate;
		
		public GenericProductBuilder name(String name) {
			this.name = name;
			return this;
		}
		public GenericProductBuilder productId(String productId) {
			this.productId = productId;
			return this;
		}
		public GenericProductBuilder price(BigDecimal price) {
			this.price = price;
			return this;
		}
		public GenericProductBuilder stock(Long stock) {
			this.stock = stock;
			return this;
		}
		public GenericProductBuilder manufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
			return this;
		}
		public GenericProductBuilder offerDate(Date offerDate) {
			this.offerDate = offerDate;
			return this;
		}
		
		public GenericProduct build() {
			return new GenericProduct(this);
		}
	}
}
