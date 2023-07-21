package com.stu.internetbanking.dto;

public class BankPassbook {
	private int sno;
	private int ac_number;
	private String t_date;
	private String type;
	private double amount;
	private double avail_bal;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getAc_number() {
		return ac_number;
	}
	public void setAc_number(int ac_number) {
		this.ac_number = ac_number;
	}
	public String getT_date() {
		return t_date;
	}
	public void setT_date(String t_date) {
		this.t_date = t_date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getAvail_bal() {
		return avail_bal;
	}
	public void setAvail_bal(double avail_bal) {
		this.avail_bal = avail_bal;
	}

}
