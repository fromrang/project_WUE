package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.Like;
import mapper.LikeMapper;
import mapper.ProductMapper;

public class LikeDaoImpl {
	private SqlSessionFactory sqlSessionFactory;
	
	public void setDataSource(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	//좋아요 등록하기
	public void insertLikeTable(String email, int pseq){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Like like = sqlSession.getMapper(LikeMapper.class).checkLikeProductAll(email, pseq);
			if(like == null) { //좋아요 테이블에 값이 없을때 인서트
				sqlSession.getMapper(LikeMapper.class).insertLike(email, pseq);
				sqlSession.commit();
			
			}else { //좋아요 테이블이 값이 있을 때 n으로 변경해서 view로 들어갈 수 있도록 변경
				sqlSession.getMapper(LikeMapper.class).changeLike(email, pseq);
				sqlSession.commit();
			}
		}finally {
			sqlSession.close();
		}
	}
	
	//좋아요 상품 리스트 보기 이미지도 같이
	public List<Like> selectLikeAll(String email) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<Like> likes = new ArrayList<Like>();
			for(Like like: sqlSession.getMapper(LikeMapper.class).selectLike(email)) {
				List<String> images =  sqlSession.getMapper(ProductMapper.class).getImages(like.getPseq());
				like.setUrl(images.get(0));
				likes.add(like);
			}
			return likes;
		}finally {
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
	
	//좋아요 취소 -> 채워진 하트 누르면 비워진 하트로 바뀌면서 디비에 result 값 채인지
	public void disLike(String email, int pseq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(LikeMapper.class).deleteLikeView(email, pseq);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
	
}
