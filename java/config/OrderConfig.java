package config;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.OdInsertController;
import controller.SOrderDetailController;
import controller.SOrderListController;
import dao.OrderDaoImpl;

@Configuration
public class OrderConfig {
	//@Autowired
	private OrderDaoImpl orderDaoImpl;
	private SqlSessionFactory sqlSessionFactory;
	
	public OrderConfig() {
		try {
			String resource = "dao/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		}catch(Exception e) {e.printStackTrace();}
	}
//	@Bean
//	public OrderDaoImpl orderDaoImpl() {
//		OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
//		orderDaoImpl.setDataSource(sqlSessionFactory);
//		return orderDaoImpl;
//	}
//	@Bean
//	public OdInsertController odInsertController() {
//		OdInsertController odInsertController = new OdInsertController();
//		odInsertController.setOrderDaoImpl(orderDaoImpl);
//		return odInsertController;
//	}
//
//	
//	//========================================seller================
//	@Bean
//	public SOrderListController SOrderListController() {
//		SOrderListController SOrderListController = new SOrderListController();
//		SOrderListController.setOrderDaoImpl(orderDaoImpl);
//		return SOrderListController;
//	}
//
//	@Bean
//	public SOrderDetailController SOrderDetailController() {
//		SOrderDetailController SOrderDetailController = new SOrderDetailController();
//		SOrderDetailController.setOrderDaoImpl(orderDaoImpl);
//		return SOrderDetailController;
//	}
}
