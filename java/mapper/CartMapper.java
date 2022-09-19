package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Cart;

public interface CartMapper {
	@Insert("insert into cart (email, pseq, quantity) values( #{email}, #{pseq}, #{quantity} )")
    public int insertCart(@Param("email")String email, @Param("pseq")int pseq, @Param("quantity")int quantity );
	
	@Select("select * from cart_view where email = #{email}")
	public List<Cart> selectCartList(@Param("email")String email);
	
	@Delete("delete from cart where cseq = #{cseq} and email = #{email}")
    public void deleteDept(@Param("email")String email, @Param("cseq")int cseq);
	
	@Select("select url from pimages where pseq=#{pseq}")
	public List<String> getImages(@Param("pseq")int pseq) throws Exception;
	
	@Select("select * from cart_view where email = #{email} and pseq=#{pseq}")
	public Cart selectCartOne(@Param("email")String email, @Param("pseq")int pseq);
	
	@Update("update cart set quantity=#{quantity} where  email = #{email} and pseq=#{pseq}")
	public void updateCart(@Param("email")String email, @Param("pseq")int pseq, @Param("quantity")int quantity);
}
