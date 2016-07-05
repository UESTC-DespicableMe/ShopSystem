package pers.mine.vo;

import java.io.Serializable;

public class Goods implements Serializable{
	  private String id;
	  private String name;
	  private double price;
	  private String brand; //品牌
	  private String blurb;//简介
	  @Override
	public String toString() {
		  return "[id=" + id + ", name=" + name + ", price=" + price
				+ ", brand=" + brand + ", blurb=" + blurb + "]";
	 }
	  
	 public Goods(){}
	/**
	 * @param id
	 * @param name
	 * @param price
	 * @param brand
	 * @param blurb
	 */
	public Goods(String id, String name, double price, String brand,
			String blurb) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.brand = brand;
		this.blurb = blurb;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param id
	 */
	public Goods(String id) {
		super();
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getBlurb() {
		return blurb;
	}
	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}
}
