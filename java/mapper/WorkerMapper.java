package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Sales;
import dto.SellerSales;
import dto.Wnotice;
import dto.Worker;

public interface WorkerMapper {
	@Select("select * from worker where id=#{id}")
	public Worker getLoginInfo(@Param("id") String id) throws Exception;

	@Select("select * from worker where id=#{id} and pw=#{pw}")
	public Worker getWorker(@Param("id") String id, @Param("pw") String pw) throws Exception;

	@Select("select * from worker")
	public List<Worker> getWorkerAll() throws Exception;

	@Insert("insert into worker(name, id, pw, email, phone) value(#{name}, #{id}, #{pw}, #{email}, #{phone})")
	public int getAdd(Worker worker) throws Exception;

	@Select("select id from worker where id=#{id}")
	public Worker getIdCheck(@Param("id") String id) throws Exception;

	@Select("select * from worker where id=#{id}")
	public Worker getSelect(@Param("id") String id) throws Exception;

	@Update("update worker set pw=#{pw}, phone=#{phone} where id=#{id}")
	public int getUpdate(Worker worker) throws Exception;

	// 게시판 등록
	@Insert("insert into wnotice(name,type,title,detail) value(#{name},#{type},#{title},#{detail})")
	public int getNoticeAdd(Wnotice notice) throws Exception;

	// 게시판 공지사항 list
	@Select("select * from wnotice where type != 1")
	public List<Wnotice> getNoticeAll() throws Exception;

	// 게시판 QnA list
	@Select("select * from wnotice where type=1")
	public List<Wnotice> getQnaAll() throws Exception;

	// 게시물 delete
	@Delete("delete from wnotice where wseq=#{wseq}")
	public void getBoardDelete(@Param("wseq") int wseq) throws Exception;

	// 게시물 update
	@Update("update wnotice set title=#{title}, detail=#{detail} where wseq=#{wseq}")
	public int getBoardUpdate(Wnotice notice) throws Exception;

	// 게시물 select
	@Select("select * from wnotice where wseq=#{wseq}")
	public Wnotice getBoardAll(@Param("wseq") int wseq) throws Exception;

	// 사용자 매출 관리 리스트
	@Select("select order_view.name,order_view.email,order_view.payment, customer.grade,customer.reg_date from order_view left join customer on order_view.email = customer.email group by name")
	public List<Sales> getCustomerSale() throws Exception;

	// 사용자별 총매출
	@Select("select sum(payment) from order_view group by name")
	public List<Integer> getSales() throws Exception;

	// 사용자 날짜별 총 매출
	@Select("SELECT DATE_FORMAT(reg_date, '%Y%m%d') AS date, sum(payment) AS cnt FROM order_view GROUP BY DATE_FORMAT(reg_date, '%Y%m%d') ORDER BY date DESC")
	public List<Integer> getDateSales() throws Exception;

	// 공지사항 가지고 오기
	@Select("select * from wnotice where type=0")
	public List<Wnotice> selctNotice() throws Exception;

	// 자주하는 질문 가지고 오기
	@Select("select * from wnotice where type=1")
	public List<Wnotice> selectQuestions() throws Exception;

	// 공지사항, 자주하는 질문 한개만 가지고 오기
	@Select("select * from wnotice where wseq=#{wseq}")
	public Wnotice selectNoticeDetail(@Param("wseq") int wseq) throws Exception;

	// 공지사항 hits plus 1 시키기
	@Update("update wnotice set hits=hits+1 where wseq=#{wseq}")
	public void hitsPlusOne(@Param("wseq") int wseq) throws Exception;

	// 주문량이 많은 상품 ranking top 8 받아오기
	// @Select("select * from (select pseq,pname,payment,sum(quantity),dense_rank()
	// over (order by sum(quantity) desc)as ranking from order_view GROUP BY pseq)
	// ranked where ranked.ranking<8")
	@Select("select pseq, count(pseq) from order_view group by pseq order by count(pseq) desc limit 8")
	public List<Integer> getRank() throws Exception;

	// ranking top 상품 best 상태 y로 변경
	@Update("update product set best='y'where pseq=#{pseq}")
	public int getBest(@Param("pseq") int pseq) throws Exception;

	// best_p_view List 받아오기
	@Select("select * from best_p_view")
	public List<Integer> getBestList() throws Exception;

	// ranking top 상품이 아닌것은 best 상태 n으로 변경
	@Update("update product set best='n'where pseq=#{pseq}")
	public int getDeleteBest(@Param("pseq") int pseq) throws Exception;

	// 좋아요가 많은 상품 ranking top 6 list 받아오기
	// @Select("select * from (select pseq,count(*),dense_rank() over (order by
	// count(*) desc)as ranking from like_view GROUP BY pseq) ranked where
	// ranked.ranking<6")
	@Select("select pseq, count(pseq) from like_view group by pseq order by count(pseq) desc limit 6;")
	public List<Integer> getLikeRank() throws Exception;

	// ranking top 상품 recommend 상태 y로 변경
	@Update("update product set recommend='y'where pseq=#{pseq}")
	public int getRecommend(@Param("pseq") int pseq) throws Exception;

	// recommend_p_view List 받아오기
	@Select("select * from recommend_p_view")
	public List<Integer> getRecommendList() throws Exception;

	// ranking top 상품이 아닌것은 recommend 상태 n으로 변경
	@Update("update product set recommend='n'where pseq=#{pseq}")
	public int getDeleteRecommend(@Param("pseq") int pseq) throws Exception;

	// sale y->n 설정
	@Update("update product set sale='n' where pseq=#{pseq}")
	public int getDiscount(@Param("pseq") int pseq) throws Exception;

	// sale n->y 설정
	@Update("update product set sale='y' where pseq=#{pseq}")
	public int getNotDiscount(@Param("pseq") int pseq) throws Exception;

	// 1번 셀러 수수료 관리 리스트
	@Select("select * from seller1")
	public List<SellerSales> getSeller1Sale() throws Exception;

	// 2번 셀러 수수료 관리 리스트
	@Select("select * from seller2")
	public List<SellerSales> getSeller2Sale() throws Exception;

	// 3번 셀러 수수료 관리 리스트
	@Select("select * from seller3")
	public List<SellerSales> getSeller3Sale() throws Exception;

	// 4번 셀러 수수료 관리 리스트
	@Select("select * from seller4")
	public List<SellerSales> getSeller4Sale() throws Exception;

	// 5번 셀러 수수료 관리 리스트
	@Select("select * from seller5")
	public List<SellerSales> getSeller5Sale() throws Exception;

	// 6번 셀러 수수료 관리 리스트
	@Select("select * from seller6")
	public List<SellerSales> getSeller6Sale() throws Exception;
}
