package config;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.PDetailPageController;
import controller.PFirstPageController;
import controller.PKindPageController;
import dao.ProductDao;
import dao.ProductDaoImpl;

@Configuration
public class ProductConfig {
	@Autowired
	private ProductDao productDao;
	private SqlSessionFactory sqlSessionFactory;
	
	public ProductConfig() {
		try {
			String resource = "dao/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e) {e.printStackTrace();}
	}
	
	@Bean
	public ProductDao productDao() {
		ProductDao productDao = new ProductDaoImpl();
		productDao.setDataSource(sqlSessionFactory);
		return productDao;
	}
	@Bean 
	public PFirstPageController pFirstPageController() {
		PFirstPageController pFirstPageController = new PFirstPageController();
		pFirstPageController.setProductDao(productDao);
		return pFirstPageController;
	}
	@Bean 
	public PKindPageController pKindPageController() {
		PKindPageController pKindPageController = new PKindPageController();
		pKindPageController.setProductDao(productDao);
		return pKindPageController;
	}
	@Bean 
	public PDetailPageController pDetailPageController() {
		PDetailPageController pDetailPageController = new PDetailPageController();
		pDetailPageController.setProductDao(productDao);
		return pDetailPageController;
	}
	
}
