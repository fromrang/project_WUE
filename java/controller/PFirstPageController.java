package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.ProductDao;
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
	public String form(Model model) {
		try {
			List<Product> products = productDao.selectByBest();
			model.addAttribute("products",products);
			return "customer/mainForm";
		}catch(Exception e) {
			e.printStackTrace();
			return "customer/login";
		}
	}
}
