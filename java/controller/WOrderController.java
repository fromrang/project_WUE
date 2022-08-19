package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.OrderDaoImpl;
import dao.SellerDaoImpl;
import dto.Order;


@Controller
//@RequestMapping("worker/order")
public class WOrderController {
private OrderDaoImpl orderDaoImpl;
private SellerDaoImpl sellerDao;

	public WOrderController setOrderDaoImpl(OrderDaoImpl orderDaoImpl,SellerDaoImpl sellerDao) {
		this.orderDaoImpl = orderDaoImpl;
		this.sellerDao = sellerDao;
		return this;
	}
	
	//주문번호 적용안한 전체 
	@RequestMapping(value = "worker/order")
		public String form(@RequestParam(value="sseq")int sseq, Model model) throws Exception { 
			List<Order> order = orderDaoImpl.SOseqOrderList(sseq);
			List<Integer> sumPrice = orderDaoImpl.STotalPrice(sseq);
			List<Integer> count = orderDaoImpl.SOseqCountList(sseq);		
			
			for(int i=0;i<count.size();i++) {
				if(count.get(i)>1) {
					order.get(i).setPname(order.get(i).getPname()+"외 "+ (count.get(i)-1)+"건");
				}
			}

			model.addAttribute("order", order);
			model.addAttribute("sum", sumPrice);
			model.addAttribute("sseq",sseq);
					
			return "worker/OrderManagement";
		}

	//주문번호별
		@RequestMapping(value = "worker/order_catagory")
		public String form2(Model model,@RequestParam(value="sseq")int sseq,@RequestParam(value="result")int result) throws Exception { 
			List<Order> order=orderDaoImpl.SOseqOrderList2(sseq,result);
			List<Integer> sumPrice=orderDaoImpl.STotalPrice2(sseq,result);
			List<Integer> count=orderDaoImpl.SOseqCountList2(sseq,result);
			System.out.println(order);
			
			for(int i=0;i<count.size();i++) {
				if(count.get(i)>1) {
					order.get(i).setPname(order.get(i).getPname()+"외 "+ (count.get(i)-1)+"건");
				}
			}

			model.addAttribute("order", order);
			model.addAttribute("sum", sumPrice);
			model.addAttribute("sseq",sseq);
					
			return "worker/OrderManagement";
		}
		
		
		@RequestMapping(value = "worker/seller_order")
		public String form3(Model model)throws Exception{
			model.addAttribute("seller", sellerDao.selectList());
			return "worker/OrderMain";
		}
	
}
