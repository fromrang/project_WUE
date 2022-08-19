package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.Order;
import dto.Product;
import dto.Review;
import mapper.OrderMapper;
import mapper.ProductMapper;

public class ReviewDaoImpl {
private SqlSessionFactory sqlSessionFactory;
	
	public void setDataSource(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	//후기 작성 가능한 상품 리스트 리턴
	public List<Order> canBeReview(String email) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			//주문테이블 중 배송이 완료된 3번 중에 사용자가 구매한 상품 가지고 오기
			List<Order> orders =  sqlSession.getMapper(OrderMapper.class).selectOrderList(email);
			List<Order> reviewsWrite = new ArrayList<Order>();
			for(Order order:orders) {
				 Review review = sqlSession.getMapper(OrderMapper.class).selectReviewByOdseq(order.getOdseq());
				 if(review == null) {
					 List<String> images =  sqlSession.getMapper(ProductMapper.class).getImages(order.getPseq());
					 order.setUrl(images.get(0));
					 //후기 작성 가능한 상품 가져오기
					 reviewsWrite.add(order);					 
				 }
			}
			return reviewsWrite;
		}finally {
			sqlSession.close();
		}
	}
	
	//이미 작성한 후기 가지고 오기
	public List<Review> writedReviewes(String email){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(OrderMapper.class).selectReviewAllbyEmail(email);
		}finally {
			sqlSession.close();
		}
	}
	
	//후기 작성하기
	//1. 주문 코드로 주문 정보가지고 오기 
	public Order selectOrderInfo(int odseq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(OrderMapper.class).selectOrderView(odseq);
		}finally {
			sqlSession.close();
		}
	}
	
	//2.리뷰 넣기
	public void insertReview(Review review, List<String> urls) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(OrderMapper.class).insertReviewContent(review);
			int maxRseq = sqlSession.getMapper(OrderMapper.class).selectMaxRseq();
			for(String url:urls) {
				sqlSession.getMapper(OrderMapper.class).insertReviewImage(url, maxRseq);
			}
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
	
	//상품별 후기 가지고 오기
	public List<Review> selectReviewPseq(int pseq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<Review> reviews = new ArrayList<Review>();
			for(Review review : sqlSession.getMapper(OrderMapper.class).selectReviewAllbyPseq(pseq)) {
				review.setImg_url(sqlSession.getMapper(OrderMapper.class).selectReviewImage(review.getRseq()));
				reviews.add(review);
			}
			return reviews;
		}finally {
			sqlSession.close();
		}
	}
}
