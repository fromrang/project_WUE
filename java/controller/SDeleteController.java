package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.SellerDaoImpl;
import dto.Seller;

@Controller
@RequestMapping("seller/SDelete")
public class SDeleteController {
	private SellerDaoImpl sellerDao;

	public SDeleteController setcustomerDao(SellerDaoImpl sellerDao) {
		this.sellerDao = sellerDao;
		return this;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submit(Seller updateMember,HttpSession session) throws Exception {
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
				}
		
		Seller loginseller=(Seller)session.getAttribute("authInfo");
		sellerDao.delteSeller(loginseller);
		return "redirect:Slogin";
		
	}
}
