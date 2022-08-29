package config;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import controller.CaInsertController;
import controller.CartListController;
import dao.CartDaoImpl;

@Configuration
public class CartConfig {
	//@Autowired
	private CartDaoImpl cartDaoImpl;
	//@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public CartConfig() {
		try {
			String resource = "dao/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e) {e.printStackTrace();}
	}

	
//	@Bean
//	public CartDaoImpl cartDaoImpl() {
//		CartDaoImpl cartDaoImpl = new CartDaoImpl();
//		cartDaoImpl.setDataSource(sqlSessionFactory);
//		return cartDaoImpl;
//	}
//	@Bean
//	public CaInsertController caInsertController() {
//		CaInsertController caInsertController = new CaInsertController();
//		caInsertController.setCartDaoImpl(cartDaoImpl);
//		return caInsertController; 
//	}
//	
//	@Bean
//	public CartListController cartListController() {
//		CartListController cartListController = new CartListController();
//		cartListController.setCartDaoImpl(cartDaoImpl);
//		return cartListController;
//	}
}
