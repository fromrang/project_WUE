package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.WorkerDaoImpl;
import dto.Wnotice;

@Controller
@RequestMapping("worker/notice")
public class WNoticeController {
	private WorkerDaoImpl workerdao;

	public WNoticeController setNoticeDao(WorkerDaoImpl workerdao) {
		this.workerdao = workerdao;
		return this;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model model) throws Exception{
		model.addAttribute("notice", workerdao.noticeList());
		return "worker/Notice";
	}
}
