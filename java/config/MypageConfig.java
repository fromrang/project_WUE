package config;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.CMyPageController;
import dao.CustomerDaoImpl;
import dao.LikeDaoImpl;
import dao.OrderDaoImpl;


@Configuration
public class MypageConfig {
	private CustomerDaoImpl customerDao;
	@Autowired
	private LikeDaoImpl likeDao;
	private OrderDaoImpl orderDao;

	private SqlSessionFactory sqlSessionFactory;
	
	public MypageConfig() {
		try {
			String resource = "dao/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			customerDao = customerDao();
			orderDao = orderDao();
		}catch(Exception e) {e.printStackTrace();}
	}
	

	public CustomerDaoImpl customerDao() {
		CustomerDaoImpl customer = new CustomerDaoImpl();
		customer.setDataSource(sqlSessionFactory); // mybatis 적용
		return customer;
	}
	@Bean
	public LikeDaoImpl likeDao() {
		LikeDaoImpl likeDao = new LikeDaoImpl();
		likeDao.setDataSource(sqlSessionFactory); // mybatis 적용
		return likeDao;
	}

	public OrderDaoImpl orderDao() {
		OrderDaoImpl orderDao = new OrderDaoImpl();
		orderDao.setDataSource(sqlSessionFactory);
		return orderDao;
	}
	
	@Bean
	public CMyPageController cMyPageController() {
		CMyPageController cMyPageController = new CMyPageController();
		cMyPageController.setDao(customerDao, likeDao, orderDao());
		return cMyPageController;
	}

}
