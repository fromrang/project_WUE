package controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.CustomerDaoImpl;
import dao.LikeDaoImpl;
import dao.OrderDaoImpl;
import dao.ReviewDaoImpl;
import dto.Customer;
import dto.Like;
import dto.Order;
import dto.Product;
import dto.Review;

@Controller
public class CMyPageController {
	@Autowired
	private CustomerDaoImpl customerDao;
	@Autowired
	private LikeDaoImpl likeDao; 
	@Autowired
	private OrderDaoImpl orderDao;
	@Autowired
	private ReviewDaoImpl reviewDao;
//	public CMyPageController setDao(CustomerDaoImpl customerDao, LikeDaoImpl likeDao, OrderDaoImpl orderDao, ReviewDaoImpl reviewDao) {
//		this.customerDao = customerDao;
//		this.likeDao = likeDao;
//		this.orderDao = orderDao;
//		this.reviewDao = reviewDao;
//		return this;
//	}
	
	public CMyPageController setDao(LikeDaoImpl likeDao) {
		this.likeDao = likeDao;
		return this;
	}
	
	@GetMapping("customer/mypage")
	public String form(HttpSession session, Model model) {
		Customer customer = (Customer)session.getAttribute("cAuthInfo");
		if(customer == null) {
			return "redirect:login";
		}else {
			String email = customer.getEmail();
			try {
				List<Order> orders = orderDao.selectOrderRecent(email);
				Customer recustomer = customerDao.SelectCByEmail(email);
				model.addAttribute("customer", recustomer);
				model.addAttribute("orders", orders);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "customer/cMypageFrom";
		}
	}
	@GetMapping("customer/like={pseq}")
	public String action(@PathVariable("pseq") int pseq, HttpServletRequest request, HttpSession session) {
		Customer customer = (Customer)session.getAttribute("cAuthInfo");
		
		if(customer == null) {
			return "redirect:login";
		}else {
			String email = customer.getEmail();
			likeDao.insertLikeTable(email, pseq);
			String referer = request.getHeader("Referer"); // ???????????? ?????? ???????????? ?????????.
			return "redirect:"+ referer;
		}
	}
	
	@GetMapping("customer/dislike={pseq}")
	public String dislikeaction(@PathVariable("pseq") int pseq, HttpServletRequest request, HttpSession session) {
		Customer customer = (Customer)session.getAttribute("cAuthInfo");
		String email = customer.getEmail();
		if(email == null) {
			return "redirect:login";
		}else {
			likeDao.disLike(email, pseq);
			String referer = request.getHeader("Referer"); // ???????????? ?????? ???????????? ?????????.
			return "redirect:"+ referer;
		}
	}
	
	@GetMapping("customer/likeList")
	public String likeListForm(HttpSession session, Model model) {
		Customer customer = (Customer)session.getAttribute("cAuthInfo");
		if(customer == null) {
			return "redirect:login";
		}else {
			String email = customer.getEmail();
			try {
				List<Like> likes = likeDao.selectLikeAll(email);
				model.addAttribute("likeList", likes);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "customer/likeForm";
		}
	}
	
	@GetMapping("customer/like/delete")
	public String likedeleteArr(HttpServletRequest request, HttpSession session, Model model, @RequestParam("likeid")ArrayList<Integer> pseqList) {
		Customer customer = (Customer)session.getAttribute("cAuthInfo");

		if(customer == null) {
			return "redirect:login";
		}else {
			String email = customer.getEmail();
			for(Integer pseq: pseqList) {
				likeDao.disLike(email, pseq);
			}
			String referer = request.getHeader("Referer"); // ???????????? ?????? ???????????? ?????????.
			return "redirect:"+ referer;
		}
	}
	//?????? ?????? ??????
	@GetMapping("customer/order/delete/odseq={odseq}&oseq={oseq}&payment={payment}")
	public String deleteOrder(HttpServletRequest request, HttpSession session, Model model, @PathVariable("odseq") int odseq,  @PathVariable("oseq") int oseq,  @PathVariable("payment") int payment) {
		
		Customer customer = (Customer)session.getAttribute("cAuthInfo");
		if(customer == null) {
			return "redirect:login";
		}else {
			orderDao.deleteOrder(odseq, oseq, payment, customer.getEmail());
			
			String referer = request.getHeader("Referer"); // ???????????? ?????? ???????????? ?????????.
			return "redirect:"+ referer;
		}
	}
	
	//???????????? ?????? - ??????????????? ????????? ?????????
	@GetMapping("customer/orderList")
	public String OrderListForm(HttpSession session, Model model) {
		Customer customer = (Customer)session.getAttribute("cAuthInfo");
		if(customer == null) {
			return "redirect:login";
		}else {
			String email = customer.getEmail();
			try {
				List<Order> orderList =  orderDao.selectOrderList(customer.getEmail());
				model.addAttribute("orderList", orderList);
				Customer recustomer = customerDao.SelectCByEmail(email);
				model.addAttribute("customer", recustomer);
				return "customer/orderListForm";
			}catch(Exception e) {
				e.printStackTrace();
				return "customer/main";
			}
		}
	}
	//???????????? ????????????
	@GetMapping("customer/change")
	public String updateCustomerForm(HttpSession session, Model model) throws Exception {
		Customer customer=(Customer)session.getAttribute("cAuthInfo");
		if(customer == null) {
			return "redirect:login";
		}
		customer =customerDao.SelectCByEmail(customer.getEmail());
		StringTokenizer st;
		String delim = "-";
		int tt = 0;
		st = new StringTokenizer(customer.getPhone(), delim, false);
		String[] tel = new String[3];
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			tel[tt++] = token;
		}
		
		model.addAttribute("tel",tel);
		model.addAttribute("customer",customer);
	      
		return "customer/changeForm";
	}

	@PostMapping("customer/change")
	public String updateCustomer(Customer customer,HttpServletRequest request) throws Exception {
		String phone="";
		phone+=request.getParameter("phone1");
		phone+="-";
		phone+=request.getParameter("phone2");
		phone+="-";
		phone+=request.getParameter("phone3");
		customer.setPhone(phone);
		
		customerDao.custoemrUpdate(customer);
		
		return "redirect:change";
		// redirect:member/list redirect:member/list
	}
	
	//?????? ????????? ?????? ?????? ?????? ????????? ????????? ?????? ????????????
	@GetMapping("customer/review")
	public String formReview(HttpSession session, Model model) {
		Customer customer = (Customer)session.getAttribute("cAuthInfo");
		if(customer == null) {
			return "redirect:login";
		}
		
		try {
			//1. ?????? ?????? ?????? ??????
			List<Order> canbeReviews = reviewDao.canBeReview(customer.getEmail());
			model.addAttribute("canbeReview", canbeReviews);
			//2. ????????? ??????
			List<Review> reviews = reviewDao.writedReviewes(customer.getEmail());
			model.addAttribute("reviews", reviews);
	 		return "customer/reviewForm";
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect:main";
		}
	}
	
	//?????? ?????? ????????? ?????????
	@GetMapping("customer/reviewAdd")
	public String form(HttpSession session, @RequestParam("odseq")int odseq, Model model) throws Exception {	
		if (session.getAttribute("cAuthInfo") != null) {
			Order order = reviewDao.selectOrderInfo(odseq);
			model.addAttribute("order", order);
			return "customer/reviewAddForm";
		}
		
		return "redirect:login";
	}
	
	//????????? ?????? ????????????
	@PostMapping("customer/reviewAdd")
	public String submit(HttpSession session,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Customer customer = (Customer)session.getAttribute("cAuthInfo");

		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String savePath = "img/rimages"; // ????????? ???????????? ???????????? ????????? ??????
			//????????? ????????????????????? ????????? ??????????????? ????????? ????????? (webapp?????? ????????? ???????????? ??????)
			int uploadFileSizeLimit = 10 * 1080 * 1920; // ?????? ????????? ?????? ?????? 10mb??? ??????
			PrintWriter out = response.getWriter();
			String encType = "UTF-8"; //????????? ?????? ??????
			ServletContext context = request.getServletContext();
			String uploadFilePath = context.getRealPath(savePath); // ???????????? ?????? ????????????
			//????????? ???????????? ???????????? ???????????? ?????? 
			MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType,
					new DefaultFileRenamePolicy());

			ArrayList<String> urlImage=new ArrayList<String>();
			Enumeration files = multi.getFileNames();
			
			while (files.hasMoreElements()) {
				String file = (String) files.nextElement();
				String file_name = multi.getFilesystemName(file);
				String ori_file_name = multi.getOriginalFileName(file);
				if(!file.equals("productImage")) {
					urlImage.add(file);} // ????????????
			}
			
			Order order = orderDao.selectOrderView(Integer.parseInt(multi.getParameter("odseq")));
			Review review = new Review();
			review.setEmail(customer.getEmail());
			review.setOdseq(Integer.parseInt(multi.getParameter("odseq")));
			review.setPseq(order.getPseq());
			review.setPname(order.getPname());
			review.setSseq(order.getSseq());
			review.setContent(multi.getParameter("detail"));
			
			
			
			
			reviewDao.insertReview(review, urlImage);


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
			
		return "redirect:review"; //???????????? 
	}
}
