package dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Customer;
import mapper.CustomerMapper;

@Repository
public class CustomerDaoImpl implements CustomerDao{
//	
//	@Autowired private CustomerMapper mapper;
//	 
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
//	public void setDataSource(SqlSessionFactory sqlSessionFactory) {
//		this.sqlSessionFactory = sqlSessionFactory;
//	}

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
	public List<Customer> selectList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(CustomerMapper.class).getCustomerAll();
		}finally{
			sqlSession.close();
		}
	}

	@Override
	public void delete(Customer customer) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(CustomerMapper.class).getDelete(customer);	
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
	
	@Override
	public List<Customer> leaveList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(CustomerMapper.class).getLeave();	
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
	
	@Override
	public void custoemrUpdate(Customer customer) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(CustomerMapper.class).updateCustomer(customer);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}


	@Override
	public Customer selectOne(String email) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(CustomerMapper.class).getSelect(email);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int update(Customer customer) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.getMapper(CustomerMapper.class).getUpdate(customer);
			sqlSession.commit(); // transaction 처리 =>save point
			return count;
		} catch (Exception e) {
			sqlSession.rollback(); 									
		} finally {
			sqlSession.close();
		}
		return -1;
	}
	
	public void deleteCustomer(Customer customer) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(CustomerMapper.class).WithdrawalCustomer(customer.getEmail());
			sqlSession.commit(); 
		} catch (Exception e) {
			sqlSession.rollback(); 									
		} finally {
			sqlSession.close();
		}
	}
	
	public Customer findCustomer(String name, String phone) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(CustomerMapper.class).selectCustomerNamePhone(name, phone);
		}finally {
			sqlSession.close();
		}
	}

	
}
