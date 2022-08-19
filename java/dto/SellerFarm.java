package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SellerFarm {
private int fpseq; 
private int fseq;
private String email;
private String experience_date;
private String  experience_time;
private int experience_price;
private int experience_number;
private String experience_context;
private Date indate;
private String url;

private String content;
private Date reg_date;


private List<String> postimages=new ArrayList<String>();

public List<String> getPostimages() {
	return postimages;
}

public void addimages(String image) {
	postimages.add(image);
}

List<SellerFarm> replyList=new ArrayList<SellerFarm>();


public void replyList(SellerFarm list) {
	replyList.add(list);
}

public List<SellerFarm> getReplyList() {
	return replyList;
}

public void setReplyList(List<SellerFarm> replyList) {
	this.replyList = replyList;
}

public Date getReg_date() {
	return reg_date;
}

public void setReg_date(Date reg_date) {
	this.reg_date = reg_date;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public void setPostimages(List<String> postimages) {
	this.postimages = postimages;
}
public String getExperience_context() {
	return experience_context;
}
public void setExperience_context(String experience_context) {
	this.experience_context = experience_context;
}
public int getFpseq() {
	return fpseq;
}
public void setFpseq(int fpseq) {
	this.fpseq = fpseq;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public int getFseq() {
	return fseq;
}
public void setFseq(int fseq) {
	this.fseq = fseq;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

public String getExperience_date() {
	return experience_date;
}
public void setExperience_date(String experience_date) {
	this.experience_date = experience_date;
}
public String getExperience_time() {
	return experience_time;
}
public void setExperience_time(String experience_time) {
	this.experience_time = experience_time;
}
public int getExperience_price() {
	return experience_price;
}
public void setExperience_price(int experience_price) {
	this.experience_price = experience_price;
}
public int getExperience_number() {
	return experience_number;
}
public void setExperience_number(int experience_number) {
	this.experience_number = experience_number;
}
public Date getIndate() {
	return indate;
}
public void setIndate(Date indate) {
	this.indate = indate;
}


}
