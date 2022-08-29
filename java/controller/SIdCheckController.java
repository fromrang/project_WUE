package controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.SellerDaoImpl;
import dto.Seller;

@Controller
@RequestMapping("seller/Idcheck")
public class SIdCheckController {
	@Autowired
	private SellerDaoImpl sellerDao;
//
//	public SIdCheckController setcustomerDao(SellerDaoImpl sellerDao) {
//		this.sellerDao = sellerDao;
//		return this;
//	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String sumbit(HttpServletRequest request,Model model) throws Exception {
       String email="";
       if(request.getParameter("email").contains("@")) {
    	  email+=request.getParameter("email");
    		Seller seller=sellerDao.SelectCByEmail(email);
    		model.addAttribute("seller", seller);
       }else {
        email+=request.getParameter("email");
        email+="@";
        email+=request.getParameter("email2");
    	Seller seller=sellerDao.SelectCByEmail(email);
		model.addAttribute("seller", seller);
       }

	
		return "seller/IdcheckForm";
	}
}
