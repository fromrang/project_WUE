package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.CustomerDaoImpl;
import dao.SellerDaoImpl;

@Controller
@RequestMapping("worker/management")
public class WManagementController {
	@Autowired
	private CustomerDaoImpl customerDao;
	@Autowired
	private SellerDaoImpl sellerDao;

//	public WManagementController setDao(CustomerDaoImpl customerDao, SellerDaoImpl sellerDao) {
//		this.customerDao = customerDao;
//		this.sellerDao = sellerDao;
//		return this;
//	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model model) throws Exception{
		model.addAttribute("customer", customerDao.selectList());
		model.addAttribute("seller", sellerDao.selectList());
		return "worker/MemberManagement";
	}
	
}
