package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Like;
import dto.Product;
import dto.Seller;
import dto.Wnotice;
import mapper.LikeMapper;
import mapper.ProductMapper;
import mapper.WorkerMapper;
@Repository
public class ProductDaoImpl implements ProductDao{
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
//	@Override
//	public void setDataSource(SqlSessionFactory sqlSessionFactory) {
//		this.sqlSessionFactory = sqlSessionFactory;
//	}
	@Override
	public List<Product> selectByCategory(int kind) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			return sqlSession.getMapper(ProductMapper.class).getProductList(kind);
			//return mapper.getLoginInfo(email);
		}finally {
			sqlSession.close();
		}

	}

	@Override
	public List<Product> selectByBest() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(ProductMapper.class).getBestProductList();			
		}finally{
			sqlSession.close();
		}
	}
	//추천 상품 가지고 오기
	@Override
	public List<Product> selectByRecommend() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(ProductMapper.class).getRecommendProductList();			
		}finally{
			sqlSession.close();
		}
	}
	@Override
	public Product selectOne(int pseq) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(ProductMapper.class).getProduct(pseq);			
		}finally{
			sqlSession.close();
		}
	}
	@Override
	public List<String> selectAllImage(int pseq) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(ProductMapper.class).getImages(pseq);			
		}finally{
			sqlSession.close();
		}
	}
//	@Override
//	public String selectQuantity(int pseq) throws Exception {
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		try {
//			return sqlSession.getMapper(ProductMapper.class).getQuantity(pseq);			
//		}finally{
//			sqlSession.close();
//		}
//	}
	
	//좋아요 판별 버튼 비활성화시 사용
	public String checkLikeExist(String email, int pseq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Like like = sqlSession.getMapper(LikeMapper.class).checkLikeProduct(email, pseq);
			if(like == null) {
				return "notExist"; //false는 db에 값이 존재하지 않음 채워진 하트 
			}else {
				return "Exist"; // true는 db에 값 존재 비워진 하트
			}
		}finally {
			sqlSession.close();
		}
	}



	//공지사항 가지고 오기
	public List<Wnotice> noticeList() throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).selctNotice();

		}finally {
			sqlSession.close();
		}
	}
	
	//자주하는 질문 가지고 오기
	public List<Wnotice> questionsList() throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).selectQuestions();

		}finally {
			sqlSession.close();
		}
	}
	
	//공지사항, 자주하는 질문 detail 가지고 오기
	public Wnotice noticeOne(int wseq) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).selectNoticeDetail(wseq);

		}finally {
			sqlSession.close();
		}
	}
	
	//hit 1 증가 시키기
	public void noticeHitPlus(int wseq) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(WorkerMapper.class).hitsPlusOne(wseq);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
	//검색
	@Override
	public List<Product> selectProductSearch(String search) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<Product> products = new ArrayList<Product>();
			for(Product product:sqlSession.getMapper(ProductMapper.class).SelectSearchProduct(search)) {
				List<String> images =  sqlSession.getMapper(ProductMapper.class).getImages(product.getPseq());
				product.setUrl(images.get(0));
				products.add(product);
			}
			return products;
		}finally {
			sqlSession.close();
		}
	}

	//=======================seller===============================
	//===========================================================
		//상품을 추가하기위한 메소드
		@Override
		public void SAddProduct(Product product) throws Exception {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
			    sqlSession.getMapper(ProductMapper.class).SaddProduct(product);
				sqlSession.commit();
			}finally{
				sqlSession.close();
			}
		}
			
		//상품을 이미지를 등록하는 메소드
		@Override
		public void SAddProductImage(String url, int pseq) throws Exception {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
			    sqlSession.getMapper(ProductMapper.class).SAddProductImage(url, pseq);
				sqlSession.commit();
			}finally{
				sqlSession.close();
			}
			
		}
		
		//상품을 등록할때 이미지에 같은 pseq를 등록하기 위해 가장 높은 숫자를 찾는 메소드
		@Override
		public int selectPseqByAdd() throws Exception {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				
			   return sqlSession.getMapper(ProductMapper.class).selectPseqByAdd();
			}finally{
				sqlSession.close();
			}
		}
		
		//준비중인 상품리스트를 뿌리기 위한 메소드(수량 1개 이상)
		@Override
		public List<Product> selectSellProductList(String sseq) throws Exception {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
			   return sqlSession.getMapper(ProductMapper.class).selectSellProductList(sseq);
			}finally{
				sqlSession.close();
			}
		}
		
		//준비중인 상품리스트를 뿌리기 위한 메소드(수량 0개)
		@Override
		public List<Product> selectReadyProductList(String sseq) throws Exception {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
			   return sqlSession.getMapper(ProductMapper.class).selectReadyProductList(sseq);
			}finally{
				sqlSession.close();
			}
		}
		
		//인스타 프로필에서 현재취급하고 있는 상품종류가 몇가지인지 파악하기 위한 메소드
		@Override
		public int SProudctCount(Seller seller) throws Exception {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
			   return sqlSession.getMapper(ProductMapper.class).SProudctCount(seller);
			}finally{
				sqlSession.close();
			}
		}
		
		//상품명을 이용하여 디비에서 상품을 삭제하는 메소드
		@Override
		public void SProudctDelte(int pseq) throws Exception {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
			    sqlSession.getMapper(ProductMapper.class).SProudctDelte(pseq);
				sqlSession.commit();
			}finally{
				sqlSession.close();
			}
			
		}
		//spimage 디비에서 사진을 삭제하는 메소드 (전체 삭제)
		@Override
		public void SProudctImageDelte(int pseq) throws Exception {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
			    sqlSession.getMapper(ProductMapper.class).SProudctImageDelte(pseq);
				sqlSession.commit();
			}finally{
				sqlSession.close();
			}
			
		}
		//상품 업데이트를 위한 자료를 선택
		@Override
		public Product selectProuctupdate(String pseq) throws Exception {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
			   return sqlSession.getMapper(ProductMapper.class).selectupdate(pseq);
			}finally{
				sqlSession.close();
			}
		}
		//상품 정보 업데이트 
		@Override
		public void SProductUpdate(Product product) throws Exception {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
			    sqlSession.getMapper(ProductMapper.class).SProductUpdate(product);
				sqlSession.commit();
			}finally{
				sqlSession.close();
			}
					
		}
		
		@Override
		public Integer SProudctSaleCount(int pseq) throws Exception{
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
			   return sqlSession.getMapper(ProductMapper.class).SProudctSaleCount(pseq);
			}finally{
				sqlSession.close();
			}
		}


}
