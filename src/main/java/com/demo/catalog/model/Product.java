package com.demo.catalog.model;

public class Product {

	private int product_id;
	private int category_id;
	private String name;
	private String summary;
	private String description;
	private String price;
	private int qty;

	public Product(){}
	
	public Product(int product_id, int category_id, String name, String summary, String description, String price,
			int qty) {
		super();
		this.product_id = product_id;
		this.category_id = category_id;
		this.name = name;
		this.summary = summary;
		this.description = description;
		this.price = price;
		this.qty = qty;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", category_id=" + category_id + ", name=" + name + ", summary="
				+ summary + ", description=" + description + ", price=" + price + ", qty=" + qty + "]";
	}

}