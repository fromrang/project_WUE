package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.WorkerDaoImpl;

@Controller
public class WSalesController {
	@Autowired
	private WorkerDaoImpl workerDao;
//	public WSalesController setDao(WorkerDaoImpl workerdao) {
//		this.workerDao = workerdao;
//		return this;
//	}
	
	//@RequestMapping(method = RequestMethod.GET)
	@RequestMapping(value ="worker/sales")
	public String list(Model model)throws Exception{
		//customer
		model.addAttribute("sales", workerDao.selectSales());	
		model.addAttribute("salesbyname", workerDao.sales());
		model.addAttribute("date_sales", workerDao.dateSales());
		// seller
		model.addAttribute("seller1Sales", workerDao.Seller1Sales());	
		model.addAttribute("seller2Sales", workerDao.Seller2Sales());
		model.addAttribute("seller3Sales", workerDao.Seller3Sales());
		model.addAttribute("seller4Sales", workerDao.Seller4Sales());
		model.addAttribute("seller5Sales", workerDao.Seller5Sales());
		model.addAttribute("seller6Sales", workerDao.Seller6Sales());
		return "worker/Performance";
	}
	
	@RequestMapping(value = "worker/expenditure")
	public String data()throws Exception{
		return "worker/Expenditure";
	}
	
	@RequestMapping(value = "worker/event")
	public String event()throws Exception{
		return "worker/Event";
	}
}
