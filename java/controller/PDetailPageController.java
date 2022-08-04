package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dao.ProductDao;
import dto.Product;

@Controller
public class PDetailPageController {
	private ProductDao productDao;
	public PDetailPageController setProductDao(ProductDao productDao) {
		this.productDao = productDao;
		return this;
	}
	@GetMapping("customer/pseq={pseq}")
	public String form(@PathVariable("pseq") int pseq, Model model) {
		try {
			Product product = productDao.selectOne(pseq);
			List<String> images = productDao.selectAllImage(pseq);
			//String quantity = productDao.selectQuantity(pseq);
			model.addAttribute("product", product);
			model.addAttribute("images", images);
			//model.addAttribute("quantity", quantity);
			return "customer/detailFrom";
		}catch(Exception e) {
			e.printStackTrace();
			return "customer/main";
		}
	}
	
}
