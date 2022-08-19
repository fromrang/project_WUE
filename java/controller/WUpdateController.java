package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.WorkerDaoImpl;
import dto.Worker;


@Controller
@RequestMapping("worker/update")
public class WUpdateController {
	private WorkerDaoImpl workerDao;

	public WUpdateController setworkerDao(WorkerDaoImpl workerDao) {
		this.workerDao = workerDao;
		return this;
	}
	@RequestMapping(method = RequestMethod.GET)
	public String form(@RequestParam(value = "id")String id, Model model) {
		try {
			model.addAttribute("worker", workerDao.selectOne(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "worker/WorkerUpdate";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(Worker worker) throws Exception {
		try {
			workerDao.update(worker);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:list";	
	}
	
}
