package com.hotfix.jpa.enitity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
    
	@Id
	@Column(name = "id")
	private String id;
	private String name;
	private String description;
	private String type;
	private String price;
	private String unit;
	
	public Product() {
		
	}
	public Product(String id, String name, String description, String type, String price, String unit) {
		
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.price = price;
		this.unit = unit;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", description=" + description + ", type=" + type
				+ ", price=" + price + ", unit=" + unit + "]";
	}

}
