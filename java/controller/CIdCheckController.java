package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.CustomerDao;
import dto.Customer;



@Controller
@RequestMapping("customer/Idcheck")
public class CIdCheckController {
	private CustomerDao customerDao;

	public CIdCheckController setcustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
		return this;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String sumbit(HttpServletRequest request,Model model) throws Exception {
       String email="";
       if(request.getParameter("email").contains("@")) {
    	  email+=request.getParameter("email");
    	  Customer customer=customerDao.SelectCByEmail(email);
    		model.addAttribute("customer", customer);
       }else {
        email+=request.getParameter("email");
        email+="@";
        email+=request.getParameter("email2");
    	Customer customer=customerDao.SelectCByEmail(email);
		model.addAttribute("customer", customer);
       }
	
		return "customer/IdcheckForm";
	}
}
