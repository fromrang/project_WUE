package controller;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.SellerDaoImpl;
import dto.Seller;

@Controller
@RequestMapping("seller/SUpdate")
public class SMyPageUpdateController {
	private SellerDaoImpl sellerDao;

	public SMyPageUpdateController setcustomerDao(SellerDaoImpl sellerDao) {
		this.sellerDao = sellerDao;
		return this;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String form(HttpSession session, Model model) throws Exception {
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
				}

		Seller loginseller=(Seller)session.getAttribute("authInfo");
//		System.out.println(loginseller.getEmail());
		Seller updateseller=sellerDao.SelectCByEmail(loginseller.getEmail());
		StringTokenizer st;
	      String delim = "-";
	      int tt=0;
	      st = new StringTokenizer(updateseller.getPhone(), delim, false);
	      String[] tel=new String[3];
	      while( st.hasMoreTokens()) {
	         String token = st.nextToken();
//	         System.out.println(token);
	         tel[tt++]=token;
	      }
		
		model.addAttribute("tel",tel);
		model.addAttribute("user",updateseller);
	      
		return "seller/SMypageUpdateForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(HttpSession session, Seller updateMember,HttpServletRequest request) throws Exception {
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
				}
		
		String phone="";
		phone+=request.getParameter("phone1");
		phone+="-";
		phone+=request.getParameter("phone2");
		phone+="-";
		phone+=request.getParameter("phone3");
		updateMember.setPhone(phone);
		
		sellerDao.updateSeller(updateMember);
		
		return "redirect:SUpdate";
		// redirect:member/list redirect:member/list
	}

}
