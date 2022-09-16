package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dao.CustomerDao;
import dto.Customer;



@Controller
//@RequestMapping("customer/Idcheck")
public class CIdCheckController {
	@Autowired
	private CustomerDao customerDao;

//	public CIdCheckController setcustomerDao(CustomerDao customerDao) {
//		this.customerDao = customerDao;
//		return this;
//	}
	@GetMapping(value = "customer/Idcheck")
	public String form() {
		return "customer/IdcheckForm";
	}
	@PostMapping(value = "customer/Idcheck")
	public String sumbit(HttpServletRequest request,Model model) throws Exception {
       String email="";
       if(request.getParameter("email").contains("@")) {
    	  email+=request.getParameter("email");
    	  Customer customer=customerDao.SelectCByEmail(email);
    		model.addAttribute("customer", customer);
    		model.addAttribute("message", "ture");
       }else {
        email+=request.getParameter("email");
        email+="@";
        email+=request.getParameter("email2");
    	Customer customer=customerDao.SelectCByEmail(email);
		model.addAttribute("customer", customer);
		model.addAttribute("message", "true");
       }
	
		return "customer/IdcheckForm";
	}
}
