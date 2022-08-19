package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.OrderDaoImpl;
import dto.Customer;
import dto.Order;
import dto.Seller;

@Controller
public class SOrderDetailController {
private OrderDaoImpl orderDaoImpl;
	
	public SOrderDetailController setOrderDaoImpl(OrderDaoImpl orderDaoImpl) {
		this.orderDaoImpl = orderDaoImpl;
		return this;
	}
	
	@RequestMapping(value = "seller/SOrderDetail")
	public String form(HttpSession session, Model model,@RequestParam(value="oseq")int oseq) throws Exception { //session 쓰고 싶으면 넣어주면 된다. 여기서 인자 첫번째로 Member을 넣어주었기 때문에 위에 주석 처리한 부분이 필요 없다.
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
				}
		
		
		Seller seller=(Seller)session.getAttribute("authInfo");
		List<Order> order=orderDaoImpl.SOrderList(seller.getSseq(),oseq);
		int payment=orderDaoImpl.SOrderListCount(seller.getSseq(), oseq);
		
		model.addAttribute("order", order);
		model.addAttribute("sum", payment);
		
	
		return "seller/SOrderDetailForm";
	}
	
	@RequestMapping(value = "seller/SOrderResultUpate")
	public String form4(HttpSession session,@RequestParam(value="result")int result,@RequestParam(value="odseq")ArrayList<Integer> odseqList) throws Exception { //session 쓰고 싶으면 넣어주면 된다. 여기서 인자 첫번째로 Member을 넣어주었기 때문에 위에 주석 처리한 부분이 필요 없다.
		
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
		}
		
		
		Seller seller=(Seller)session.getAttribute("authInfo");
		
		for(Integer odseq:odseqList) {
			orderDaoImpl.SOrderResultUpdate(seller.getSseq(), odseq, result);
			if(result == 3) {
				getPointCustomer(odseq);
			}
		}
		
		
		return "redirect:SOrderList";
	}
	//주문번호를 가지고 고객에게 포인트 주기
	public void getPointCustomer(int odseq) {
		try {
			Order order = orderDaoImpl.selectOrderView(odseq);
			calculatePoint(order.getEmail(), order.getPayment());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void calculatePoint(String email, int payment) {
		Customer customer = orderDaoImpl.selectCustomer(email);
		String grade = customer.getGrade();
		int accumulatePoint = 0;
		
		if(grade.equals("도라지")) {
			accumulatePoint = (int) Math.round(payment * 0.003);
		}else if(grade.equals("인삼")) {
			accumulatePoint = (int) Math.round(payment * 0.005);
		}else {
			accumulatePoint = (int) Math.round(payment * 0.01);
		}
		System.out.println("적립 포인트: "+accumulatePoint);
		orderDaoImpl.UpdateCustomerPoint(email, accumulatePoint);
	}
}
