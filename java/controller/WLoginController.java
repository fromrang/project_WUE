package controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.WorkerDaoImpl;
import dto.Worker;

@Controller
@RequestMapping("worker/login")
public class WLoginController {
	@Autowired
	private WorkerDaoImpl workerDao;

//	public WLoginController setworkerDao(WorkerDaoImpl workerDao) {
//		this.workerDao = workerDao;
//		return this;
//	}

	@RequestMapping(method = RequestMethod.GET)
	public String form(Worker loginCommand, HttpSession session, Model model) {
		if (session.getAttribute("authInfo") != null) {
			if (((Worker) session.getAttribute("authInfo")).getId() != loginCommand.getId()) {
				return "worker/WorkerLogin";
			} 
			return "redirect:list";
		}
		return "worker/WorkerLogin";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(Worker loginCommand, Errors errors, HttpSession session, HttpServletResponse response,
			Model model) {
		if (errors.hasErrors()) {
			return "worker/Error";
		}
		try {
			
			Worker authInfo = workerDao.exist(loginCommand.getId(), loginCommand.getPw());
			if (authInfo == null) {
				model.addAttribute("emptyMember", "존재하지 않은 관리자입니다.");
				return "worker/WorkerLogin";
			}

			session.setAttribute("authInfo", authInfo);
			return "redirect:list";

		} catch (Exception e) {
			e.printStackTrace();
			return "worker/WorkerLogin";
		}
	}
}
