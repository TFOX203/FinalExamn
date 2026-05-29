/**
 * 
 */
package com.example.demo.domain;

/*
 *
 * @author Valenciano
 * 9 abr 2026
 */
public class Productos {
	
	//fields
	private String model;
	
	private long price;
	
	private int year;
	
	private long kms;
	
	private int cv;
	
	private String color;
	
	private long id;
	
	//methods
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public long getKms() {
		return kms;
	}

	public void setKms(long kms) {
		this.kms = kms;
	}

	public int getCv() {
		return cv;
	}

	public void setCv(int cv) {
		this.cv = cv;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	//constructors
	public Productos(String model, long price, int year, long kms, int cv, String color, long id) {
		this.model = model;
		this.price = price;
		this.year = year;
		this.kms = kms;
		this.cv = cv;
		this.color = color;
		this.id = id;
	}

}
