package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import dto.Product;
import dto.Seller;
import dto.Wnotice;

public interface ProductDao {
	//Search products by category
	//void setDataSource(SqlSessionFactory sqlSessionFactory);
	List<Product> selectByCategory(int kind) throws Exception;
	List<Product> selectByBest() throws Exception;
	List<Product> selectByRecommend() throws Exception;
	Product selectOne(int pseq) throws Exception;
	List<String> selectAllImage(int pseq) throws Exception;
	//String selectQuantity(int pseq) throws Exception;
	String checkLikeExist(String email, int pseq);
	List<Wnotice> noticeList() throws Exception;
	List<Wnotice> questionsList() throws Exception;
	Wnotice noticeOne(int wseq) throws Exception;
	void noticeHitPlus(int wseq) throws Exception;
	List<Product> selectProductSearch(String search) throws Exception;

	//========================seller===================
	void SAddProduct(Product product) throws Exception;
	void SAddProductImage(String url,int pseq) throws Exception;
	int selectPseqByAdd() throws Exception;
	List<Product> selectSellProductList(String sseq)throws Exception;
	List<Product> selectReadyProductList(String sseq)throws Exception;
	int SProudctCount(Seller seller) throws Exception;
	void SProudctDelte(int pseq) throws Exception;
	void SProudctImageDelte(int pseq) throws Exception;
	public Product selectProuctupdate(String pseq)throws Exception;
	public void SProductUpdate(Product product) throws Exception;
	public Integer SProudctSaleCount(int pseq) throws Exception;
}
