package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.Cart;
import mapper.CartMapper;

public class CartDaoImpl {
	private SqlSessionFactory sqlSessionFactory;
	
	public void setDataSource(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public void insetCart(String email, int pseq, int quantity) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Cart cart = sqlSession.getMapper(CartMapper.class).selectCartOne(email, pseq);
			if(cart == null) {
				int a = sqlSession.getMapper(CartMapper.class).insertCart(email, pseq, quantity);
				sqlSession.commit();
			}else {
				int newQuantity = cart.getQuantity() + quantity;
				sqlSession.getMapper(CartMapper.class).updateCart(email, pseq, newQuantity);
				sqlSession.commit();
			}
		}finally {
			sqlSession.close();
		}
	}
	
	public List<Cart> selectCartList(String email){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(CartMapper.class).selectCartList(email);
		}finally {			
			sqlSession.close();
		}
	}
	
	public void delectCart(int cseq, String email) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(CartMapper.class).deleteDept(email, cseq);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
	
	public List<String> selectAllImage(int pseq) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(CartMapper.class).getImages(pseq);			
		}finally{
			sqlSession.close();
		}
	}

}
