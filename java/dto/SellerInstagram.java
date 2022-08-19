package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SellerInstagram {	
	private int iseq;
	private int postnumber;
	private String url;
	private String seller_email;
	private Date reg_date;
	private String comment;
	private String webSite;
	//-------------------------게시물 추가(post)
	private String context;
	private String content;
	private String rely;
	private String email;

	private List<String> postimages=new ArrayList<String>();

	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public List<String> getPostimages() {
		return postimages;
	}
	public void setPostimages(List<String> postimages) {
		this.postimages = postimages;
	}
	
	public void addimages(String image) {
		postimages.add(image);
	}
	
	public int getIseq() {
		return iseq;
	}
	public void setIseq(int iseq) {
		this.iseq = iseq;
	}
	public int getPostnumber() {
		return postnumber;
	}
	public void setPostnumber(int postnumber) {
		this.postnumber = postnumber;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSeller_email() {
		return seller_email;
	}
	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getRely() {
		return rely;
	}
	public void setRely(String rely) {
		this.rely = rely;
	}
	
		
	
}
