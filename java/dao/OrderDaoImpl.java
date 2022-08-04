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
	
	//장바구니에서 주문페이지로 넘어갈 때 = form으로 부터 cseq가 넘어갈 때 
//	public void insertOrderFromCart(String email, ArrayList<Integer> cseqList) {
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		try {
//			sqlSession.getMapper(OrderMapper.class).insertOrder(email);
//			int maxOseq = sqlSession.getMapper(OrderMapper.class).selectMaxOseq();
//			System.out.println("!!!"+maxOseq);
//			
//			
//			for(Integer cseq: cseqList) {
//				Cart cart = sqlSession.getMapper(OrderMapper.class).selectCart(cseq);
//				sqlSession.getMapper(OrderMapper.class).insertOrderDetail(maxOseq, cart.getPseq(), cart.getQuantity());
//				sqlSession.getMapper(OrderMapper.class).updateCartResult(cart.getCseq());	
//			}
//			sqlSession.commit();
//		}finally {
//			sqlSession.close();
//		}
//
//	}
	
	//상품 상세 페이지에서 주문 페이지로 넘어갈 때 = 상품이 딱 한개만 주문 될 때 
	public void insertOrderFromDetail(String email, int pseq, int quantity) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(OrderMapper.class).insertOrder(email);
			int maxOseq = sqlSession.getMapper(OrderMapper.class).selectMaxOseq();
			System.out.println("!!!"+maxOseq);
			sqlSession.getMapper(OrderMapper.class).insertOrderDetail(maxOseq, pseq, quantity);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}

	}
	public void insertOrder(String email, List<Order> orderList, int point) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(OrderMapper.class).insertOrder(email);
			sqlSession.getMapper(OrderMapper.class).updateCustomerPoint(email, point);
			int maxOseq = sqlSession.getMapper(OrderMapper.class).selectMaxOseq();
		
			for(Order order: orderList) {
				sqlSession.getMapper(OrderMapper.class).insertOrderDetail(maxOseq, order.getPseq(), order.getQuantity());
				sqlSession.getMapper(OrderMapper.class).updateCartResult(email, order.getPseq(), order.getQuantity());	
			}
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}

	}
	
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
	
	
//	public String selectImage(int pseq) throws Exception {
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		try {
//			List<String> images =  sqlSession.getMapper(ProductMapper.class).getImages(pseq);	
//			return images.get(0);
//		}finally{
//			sqlSession.close();
//		}
//	}
	
}
