package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Product;
import dto.Seller;

public interface ProductMapper {
	@Select("select * from product where kind=#{kind}")
	public List<Product> getProductList(@Param("kind")int kind) throws Exception;
	
	@Select("select * from product")
	public List<Product> getAllProductList() throws Exception;
	
	@Select("select * from best_p_view")
	public List<Product> getBestProductList() throws Exception;
	
	@Select("select * from recommend_p_view")
	public List<Product> getRecommendProductList() throws Exception;
	
	@Select("select * from product where pseq=#{pseq}")
	public Product getProduct(@Param("pseq")int pseq) throws Exception;
	
	@Select("select url from pimages where pseq=#{pseq}")
	public List<String> getImages(@Param("pseq")int pseq) throws Exception;
	
	//검색후 상품 리스트 가지고 오기
	@Select("select * from product where name like CONCAT('%', #{search}, '%')")
	public List<Product> SelectSearchProduct(@Param("search")String search) throws Exception;


	//===============================================================
	
	@Insert("insert into product(name,kind,cost,price,sale_price,sseq,quantity) values(#{name},#{kind},#{cost},#{price},#{sale_price},#{sseq},#{quantity})")
	public void SaddProduct(Product product) throws Exception;
	
	@Insert("insert into pimages(url,pseq) values(#{url},#{pseq})")
	public void SAddProductImage(@Param("url") String url,@Param("pseq") int pseq) throws Exception;
	
	@Select("SELECT pseq FROM product order by pseq desc limit 1")
	public int selectPseqByAdd()throws Exception;
	
	@Select("SELECT * FROM product where sseq=#{sseq} and quantity>0")
	public List<Product> selectSellProductList(@Param("sseq") String sseq)throws Exception;
	
	@Select("SELECT * FROM product where sseq=#{sseq} and quantity=0")
	public List<Product> selectReadyProductList(@Param("sseq") String sseq)throws Exception;

	@Select("SELECT * FROM product where pseq=#{pseq} ")
	public Product selectupdate(@Param("pseq") String pseq)throws Exception;
	
	@Select("SELECT count(name) FROM product where sseq=#{sseq}")
	public int SProudctCount(Seller seller) throws Exception;
	

	@Delete("delete from product where pseq=#{pseq}")
	public void SProudctDelte(@Param("pseq")int pseq) throws Exception;
	
	@Delete("delete from pimages where pseq=#{pseq}")
	public void SProudctImageDelte(@Param("pseq")int pseq) throws Exception;
	
	@Update("update product set name=#{name},kind=#{kind},cost=#{cost},price=#{price},sale_price=#{sale_price},quantity=#{quantity} where pseq=#{pseq}")
	 public void SProductUpdate(Product product) throws Exception;
	
	@Select("SELECT sum(quantity) FROM order_view where pseq=#{pseq};")
	public Integer SProudctSaleCount(@Param("pseq")int pseq) throws Exception;

}
