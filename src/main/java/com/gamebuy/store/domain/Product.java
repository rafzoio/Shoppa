package com.gamebuy.store.domain;

public class Product {
	
	private int id;
	private String SKU;
	private String description;
	private String category;
	private int available;
	private int price;

	public Product(String SKU, String description, String category, int available, int price) {
		this.SKU = SKU;
		this.description = description;
		this.category = category;
		this.available = available;
		this.price = price;
	}

	public Product(int id, String SKU, String description, String category,int available, int price) {
		this.id = id;
		this.SKU = SKU;
		this.description = description;
		this.category = category;
		this.available = available;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", SKU='" + SKU + '\'' +
				", description='" + description + '\'' +
				", category='" + category + '\'' +
				", price=" + price +
				'}';
	}
}
