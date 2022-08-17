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
import dto.Address;
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
		Customer customer = (Customer)session.getAttribute("cAuthInfo");
		if(customer != null) {
			try {
				
				Product product = orderDaoImpl.selectOne(pseq);
				product.setQuantity(quantity);
				List<Product> products = new ArrayList<Product>();
				products.add(product);
				Customer recustomer = orderDaoImpl.selectCustomer(customer.getEmail());
				model.addAttribute("customer", recustomer);
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
	
	//장바구니에서 결제페이지 이동
	@PostMapping("customer/order")
	public String form(HttpSession session, @RequestParam("cartid")ArrayList<Integer> cartidList, Model model) {
		Customer customer = (Customer)session.getAttribute("cAuthInfo");
		if(customer != null) {
			try {
				List<Cart> cartList = orderDaoImpl.selectCartList(cartidList);
				model.addAttribute("products", cartList);
				Customer recustomer = orderDaoImpl.selectCustomer(customer.getEmail());
				model.addAttribute("customer", recustomer);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "customer/orderForm";
		}else {
			model.addAttribute("nullCustomer", "로그인 후 이용 가능합니다.");
			return "redirect:/customer/login";
		}
	}
	
	//주문하기를 눌렀을 떄
	@PostMapping("customer/payment")
	public String action1(HttpSession session, OrderPage orderPage, Address address, @RequestParam("point") int usedPoint, Model model) {
		Customer customer = (Customer)session.getAttribute("cAuthInfo");
		System.out.println("사용 포인트: " + usedPoint);
		System.out.println("결제 금액: "+  orderPage.getAmount());
		if(address.getPostcode() != null) {
			updateAddress(customer.getEmail(), address);
		}
		
		//사용자 포인트 가지고 오기
		int existPoint = orderDaoImpl.selectPoint(customer.getEmail());
		int revisePoint = existPoint - usedPoint;
		System.out.println("수정될 포인트: "+revisePoint);
		
		//총 주문 금액 가지고 오기(포인트 사용전)
		int amount = orderPage.getAmount();
		List<Order> orders = orderPage.getOrders();
		
		//주문 로직 수행
		orderDaoImpl.insertOrder(customer.getEmail(), orders, revisePoint, amount, usedPoint);
		
		//포인트 적립 수행-> 취소
		//calculatePoint(customer.getEmail(), amount-usedPoint);
		model.addAttribute("orders", orders);
		return "customer/orderCompleteForm"; //결제 하기 버튼을 누르면 팝업 창으로 결제하시겠습니까? 물어보기 네 하면 이 메소드로 전달
	}
	
	
	public void updateAddress(String email, Address address) {
		String zip_code = address.getPostcode();
		String addressinfo = address.getAddress() + " " + address.getDetailAddress();

		orderDaoImpl.updateCustomerAddress(email, zip_code, addressinfo);
	}
	
	//등급별로 포인트 적립할 수 있도록 하기
	public void calculatePoint(String email, int amount) {
		Customer customer = orderDaoImpl.selectCustomer(email);
		String grade = customer.getGrade();
		int accumulatePoint = 0;
		
		if(grade.equals("도라지")) {
			accumulatePoint = (int) Math.round(amount * 0.003);
		}else if(grade.equals("인삼")) {
			accumulatePoint = (int) Math.round(amount * 0.005);
		}else {
			accumulatePoint = (int) Math.round(amount * 0.01);
		}
		System.out.println("적립 포인트: "+accumulatePoint);
		orderDaoImpl.UpdateCustomerPoint(email, accumulatePoint);
	}

}
