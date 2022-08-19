package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SLogOutcontroller {
	

	@RequestMapping(value="seller/Slogout")
	public String logout(HttpSession session) {
		System.out.println("s");
		session.invalidate();		
		return "redirect:Slogin";
	}
}
