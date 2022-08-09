package dao;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dto.Customer;
import mapper.CustomerMapper;


public class CustomerDaoImpl implements CustomerDao{
//	
//	@Autowired private CustomerMapper mapper;
//	 
	private SqlSessionFactory sqlSessionFactory;
	
	public void setDataSource(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public Customer SelectCByEmail(String email) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			return sqlSession.getMapper(CustomerMapper.class).getLoginInfo(email);
			//return mapper.getLoginInfo(email);
		}finally {
			sqlSession.close();
		}
	}

	@Override
	public Customer SelectCByEamilAndPw(String email, String pw) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(CustomerMapper.class).getMember(email,pw);
			//return mapper.getMember(email, pw);
		}finally {
			sqlSession.close();
		}
	}

	@Override
	public void CustomerJoin(Customer customer) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(CustomerMapper.class).insertCustomer(customer);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
		
	}
	
}
