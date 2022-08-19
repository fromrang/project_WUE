package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Seller;
import dto.SellerFarm;
import dto.SellerInstagram;
import dto.Wnotice;

public interface SellerMapper {
	@Select("select * from seller where email=#{email}")
	public Seller getLoginInfo(@Param("email")String email) throws Exception;
	
	//sseq로 셀러 정보 가지고 오기 -> 상품 디테일페이지에서 셀러 이미지 가지고올 때 사용합니다!(여랑)
	@Select("select * from seller where sseq=#{sseq}")
	public Seller getSellerInfoforWithSseq(@Param("sseq")int sseq);
	
	@Select("select * from seller where email=#{email} and pw=#{pw}")
	public Seller getMember(@Param("email")String email, @Param("pw")String pw) throws Exception;

	
	@Insert("Insert into seller (name,email,pw,phone) values (#{name},#{email},#{pw},#{phone})")
	public void joinSeller(Seller seller) throws Exception;
	 
	@Select("select * from seller")
		public List<Seller> getSellerAll()throws Exception;
		
	@Delete("delete from seller where email=#{email}")
		public void getDelete(Seller seller)throws Exception;
		
	@Select("select * from seller where status=1")
		public List<Seller> getLeave()throws Exception;
	
	 
	 @Update("update seller set name=#{name},pw=#{pw},phone=#{phone},zip_num=#{zip_num},address=#{address} where email=#{email}")
	 public void updateSeller(Seller seller) throws Exception;
	 
	 //탈퇴한 유저의 상태를 1로 변경(최종 탈퇴는 관리자가 진행)
	 @Update("update seller set status=1 where email=#{email}")
	 public void delteSeller(Seller seller) throws Exception;
	//======================인스타 그램=============-------------
	 @Select("select * from seller_instagram where seller_email=#{email}")
		public SellerInstagram getFrofile(@Param("email")String email) throws Exception;
		
		// 사진 정보 불러오는 select (postnumber로 검색)
		@Select("select url from instagram_postimage where postnumber=#{postnumber}")
		public List<String> getPostImage(@Param("postnumber")int postnumber) throws Exception;
		
		// 해당 유저의 post정보를 불러오는 select ->개인 프로필에서 본인 게시물 사진뿌리는 용도
		@Select("select postnumber from instagram_post where email=#{email} ")
		public List<Integer> getUserPostNumber(@Param("email")String email) throws Exception;
		
		//전 유저의 post정보를 불러오는 select
		@Select("select * from instagram_post ORDER BY reg_date DESC")
		public List<SellerInstagram> getPostAllList() throws Exception;
		
		
		//해당유저의 게시물을 보이게 하기 위한 select
		@Select("select * from instagram_post where email=#{email} and postnumber=#{postnumber}")
		public SellerInstagram getpostSelect(@Param("email")String email,@Param("postnumber")int postnumber) throws Exception;
		
		//회원가입시 인스타 계정 자동 가입 
		@Insert("Insert into seller_instagram (seller_email) values (#{seller_email})")
		 public void joinInstaSeller(SellerInstagram seller) throws Exception;
		
		//인스타그램 본인 프로필 업데이트
		@Update("update seller_instagram set webSite=#{webSite},url=#{url},comment=#{comment} where seller_email=#{seller_email}")
		 public void UpdateInstaSeller(SellerInstagram seller) throws Exception;
		
		//주말농장 게시판 등록 
		@Insert("insert into farm (email,experience_date,experience_time,experience_price,experience_number,experience_context) values(#{email},#{experience_date},#{experience_time},#{experience_price},#{experience_number},#{experience_context})")
		public void SfarmAdd(SellerFarm farm) throws Exception;
		
		@Select("SELECT fseq FROM farm order by fseq desc limit 1")
		public int selectFseqByAdd()throws Exception;
		
		//주말농장 사진 등록
		@Insert("insert into farm_postimage(url,fseq,email) values(#{url},#{fseq},#{email})")
		public void SAddfarmImage(@Param("url") String url,@Param("fseq") int fseq,@Param("email") String email) throws Exception;
		
		//주말농장 등록 여부 확인 
		@Select("SELECT * FROM farm where email=#{email}")
		public List<SellerFarm> Sconfirmfarm(@Param("email")String email)throws Exception;
		
