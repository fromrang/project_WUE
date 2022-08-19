package controller;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.CustomerDao;
import dto.Customer;

@Controller
public class CJoinController {
	private CustomerDao customerDao;

	public CJoinController setcustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
		return this;
	}

	@RequestMapping("customer/joinForm")
	public String form() {
		return "customer/cJoinForm";
	}

	@PostMapping("customer/Join")
	public String submit(Customer customer, HttpServletRequest request) throws Exception {
		String email = "";
		email += request.getParameter("email");
		email += "@";
		email += request.getParameter("email2");
		customer.setEmail(email);

		String phone = "";
		phone += request.getParameter("phone");
		phone += "-";
		phone += request.getParameter("phone2");
		phone += "-";
		phone += request.getParameter("phone3");
		customer.setPhone(phone);

		customerDao.CustomerJoin(customer);
		return "redirect:login";
	}
	
	@PostMapping("customer/delete") 
	public String delete(HttpSession session) throws Exception {
		Customer customer = (Customer)session.getAttribute("cAuthInfo");
		
		if(customer == null) {
			return "redirect:login";
		}else {
			customerDao.deleteCustomer(customer);
			session.invalidate();
			return "redirect:main";
		}
	}
	 

}
