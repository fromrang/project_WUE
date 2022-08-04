package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.ProductDao;
import dto.Product;

@Controller
public class PKindPageController {
	private ProductDao productDao;
	public PKindPageController setProductDao(ProductDao productDao) {
		this.productDao = productDao;
		return this;
	}
	@RequestMapping("customer/main/kind={kind}")
	public String form(@PathVariable("kind") int kind, Model model) {
		try {
			List<Product> products = productDao.selectByCategory(kind);
			List<String> imagebyProduct = new ArrayList<String>();
			for(Product product:products) {				
				List<String> images = productDao.selectAllImage(product.getPseq());
				imagebyProduct.add(images.get(0));
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
