package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.CustomerDao;
import dto.Customer;

@Controller
@RequestMapping("customer/Join")
public class CJoinController {
	private CustomerDao customerDao;

	public CJoinController setcustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
		return this;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String form() {
		return "customer/cJoinForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submit(Customer customer,HttpServletRequest request) throws Exception {
		String email="";
		email+=request.getParameter("email");
		email+="@";
		email+=request.getParameter("email2");
		customer.setEmail(email);
		
		String phone="";
		phone+=request.getParameter("phone");
		phone+="-";
		phone+=request.getParameter("phone2");
		phone+="-";
		phone+=request.getParameter("phone3");
		customer.setPhone(phone);
		
		customerDao.CustomerJoin(customer);
		return "redirect:login";
	}
}
