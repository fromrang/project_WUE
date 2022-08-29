package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.WorkerDaoImpl;
import dto.Wnotice;

@Controller
@RequestMapping("worker/board_update")
public class WBoardUpdateController {
	@Autowired
	private WorkerDaoImpl workerdao;

//	public WBoardUpdateController setNoticeDao(WorkerDaoImpl workerdao) {
//		this.workerdao = workerdao;
//		return this;
//	}

	@RequestMapping(method = RequestMethod.GET)
//	@GetMapping("worker/board_update")
//	public String form(@RequestParam(value = "wseq") int wseq, Model model) throws Exception {
	public String form(@RequestParam("wseq")int wseq, Model model) throws Exception {	
		try {
			System.out.println(wseq);
			model.addAttribute("board", workerdao.select(wseq));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "worker/BoardUpdate";

	}

	@RequestMapping(method = RequestMethod.POST)
//	@PostMapping("worker/board_update")
	public String submit(Wnotice notice) throws Exception {
		System.out.println(notice.getWseq());
		try {
			workerdao.update(notice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:notice";

	}

}
