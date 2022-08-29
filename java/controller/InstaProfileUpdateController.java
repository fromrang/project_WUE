package controller;

import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.SellerDaoImpl;
import dto.SellerInstagram;

@Controller
@RequestMapping("seller/instagramProfileUpdate")
public class InstaProfileUpdateController {
	@Autowired
	private SellerDaoImpl sellerDao;

//	public InstaProfileUpdateController setcustomerDao(SellerDaoImpl sellerDao) {
//		this.sellerDao = sellerDao;
//		return this;
//	}
	@RequestMapping(method = RequestMethod.GET)
	public String form(HttpSession session,@RequestParam(value="seller_email")String seller_email,Model model) throws Exception {		
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
				}
		
		SellerInstagram	member=sellerDao.InstagramProfile(seller_email);
		model.addAttribute("user", member);
		return "seller/InstaProfileUpdateForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submit(SellerInstagram updateSeller,HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
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
		updateSeller.setComment(multi.getParameter("commnet"));
		updateSeller.setWebSite(multi.getParameter("webSite"));
		updateSeller.setSeller_email(multi.getParameter("seller_email"));
		System.out.println(multi.getParameter("commnet"));
		
		SellerInstagram seller=sellerDao.InstagramProfile(updateSeller.getSeller_email());


		if(seller.getUrl()!=multi.getFilesystemName("img")) {
			try {
				if(!multi.getFilesystemName("img").isEmpty()) {
				Enumeration files = multi.getFileNames();
				while (files.hasMoreElements()) {
					String file = (String) files.nextElement();
					String file_name = multi.getFilesystemName(file);
					String ori_file_name = multi.getOriginalFileName(file);
					System.out.println("name: "+file_name);
					updateSeller.setUrl(file_name);	
				}
				sellerDao.UpdateInstaSeller(updateSeller);
			}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return "redirect:myinstagramProfile";
	}


	
	

}
