package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.OrderDaoImpl;
import dao.ProductDao;
import dao.SellerDaoImpl;
import dto.Seller;
import dto.SellerInstagram;

@Controller
@RequestMapping("seller/SMain")
public class SMainController {
	
	private SellerDaoImpl sellerDao;
	private ProductDao productDao;
	private OrderDaoImpl orderDaoImpl;

	public SMainController setProductDao(SellerDaoImpl sellerDao, ProductDao productDao, OrderDaoImpl orderDaoImpl) {
		this.sellerDao = sellerDao;
		this.productDao=productDao;
		this.orderDaoImpl=orderDaoImpl;
		return this;
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String form(HttpSession session, Model model) throws Exception {
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
				}
		Seller authInfo=(Seller)session.getAttribute("authInfo");
		SellerInstagram	member=sellerDao.InstagramProfile(authInfo.getEmail());
		 List<Integer> ordercount=orderDaoImpl.SOseqCountList(authInfo.getSseq());
		int productcount=productDao.SProudctCount(authInfo);
		
		model.addAttribute("count", productcount);
		model.addAttribute("user", member);
		model.addAttribute("count2", ordercount);
		return "seller/SMainForm";
	}
}
