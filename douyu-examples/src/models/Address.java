package models;

import douyu.mvc.Model;

@Model
public class Address {
	private String country;
	private String city;

	public void set(String country, String city) {
		this.country = country;
		this.city = city;
	}

	public String toString() {
		return "Address[country=" + country + ", city=" + city + "]";
	}
}
