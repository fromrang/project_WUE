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
import controller.SMainController;
import controller.SProductAddController;
import controller.SProductController;
import controller.SProductDeleteController;
import dao.OrderDaoImpl;
import dao.ProductDao;
import dao.ProductDaoImpl;
import dao.ReviewDaoImpl;
import dao.SellerDaoImpl;

@Configuration
public class ProductConfig {
	//@Autowired
	private ProductDao productDao;
	private SqlSessionFactory sqlSessionFactory;
	private OrderDaoImpl orderDaoImpl;
	
	public ProductConfig() {
		try {
			String resource = "dao/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			//orderDaoImpl = orderDaoImpl();
		}catch(Exception e) {e.printStackTrace();}
	}
	
//	@Bean
//	public ProductDao productDao() {
//		ProductDao productDao = new ProductDaoImpl();
//		productDao.setDataSource(sqlSessionFactory);
//		return productDao;
//	}
//	@Bean 
//	public PFirstPageController pFirstPageController() {
//		PFirstPageController pFirstPageController = new PFirstPageController();
//		pFirstPageController.setProductDao(productDao);
//		return pFirstPageController;
//	}
//	@Bean 
//	public PKindPageController pKindPageController() {
//		PKindPageController pKindPageController = new PKindPageController();
//		pKindPageController.setProductDao(productDao);
//		return pKindPageController;
//	}
//	@Bean 
//	public PDetailPageController pDetailPageController() {
//		PDetailPageController pDetailPageController = new PDetailPageController();
//		pDetailPageController.setProductDao(productDao, reviewDao(), SellerDao());
//		return pDetailPageController;
//	}
//	public ReviewDaoImpl reviewDao() {
//		ReviewDaoImpl reviewDao = new ReviewDaoImpl();
//		reviewDao.setDataSource(sqlSessionFactory);
//		return reviewDao;
//	}
//	
//	//=========================================================
//	
//	private SellerDaoImpl SellerDao;
//	
//	public SellerDaoImpl SellerDao() {
//		SellerDaoImpl seller = new SellerDaoImpl();
//		seller.setDataSource(sqlSessionFactory); // mybatis 적용
//		return seller;
//	}
//	
//	public OrderDaoImpl orderDaoImpl() {
//		OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
//		orderDaoImpl.setDataSource(sqlSessionFactory);
//		return orderDaoImpl;
//	}
//	
//
//	@Bean 
//	public SProductAddController sProductAddController() {
//		SProductAddController sProductAddController = new SProductAddController();
//		SellerDao = SellerDao();
//		sProductAddController.setProductDao(productDao,SellerDao);
//		return sProductAddController;
//	}
//	
//	@Bean
//	public SProductController SProductListController() {
//		SProductController SProductListController = new SProductController();
//		SProductListController.setProductDao(productDao);
//		return SProductListController;
//	}
//	
//	
//	@Bean
//	public SProductDeleteController SProductDeleteController() {
//		SProductDeleteController SProductDeleteController = new SProductDeleteController();
//		SProductDeleteController.setProductDao(productDao);
//		return SProductDeleteController;
//	}
//	
//	
//	@Bean
//	public SMainController SMainController() {
//		SMainController SMainController = new SMainController();
//		SMainController.setProductDao(SellerDao(),productDao,orderDaoImpl());
//		return SMainController;
//	}
}
