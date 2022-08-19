package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Seller {
	private int sseq;
	private String name;
	private String email;
	private String pw;
	private String phone;
	private String zip_num;
	private String address;
	private char status;
	private Date reg_date;
	
	
	public String getZip_num() {
		return zip_num;
	}

	public void setZip_num(String zip_num) {
		this.zip_num = zip_num;
	}
	private List<SellerFarm> FramList=new ArrayList<SellerFarm>();


	public List<SellerFarm> getFramList() {
		return FramList;
	}

	public void setFramList(List<SellerFarm> framList) {
		FramList = framList;
	}

	public void addFram(SellerFarm image) {
		FramList.add(image);
	}

	
	public int getSseq() {
		return sseq;
	}
	public void setSseq(int sseq) {
		this.sseq = sseq;
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
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	
	

}
