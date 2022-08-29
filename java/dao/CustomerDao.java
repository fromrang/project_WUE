package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import dto.Customer;


public interface CustomerDao {
	Customer SelectCByEmail(String email) throws Exception;
	Customer SelectCByEamilAndPw(String email, String pw) throws Exception;
	List<Customer> selectList() throws Exception;
	void delete(Customer customer)throws Exception;
	List<Customer> leaveList()throws Exception;
	void CustomerJoin(Customer customer) throws Exception;
	void custoemrUpdate(Customer customer) throws Exception;
	Customer selectOne(String email) throws Exception;
	int update(Customer customer)throws Exception;
	void deleteCustomer(Customer customer) throws Exception;
}
