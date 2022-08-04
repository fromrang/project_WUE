package controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.OrderDaoImpl;
import dto.Cart;
import dto.Customer;
import dto.Order;
import dto.OrderPage;
import dto.Product;

@Controller
public class OdInsertController {
	private OrderDaoImpl orderDaoImpl;
	public OdInsertController setOrderDaoImpl(OrderDaoImpl orderDaoImpl) {
		this.orderDaoImpl = orderDaoImpl;
		return this;
	}
	//결제 페이지로 정보 가지고 이동
	@GetMapping("customer/order")
	public String form(HttpSession session, @RequestParam("pseq")int pseq, @RequestParam("quantity") int quantity, Model model) {
		Customer customer = (Customer)session.getAttribute("authInfo");
		if(customer != null) {
			try {
				Product product = orderDaoImpl.selectOne(pseq);
				product.setQuantity(quantity);
				List<Product> products = new ArrayList<Product>();
				products.add(product);
				model.addAttribute("products", products);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "customer/orderForm";
		}else {
			model.addAttribute("nullCustomer", "로그인 후 이용 가능합니다.");
			return "redirect:/customer/login";
		}
	}
	

	@PostMapping("customer/order")
	public String form(HttpSession session, @RequestParam("cartid")ArrayList<Integer> cartidList, Model model) {
		Customer customer = (Customer)session.getAttribute("authInfo");
		if(customer != null) {
			try {
				List<Cart> cartList = orderDaoImpl.selectCartList(cartidList);
				model.addAttribute("products", cartList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "customer/orderForm";
		}else {
			model.addAttribute("nullCustomer", "로그인 후 이용 가능합니다.");
			return "redirect:/customer/login";
		}
	}
	
	@GetMapping("customer/payment")
	public String action1(HttpSession session, OrderPage orderPage, @RequestParam("point") int point) {
		Customer customer = (Customer)session.getAttribute("authInfo");
		point = customer.getPoint() - point;
		List<Order> orders = orderPage.getOrders();
		orderDaoImpl.insertOrder(customer.getEmail(), orders, point);
		return "redirect:/customer/main"; //결제 하기 버튼을 누르면 팝업 창으로 결제하시겠습니까? 물어보기 네 하면 이 메소드로 전달
	}

	
}
