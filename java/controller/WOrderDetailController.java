package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.OrderDaoImpl;
import dto.Order;

@Controller
public class WOrderDetailController {
	@Autowired
	private OrderDaoImpl orderDaoImpl;
	
//	public WOrderDetailController setOrderDaoImpl(OrderDaoImpl orderDaoImpl) {
//		this.orderDaoImpl = orderDaoImpl;
//		return this;
//	}
	
	//�ֹ���ȣ �� 
		@RequestMapping(value = "worker/order_detail")
		public String form(Model model,@RequestParam(value="sseq")int sseq,@RequestParam(value="oseq") int oseq) throws Exception {
			List<Order> order=orderDaoImpl.SOrderList(sseq,oseq);
			int payment=orderDaoImpl.SOrderListCount(sseq,oseq);
			model.addAttribute("order", order);
			model.addAttribute("sum", payment);
				
			return "worker/OrderDetail";
		}
	
	
	
}
