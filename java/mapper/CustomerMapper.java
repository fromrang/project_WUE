package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Customer;
//멤버 탈퇴 
public interface CustomerMapper {
	@Select("select * from customer where email=#{email}")
	public Customer getLoginInfo(@Param("email")String email) throws Exception;
	
	@Select("select * from customer where email=#{email} and pw=#{pw}")
	public Customer getMember(@Param("email")String email, @Param("pw")String pw) throws Exception;

	@Select("select * from customer")
	public List<Customer> getCustomerAll()throws Exception;
	
	@Delete("delete from customer where email=#{email}")
	public void getDelete(Customer customer)throws Exception;
	
	@Select("select * from customer where status=1")
	public List<Customer> getLeave()throws Exception;
	
	@Insert("insert into customer (name, email, pw, phone, point) values(#{name}, #{email}, #{pw}, #{phone}, 1000)")
	public void insertCustomer(Customer customer) throws Exception;
	//회원 grade
	@Select("select * from customer where email=#{email}")
	public Customer getSelect(@Param("email") String email) throws Exception;
	//회원 grade 변경
	@Update("update customer set name=#{name},email=#{email},pw=#{pw},phone=#{phone},grade=#{grade},point=#{point} where email=#{email}")
	public int getUpdate(Customer customer) throws Exception;
	
	//회원 탈퇴
	@Update("update customer set status = 1, mod_date = now() where email=#{email}")
	public void WithdrawalCustomer(@Param("email")String email);
	//회원 수정
	@Update("update customer set name=#{name}, pw=#{pw}, phone=#{phone}, zip_code=#{zip_code}, address=#{address} where email=#{email}")
	public void updateCustomer(Customer customer) throws Exception;
	
	//회원 검색(아이디 찾기)
	@Select("select * from customer where name=#{name} and phone=#{phone}")
	public Customer selectCustomerNamePhone(@Param("name")String name, @Param("phone")String phone);
	
	//비밀번호 변경
	@Update("update customer set pw=#{pw} where email=#{email}")
	public void changePw(@Param("pw")String pw, @Param("email")String email);
}
