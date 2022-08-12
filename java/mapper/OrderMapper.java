package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Cart;
import dto.Customer;
import dto.Order;
import dto.Review;

public interface OrderMapper {
	@Insert("insert into orders(email, amount) values(#{email}, #{amount})")
	public void insertOrder(@Param("email")String email, @Param("amount")int amount);
	
	@Insert("insert into order_detail(oseq, pseq, quantity, payment) values(#{oseq}, #{pseq}, #{quantity}, #{payment})")
	public void insertOrderDetail(@Param("oseq")int oseq, @Param("pseq")int pseq, @Param("quantity")int quantity, @Param("payment")int payment);
	
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
	
	@Select("select * from customer where email=#{email}")
	public Customer selectCustomer(@Param("email")String email);
	
	@Select("select point from customer where email=#{email}")
	public int selectPoint(@Param("email")String email);

	@Update("update product set quantity=quantity-#{quantity} where pseq=#{pseq}")
	public void updateQuantity(@Param("pseq")int pseq, @Param("quantity")int quantity);
	
	@Update("update customer set zip_code=#{zip_code}, address=#{address} where email=#{email}")
	public void updateAddress(@Param("zip_code")String zip_code, @Param("address")String address, @Param("email")String email);
	
	@Select("select * from order_view where email=#{email} and result not in ('3')")
	public List<Order> selectRecentorder(@Param("email")String email);
	
	//주문 취소 --- 
	//order_detail 삭제
	@Delete("delete from order_detail where odseq=#{odseq}")
	public void deleteOrderDetail(@Param("odseq")int odseq);
	//order_detail에서 odseq로 pseq랑 주문 수량 가지고 오기
	@Select("select * from order_detail where odseq=#{odseq}")
	public Order selectOrderDetailByOdseq(@Param("odseq")int odseq);
	//orders에서 amount 바꿔주기
	@Update("update orders set amount=amount-#{payment} where oseq=#{oseq}")
	public void updateAmount(@Param("oseq")int oseq, @Param("payment")int payment);
	//orders 테이블 삭제하기
	@Delete("delete from orders where amount=0")
	public void deleteOrders();
	//상품 수량 다시 복구하기
	@Update("update product set quantity=quantity+#{quantity} where pseq=#{pseq}")
	public void updateProductQua(@Param("pseq")int pseq, @Param("quantity")int quantity);
	//---------------------
	
	//후기--------------
	//구매내역보기 -- 후기 작성 가능한 상품에도 사용
	@Select("select * from order_view where email=#{email} and result=3")
	public List<Order> selectOrderList(@Param("email")String email);
	//후기 작성 가능한 상품 중 이미 후기 작성한 상품을 제외하기 위해서 odseq 있을 시에 review 페이지 가지고 오기
	@Select("select * from review where odseq=#{odseq}")
	public Review selectReviewByOdseq(@Param("odseq")int odseq);
	//사용자가 쓴 전체 후기 가지고 오기
	@Select("select * from review where email=#{email}")
	public List<Review> selectReviewAllbyEmail(@Param("email")String email);
	//상품별 후기 가지고 오기
	@Select("select * from review where pseq=#{pseq}")
	public List<Review> selectReviewAllbyPseq(@Param("pseq")int pseq);
	//order_view에서 odseq로 주문정보 갖고 오기
	@Select("select * from order_view where odseq=#{odseq}")
	public Order selectOrderView(@Param("odseq")int odseq);
	//리뷰 등록하기
	@Insert("insert into review(odseq, sseq, email, pseq, pname, content) values (#{odseq}, #{sseq}, #{email}, #{pseq}, #{pname}, #{content})")
	public void insertReviewContent(Review review);
	//리뷰 최대 값 가지고 오기
	@Select("select max(rseq) from review")
	public int selectMaxRseq();
	//리뷰 이미지 등록하기
	@Insert("insert into rimages(url, rseq) values (#{url}, #{rseq})")
	public void insertReviewImage(@Param("url")String url,@Param("rseq")int rseq);
	//리뷰 이미지 가지고 오기
	@Select("select url from rimages where rseq=#{rseq}")
	public List<String> selectReviewImage(@Param("rseq")int rseq);
}
 