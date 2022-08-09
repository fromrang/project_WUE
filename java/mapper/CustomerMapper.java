package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Customer;

public interface CustomerMapper {
	@Select("select * from customer where email=#{email}")
	public Customer getLoginInfo(@Param("email")String email) throws Exception;
	
	@Select("select * from customer where email=#{email} and pw=#{pw}")
	public Customer getMember(@Param("email")String email, @Param("pw")String pw) throws Exception;
	
	@Insert("insert into customer (name, email, pw, phone) values(#{name}, #{email}, #{pw}, #{phone})")
	public void insertCustomer(Customer customer) throws Exception;
	//회원 수정
	@Update("update customer set status = 1, mod_date = now() where email=#{email} and pw=#{pw}")
	public void WithdrawalCustomer(@Param("email")String email, @Param("pw")String pw);

}