		//전체 셀러 리스트
		@Select("SELECT email FROM farm group by email")
		public List<Seller> SellerList()throws Exception;
		
		//주말농장 사진 가져오는 메소드
		@Select("select url from farm_postimage where email=#{email} or fseq=#{fseq}")
		public List<String> getfarmImage(@Param("fseq")int fseq,@Param("email")String email) throws Exception;
		
		//주말농장 내용변경(but,사진변경은 아직 불가능)
		@Update("update farm set experience_date=#{experience_date},experience_time=#{experience_time},experience_price=#{experience_price},experience_number=#{experience_number},experience_context=#{experience_context} where fseq=#{fseq}")
		 public void UpdateInstafarm(@Param("fseq")int fseq,@Param("experience_time")String experience_time,@Param("experience_price")int experience_price
		,@Param("experience_date")String experience_date,@Param("experience_number")int experience_number,@Param("experience_context")String experience_context) throws Exception;

		//예약 시간 삭제
		@Delete("delete from farm where fseq=#{fseq}")
		public void SfarmDelte(@Param("fseq")int fseq) throws Exception;
		
		//농장 사진 삭제
		@Delete("delete from farm_postimage where fseq=#{fseq}")
		public void SfarmImageDelte(@Param("fseq")int fseq) throws Exception;
		
		//인스타 게시물 번호 조회(가장 높은것)
		@Select("SELECT postnumber FROM instagram_post order by postnumber desc limit 1")
		public int selectPostNumberByAdd()throws Exception;
		
		//인스타그램 게시물 추가
		@Insert("insert into instagram_post (email,context,postnumber) values(#{email},#{context},#{postnumber})")
		public void SAddinstaPost(@Param("postnumber")int postnumber,@Param("email")String email,@Param("context")String context) throws Exception;
		
		//인스타그램 게시물 사진 추가
		@Insert("insert into instagram_postimage(url,postnumber,email) values(#{url},#{postnumber},#{email})")
		public void SAddPostImage(@Param("url") String url,@Param("postnumber") int postnumber,@Param("email") String email) throws Exception;
		
		//인스타그램 게시물 변경 
		@Update("update instagram_post set context=#{context} where postnumber=#{postnumber}")
		 public void SPostUpdate(@Param("postnumber")int postnumber,@Param("context")String context) throws Exception;
		
		//인스타 게시물 삭제
		@Delete("delete from instagram_postimage where postnumber=#{postnumber}")
		public void SPostImageDelte(@Param("postnumber") int postnumber) throws Exception;
		//인스타 게시물 사진 삭제
		@Delete("delete from instagram_post where postnumber=#{postnumber}")
		public void SinstaPostDelte(@Param("postnumber") int postnumber) throws Exception;
		
		@Insert("insert into instagram_reply (email,content,postnumber) values(#{email},#{content},#{postnumber})")
		public void SAddinstaRely(@Param("postnumber")int postnumber,@Param("email")String email,@Param("content")String content) throws Exception;

		@Select("select * from instagram_reply where postnumber=#{postnumber}")
		public List<SellerInstagram> getPostRely(@Param("postnumber") int postnumber) throws Exception;
		
		@Select("select count(email) from instagram_reply where postnumber=#{postnumber}")
		public Integer getPostRelyCount(@Param("postnumber") int postnumber) throws Exception;
		
		@Insert("insert into farm_reply (email,content,fseq) values(#{email},#{content},#{fseq})")
		public void SAddfarmRely(@Param("fseq")int fseq,@Param("email")String email,@Param("content")String content) throws Exception;

		@Select("select * from farm_reply where fseq=#{fseq}")
		public List<SellerFarm> getRramRely(@Param("fseq") int fseq) throws Exception;
		
		@Select("select count(email) from farm_reply where fseq=#{fseq}")
		public Integer getRramRelyCount(@Param("fseq") int fseq) throws Exception;
		
		@Delete("delete from instagram_reply where postnumber=#{postnumber}")
		public void SPostRelyeDelte(@Param("postnumber") int postnumber) throws Exception;

		@Select("select * from wnotice where type=2")
		public List<Wnotice> getNoticeAll() throws Exception;
		
		
}
