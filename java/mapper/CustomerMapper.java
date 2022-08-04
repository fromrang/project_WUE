package mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import dto.Customer;
//멤버 탈퇴 
public interface CustomerMapper {
	@Select("select * from customer where email=#{email}")
	public Customer getLoginInfo(@Param("email")String email) throws Exception;
	
	@Select("select * from customer where email=#{email} and pw=#{pw}")
	public Customer getMember(@Param("email")String email, @Param("pw")String pw) throws Exception;

}
