package com.cognizant.springlearn.model;

public class Department {
	private int id;
	private String name;
	private String location;
	public Department() {
		super();
	}
	public Department(int id, String name, String location) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", location=" + location + "]";
	}
}
