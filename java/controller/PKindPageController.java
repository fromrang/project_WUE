package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.ProductDao;
import dto.Customer;
import dto.Product;

@Controller
public class PKindPageController {
	private ProductDao productDao;
	public PKindPageController setProductDao(ProductDao productDao) {
		this.productDao = productDao;
		return this;
	}
	@RequestMapping("customer/main/kind={kind}")
	public String form(@PathVariable("kind") int kind, Model model, HttpSession session) {
		Customer customer = (Customer)session.getAttribute("authInfo");
		try {
			List<Product> products = new ArrayList<Product>();
			List<String> imagebyProduct = new ArrayList<String>();
			
			for(Product product : productDao.selectByCategory(kind)) {				
				List<String> images = productDao.selectAllImage(product.getPseq());
				imagebyProduct.add(images.get(0));
				
				if(customer == null) {
					products.add(product);
				}else {
					//사용자가 좋아요한 상품 가지고 오기
					String result = productDao.checkLikeExist(customer.getEmail(), product.getPseq());
					product.setLike(result);
					products.add(product);
				}
			}
			model.addAttribute("products", products);
			model.addAttribute("imagebyProduct", imagebyProduct);
			return "customer/kindForm";
	
		}catch(Exception e) {
			e.printStackTrace();
			return "customer/main";
		}
	}
}
