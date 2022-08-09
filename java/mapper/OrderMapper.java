package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Cart;
import dto.Order;

public interface OrderMapper {
	@Insert("insert into orders(email, amount) values(#{email}, #{amount})")
	public void insertOrder(@Param("email")String email, @Param("amount")int amount);
	
	@Insert("insert into order_detail(oseq, pseq, quantity) values(#{oseq}, #{pseq}, #{quantity})")
	public void insertOrderDetail(@Param("oseq")int oseq, @Param("pseq")int pseq, @Param("quantity")int quantity);
	
	@Select("select max(oseq) from orders")
	public int selectMaxOseq();
	
	@Update("update cart set result='y' where pseq=#{pseq} and email=#{email} and quantity=#{quantity}")
	public void updateCartResult(@Param("email")String email, @Param("pseq")int pseq, @Param("quantity")int quantity);
	
	@Update("delete from cart where pseq=#{pseq} and email=#{email} and quantity=#{quantity}")
	public void deleteCart(@Param("email")String email, @Param("pseq")int pseq, @Param("quantity")int quantity);
	
	@Select("select * from cart_view where cseq=#{cseq}")
	public Cart selectCart(@Param("cseq")int cseq);
	
	@Update("update customer set point=#{point} where email=#{email}")
	public void updateCustomerPoint(@Param("email")String email, @Param("point")int point);
	
	@Select("select point from customer where email=#{email}")
	public int selectPoint(@Param("email")String email);
	
	@Update("update product set quantity=quantity-#{quantity} where pseq=#{pseq}")
	public void updateQuantity(@Param("pseq")int pseq, @Param("quantity")int quantity);
	
	@Update("update customer set zip_code=#{zip_code}, address=#{address} where email=#{email}")
	public void updateAddress(@Param("zip_code")String zip_code, @Param("address")String address, @Param("email")String email);
	
	@Select("select * from order_view where email=#{email} and result not in ('3')")
	public List<Order> selectRecentorder(@Param("email")String email);
}
 