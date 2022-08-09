package dao;

import dto.Customer;

public interface CustomerDao {
	Customer SelectCByEmail(String email) throws Exception;
	Customer SelectCByEamilAndPw(String email, String pw) throws Exception;
	void CustomerJoin(Customer customer) throws Exception;
}
