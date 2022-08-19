package config;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import controller.IdCheckController;
import controller.IndexController;
import controller.LogOutController;
import controller.PKindController;
import controller.PRegController;
import controller.WAddController;
import controller.WBoardController;
import controller.WBoardUpdateController;
import controller.WDeleteController;
import controller.WLeaveController;
import controller.WListController;
import controller.WLoginController;
import controller.WManagementController;
import controller.WNoticeController;
import controller.WOrderController;
import controller.WOrderDetailController;
import controller.WPromoteController;
import controller.WQnaController;
import controller.WSalesController;
import controller.WUpdateController;
import dao.CustomerDaoImpl;
import dao.OrderDaoImpl;
import dao.ProductDaoImpl;
import dao.SellerDaoImpl;
import dao.WorkerDaoImpl;

@Configuration // Handler Mapping에 의해서 bean을 찾기위해 Annotation이 필요
public class WorkerConfig implements WebMvcConfigurer {
	private SqlSessionFactory sqlSessionFactory;

	public WorkerConfig() {
		try {
			String resource = "dao/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Bean
	public WorkerDaoImpl workerDao() {
		WorkerDaoImpl worker = new WorkerDaoImpl();
		worker.setDataSource(sqlSessionFactory);
		return worker;
	}

	@Autowired
	private WorkerDaoImpl workerDao;

	@Bean
	public WLoginController wLoginController() {
		WLoginController wLoginController = new WLoginController();
		wLoginController.setworkerDao(workerDao);
		return wLoginController;
	}

	@Bean
	public WListController wListController() {
		WListController wListController = new WListController();
		wListController.setworkerDao(workerDao);
		return wListController;
	}

	@Bean
	public WAddController wAddController() {
		WAddController wAddController = new WAddController();
		wAddController.setworkerDao(workerDao);
		return wAddController;
	}

	@Bean
	public WUpdateController wUpdateController() {
		WUpdateController wUpdateController = new WUpdateController();
		wUpdateController.setworkerDao(workerDao);
		return wUpdateController;
	}

	@Bean
	public IdCheckController idCheckController() {
		IdCheckController idCheckController = new IdCheckController();
		idCheckController.setworkerDao(workerDao);
		return idCheckController;
	}

	@Bean
	public LogOutController logOutController() {
		return new LogOutController();

	}

//index page---------------------------------------------------------------------------	

	@Bean
	public IndexController indexController() {
		IndexController indexController = new IndexController();
		return indexController;
	}

//회원정보관리---------------------------------------------------------------------------------	

	private CustomerDaoImpl customerDao;

	public CustomerDaoImpl customerDao() {
		CustomerDaoImpl customer = new CustomerDaoImpl();
		customer.setDataSource(sqlSessionFactory);
		return customer;
	}

	private SellerDaoImpl sellerDao;

	public SellerDaoImpl sellerDao() {
		SellerDaoImpl seller = new SellerDaoImpl();
		seller.setDataSource(sqlSessionFactory);
		return seller;
	}

	// 회원 관리
	@Bean
	public WManagementController wManagementController() {
		WManagementController wManagementController = new WManagementController();
		sellerDao = sellerDao();
		customerDao = customerDao();
		wManagementController.setDao(customerDao, sellerDao);
		return wManagementController;
	}

	// 회원 강제 탈퇴
	@Bean
	public WDeleteController wDeleteController() {
		WDeleteController wDeleteController = new WDeleteController();
		sellerDao = sellerDao();
		customerDao = customerDao();
		wDeleteController.setDao(customerDao, sellerDao);
		return wDeleteController;
	}

	// 탈퇴 신청한 회원
	@Bean
	public WLeaveController wLeaveController() {
		WLeaveController wLeaveController = new WLeaveController();
		sellerDao = sellerDao();
		customerDao = customerDao();
		wLeaveController.setDao(customerDao, sellerDao);
		return wLeaveController;
	}

	// customer grade promote
	@Bean
	public WPromoteController wPromoteController() {
		WPromoteController wPromoteController = new WPromoteController();
		sellerDao = sellerDao();
		customerDao = customerDao();
		wPromoteController.setDao(customerDao);
		return wPromoteController;
	}

//상품관리-----------------------------------------------------------------------------	

	private ProductDaoImpl productDao;

	public ProductDaoImpl productDao() {
		ProductDaoImpl productDao = new ProductDaoImpl();
		productDao.setDataSource(sqlSessionFactory);
		return productDao;
	}

	@Bean // 카테고리별 상품 리스트
	public PKindController pKindController() {
		PKindController pKindController = new PKindController();
		productDao = productDao();
		pKindController.setProductDao(productDao, workerDao);
		return pKindController;
	}

	@Bean // 상품 등록
	public PRegController pRegController() {
		PRegController pRegController = new PRegController();
		pRegController.setProductDao(productDao, workerDao);
		return pRegController;
	}

// 주문내역관리---------------------------------------------------------------------------

	private OrderDaoImpl orderDaoImpl;

	public OrderDaoImpl orderDaoImpl() {
		OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
		orderDaoImpl.setDataSource(sqlSessionFactory);
		return orderDaoImpl;
	}

	@Bean // 셀러별 주문 리스트
	public WOrderController wOrderController() {
		WOrderController wOrderController = new WOrderController();
		wOrderController.setOrderDaoImpl(orderDaoImpl(), sellerDao);
		return wOrderController;

	}

	@Bean
	public WOrderDetailController wOrderDetailController() {
		WOrderDetailController wOrderDetailController = new WOrderDetailController();
		wOrderDetailController.setOrderDaoImpl(orderDaoImpl());
		return wOrderDetailController;
	}

// 게시판관리---------------------------------------------------------------------------

	@Bean // 게시물 등록 type =0 일때 공지사항 type=1 이면 QnA
	public WBoardController wBoardController() {
		WBoardController wBoardController = new WBoardController();
		wBoardController.setNoticeDao(workerDao);
		return wBoardController;
	}

	@Bean // 공지사항 List
	public WNoticeController wNoticeController() {
		WNoticeController wNoticeController = new WNoticeController();
		wNoticeController.setNoticeDao(workerDao);
		return wNoticeController;
	}

	@Bean // QnA List
	public WQnaController wQnaController() {
		WQnaController wQnaController = new WQnaController();
		wQnaController.setNoticeDao(workerDao);
		return wQnaController;
	}

	@Bean
	public WBoardUpdateController wBoardUpdateController() {
		WBoardUpdateController wBoardUpdateController = new WBoardUpdateController();
		wBoardUpdateController.setNoticeDao(workerDao);
		return wBoardUpdateController;
	}

// 매출관리---------------------------------------------------------------------------

	@Bean
	public WSalesController wSalesController() {
		WSalesController wSalesController = new WSalesController();
		wSalesController.setDao(workerDao);
		return wSalesController;
	}
}
