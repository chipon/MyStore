package com.hitwh.domain;

import java.io.Serializable;

public class Product implements Serializable{
	private int productid;
	private String name;
	private double price;
	private byte typeid;
	private int stoke;
	private String intruduction;
	private String description;
	private String imgurl;
	private String param;
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getIntruduction() {
		return intruduction;
	}
	public void setIntruduction(String intruduction) {
		this.intruduction = intruduction;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
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
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public byte getTypeid() {
		return typeid;
	}
	public void setTypeid(byte typeid) {
		this.typeid = typeid;
	}
	public int getStoke() {
		return stoke;
	}
	public void setStoke(int stoke) {
		this.stoke = stoke;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
