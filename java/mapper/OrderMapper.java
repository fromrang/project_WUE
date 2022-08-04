package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Cart;
import dto.Order;

public interface OrderMapper {
	@Insert("insert into orders(email) values(#{email})")
	public void insertOrder(@Param("email")String email);
	
	@Insert("insert into order_detail(oseq, pseq, quantity) values(#{oseq}, #{pseq}, #{quantity})")
	public void insertOrderDetail(@Param("oseq")int oseq, @Param("pseq")int pseq, @Param("quantity")int quantity);
	
	@Select("select max(oseq) from orders")
	public int selectMaxOseq();
	
	@Update("update cart set result='y' where pseq=#{pseq} and email=#{email} and quantity=#{quantity}")
	public void updateCartResult(@Param("email")String email, @Param("pseq")int pseq, @Param("quantity")int quantity);
	
	@Select("select * from cart_view where cseq=#{cseq}")
	public Cart selectCart(@Param("cseq")int cseq);
	
	@Update("update customer set point=#{point} where email=#{email}")
	public void updateCustomerPoint(@Param("email")String email, @Param("point")int point);
}
 