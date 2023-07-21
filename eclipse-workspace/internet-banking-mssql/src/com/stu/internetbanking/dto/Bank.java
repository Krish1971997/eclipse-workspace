package com.stu.internetbanking.dto;
import java.util.Objects;

public class Bank {
	private int ac_number;
	private String username;
	private String password;
	private double amount;
	private String email;
	private long phone;
	private String created_date;
	private String updated_date;
	private int otp;
	
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public int getAc_number() {
		return ac_number;
	}
	public void setAc_number(int ac_number) {
		this.ac_number = ac_number;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String address) {
		this.email = address;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(ac_number,username,password,amount,email,phone,created_date,updated_date,otp);
	}
}