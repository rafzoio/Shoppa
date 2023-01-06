package com.gamebuy.store.domain;

public class Address {


	private int customerId;
	private String house;
	private String addressLine1;
	private String addressLine2;
	private String country;
	private String postcode;

	public Address(int customerId, String house, String addressLine1, String addressLine2, String country, String postcode) {
		this.customerId = customerId;
		this.house = house;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.country = country;
		this.postcode = postcode;
	}

    public Address() {
    }

    public String getHouse() {
		return house;
	}
	public void setHouse(String house) {
		this.house = house;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Address{" +
				"customerId=" + customerId +
				", house='" + house + '\'' +
				", addressLine1='" + addressLine1 + '\'' +
				", addressLine2='" + addressLine2 + '\'' +
				", country='" + country + '\'' +
				", postcode='" + postcode + '\'' +
				'}';
	}
}
