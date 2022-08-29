package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import dto.Seller;
import dto.SellerFarm;
import dto.SellerInstagram;
import dto.Wnotice;

public interface SellerDao {
	Seller SelectCByEmail(String email) throws Exception;
	Seller SelectCByEamilAndPw(String email, String pw) throws Exception;
	void joinSeller(Seller seller) throws Exception;
	List<Seller> selectList() throws Exception;
	void delete(Seller seller)throws Exception;
	List<Seller> leaveList()throws Exception;
	 public void updateSeller(Seller seller) throws Exception;
	 public SellerInstagram getSellerImageUrl(int sseq) throws Exception;
	//==========인스타 그램================
	 List<SellerInstagram> InstagramPost() throws Exception;
		Seller SelectPost(String email,int postnumber) throws Exception;
		SellerInstagram InstagramProfile(String email) throws Exception;
		List<String> instagramImages(int postnumber) throws Exception;
		public List<Integer> instagramUserPostNumber(String email) throws Exception;
		public void joinInstaSeller(SellerInstagram seller) throws Exception;
		public void UpdateInstaSeller(SellerInstagram seller) throws Exception;
		public void delteSeller(Seller seller) throws Exception;
		public void SfarmAdd(SellerFarm farm) throws Exception;
		public int selectFseqByAdd()throws Exception;
		public void SAddfarmImage(String url,int fseq,String email) throws Exception;
		public List<SellerFarm> Sconfirmfarm(String email)throws Exception;
		public List<String> getfarmImage(int fseq,String email) throws Exception;
		public List<Seller> SellerList()throws Exception;
		public void UpdateInstafarm(int fseq,String experience_time,int experience_price,String experience_date,int experience_number,String experience_context) throws Exception;
		public void SfarmDelte(int fseq) throws Exception;
		public void SfarmImageDelte(int fseq) throws Exception;
		public int selectPostNumberByAdd()throws Exception;
		public void SAddinstaPost(int postnumber,String email,String context) throws Exception;
		public void SAddPostImage(String url, int postnumber,String email) throws Exception;
		public SellerInstagram getpostSelect(String email,int postnumber) throws Exception;
		public void SPostImageDelte(int postnumber) throws Exception;
		public void SPostUpdate(int postnumber,String context) throws Exception;
		public void SinstaPostDelte(int postnumber) throws Exception;
		public void SAddinstaRely(@Param("postnumber")int postnumber,@Param("email")String email,@Param("content")String content) throws Exception;
		public List<SellerInstagram> getPostRely(@Param("postnumber") int postnumber) throws Exception;
		public void SAddfarmRely(@Param("fseq")int fseq,@Param("email")String email,@Param("content")String content) throws Exception;
		public List<SellerFarm> getRramRely(@Param("fseq") int fseq) throws Exception;
		public Integer getPostRelyCount(int postnumber) throws Exception;
		public Integer getRramRelyCount(int fseq) throws Exception;
		public void SPostRelyeDelte(int postnumber) throws Exception;
		List<Wnotice> noticeList() throws Exception;
		public int selectSmallFseqByAdd(String email)throws Exception;
		public String selectContextByAdd(int fseq)throws Exception;
		 public void UpdateSamallDelete(int fseq,String experience_context) throws Exception;
		 public void UpdateimageSamallDelete(int fseq,String email) throws Exception;
}
