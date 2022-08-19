package config;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.InstaPostController;
import controller.InstaProfileUpdateController;
import controller.InstagramProfileController;
import controller.InstargramAllPost;
import controller.SDeleteController;
import controller.SFarmController;
import controller.SIdCheckController;
import controller.SJoinController;
import controller.SLogOutcontroller;
import controller.SLoginController;
import controller.SMyPageUpdateController;
import controller.SnoticeController;
import dao.CustomerDaoImpl;
import dao.SellerDaoImpl;

@Configuration
public class SellerConfig  {
	@Autowired
	private SellerDaoImpl SellerDao;
	private CustomerDaoImpl customerDao;
	private SqlSessionFactory sqlSessionFactory;
	
	public SellerConfig() {
		try {
			String resource = "dao/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			customerDao=customerDao();
		}catch(Exception e) {e.printStackTrace();}
	}
	
	@Bean
	public CustomerDaoImpl customerDao() {
		CustomerDaoImpl customer = new CustomerDaoImpl();
		customer.setDataSource(sqlSessionFactory); // mybatis 적용
		return customer;
	}
	
	@Bean
	public SellerDaoImpl SellerDao() {
		SellerDaoImpl seller = new SellerDaoImpl();
		seller.setDataSource(sqlSessionFactory); // mybatis 적용
		return seller;
	}
	//----------------------------------------------------컨드롤러
	
	@Bean
	public SLoginController logInController() {
		//loginController로 객체를 찾아서 생성한다.
		SLoginController logInController = new SLoginController();
		logInController.setcustomerDao(SellerDao,customerDao());
		return logInController;
	}
	
	@Bean
	public SJoinController SJoinController() {
		controller.SJoinController SJoinController=new SJoinController();
		SJoinController.setcustomerDao(SellerDao);
		return SJoinController;
	}
	
	@Bean
	public SIdCheckController IdCheckController() {
		controller.SIdCheckController IdCheckController=new SIdCheckController();
		IdCheckController.setcustomerDao(SellerDao);
		return IdCheckController;
	}
		
	@Bean
	public SMyPageUpdateController SMyPageUpdateController() {
		controller.SMyPageUpdateController SMyPageUpdateController=new SMyPageUpdateController();
		SMyPageUpdateController.setcustomerDao(SellerDao);
		return SMyPageUpdateController;
	}
	
	@Bean
	public SDeleteController sDeleteController() {
		controller.SDeleteController sDeleteController=new SDeleteController();
		sDeleteController.setcustomerDao(SellerDao);
		return sDeleteController;
	}
	
	
	//=============인스타그램=================
	
	@Bean
	public InstargramAllPost PostAllController() {
		InstargramAllPost PostAllController = new InstargramAllPost();
		PostAllController.setcustomerDao(SellerDao);
		return PostAllController;
	}

	@Bean
	public InstagramProfileController InstagramProfileController() {
		InstagramProfileController profileController = new InstagramProfileController();
		profileController.setcustomerDao(SellerDao);
		return profileController;
	}
	
	@Bean
	public InstaProfileUpdateController InstaProfileUpdateController() {
		InstaProfileUpdateController InstaProfileUpdateController = new InstaProfileUpdateController();
		InstaProfileUpdateController.setcustomerDao(SellerDao);
		return InstaProfileUpdateController;
	}
	
	@Bean
	public SFarmController sFramController() {
		SFarmController sFramController = new SFarmController();
		sFramController.setcustomerDao(SellerDao);
		return sFramController;
	}
	
	
	@Bean
	public InstaPostController instaPostController() {
		InstaPostController instaPostController = new InstaPostController();
		instaPostController.setcustomerDao(SellerDao);
		return instaPostController;
	}

	
	@Bean
	public SnoticeController snoticeController() {
		SnoticeController snoticeController = new SnoticeController();
		snoticeController.setcustomerDao(SellerDao);
		return snoticeController;
	}
	
	@Bean
	public SLogOutcontroller sLogOutcontroller() {
		SLogOutcontroller sLogOutcontroller = new SLogOutcontroller();
		return sLogOutcontroller;
	}

		
}
