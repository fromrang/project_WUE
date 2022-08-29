package controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.SellerDao;
import dao.SellerDaoImpl;
import dto.Seller;
import dto.SellerInstagram;

@Controller
public class InstaPostController {
	@Autowired
	private SellerDaoImpl sellerDao;

//	public InstaPostController setcustomerDao(SellerDaoImpl sellerDao) {
//		this.sellerDao = sellerDao;
//		return this;
//	}
	
	@RequestMapping(value="seller/PostAdd")
	public String form6(HttpSession session,Model model) throws Exception {	
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
			}
		return "seller/SInstaPostAddForm";
	}
	
	@RequestMapping(value="seller/instaPostAdd",method = RequestMethod.POST)
	public String form2(Model model,HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception {	
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
			}
		Seller seller=(Seller)session.getAttribute("authInfo");

		
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String savePath = "img"; // 여기를 바꿔주면 다운받는 경로가 바뀜
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
					urlImage.add(file);}
//				System.out.print(file);
			}
			
			int postnumber=sellerDao.selectPostNumberByAdd();
			SellerInstagram post=new SellerInstagram();
			post.setEmail(seller.getEmail());
			post.setPostnumber(postnumber+1);
			post.setContext(multi.getParameter("context"));
			
			sellerDao.SAddinstaPost(post.getPostnumber(), seller.getEmail(), post.getContext());
			
			for(String url:urlImage) {
//				System.out.println(url);
				sellerDao.SAddPostImage(url, post.getPostnumber(),seller.getEmail());
			}
			return "redirect:myinstagramProfile";

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}

	}
	
	@RequestMapping(value="seller/InstaPostUpdateForm")
	public String form3(HttpSession session,Model model,HttpServletRequest requset) throws Exception {	
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
			}
		Seller seller=(Seller)session.getAttribute("authInfo");
	
		 SellerInstagram post=sellerDao.getpostSelect(seller.getEmail(),Integer.valueOf(requset.getParameter("postnumber")));
		    
//		 model.addAttribute("email",seller.getEmail());
		     model.addAttribute("post",post);
		return "seller/SInstaPostUpdateForm";
	}
	
	@RequestMapping(value="seller/InstaPostUpdate")
	public String form4(HttpServletRequest  request,HttpSession session) throws Exception {	
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
			}
		 Seller seller=(Seller)session.getAttribute("authInfo");
//		System.out.println(request.getParameter("context"));
//		System.out.println(request.getParameter("postnumber"));
     	sellerDao.SPostUpdate(Integer.valueOf(request.getParameter("postnumber")),request.getParameter("context"));
		
     	return "redirect:instagramSelectPost?postnumber="+request.getParameter("postnumber")
     	+"&email="+seller.getEmail();
	}
	
	@RequestMapping(value="seller/InstaPostDelete")
	public String form5(HttpServletRequest request,HttpSession session) throws Exception {	
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
			}
		sellerDao.SinstaPostDelte(Integer.valueOf(request.getParameter("postnumber")));
		sellerDao.SPostImageDelte(Integer.valueOf(request.getParameter("postnumber")));
		sellerDao.SPostRelyeDelte(Integer.valueOf(request.getParameter("postnumber")));
		
		return "redirect:myinstagramProfile";
	}
}
