//package controller;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import dto.OrderPage;
//
//@Controller
//public class OrderController {
//	@GetMapping("customer/order")
//	public void orderPageGet(HttpSession session, OrderPage orderpage, Model model) {
//		System.out.println("!!!"+orderpage.getOrders());
//		System.out.println("!!!"+orderpage.getOrders().get(0).getCount());
//	}
//}	
