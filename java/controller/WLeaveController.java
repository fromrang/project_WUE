package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.CustomerDaoImpl;
import dao.SellerDaoImpl;

@Controller
@RequestMapping("worker/leave")
public class WLeaveController {
	@Autowired
	private CustomerDaoImpl customerDao;
	@Autowired
	private SellerDaoImpl sellerDao;

//	public WLeaveController setDao(CustomerDaoImpl customerDao, SellerDaoImpl sellerDao) {
//		this.customerDao = customerDao;
//		this.sellerDao = sellerDao;
//		return this;
//	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String leave(Model model) throws Exception{
		model.addAttribute("customer", customerDao.leaveList());
		model.addAttribute("seller", sellerDao.leaveList());
		return "worker/MemberLeave";
	}
}
