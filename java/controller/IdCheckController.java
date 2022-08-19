package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.WorkerDaoImpl;
import dto.Worker;

@Controller
@RequestMapping("worker/idcheck")
public class IdCheckController {
	private WorkerDaoImpl workerDao;

	public IdCheckController setworkerDao(WorkerDaoImpl workerDao) {
		this.workerDao = workerDao;
		return this;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String idcheck(Worker worker, Model model) throws Exception {
		Worker workers = workerDao.idCheck(worker.getId());
		model.addAttribute("worker", workers);
		return "worker/IdCheck";
	}

}
