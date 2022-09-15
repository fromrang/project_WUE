package controller;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.CustomerDaoImpl;
import dto.Customer;


@Controller
public class CLoginController {
	@Autowired
	private CustomerDaoImpl customerDao;
//	public CLoginController setcustomerDao(CustomerDaoImpl customerDao) {
//		this.customerDao = customerDao;
//		return this;
//	}

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
			loginCommand.setPw(encrypt(loginCommand.getPw()));
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
	@RequestMapping(value = "customer/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
	
    public String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());

        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
    
    //아이디찾기 창 켜기
    @GetMapping(value = "customer/findId")
    public String findIdForm() {
    	return "customer/findIdForm";
    }
    //아이디 찾기 action
    @PostMapping(value = "customer/findId")
    public String findIdAction(Customer customer, HttpServletRequest request, Model model) {
    	String phone = "";
		phone += request.getParameter("phone1");
		phone += "-";
		phone += request.getParameter("phone2");
		phone += "-";
		phone += request.getParameter("phone3");
    	
    	
    	try {
    		Customer newCustoemr = customerDao.findCustomer(customer.getName(), phone);
			if(newCustoemr != null) {
				model.addAttribute("email", newCustoemr.getEmail() );
			}else {
				model.addAttribute("message", "존재하지 않는 회원입니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "customer/findIdForm";
    }
    //비밀번호 찾기 창 켜기
    @GetMapping("customer/findPw")
    public String findPw() {
    	return "customer/findPwForm";
    }
    
    //비밀번호 찾기 action
    @PostMapping("customer/findPw")
    public String findPwAction(HttpServletRequest request, Model model){
    	String phone = "";
		phone += request.getParameter("phone1");
		phone += "-";
		phone += request.getParameter("phone2");
		phone += "-";
		phone += request.getParameter("phone3");
		
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		
		//System.out.printf("%s | %s | %s",phone, name, email);
		
		try {
			Customer newCustoemr = customerDao.findCustomer(name, phone);
			
			if(newCustoemr == null){
				model.addAttribute("message", "존배하지 않는 회원입니다.");
			}else if(newCustoemr.getEmail().equals(email)) {
				String newPw = newPassword();
				customerDao.changePw(encrypt(newPw), email);
				model.addAttribute("password", newPw);
				//디비 비밀번호 변경
			}else {
				model.addAttribute("message", "이메일이 일치하지 않습니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "customer/findPwForm";
    }
    
    public String newPassword() {
    	
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                                       .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                                       .limit(targetStringLength)
                                       .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                       .toString();
    	return generatedString;
    }
    
}
