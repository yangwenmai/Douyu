package models;

import douyu.mvc.Model;

@Model
public class Consumer {
	private String name;
	private Address address;

	public void set(String name, Address address) {
		this.name = name;
		this.address = address;
	}

	public String toString() {
		return "Consumer[name=" + name + ", address=" + address + "]";
	}
}
