package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.WorkerDaoImpl;

@Controller
@RequestMapping("worker/list")
public class WListController {
	private WorkerDaoImpl workerDao;

	public WListController setworkerDao(WorkerDaoImpl workerDao) {
		this.workerDao = workerDao;
		return this;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model model) throws Exception{
		model.addAttribute("workers", workerDao.selectList());
		return "worker/WorkerMain";
	}
}
