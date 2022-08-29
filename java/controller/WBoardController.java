package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.WorkerDaoImpl;
import dto.Wnotice;

@Controller
//@RequestMapping("worker/board")
public class WBoardController {	
	@Autowired
	private WorkerDaoImpl workerdao;

//	public WBoardController setNoticeDao(WorkerDaoImpl workerdao) {
//		this.workerdao = workerdao;
//		return this;
//	}
	@GetMapping("worker/board")
	public String form() throws Exception{
		return "worker/BulletinBoard";
	}
	//@RequestMapping(method=RequestMethod.GET)
	@PostMapping("worker/board")
	public String reg(Wnotice notice, Model model) throws Exception{
		workerdao.insert(notice);
		return "redirect:list";
	}
	
	@GetMapping("worker/board_delete")
	public String delete(@RequestParam("wseq")int wseq, Model model, HttpServletRequest request)throws Exception{
		System.out.println("!!!wdwd"+wseq);
		workerdao.delete(wseq);
		String referer = request.getHeader("Referer"); // ������� ���� �������� �д´�.
        return "redirect:"+ referer;

		
	}
}
