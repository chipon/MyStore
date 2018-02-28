package com.hitwh.domain;

import java.sql.Timestamp;
import java.util.List;

public class Order {
	private String orderid;
	private int userid;
	private double money;
	private Timestamp time;
	private boolean paystate;
	private String message;
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public boolean isPaystate() {
		return paystate;
	}
	public void setPaystate(boolean paystate) {
		this.paystate = paystate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
