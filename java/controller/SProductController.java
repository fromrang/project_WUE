package controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import dao.ProductDao;
import dto.Product;
import dto.Seller;

@Controller
public class SProductController {
	private ProductDao productDao;
	
	public SProductController setProductDao(ProductDao productDao) {
		this.productDao = productDao;
		return this;
	}
	
	
	@RequestMapping(value="seller/ProuctList")
	public String form(HttpSession session, Model model) throws Exception { //session 쓰고 싶으면 넣어주면 된다. 여기서 인자 첫번째로 Member을 넣어주었기 때문에 위에 주석 처리한 부분이 필요 없다.
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
				}

		Seller seller=(Seller)session.getAttribute("authInfo");
		List<Product> sellproduct =productDao.selectSellProductList(String.valueOf(seller.getSseq()));
		List<String> image =new ArrayList<String>();
		List<String> imageFrist=new ArrayList<String>();
		 
		for(Product url:sellproduct) {
			image=productDao.selectAllImage(url.getPseq());
			imageFrist.add(image.get(0));
		}
		
		List<Product> readyproduct=productDao.selectReadyProductList(String.valueOf(seller.getSseq()));
		
		List<String> image2 =new ArrayList<String>();
		List<String> imageFrist2=new ArrayList<String>();
		 

		for(Product url:readyproduct) {
			image2=productDao.selectAllImage(url.getPseq());
			imageFrist2.add(image2.get(0));
//			System.out.println(image2.get(0));
		}
		
		model.addAttribute("Sellproduct", sellproduct);
		model.addAttribute("Sellimage", imageFrist);
		model.addAttribute("readyproduct", readyproduct);
		model.addAttribute("Sellimage2", imageFrist2);
		
		return "seller/SProductListForm";
	}

	
	@RequestMapping(value="seller/ProuctUpdateForm")
	public String form2(HttpSession session, Model model,HttpServletRequest request) throws Exception { //session 쓰고 싶으면 넣어주면 된다. 여기서 인자 첫번째로 Member을 넣어주었기 때문에 위에 주석 처리한 부분이 필요 없다.
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
				}

		Seller seller=(Seller)session.getAttribute("authInfo");
		Product sellproduct =productDao.selectProuctupdate(request.getParameter("pseq"));
		
	
		
		model.addAttribute("product", sellproduct);
		model.addAttribute("pseq",request.getParameter("pseq"));
		
		return "seller/SUpdateProductForm";
	}
	
	@RequestMapping(value="seller/ProuctUpdate")
	public String form3(HttpSession session, Model model,HttpServletRequest request) throws Exception { //session 쓰고 싶으면 넣어주면 된다. 여기서 인자 첫번째로 Member을 넣어주었기 때문에 위에 주석 처리한 부분이 필요 없다.
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
				}
		Product update=new Product();
		update.setCost(Integer.valueOf(request.getParameter("cost")));
		update.setKind(Integer.valueOf(request.getParameter("kind")));
		update.setPrice(Integer.valueOf(request.getParameter("price")));
		update.setSale_price(Integer.valueOf(request.getParameter("sale_price")));
		update.setQuantity(Integer.valueOf(request.getParameter("quantity")));
		update.setPseq(Integer.valueOf(request.getParameter("pseq")));
		update.setName(request.getParameter("name"));
		
//		System.out.println(update.getCost());
//		System.out.println(update.getKind());
//		System.out.println(update.getPrice());
//		System.out.println(update.getSale_price());
//		System.out.println(update.getQuantity());
//		System.out.println(update.getPseq());
//		System.out.println(update.getName());

		productDao.SProductUpdate(update);
		
		return "redirect:ProuctList";
	}

}
