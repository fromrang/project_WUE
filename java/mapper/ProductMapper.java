package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import dto.Product;

public interface ProductMapper {
	@Select("select * from product where kind=#{kind}")
	public List<Product> getProductList(@Param("kind")int kind) throws Exception;
	
	@Select("select * from product")
	public List<Product> getAllProductList() throws Exception;
	
	@Select("select * from best_p_view")
	public List<Product> getBestProductList() throws Exception;
	
	@Select("select * from product where pseq=#{pseq}")
	public Product getProduct(@Param("pseq")int pseq) throws Exception;
	
	@Select("select url from pimages where pseq=#{pseq}")
	public List<String> getImages(@Param("pseq")int pseq) throws Exception;

}
