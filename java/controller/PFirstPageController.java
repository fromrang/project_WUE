package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.ProductDao;
import dto.Customer;
import dto.Product;
import dto.Wnotice;

@Controller
public class PFirstPageController {
	private ProductDao productDao;
	public PFirstPageController setProductDao(ProductDao productDao) {
		this.productDao = productDao;
		return this;
	}
	
	@GetMapping("customer/main")
	public String form(Model model,HttpSession session) {

			//List<Product> products = productDao.selectByBest();
			//model.addAttribute("products",products);
			
		Customer customer = (Customer)session.getAttribute("cAuthInfo");
		try {
			List<Product> products = new ArrayList<Product>();
			List<Wnotice> noticeList = productDao.noticeList();
			List<Wnotice> qnaList = productDao.questionsList();
			List<Product> Rproducts = new ArrayList<Product>();
			
			
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
			for(Product product : productDao.selectByRecommend()) {
				
				if(customer == null) {
					Rproducts.add(product);
				}else {
					//사용자가 좋아요한 상품 가지고 오기
					String result = productDao.checkLikeExist(customer.getEmail(), product.getPseq());
					product.setLike(result);
					Rproducts.add(product);
				}
			}
			model.addAttribute("products", products);
			model.addAttribute("Rproducts", Rproducts);
			model.addAttribute("noticeList", noticeList);
			model.addAttribute("qnaList", qnaList);
			return "customer/mainForm";
		}catch(Exception e) {
			e.printStackTrace();
			return "customer/login";
		}
	}
	//공지사항 자세히 보기
	@GetMapping("customer/notice")
	public String noticeDetail(Model model, @RequestParam("wseq")int wseq) {
		try {
			List<Wnotice> noticeList = productDao.noticeList();
			List<Wnotice> qnaList = productDao.questionsList();
			Wnotice wnotice = productDao.noticeOne(wseq);
			model.addAttribute("noticeList", noticeList);
			model.addAttribute("qnaList", qnaList);
			model.addAttribute("wnotice", wnotice);
			
			//누를때마다 hits 1 증가 시키기
			productDao.noticeHitPlus(wseq);
			return "customer/noticeDetailForm";
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect:main";
		}
	}
	//검색 기능
	@GetMapping("customer/search")
	public String search(HttpSession session, Model model, @RequestParam("search")String search) {
		Customer customer = (Customer)session.getAttribute("cAuthInfo");
		try {
			String searchTrim = search.trim();
			//List<Product> products = productDao.selectProductSearch(search);
			List<Product> products = new ArrayList<Product>();
			
			
			for(Product product : productDao.selectProductSearch(searchTrim)) {
				
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
			
			return "customer/searchForm";
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect:main";
		}
		
	}
}
