package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dao.ProductDao;
import dao.WorkerDao;

@Controller
public class WDiscountController {
	private ProductDao productDao;
	private WorkerDao workerDao;

	public WDiscountController setProductDao(ProductDao productDao, WorkerDao workerDao) {
		this.productDao = productDao;
		this.workerDao = workerDao;
		return this;
	}
	
	@GetMapping("worker/discount={pseq}")
	public String action(@PathVariable("pseq") int pseq, HttpServletRequest request) {
		try {
			
			workerDao.discount(pseq);
			String referer = request.getHeader("Referer"); // 헤더에서 이전 페이지를 읽는다.
			return "redirect:"+ referer;
		} catch (Exception e) {
			e.printStackTrace();
			return "worker/Error";
		}
		//return "worker/ProductKind";

	}
	
	@GetMapping("worker/notDiscount={pseq}")
	public String notDiscountaction(@PathVariable("pseq") int pseq, HttpServletRequest request) {
		try {
			
			workerDao.notDiscount(pseq);
			String referer = request.getHeader("Referer"); // 헤더에서 이전 페이지를 읽는다.
			return "redirect:"+ referer;
		} catch (Exception e) {
			e.printStackTrace();
			return "worker/Error";
		}
		//return "worker/ProductKind";

	}
}
