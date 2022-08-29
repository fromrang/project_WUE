package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.SellerDaoImpl;
import dto.Seller;
import dto.SellerFarm;
import dto.SellerInstagram;


@Controller
public class InstagramProfileController {
	@Autowired
	private SellerDaoImpl sellerDao;

//	public InstagramProfileController setcustomerDao(SellerDaoImpl sellerDao) {
//		this.sellerDao = sellerDao;
//		return this;
//	}
	
	
	@RequestMapping(value="seller/instagramProfile")
	public String form(Model model,@RequestParam(value="email")String email,HttpSession session) { 
	
		try {
		 Seller loginSeller= (Seller)session.getAttribute("authInfo");
		 SellerInstagram user=sellerDao.InstagramProfile(email);
		 List<Integer> userPostNumber=sellerDao.instagramUserPostNumber(email);
		 List<String> image=new ArrayList();
		 List<String> imageFrist=new ArrayList<String>();
		 for(Integer post:userPostNumber) {
			 image=sellerDao.instagramImages(post);
			 imageFrist.add(image.get(0));
			 
			 }
		 
		 List<SellerFarm>frampost =sellerDao.Sconfirmfarm(email);
		 List<String> framimage=new ArrayList<String>();
		 if(!frampost.isEmpty()) {
			 for(int i=0;i<1;i++) {
				 
				 framimage=sellerDao.getfarmImage(frampost.get(i).getFseq(), email);
			 }
		 }
		 
		 	
		 
		 model.addAttribute("fram", sellerDao.Sconfirmfarm(email));
		 model.addAttribute("authInfo", loginSeller);
		 model.addAttribute("userPostNumber", userPostNumber);
		 model.addAttribute("user",user);
		 model.addAttribute("imageFrist",imageFrist);
		 model.addAttribute("framimage",framimage);
		 return "seller/InstramProfile";
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}

	}


	@RequestMapping(value="seller/myinstagramProfile")
	public String myprofile(Model model,HttpSession session) { 
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
				}
		try {
			//위에 프로필 및 게시판 
		 Seller loginSeller= (Seller)session.getAttribute("authInfo");
		 SellerInstagram user=sellerDao.InstagramProfile(loginSeller.getEmail());
		 List<Integer> userPostNumber=sellerDao.instagramUserPostNumber(loginSeller.getEmail());
		 
		 List<String> image=new ArrayList();
		 List<String> imageFrist=new ArrayList<String>();
		 for(Integer post:userPostNumber) {
			 image=sellerDao.instagramImages(post);
			 imageFrist.add(image.get(0));
			 }
		 
		 //밑에 농장 사진 추가
		 List<SellerFarm>frampost =sellerDao.Sconfirmfarm(loginSeller.getEmail());
		 List<String> framimage=new ArrayList<String>();
		
		 if(!frampost.isEmpty()) {
		 for(int i=0;i<1;i++) {
			 framimage=sellerDao.getfarmImage(frampost.get(i).getFseq(), loginSeller.getEmail());
			 }
		 }
		 	
		 
		 model.addAttribute("fram", sellerDao.Sconfirmfarm(loginSeller.getEmail()));
		 model.addAttribute("authInfo", loginSeller);
		 model.addAttribute("userPostNumber", userPostNumber);
		 model.addAttribute("user",user);
		 model.addAttribute("imageFrist",imageFrist);
		 model.addAttribute("framimage",framimage);
		 return "seller/InstramProfile";
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}
	}

	
}
