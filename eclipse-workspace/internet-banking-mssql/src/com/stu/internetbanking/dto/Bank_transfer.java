package com.stu.internetbanking.dto;

public class Bank_transfer {
	private int id;
	private int ac_number;
	private int transfer_acno;
	private String ifsc;
	private String sent_date;
	private double amount;
	private String Status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAc_number() {
		return ac_number;
	}
	public void setAc_number(int ac_number) {
		this.ac_number = ac_number;
	}
	public int getTransfer_acno() {
		return transfer_acno;
	}
	public void setTransfer_acno(int transfer_acno) {
		this.transfer_acno = transfer_acno;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getSent_date() {
		return sent_date;
	}
	public void setSent_date(String sent_date) {
		this.sent_date = sent_date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	

}
