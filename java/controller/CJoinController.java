package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.CustomerDaoImpl;
import dto.Customer;

@Controller
public class CJoinController {
	@Autowired
	private CustomerDaoImpl customerDao;

//	public CJoinController setcustomerDao(CustomerDao customerDao) {
//		this.customerDao = customerDao;
//		return this;
//	}

	@RequestMapping("customer/joinForm")
	public String form() {
		return "customer/cJoinForm";
	}

	@PostMapping("customer/Join")
	public String submit(Customer customer, HttpServletRequest request, Model model) throws Exception {
		String email = "";
		email += request.getParameter("email");
		email += "@";
		email += request.getParameter("email2");
		customer.setEmail(email);

		String phone = "";
		phone += request.getParameter("phone");
		phone += "-";
		phone += request.getParameter("phone2");
		phone += "-";
		phone += request.getParameter("phone3");
		customer.setPhone(phone);
		
		Customer existCustomer = customerDao.findCustomer(customer.getName(), phone);
		if(existCustomer == null) {
			customer.setPw(encrypt(customer.getPw()));
			customerDao.CustomerJoin(customer);
			model.addAttribute("message", "1000point 적립 완료:)");
			return "customer/loginForm";
		}else {
			model.addAttribute("message", "이미 존재하는 회원입니다.");
			return "customer/loginForm";
		}
		
		
		
	}
	
	@PostMapping("customer/delete") 
	public String delete(HttpSession session) throws Exception {
		Customer customer = (Customer)session.getAttribute("cAuthInfo");
		
		if(customer == null) {
			return "redirect:login";
		}else {
			customerDao.deleteCustomer(customer);
			session.invalidate();
			return "redirect:main";
		}
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
	 

}
