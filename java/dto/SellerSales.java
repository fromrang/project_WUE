package dto;

import java.util.Date;

public class SellerSales {
	private int sasseq;
	private String name;
	private String email;
	private String month;
	private int money;
	private Date reg_date;
	public int getSasseq() {
		return sasseq;
	}
	public void setSasseq(int sasseq) {
		this.sasseq = sasseq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}
