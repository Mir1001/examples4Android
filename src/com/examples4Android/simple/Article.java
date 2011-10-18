package com.examples4Android.simple;

import java.util.Random;

public class Article {
	private String name;
	private double price;
	private boolean selected;
	
	public static Integer id = 0;
	
	public Article(String _name, double _price, boolean _selected) {
		name = _name;
		price = _price;
		selected = _selected;
	}
	
	public static Article getRandom() {
		id++;
		return new Article("item"+id, new Random().nextInt(5000), id%2==0);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}
