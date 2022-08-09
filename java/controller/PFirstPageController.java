package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.ProductDao;
import dto.Customer;
import dto.Product;

@Controller
@RequestMapping("customer/main")
public class PFirstPageController {
	private ProductDao productDao;
	public PFirstPageController setProductDao(ProductDao productDao) {
		this.productDao = productDao;
		return this;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String form(Model model,HttpSession session) {

			//List<Product> products = productDao.selectByBest();
			//model.addAttribute("products",products);
			
		Customer customer = (Customer)session.getAttribute("authInfo");
		try {
			List<Product> products = new ArrayList<Product>();
			
			for(Product product : productDao.selectByBest()) {
				
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
			return "customer/mainForm";
		}catch(Exception e) {
			e.printStackTrace();
			return "customer/login";
		}
	}
}
