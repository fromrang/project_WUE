package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.CustomerDaoImpl;
import dao.SellerDaoImpl;
import dto.Customer;
import dto.Seller;

@Controller
@RequestMapping("worker/delete")
public class WDeleteController {
	@Autowired
	private CustomerDaoImpl customerDao;
	@Autowired
	private SellerDaoImpl sellerDao;

//	public WDeleteController setDao(CustomerDaoImpl customerDao, SellerDaoImpl sellerDao) {
//		this.customerDao = customerDao;
//		this.sellerDao = sellerDao;
//		return this;
//	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String delete(Customer customer, Seller seller) throws Exception{
		try{
			customerDao.delete(customer);
			sellerDao.delete(seller);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:management";
	}

}
