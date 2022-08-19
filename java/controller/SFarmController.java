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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.SellerDaoImpl;
import dto.Seller;
import dto.SellerFarm;
import dto.SellerInstagram;

@Controller
public class SFarmController {
	private SellerDaoImpl sellerDao;

	public SFarmController setcustomerDao(SellerDaoImpl sellerDao) {
		this.sellerDao = sellerDao;
		return this;
	}
	
	
	@RequestMapping(value="seller/farmForm")
	public String form6(HttpSession session,Model model) throws Exception {	
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
			}
		return "seller/AddFarmPulsForm";
	}
	
	@RequestMapping(value="seller/farmfist")
	public String form(HttpSession session,Model model) throws Exception {	
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
			}
       
		return "seller/FarmAddForm";
	}
	
	
	@RequestMapping(value="seller/farmAdd",method = RequestMethod.POST)
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
			}
			
			SellerFarm farm=new SellerFarm();
			farm.setEmail(seller.getEmail());
			farm.setExperience_date( multi.getParameter("experience_date"));
			farm.setExperience_time(multi.getParameter("experience_time"));
			farm.setExperience_number(Integer.valueOf(multi.getParameter("experience_number")));
			farm.setExperience_price(Integer.valueOf(multi.getParameter("experience_price")));
			farm.setExperience_context(multi.getParameter("experience_context"));
			
			
			sellerDao.SfarmAdd(farm);
			
		    int fseq=sellerDao.selectFseqByAdd();
			
			for(String url:urlImage) {
				sellerDao.SAddfarmImage(url, fseq, seller.getEmail());
			}
			
			 model.addAttribute("email",seller.getEmail());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "redirect:myinstagramProfile";
	}
	
	@RequestMapping(value="seller/farmPuls")
	public String AddPuls(Model model,HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception {	
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
			}
		Seller seller=(Seller)session.getAttribute("authInfo");

		
		try {
			SellerFarm farm=new SellerFarm();
			farm.setEmail(seller.getEmail());
			farm.setExperience_date( request.getParameter("experience_date"));
			farm.setExperience_time(request.getParameter("experience_time"));
			farm.setExperience_number(Integer.valueOf(request.getParameter("experience_number")));
			farm.setExperience_price(Integer.valueOf(request.getParameter("experience_price")));
			
//			System.out.println(request.getParameter("experience_date"));

			sellerDao.SfarmAdd(farm);
			
		   
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "redirect:myinstagramProfile";
	}
	
	
	@RequestMapping(value="seller/farmUpdateForm")
	public String form3(HttpSession session,Model model) throws Exception {	
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
			}
		Seller seller=(Seller)session.getAttribute("authInfo");
		
		  List<Seller> sellerlist=sellerDao.SellerList();
		     List<SellerFarm> farmpost=new ArrayList<SellerFarm>();
			 List<String> farmimage=new ArrayList();
			 SellerInstagram farmprofileImage=null;
		     
		     for(Seller farmseller:sellerlist) {
		    	 
		    	 farmpost =sellerDao.Sconfirmfarm(seller.getEmail());
		    	 farmseller.setFramList(farmpost);
		     }
		    
		     model.addAttribute("posting2",sellerlist);
		return "seller/FarmUpdateFrom";
	}
	
	@RequestMapping(value="seller/farmUpdate")
	public String form4(@RequestParam(value="experience_date")ArrayList<String> experience_date,
			@RequestParam(value="experience_time")ArrayList<String> experience_time,
			@RequestParam(value="experience_price")ArrayList<Integer> experience_price,
			@RequestParam(value="experience_number")ArrayList<Integer> experience_number,
			@RequestParam(value="fseq")ArrayList<Integer> fseq,
			HttpServletRequest request,HttpSession session) throws Exception {	
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
			}
		Seller seller=(Seller)session.getAttribute("authInfo");
		for(int i=0;i<fseq.size();i++) {
			if(i==0) {
			sellerDao.UpdateInstafarm(fseq.get(i), experience_time.get(i),experience_price.get(i) , experience_date.get(i), experience_number.get(i), request.getParameter("experience_context"));
			}
			if(i>0) {
			 sellerDao.UpdateInstafarm(fseq.get(i), experience_time.get(i),experience_price.get(i) , experience_date.get(i), experience_number.get(i), "");
			}
//			System.out.println(experience_date.get(i));
//			System.out.println(experience_number.get(i));
//			System.out.println(experience_price.get(i));
//			System.out.println(experience_time.get(i));
//			System.out.println(fseq.get(i));
		}

		return "redirect:instagramSelectfarm?email="+seller.getEmail();
	}
	
	@RequestMapping(value="seller/deltefarm")
	public String delete2(HttpSession session,HttpServletRequest repuset) throws Exception {	
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
			}
		Seller seller=(Seller)session.getAttribute("authInfo");
		
		 String farm=repuset.getParameter("fseq");
		 sellerDao.SfarmDelte(Integer.valueOf(farm));
		 
		return "redirect:myinstagramProfile";
	}
	
	@RequestMapping(value="seller/deltefarmAll")
	public String delete(HttpSession session,HttpServletRequest repuset,@RequestParam(value="fseq")ArrayList<Integer> fseq) throws Exception {	
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
			}
		Seller seller=(Seller)session.getAttribute("authInfo");
		
		 for(int farms:fseq) {
			 sellerDao.SfarmDelte(farms);
			 sellerDao.SfarmImageDelte(farms);
		 }
		    
		     
		return "redirect:myinstagramProfile";
	}
}
