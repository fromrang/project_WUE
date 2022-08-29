package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.SellerDaoImpl;

@Controller
@RequestMapping("seller/Snotice")
public class SnoticeController {
	@Autowired
	private SellerDaoImpl sellerDao;

//	public SnoticeController setcustomerDao(SellerDaoImpl sellerDao) {
//		this.sellerDao = sellerDao;
//		return this;
//	}
//	
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model model) throws Exception{
		model.addAttribute("notice", sellerDao.noticeList());
		return "seller/Snotice";
	}
}
