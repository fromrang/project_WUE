package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.CustomerDaoImpl;
import dto.Customer;

@Controller
@RequestMapping("worker/promote")
public class WPromoteController {
	@Autowired
	private CustomerDaoImpl customerDao;
	
//	public WPromoteController setDao(CustomerDaoImpl customerDao) {
//		this.customerDao = customerDao;
//		return this;
//	}
	
	@RequestMapping(method = RequestMethod.GET)
	//@GetMapping("worker/promote")
	public String form(@RequestParam(value="email")String email, Model model) {
		try {
			model.addAttribute("customer",customerDao.SelectCByEmail(email));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "worker/CustomerPromote";

	}

	@RequestMapping(method = RequestMethod.POST)	
	public String submit(Customer customer) throws Exception {
		try {
			customerDao.update(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:management";	
	}
}
