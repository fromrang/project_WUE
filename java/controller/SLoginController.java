package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.CustomerDaoImpl;
import dao.SellerDaoImpl;
import dto.Customer;
import dto.Seller;

@Controller
public class SLoginController {
	@Autowired
	private SellerDaoImpl sellerDao;
	@Autowired
	private CustomerDaoImpl customerDao;

//	public SLoginController setcustomerDao(SellerDaoImpl sellerDao,CustomerDaoImpl customerDao) {
//		this.customerDao=customerDao;
//		this.sellerDao = sellerDao;
//		return this;
//	}
	
	
	@RequestMapping(method = RequestMethod.GET,value="seller/Slogin")
	public String form(Seller loginCommand,Customer customer, HttpSession session, Model model,HttpServletRequest request) { //session 쓰고 싶으면 넣어주면 된다. 여기서 인자 첫번째로 Member을 넣어주었기 때문에 위에 주석 처리한 부분이 필요 없다.
		 if(session.getAttribute("cAuthInfo")!=null) {
			 if(((Customer)session.getAttribute("cAuthInfo")).getEmail() != customer.getEmail()){
				 return "redirect:/customer/main";
				}
			
		 }	
		 else if(session.getAttribute("authInfo") != null) {
			if(((Seller)session.getAttribute("authInfo")).getEmail() != loginCommand.getEmail()){
				return "redirect:SMain";
			}
		}
//		else if(loginCommand != null) {
//			model.addAttribute("member", loginCommand);
//			return "seller/SloginForm";
//		}
		return "seller/SloginForm";
	}

	// 맨처음 켰을때
	@RequestMapping(method = RequestMethod.POST,value="seller/Slogin")
	public String form( HttpSession session, Model model,HttpServletRequest request ) {
		
		Seller authInfo=null;
		Customer customer=null;
		
		try {

			 if(sellerDao.SelectCByEamilAndPw(request.getParameter("email"),request.getParameter("password"))==null&&
					customerDao.SelectCByEamilAndPw(request.getParameter("email"),request.getParameter("password"))==null){
				model.addAttribute("notMember", "일치하지않는 회원정보입니다.다시 로그인하여주십시오");
				return "seller/SloginForm";
				
			}
			if (sellerDao.SelectCByEamilAndPw(request.getParameter("email"),request.getParameter("password"))!=null) {
				authInfo=sellerDao.SelectCByEamilAndPw(request.getParameter("email"),request.getParameter("password"));
				session.setAttribute("authInfo", authInfo);
				return "redirect:SMain";
				// throw new Exception("회원자 없음");
			}else if(customerDao.SelectCByEamilAndPw(request.getParameter("email"),request.getParameter("password"))!=null) {
				customer=customerDao.SelectCByEamilAndPw(request.getParameter("email"),request.getParameter("password"));
				session.setAttribute("cAuthInfo", customer);
			
				return "redirect:/customer/main";
			}
		
			return"";
		} catch (Exception e) {
			e.printStackTrace();
			return "seller/SloginForm";
		}

	}

}
