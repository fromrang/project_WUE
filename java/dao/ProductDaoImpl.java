package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.Product;
import mapper.ProductMapper;

public class ProductDaoImpl implements ProductDao{
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public void setDataSource(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
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


}
