package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.CartDaoImpl;
import dto.Customer;

@Controller
//@RequestMapping("customer/cart/insert?pseq={pseq}&quantity={quantity}")
@RequestMapping("customer/cart/insert")
public class CaInsertController {
	@Autowired
	private CartDaoImpl cartDaoImpl;
//	public CaInsertController setCartDaoImpl(CartDaoImpl cartDaoImpl) {
//		this.cartDaoImpl = cartDaoImpl;
//		return this;
//	}
	@RequestMapping(method = RequestMethod.GET)
	public String action(HttpSession session, @RequestParam("pseq")int pseq, @RequestParam("quantity") int quantity, Model model) {
		Customer customer = (Customer)session.getAttribute("cAuthInfo");
		if(customer != null) {
			try {
				cartDaoImpl.insetCart(customer.getEmail(), pseq, quantity);
				return "redirect:/customer/pseq="+pseq;
			}catch(Exception e) {
				e.printStackTrace();
				return "redirect:/customer/main";//에러페이지 이동
			}
		}else {
			model.addAttribute("nullCustomer", "로그인 후 이용 가능합니다.");
			return "redirect:/customer/login";
		}
	}
	
}
