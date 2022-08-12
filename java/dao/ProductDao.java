package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import dto.Product;

public interface ProductDao {
	//Search products by category
	void setDataSource(SqlSessionFactory sqlSessionFactory);
	List<Product> selectByCategory(int kind) throws Exception;
	List<Product> selectByBest() throws Exception;
	Product selectOne(int pseq) throws Exception;
	List<String> selectAllImage(int pseq) throws Exception;
	//String selectQuantity(int pseq) throws Exception;
	String checkLikeExist(String email, int pseq);
	
}
