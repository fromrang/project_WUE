package controller;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.CustomerDaoImpl;
import dto.Customer;


@Controller
public class CLoginController {
	private CustomerDaoImpl customerDao;
	public CLoginController setcustomerDao(CustomerDaoImpl customerDao) {
		this.customerDao = customerDao;
		return this;
	}

	//맨처음 켰을때 
	@RequestMapping(value = "customer/login", method = RequestMethod.GET)
	public String form(Customer loginCommand, HttpSession session, Model model) { //session 쓰고 싶으면 넣어주면 된다. 여기서 인자 첫번째로 Member을 넣어주었기 때문에 위에 주석 처리한 부분이 필요 없다.
		
		if(session.getAttribute("cAuthInfo") != null) {
			if(((Customer)session.getAttribute("cAuthInfo")).getEmail() != loginCommand.getEmail()){
				return "customer/loginForm";
			}
		}else if(loginCommand != null) {
			model.addAttribute("member", loginCommand);
			return "customer/loginForm";
		}
		return "customer/loginForm";
	}
	
	//form에 입력하고 넘어 왔을때
	@RequestMapping(value = "customer/login", method = RequestMethod.POST)
	//바로 이어서 쓰는게 멀티플컨트롤러
	public String submit(Customer loginCommand, HttpSession session, HttpServletResponse response, Model model) {

		try {
			if(customerDao.SelectCByEmail(loginCommand.getEmail()) == null){
				model.addAttribute("notMember", "존재하지 않는 회원입니다.");
				return "customer/loginForm";
			}
			
			
			Customer authInfo = customerDao.SelectCByEamilAndPw(loginCommand.getEmail(), loginCommand.getPw());
			if(authInfo == null) {
				model.addAttribute("notMember", "비밀번호가 일치하지 않습니다.");
				return "customer/loginForm";
				//throw new Exception("회원자 없음");	
			}
			
			session.setAttribute("cAuthInfo", authInfo);
			return "redirect:main";
			
		}catch(Exception e) {
			e.printStackTrace();
			return "customer/loginForm";
		}
	}
	@PostMapping("customer/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
}
