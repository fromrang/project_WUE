package controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	private CustomerDaoImpl customerDao;
	private LikeDaoImpl likeDao; 
	private OrderDaoImpl orderDao;
	private ReviewDaoImpl reviewDao;
	public CMyPageController setDao(CustomerDaoImpl customerDao, LikeDaoImpl likeDao, OrderDaoImpl orderDao, ReviewDaoImpl reviewDao) {
		this.customerDao = customerDao;
		this.likeDao = likeDao;
		this.orderDao = orderDao;
		this.reviewDao = reviewDao;
		return this;
	}
	
	public CMyPageController setDao(LikeDaoImpl likeDao) {
		this.likeDao = likeDao;
		return this;
	}
	
	@GetMapping("customer/mypage")
	public String form(HttpSession session, Model model) {
		Customer customer = (Customer)session.getAttribute("authInfo");
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
		Customer customer = (Customer)session.getAttribute("authInfo");
		
		if(customer == null) {
			return "redirect:login";
		}else {
			String email = customer.getEmail();
			likeDao.insertLikeTable(email, pseq);
			String referer = request.getHeader("Referer"); // 헤더에서 이전 페이지를 읽는다.
			return "redirect:"+ referer;
		}
	}
	
	@GetMapping("customer/dislike={pseq}")
	public String dislikeaction(@PathVariable("pseq") int pseq, HttpServletRequest request, HttpSession session) {
		Customer customer = (Customer)session.getAttribute("authInfo");
		String email = customer.getEmail();
		if(email == null) {
			return "redirect:login";
		}else {
			likeDao.disLike(email, pseq);
			String referer = request.getHeader("Referer"); // 헤더에서 이전 페이지를 읽는다.
			return "redirect:"+ referer;
		}
	}
	
	@GetMapping("customer/likeList")
	public String likeListForm(HttpSession session, Model model) {
		Customer customer = (Customer)session.getAttribute("authInfo");
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
		Customer customer = (Customer)session.getAttribute("authInfo");

		if(customer == null) {
			return "redirect:login";
		}else {
			String email = customer.getEmail();
			for(Integer pseq: pseqList) {
				likeDao.disLike(email, pseq);
			}
			String referer = request.getHeader("Referer"); // 헤더에서 이전 페이지를 읽는다.
			return "redirect:"+ referer;
		}
	}
	//주문 취소 하기
	@GetMapping("customer/order/delete/odseq={odseq}&oseq={oseq}&payment={payment}")
	public String deleteOrder(HttpServletRequest request, HttpSession session, Model model, @PathVariable("odseq") int odseq,  @PathVariable("oseq") int oseq,  @PathVariable("payment") int payment) {
		
		Customer customer = (Customer)session.getAttribute("authInfo");
		if(customer == null) {
			return "redirect:login";
		}else {
			orderDao.deleteOrder(odseq, oseq, payment);
			
			String referer = request.getHeader("Referer"); // 헤더에서 이전 페이지를 읽는다.
			return "redirect:"+ referer;
		}
	}
	
	//구매내역 보기 - 배송완료된 상품만 볼거임
	@GetMapping("customer/orderList")
	public String OrderListForm(HttpSession session, Model model) {
		Customer customer = (Customer)session.getAttribute("authInfo");
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
	//회원정보 변경하기
	@GetMapping("customer/change")
	public String changeCustomerInfo(HttpSession session, Model model) {
		return "customer/changeForm";
	}
	//후기 클릭시 후기 작성 가능 상품과 작성한 후기 띄워주기
	@GetMapping("customer/review")
	public String formReview(HttpSession session, Model model) {
		Customer customer = (Customer)session.getAttribute("authInfo");
		if(customer == null) {
			return "redirect:login";
		}
		
		try {
			//1. 후기 작성 가능 상품
			List<Order> canbeReviews = reviewDao.canBeReview(customer.getEmail());
			model.addAttribute("canbeReview", canbeReviews);
			//2. 작성한 후기
			List<Review> reviews = reviewDao.writedReviewes(customer.getEmail());
			model.addAttribute("reviews", reviews);
	 		return "customer/reviewForm";
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect:main";
		}
	}
	
	//후기 작성 페이지 띄우기
	@GetMapping("customer/reviewAdd")
	public String form(HttpSession session, @RequestParam("odseq")int odseq, Model model) throws Exception {	
		if (session.getAttribute("authInfo") != null) {
			Order order = reviewDao.selectOrderInfo(odseq);
			model.addAttribute("order", order);
			return "customer/reviewAddForm";
		}
		
		return "redirect:login";
	}
	
	//작성한 후기 등록하기
	@PostMapping("customer/reviewAdd")
	public String submit(HttpSession session,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Customer customer = (Customer)session.getAttribute("authInfo");

		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String savePath = "img/rimages"; // 여기를 바꿔주면 다운받는 경로가 바뀜
			//폴더를 만들어줘야하며 그래야 실제경로에 파일이 올라감 (webapp에는 파일이 올라가지 않음)
			int uploadFileSizeLimit = 10 * 1080 * 1920; // 최대 업로드 파일 크기 10mb로 제한
			PrintWriter out = response.getWriter();
			String encType = "UTF-8"; //인코딩 방식 지정
			ServletContext context = request.getServletContext();
			String uploadFilePath = context.getRealPath(savePath); // 서버상의 실체 디렉토리
			//실제로 올라가면 지정되어 저장되는 경로 
			MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType,
					new DefaultFileRenamePolicy());

			ArrayList<String> urlImage=new ArrayList<String>();
			Enumeration files = multi.getFileNames();
			
			while (files.hasMoreElements()) {
				String file = (String) files.nextElement();
				String file_name = multi.getFilesystemName(file);
				String ori_file_name = multi.getOriginalFileName(file);
				if(!file.equals("productImage")) {
					urlImage.add(file);} // 추가부분
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
		
			
		return "redirect:review"; //수정부분 
	}
}
