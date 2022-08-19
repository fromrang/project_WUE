package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dao.ProductDao;
import dao.ReviewDaoImpl;
import dao.SellerDao;
import dto.Product;
import dto.Review;
import dto.SellerInstagram;

@Controller
public class PDetailPageController {
	private ProductDao productDao;
	private SellerDao sellerDao;
	private ReviewDaoImpl reviewDao;
	public PDetailPageController setProductDao(ProductDao productDao, ReviewDaoImpl reviewDao, SellerDao sellerDao) {
		this.productDao = productDao;
		this.reviewDao = reviewDao;
		this.sellerDao = sellerDao;
		return this;
	}
	@GetMapping("customer/pseq={pseq}")
	public String form(@PathVariable("pseq") int pseq, Model model) {
		try {
			Product product = productDao.selectOne(pseq);
			List<String> images = productDao.selectAllImage(pseq);
			List<Review> reviews = reviewDao.selectReviewPseq(pseq);
			SellerInstagram sellerInstagram= sellerDao.getSellerImageUrl(product.getSseq());
			model.addAttribute("product", product);
			model.addAttribute("images", images);
			model.addAttribute("reviews", reviews);
			model.addAttribute("seller", sellerInstagram);

			
			return "customer/detailFrom";
		}catch(Exception e) {
			e.printStackTrace();
			return "customer/main";
		}
	}
	
}
