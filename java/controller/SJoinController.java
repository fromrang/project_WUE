package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.SellerDaoImpl;
import dto.Seller;
import dto.SellerInstagram;

@Controller
@RequestMapping("seller/SJoin")
public class SJoinController {
	private SellerDaoImpl sellerDao;

	public SJoinController setcustomerDao(SellerDaoImpl sellerDao) {
		this.sellerDao = sellerDao;
		return this;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String form(HttpServletRequest requset) {			
			return "seller/SContractForm";

		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submit(Seller insertSelller,HttpServletRequest request) throws Exception {
//		System.out.println(request.getParameter("contract"));
		if(request.getParameter("contract").equals("y")) {
			return "seller/SJoinForm";
		}else {
//			System.out.println("a");
			String email="";
			email+=request.getParameter("email");
			email+="@";
			email+=request.getParameter("email2");
			insertSelller.setEmail(email);
			
			String phone="";
			phone+=request.getParameter("phone");
			phone+="-";
			phone+=request.getParameter("phone2");
			phone+="-";
			phone+=request.getParameter("phone3");
			insertSelller.setPhone(phone);
			
			SellerInstagram seller=new SellerInstagram();
			seller.setSeller_email(email);
			
			sellerDao.joinSeller(insertSelller);
			sellerDao.joinInstaSeller(seller);
			return "redirect:Slogin";
			
			
	
		}
	}
}
