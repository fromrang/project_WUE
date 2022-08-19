package controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.ProductDao;

@Controller
@RequestMapping("seller/ProductListDelte")
public class SProductDeleteController {
    private ProductDao productDao;
	
	public SProductDeleteController setProductDao(ProductDao productDao) {
		this.productDao = productDao;
		return this;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String form(HttpSession session,@RequestParam(value="pseq")int pseq) throws Exception {
		if (session.getAttribute("authInfo") == null) {
			return "redirect:Slogin";
				}
		
		
		productDao.SProudctDelte(pseq);
		productDao.SProudctImageDelte(pseq);
		return "redirect:ProuctList";
	}
	

}
