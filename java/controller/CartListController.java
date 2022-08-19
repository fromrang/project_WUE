package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.CartDaoImpl;
import dto.Cart;
import dto.Customer;

@Controller
public class CartListController {
	private CartDaoImpl cartDaoImpl;
	public CartListController setCartDaoImpl(CartDaoImpl cartDaoImpl) {
		this.cartDaoImpl = cartDaoImpl;
		return this;
	}
	@GetMapping("customer/cart/list")
	public String form(HttpSession session, Model model) {
		Customer customer = (Customer)session.getAttribute("cAuthInfo");
		if(customer != null) {
			try {
				List<Cart> cartList = cartDaoImpl.selectCartList(customer.getEmail());
				List<String> imagebyProduct = new ArrayList<String>();
				for(Cart cart:cartList) {				
					List<String> images = cartDaoImpl.selectAllImage(cart.getPseq());
					imagebyProduct.add(images.get(0));
				}
				model.addAttribute("imagebyProduct", imagebyProduct);
				model.addAttribute("cartList", cartList);
				return "/customer/cartListForm";
			}catch(Exception e) {
				e.printStackTrace();
				return "/customer/error";
			}
		}else {
			model.addAttribute("nullCustomer", "로그인 후 이용 가능합니다.");
			return "redirect:/customer/login";
		}
	}
	
	@PostMapping("customer/cart/delete")
	public String action(HttpSession session, @RequestParam("cartid")ArrayList<Integer> cartidList) {
		Customer customer = (Customer)session.getAttribute("cAuthInfo");
		if(customer != null) {
			try {
				for(Integer cseq: cartidList) {
					cartDaoImpl.delectCart(cseq, customer.getEmail());
				}
				return "redirect:/customer/cart/list";
			}catch(Exception e) {
				e.printStackTrace();
				return "/customer/error";
			}
		}else {
			return "redirect:/customer/login";
		}
	}
}
