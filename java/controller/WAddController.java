package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.WorkerDaoImpl;
import dto.Worker;

@Controller
@RequestMapping("worker/add")
public class WAddController {
	@Autowired
	private WorkerDaoImpl workerDao;

//	public WAddController setworkerDao(WorkerDaoImpl workerDao) {
//		this.workerDao = workerDao;
//		return this;
//	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String add(Worker worker, HttpServletRequest request)throws Exception {
		if(worker != null && worker.getId() != null) {
			String email="";
			email += request.getParameter("email");			
			email += request.getParameter("email2");
			worker.setEmail(email);
			String phone="";
			phone += request.getParameter("phone");
			phone += "-";
			phone += request.getParameter("phone2");
			phone += "-";
			phone += request.getParameter("phone3");
			worker.setPhone(phone);
			workerDao.insert(worker);
			return "redirect:list";
		}else {
			return "worker/WorkerAddForm";
		}
	}
	
}
