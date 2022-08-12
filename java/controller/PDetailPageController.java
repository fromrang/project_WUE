package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dao.ProductDao;
import dao.ReviewDaoImpl;
import dto.Product;
import dto.Review;

@Controller
public class PDetailPageController {
	private ProductDao productDao;
	private ReviewDaoImpl reviewDao;
	public PDetailPageController setProductDao(ProductDao productDao, ReviewDaoImpl reviewDao) {
		this.productDao = productDao;
		this.reviewDao = reviewDao;
		return this;
	}
	@GetMapping("customer/pseq={pseq}")
	public String form(@PathVariable("pseq") int pseq, Model model) {
		try {
			Product product = productDao.selectOne(pseq);
			List<String> images = productDao.selectAllImage(pseq);
			List<Review> reviews = reviewDao.selectReviewPseq(pseq);
			model.addAttribute("product", product);
			model.addAttribute("images", images);
			model.addAttribute("reviews", reviews);
			return "customer/detailFrom";
		}catch(Exception e) {
			e.printStackTrace();
			return "customer/main";
		}
	}
	
}
