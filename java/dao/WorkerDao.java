package dao;

import java.util.List;

import dto.Sales;
import dto.SellerSales;
import dto.Wnotice;
import dto.Worker;

public interface WorkerDao {
	Worker SelectById(String id) throws Exception;
	Worker exist(String id, String pw) throws Exception;
	List<Worker> selectList() throws Exception;
	int insert(Worker worker) throws Exception;
	Worker selectOne(String id) throws Exception;
	int update(Worker worker)throws Exception;
	Worker idCheck(String id) throws Exception;
	int insert(Wnotice notice) throws Exception;
	//게시판
	List<Wnotice> noticeList() throws Exception;
	List<Wnotice> qnaList() throws Exception;
	int update(Wnotice notice)throws Exception;	
	Wnotice select(int wseq) throws Exception;
	void delete(int notice)throws Exception;
	//customer 매출관리
	List<Sales> selectSales() throws Exception;
	List<Integer> sales() throws Exception;
	List<Integer> dateSales() throws Exception;
	// seller 수수료 관리
	List<SellerSales> Seller1Sales() throws Exception;
	List<SellerSales> Seller2Sales() throws Exception;
	List<SellerSales> Seller3Sales() throws Exception;
	List<SellerSales> Seller4Sales() throws Exception;
	List<SellerSales> Seller5Sales() throws Exception;
	List<SellerSales> Seller6Sales() throws Exception;
	// best product list
	List<Integer> rank()throws Exception;
	int best(int pseq) throws Exception;
	List<Integer> bestList() throws Exception;
	int deleteBest(int pseq) throws Exception;
	// recommend product list
	List<Integer> likeRank()throws Exception;
	int recommend(int pseq) throws Exception;
	List<Integer> recommendList() throws Exception;
	int deleteRecommend(int pseq) throws Exception;
}
