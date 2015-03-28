package com.home.autoparts.storage.datasource.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.home.autoparts.domain.GenericProduct;

@Document(collection = "products")
public class MongoProduct implements GenericProduct {
	
	@Id
	private String id;
	
	@Indexed(direction=IndexDirection.ASCENDING, name="prod_name_idx")
	private String name;
	
	private String productId;
	private double price;
	private Long stock;
	private String manufacturer;
	private Date offerDate;
	
	//needed for serialization
	public MongoProduct() {
		super();
	}
	
	private MongoProduct(MongoProductBuilder genericProductBuilder) {
		this.name = genericProductBuilder.name;
		this.productId = genericProductBuilder.productId;
		this.price = genericProductBuilder.price;
		this.stock = genericProductBuilder.stock;
		this.manufacturer = genericProductBuilder.manufacturer;
		this.offerDate = genericProductBuilder.offerDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((offerDate == null) ? 0 : offerDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
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
		MongoProduct other = (MongoProduct) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (offerDate == null) {
			if (other.offerDate != null)
				return false;
		} else if (!offerDate.equals(other.offerDate))
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		return true;
	}

	public static class MongoProductBuilder{
		private String name;
		private String productId;
		private double price;
		private Long stock;
		private String manufacturer;
		private Date offerDate;
		
		public MongoProductBuilder name(String name) {
			this.name = name;
			return this;
		}
		public MongoProductBuilder productId(String productId) {
			this.productId = productId;
			return this;
		}
		public MongoProductBuilder price(double price) {
			this.price = price;
			return this;
		}
		public MongoProductBuilder stock(Long stock) {
			this.stock = stock;
			return this;
		}
		public MongoProductBuilder manufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
			return this;
		}
		public MongoProductBuilder offerDate(Date offerDate) {
			this.offerDate = offerDate;
			return this;
		}
		
		public MongoProduct build() {
			return new MongoProduct(this);
		}
	}
}
