package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import dao.CustomerDaoImpl;
import dao.LikeDaoImpl;
import dao.OrderDaoImpl;
import dto.Customer;
import dto.Like;
import dto.Order;

@Controller
public class CMyPageController {
	private CustomerDaoImpl customerDao;
	private LikeDaoImpl likeDao; 
	private OrderDaoImpl orderDao;
	public CMyPageController setDao(CustomerDaoImpl customerDao, LikeDaoImpl likeDao, OrderDaoImpl orderDao) {
		this.customerDao = customerDao;
		this.likeDao = likeDao;
		this.orderDao = orderDao;
		return this;
	}
	
	public CMyPageController setDao(LikeDaoImpl likeDao) {
		this.likeDao = likeDao;
		return this;
	}
	
	@GetMapping("customer/mypage")
	public String form(HttpSession session, Model model) {
		Customer customer = (Customer)session.getAttribute("authInfo");
		if(customer == null) {
			return "redirect:login";
		}else {
			String email = customer.getEmail();
			try {
				List<Order> orders = orderDao.selectOrderRecent(email);
				Customer recustomer = customerDao.SelectCByEmail(email);
				model.addAttribute("customer", recustomer);
				model.addAttribute("orders", orders);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "customer/cMypageFrom";
		}
	}
	@GetMapping("customer/like={pseq}")
	public String action(@PathVariable("pseq") int pseq, HttpServletRequest request, HttpSession session) {
		Customer customer = (Customer)session.getAttribute("authInfo");
		
		if(customer == null) {
			return "redirect:login";
		}else {
			String email = customer.getEmail();
			likeDao.insertLikeTable(email, pseq);
			String referer = request.getHeader("Referer"); // 헤더에서 이전 페이지를 읽는다.
			return "redirect:"+ referer;
		}
	}
	
	@GetMapping("customer/dislike={pseq}")
	public String dislikeaction(@PathVariable("pseq") int pseq, HttpServletRequest request, HttpSession session) {
		Customer customer = (Customer)session.getAttribute("authInfo");
		String email = customer.getEmail();
		if(email == null) {
			return "redirect:login";
		}else {
			likeDao.disLike(email, pseq);
			String referer = request.getHeader("Referer"); // 헤더에서 이전 페이지를 읽는다.
			return "redirect:"+ referer;
		}
	}
	
	@GetMapping("customer/likeList")
	public String likeListForm(HttpSession session, Model model) {
		Customer customer = (Customer)session.getAttribute("authInfo");
		if(customer == null) {
			return "redirect:login";
		}else {
			String email = customer.getEmail();
			try {
				List<Like> likes = likeDao.selectLikeAll(email);
				model.addAttribute("likeList", likes);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "customer/likeForm";
		}
	}
	
	@GetMapping("customer/like/delete")
	public String likedeleteArr(HttpServletRequest request, HttpSession session, Model model, @RequestParam("likeid")ArrayList<Integer> pseqList) {
		Customer customer = (Customer)session.getAttribute("authInfo");

		if(customer == null) {
			return "redirect:login";
		}else {
			String email = customer.getEmail();
			for(Integer pseq: pseqList) {
				likeDao.disLike(email, pseq);
			}
			String referer = request.getHeader("Referer"); // 헤더에서 이전 페이지를 읽는다.
			return "redirect:"+ referer;
		}
	}
	//주문 취소 하기
	@GetMapping("customer/order/delete/odseq={odseq}&oseq={oseq}&payment={payment}")
	public String deleteOrder(HttpServletRequest request, HttpSession session, Model model, @PathVariable("odseq") int odseq,  @PathVariable("oseq") int oseq,  @PathVariable("payment") int payment) {
		
		Customer customer = (Customer)session.getAttribute("authInfo");
		if(customer == null) {
			return "redirect:login";
		}else {
			orderDao.deleteOrder(odseq, oseq, payment);
			
			String referer = request.getHeader("Referer"); // 헤더에서 이전 페이지를 읽는다.
			return "redirect:"+ referer;
		}
	}
	
	//구매내역 보기 - 배송완료된 상품만 볼거임
	@GetMapping("customer/orderList")
	public String OrderListForm(HttpSession session, Model model) {
		Customer customer = (Customer)session.getAttribute("authInfo");
		if(customer == null) {
			return "redirect:login";
		}else {
			String email = customer.getEmail();
			try {
				List<Order> orderList =  orderDao.selectOrderList(customer.getEmail());
				model.addAttribute("orderList", orderList);
				Customer recustomer = customerDao.SelectCByEmail(email);
				model.addAttribute("customer", recustomer);
				return "customer/orderListForm";
			}catch(Exception e) {
				e.printStackTrace();
				return "customer/main";
			}
		}
	}
	
}
