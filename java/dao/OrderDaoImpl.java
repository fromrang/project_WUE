package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.Cart;
import dto.Order;
import dto.Product;
import mapper.OrderMapper;
import mapper.ProductMapper;

public class OrderDaoImpl {
	private SqlSessionFactory sqlSessionFactory;
	
	public void setDataSource(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	//사용자 point 가지고 오는 메소드
	public int selectPoint(String email) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(OrderMapper.class).selectPoint(email);
			
		}finally {
			sqlSession.close();
		}
	}
	
	
	//주문할때 사용하는 메소드
	public void insertOrder(String email, List<Order> orderList, int point, int amount) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(OrderMapper.class).insertOrder(email, amount);
			sqlSession.getMapper(OrderMapper.class).updateCustomerPoint(email, point);
			int maxOseq = sqlSession.getMapper(OrderMapper.class).selectMaxOseq();
		
			for(Order order: orderList) {
				sqlSession.getMapper(OrderMapper.class).insertOrderDetail(maxOseq, order.getPseq(), order.getQuantity());
				sqlSession.getMapper(OrderMapper.class).updateCartResult(email, order.getPseq(), order.getQuantity());	
				sqlSession.getMapper(OrderMapper.class).updateQuantity(order.getPseq(), order.getQuantity());
			}
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}

	}
	
	//상품 코드로 상품 정보 가지고 오고 첫번째 이미지도 같이 가지고 오는 메소드
	public Product selectOne(int pseq) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<String> images =  sqlSession.getMapper(ProductMapper.class).getImages(pseq);
			Product product = sqlSession.getMapper(ProductMapper.class).getProduct(pseq);
			product.setUrl(images.get(0));
			return product;			
		}finally{
			sqlSession.close();
		}
	}
	
	//장바구니 코드로 장바구니 정보 가지고 오고 첫번째 이미지도 같이 가지고 오는 코드
	public List<Cart> selectCartList(List<Integer> cseqList) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<Cart> carts = new ArrayList<Cart>();
			for(int cseq:cseqList) {
				Cart cart = sqlSession.getMapper(OrderMapper.class).selectCart(cseq);
				List<String> images =  sqlSession.getMapper(ProductMapper.class).getImages(cart.getPseq());
				cart.setUrl(images.get(0));
				carts.add(cart);
			}
			return carts;	
		}finally{
			sqlSession.close();
		}
	}
	
	public void updateCustomerAddress(String email, String postcode, String address) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(OrderMapper.class).updateAddress(postcode, address, email);
			sqlSession.commit();
		}finally{
			sqlSession.close();
		}
	}
	
}
