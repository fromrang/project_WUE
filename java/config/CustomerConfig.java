package config;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.CIdCheckController;
import controller.CJoinController;
import controller.CLoginController;
import controller.CMyPageController;
import dao.CustomerDaoImpl;

@Configuration
//@Import ( CustomerMapper.class)
public class CustomerConfig {
	@Autowired
	private CustomerDaoImpl customerDao;
	private SqlSessionFactory sqlSessionFactory;
	
	public CustomerConfig() {
		try {
			String resource = "dao/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e) {e.printStackTrace();}
	}
	
	@Bean
	public CustomerDaoImpl customerDao() {
		CustomerDaoImpl customer = new CustomerDaoImpl();
		customer.setDataSource(sqlSessionFactory); // mybatis 적용
		return customer;
	}
	
	@Bean
	public CLoginController logInController() {
		//loginController로 객체를 찾아서 생성한다.
		CLoginController logInController = new CLoginController();
		logInController.setcustomerDao(customerDao);
		
		return logInController;
	}
	
	@Bean
	public CJoinController joinController() {
		CJoinController joinController = new CJoinController();
		joinController.setcustomerDao(customerDao);
		
		return joinController;
	}
	
	@Bean
	public CIdCheckController cIdCheckController() {
		CIdCheckController cIdCheckController = new CIdCheckController();
		cIdCheckController.setcustomerDao(customerDao);
		
		return cIdCheckController;
	}
	

}
