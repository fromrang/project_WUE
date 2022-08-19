package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.WorkerDaoImpl;
import dto.Wnotice;

@Controller
@RequestMapping("worker/qna")
public class WQnaController {
	private WorkerDaoImpl workerdao;

	public WQnaController setNoticeDao(WorkerDaoImpl workerdao) {
		this.workerdao = workerdao;
		return this;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String list(Model model) throws Exception{		
		model.addAttribute("qna", workerdao.qnaList());
		return "worker/QnA";
	}
}
