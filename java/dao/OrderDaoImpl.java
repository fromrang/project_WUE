package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Cart;
import dto.Customer;
import dto.Like;
import dto.Order;
import dto.Product;
import mapper.LikeMapper;
import mapper.OrderMapper;
import mapper.ProductMapper;
@Repository
public class OrderDaoImpl {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
//	public void setDataSource(SqlSessionFactory sqlSessionFactory) {
//		this.sqlSessionFactory = sqlSessionFactory;
//	}
	
	//사용자 point 가지고 오는 메소드
	public int selectPoint(String email) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(OrderMapper.class).selectPoint(email);
			
		}finally {
			sqlSession.close();
		}
	}
	//사용자 데이터 가지고 오는 메소드
	public Customer selectCustomer(String email) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(OrderMapper.class).selectCustomer(email);
			
		}finally {
			sqlSession.close();
		}
	}
	
	
	//주문할때 사용하는 메소드
	public void insertOrder(String email, List<Order> orderList, int revisePoint, int amount, int usedPoint) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(OrderMapper.class).insertOrder(email, amount, usedPoint);
			sqlSession.getMapper(OrderMapper.class).updateCustomerPoint(email, revisePoint);
			int maxOseq = sqlSession.getMapper(OrderMapper.class).selectMaxOseq();
		
			for(Order order: orderList) {
				sqlSession.getMapper(OrderMapper.class).insertOrderDetail(maxOseq, order.getPseq(), order.getQuantity(), order.getPayment());
				//sqlSession.getMapper(OrderMapper.class).updateCartResult(email, order.getPseq(), order.getQuantity());
				sqlSession.getMapper(OrderMapper.class).updateCartResult(email, order.getPseq(), order.getQuantity());	
				sqlSession.getMapper(OrderMapper.class).deleteCart(email, order.getPseq(), order.getQuantity());
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
	
	//좋아요 판별 버튼 비활성화시 사용
	public boolean checkLikeExist(String email, int pseq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Like like = sqlSession.getMapper(LikeMapper.class).checkLikeProduct(email, pseq);
			if(like == null) {
				return false; //false는 db에 값이 존재하지 않음 채워진 하트 
			}else {
				return true; // true는 db에 값 존재 비워진 하트
			}
		}finally {
			sqlSession.close();
		}
	}
	
	//주문/배송 조회
	public List<Order> selectOrderRecent(String email) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<Order> orders = new ArrayList<Order>();
			for(Order order:sqlSession.getMapper(OrderMapper.class).selectRecentorder(email)) {
				List<String> images =  sqlSession.getMapper(ProductMapper.class).getImages(order.getPseq());
				order.setUrl(images.get(0));
				orders.add(order);
			}
			return orders;
		}finally {
			sqlSession.close();
		}
		
	}
	
	//주문 취소하기
	public void deleteOrder(int odseq, int oseq, int payment, String email) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			Order order = sqlSession.getMapper(OrderMapper.class).selectOrderDetailByOdseq(odseq);
			
			sqlSession.getMapper(OrderMapper.class).deleteOrderDetail(odseq);
			sqlSession.getMapper(OrderMapper.class).updateAmount(oseq, payment);
			//orders amount가 0이면 전부 취소했다는 의미 전체 취소는 포인트를 돌려준다.
			if(sqlSession.getMapper(OrderMapper.class).selectCancelPoint() != null) {
				int used_point = Integer.parseInt(sqlSession.getMapper(OrderMapper.class).selectCancelPoint());
				sqlSession.getMapper(OrderMapper.class).reUpdatePoint(used_point, email);
			}
			//orders amount = 0이면 삭제하기
			sqlSession.getMapper(OrderMapper.class).deleteOrders();
			
			//취소한 상품 수량 만큼 수량 추가해주기
			sqlSession.getMapper(OrderMapper.class).updateProductQua(order.getPseq(), order.getQuantity());
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
	
	//구매내역 보기
	public List<Order> selectOrderList(String email) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			
			List<Order> orders = new ArrayList<Order>();
			for(Order order:sqlSession.getMapper(OrderMapper.class).selectOrderList(email)) {
				List<String> images =  sqlSession.getMapper(ProductMapper.class).getImages(order.getPseq());
				order.setUrl(images.get(0));
				orders.add(order);
			}
			return orders;

		}finally {
			sqlSession.close();
		}
	}
	
	//주문 코드로 주문 내역 가지고 오기
	public Order selectOrderView(int odseq) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			Order order = sqlSession.getMapper(OrderMapper.class).selectOrderView(odseq);
			List<String> images =  sqlSession.getMapper(ProductMapper.class).getImages(order.getPseq());
			order.setUrl(images.get(0));
			
			return order;

		}finally {
			sqlSession.close();
		}
	}
	
	//등급별로 포인트 적립하기
	public void UpdateCustomerPoint(String email, int point){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			sqlSession.getMapper(OrderMapper.class).updatePoint(point, email);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
	
	//==================== seller=============================
	//seller에서 customer가 주문한 상품을 가져오는 메소드(주문번호 group by 적용 x=디테일)
	public List<Order> SOrderList(int sseq,int oseq) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return	sqlSession.getMapper(OrderMapper.class).SOrderList(sseq,oseq);
		}finally{
			sqlSession.close();
		}
	}
	
	// 주문내역에 관한 총합계 
	public int SOrderListCount(int sseq,int oseq) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return	sqlSession.getMapper(OrderMapper.class).SOrderListCount(sseq,oseq);
		}finally{
			sqlSession.close();
		}
		
	}
	
	
	
	//주문 번호별 총 합산 금액을 가져오는 메소드
	public List<Integer> STotalPrice(int sseq) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return	sqlSession.getMapper(OrderMapper.class).STotalPrice(sseq);
		}finally{
			sqlSession.close();
		}
		
	}
	
	//주문 번호별 상품 내역을 가져오는 메소드 (주문번호 group by)
	public List<Order> SOseqOrderList(int sseq) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return	sqlSession.getMapper(OrderMapper.class).SOseqOrderList(sseq);
		}finally{
			sqlSession.close();
		}
		
	}
	
	//주문 번호별 총 갯수를 가져오는 메소드 (주문번호 group by)
	public List<Integer> SOseqCountList(int sseq) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return	sqlSession.getMapper(OrderMapper.class).SOseqCountList(sseq);
		}finally{
			sqlSession.close();
		}
		
	}
	
	//주문 번호별 총 합산 금액을 가져오는 메소드 (주문상태 적용)
	public List<Integer> STotalPrice2(int sseq,int result) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return	sqlSession.getMapper(OrderMapper.class).STotalPrice2(sseq,result);
		}finally{
			sqlSession.close();
		}
		
	}
	
	//주문 번호별 상품 내역을 가져오는 메소드 (주문번호 group by) (주문상태 적용)
	public List<Order> SOseqOrderList2(int sseq,int result) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return	sqlSession.getMapper(OrderMapper.class).SOseqOrderList2(sseq,result);
		}finally{
			sqlSession.close();
		}
		
	}
	
	//주문 번호별 총 갯수를 가져오는 메소드 (주문번호 group by) (주문상태 적용)
	public List<Integer> SOseqCountList2(int sseq,int result) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return	sqlSession.getMapper(OrderMapper.class).SOseqCountList2(sseq,result);
		}finally{
			sqlSession.close();
		}
		
	}
	
	//배송상태 변경
	public void SOrderResultUpdate(int sseq,int oseq, int result) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(OrderMapper.class).SOrderResultUpdate(sseq, oseq, result);
			sqlSession.commit();
		}finally{
			sqlSession.close();
		}
	}
	
	//==============================================================
	public List<Order> WOrderList() throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return	sqlSession.getMapper(OrderMapper.class).WAllOrderList();
		}finally{
			sqlSession.close();
		}
		
	}
		
}
