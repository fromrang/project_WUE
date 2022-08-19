package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.OrderDaoImpl;
import dto.Order;
import dto.Seller;

@Controller
/* @RequestMapping("seller/SOrderList") */
public class SOrderListController {
	private OrderDaoImpl orderDaoImpl;
	
	public SOrderListController setOrderDaoImpl(OrderDaoImpl orderDaoImpl) {
		this.orderDaoImpl = orderDaoImpl;
		return this;
	}
	
	@RequestMapping(value = "seller/SOrderList")
	public String form(HttpSession session, Model model) throws Exception { //session 쓰고 싶으면 넣어주면 된다. 여기서 인자 첫번째로 Member을 넣어주었기 때문에 위에 주석 처리한 부분이 필요 없다.
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
				}

		Seller seller=(Seller)session.getAttribute("authInfo");
		List<Order> order=orderDaoImpl.SOseqOrderList(seller.getSseq());
		List<Integer> sumPrice=orderDaoImpl.STotalPrice(seller.getSseq());
		List<Integer> count=orderDaoImpl.SOseqCountList(seller.getSseq());
		
		for(int i=0;i<count.size();i++) {
			if(count.get(i)>1) {
				order.get(i).setPname(order.get(i).getPname()+"외 "+ (count.get(i)-1)+"건");
			}
		}

		model.addAttribute("order", order);
		model.addAttribute("sum", sumPrice);
		
	
		return "seller/SOrderListForm";
	}
	
	//주문번호별
	@RequestMapping(value = "seller/SOrderListResult")
	public String form2(HttpSession session, Model model,@RequestParam(value="result")int result) throws Exception { //session 쓰고 싶으면 넣어주면 된다. 여기서 인자 첫번째로 Member을 넣어주었기 때문에 위에 주석 처리한 부분이 필요 없다.
		
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
				}
		
		Seller seller=(Seller)session.getAttribute("authInfo");
		
		List<Order> order=orderDaoImpl.SOseqOrderList2(seller.getSseq(),result);
		List<Integer> sumPrice=orderDaoImpl.STotalPrice2(seller.getSseq(),result);
		List<Integer> count=orderDaoImpl.SOseqCountList2(seller.getSseq(),result);
		
		for(int i=0;i<count.size();i++) {
			if(count.get(i)>1) {
				order.get(i).setPname(order.get(i).getPname()+"외 "+ (count.get(i)-1)+"건");
			}
		}

		model.addAttribute("order", order);
		model.addAttribute("sum", sumPrice);
		
	
		return "seller/SOrderListForm";
	}
	
	
	
	
}
