package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.SellerDaoImpl;
import dto.Customer;
import dto.Seller;
import dto.SellerFarm;
import dto.SellerInstagram;

@Controller
public class InstargramAllPost {
	@Autowired
	private SellerDaoImpl sellerDao;

//	public InstargramAllPost setcustomerDao(SellerDaoImpl sellerDao) {
//		this.sellerDao = sellerDao;
//		return this;
//	}

	@RequestMapping(value = "seller/instagramAll")
	public String form(HttpSession session, Model model) {

		try {
			// 개인 인스타 그램 게시물 리스트
			List<SellerInstagram> post = sellerDao.InstagramPost();
			List<String> image = new ArrayList();
			SellerInstagram profileImage = null;
			List<Integer> replycount = new ArrayList<Integer>();
			for (SellerInstagram instagram : post) {
				// 이미지사진을 넣은 것
				image = sellerDao.instagramImages(instagram.getPostnumber());
				instagram.setPostimages(image);
				// url 정보를 넣은 것
				profileImage = sellerDao.InstagramProfile(instagram.getEmail());
				instagram.setUrl(profileImage.getUrl());

				// 댓글 갯수를 넣은것
				if (sellerDao.getPostRelyCount(instagram.getPostnumber()) == null) {
					replycount.add(0);
				} else {
					replycount.add(sellerDao.getPostRelyCount(instagram.getPostnumber()));
				}

			}
			// 인스타 주말농장 리스트

			List<Seller> sellerlist = sellerDao.SellerList();
			List<SellerFarm> frampost = new ArrayList<SellerFarm>();
			List<String> framimage = new ArrayList();
			SellerInstagram framprofileImage = null;
			List<Integer> replycount2 = new ArrayList<Integer>();

			for (Seller framseller : sellerlist) {
				frampost = sellerDao.Sconfirmfarm(framseller.getEmail());
				framseller.setFramList(frampost);
			}

			int count = 0;
			for (int i = 0; i < sellerlist.size(); i++) {
				count = 0;
				// System.out.println(sellerlist.get(i).getFramList().size());
				for (int j = 0; j < sellerlist.get(i).getFramList().size(); j++) {
					// 이미지사진을 넣은 것
					image = sellerDao.getfarmImage(sellerlist.get(i).getFramList().get(j).getFseq(),
							sellerlist.get(i).getEmail());
					sellerlist.get(i).getFramList().get(j).setPostimages(image);
					// url 정보를 넣은 것
					profileImage = sellerDao.InstagramProfile(sellerlist.get(i).getEmail());
					sellerlist.get(i).getFramList().get(j).setUrl(profileImage.getUrl());
					// 주말농장 댓글 갯수넣은것

					if (sellerDao.getRramRelyCount(sellerlist.get(i).getFramList().get(j).getFseq()) == null) {
						count += 0;
					} else {
						count += sellerDao.getRramRelyCount(sellerlist.get(i).getFramList().get(j).getFseq());

						;
					}
				}
				replycount2.add(count);
			}

			model.addAttribute("posting", post);
			model.addAttribute("posting2", sellerlist);
			model.addAttribute("replycount1", replycount);
			model.addAttribute("replycount2", replycount2);

			return "seller/InstramList";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}
	}

	// 게시물 선택할시
	@RequestMapping(value = "seller/instagramSelectPost")
	public String form2(HttpSession session, Model model, HttpServletRequest requset) {

		try {
			SellerInstagram user = sellerDao.InstagramProfile(requset.getParameter("email"));
			;
			Seller loginSeller = (Seller) session.getAttribute("authInfo");

			// 개인 인스타 그램 게시물 리스트
			SellerInstagram post = sellerDao.getpostSelect(requset.getParameter("email"),
			Integer.valueOf(requset.getParameter("postnumber")));
			List<String> image = new ArrayList();
			SellerInstagram profileImage = null;

			// 이미지사진을 넣은 것
			image = sellerDao.instagramImages(post.getPostnumber());
			post.setPostimages(image);
			// url 정보를 넣은 것
			profileImage = sellerDao.InstagramProfile(post.getEmail());
			post.setUrl(profileImage.getUrl());

			// 댓글 갯수 정보 넣은것
			int replycount = sellerDao.getPostRelyCount(post.getPostnumber());

			List<SellerInstagram> replyList = sellerDao.getPostRely(post.getPostnumber());

			model.addAttribute("authInfo", loginSeller);
			model.addAttribute("user", user);
			model.addAttribute("posting", post);
			model.addAttribute("replycount", replycount);
			model.addAttribute("replyList", replyList);

			return "seller/InstramListSelect";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}

	}

