package com.example.demo;

public class Vendor {
	private int vendor_id;
	private String name;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private int zip;
	private String country;
	private int phone;
	private String website;

	public Vendor() {
	}

	public Vendor(int vendor_id, String name, String address1, String address2, String city, String state, int zip,
			String country, int phone, String website) {
		super();
		this.vendor_id = vendor_id;
		this.name = name;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
		this.phone = phone;
		this.website = website;
	}

	public int getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public String toString() {
		return "Vendor [vendor_id=" + vendor_id + ", name=" + name + ", address1=" + address1 + ", address2=" + address2
				+ ", city=" + city + ", state=" + state + ", zip=" + zip + ", country=" + country + ", phone=" + phone
				+ ", website=" + website + "]";
	}

}