	// 농장게시물 선택할시
	@RequestMapping(value = "seller/instagramSelectfarm")
	public String form3(HttpSession session, Model model, HttpServletRequest requset) {

		try {
			SellerInstagram user = sellerDao.InstagramProfile(requset.getParameter("email"));
			;
			Seller loginperson = (Seller) session.getAttribute("authInfo");

			List<String> image = new ArrayList();
			SellerInstagram profileImage = null;

			// 인스타 주말농장 리스트

			Seller framseller = new Seller();
			framseller.setEmail(requset.getParameter("email"));
			List<SellerFarm> frampost = new ArrayList<SellerFarm>();

			frampost = sellerDao.Sconfirmfarm(framseller.getEmail());
			framseller.setFramList(frampost);
			int replycount = 0;

			SellerFarm replyList = new SellerFarm();
			for (int j = 0; j < framseller.getFramList().size(); j++) {
				// 이미지사진을 넣은 것
				image = sellerDao.getfarmImage(framseller.getFramList().get(j).getFseq(), framseller.getEmail());
				framseller.getFramList().get(j).setPostimages(image);
				// url 정보를 넣은 것
				profileImage = sellerDao.InstagramProfile(framseller.getEmail());
				framseller.getFramList().get(j).setUrl(profileImage.getUrl());

				replycount += sellerDao.getRramRelyCount(framseller.getFramList().get(j).getFseq());

				for (SellerFarm si : sellerDao.getRramRely(framseller.getFramList().get(j).getFseq())) {
					replyList.replyList(si);

				}

			}

			model.addAttribute("replyList", replyList);
			model.addAttribute("authInfo", loginperson);
			model.addAttribute("user", user);
			model.addAttribute("posting2", framseller);
			model.addAttribute("replycount", replycount);

			return "seller/InstramListSelect";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}

	}

	@RequestMapping(value = "seller/postAddreply")
	public String form4(HttpSession session, Model model, HttpServletRequest requset) {
		if (session.getAttribute("cAuthInfo") == null && session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
		}
		Seller loginuser=new Seller();
		Customer cloginuser=new Customer();
		String email="";

		if(session.getAttribute("cAuthInfo") == null) {
		 loginuser = (Seller) session.getAttribute("authInfo");
		 email=loginuser.getEmail();
		}else {
			cloginuser=(Customer) session.getAttribute("cAuthInfo");
			email=cloginuser.getEmail();
		}

		try {
			SellerInstagram reply = new SellerInstagram();
			reply.setContent(requset.getParameter("content"));

			

			sellerDao.SAddinstaRely(Integer.valueOf(requset.getParameter("postnumber")), email,
					requset.getParameter("content"));

			return "redirect:" + requset.getHeader("Referer");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}

	}

	@RequestMapping(value = "seller/farmAddreply")
	public String form5(HttpSession session, Model model, HttpServletRequest requset) {
		if (session.getAttribute("cAuthInfo") == null && session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
		}
		Seller loginuser=new Seller();
		Customer cloginuser=new Customer();
		String email="";

		if(session.getAttribute("cAuthInfo") == null) {
		 loginuser = (Seller) session.getAttribute("authInfo");
		 email=loginuser.getEmail();
		}else {
			cloginuser=(Customer) session.getAttribute("cAuthInfo");
			email=cloginuser.getEmail();
		}

		try {
			SellerInstagram reply = new SellerInstagram();
			reply.setContent(requset.getParameter("content"));
			reply.setEmail(email);

//		 System.out.println(reply.getContent());
//		 System.out.println(requset.getParameter("fseq"));

		
				sellerDao.SAddfarmRely(Integer.valueOf(requset.getParameter("fseq")), reply.getEmail(),
						reply.getContent());
			

			return "redirect:" + requset.getHeader("Referer");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}

	}

}
