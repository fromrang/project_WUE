package controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.ProductDao;
import dao.WorkerDaoImpl;
import dto.Product;
import dto.Worker;

@Controller
@RequestMapping("worker/reg")
public class PRegController {
	private ProductDao productDao;
	private WorkerDaoImpl workerDao;

	public PRegController setProductDao(ProductDao productDao, WorkerDaoImpl workerDao) {
		this.productDao = productDao;
		this.workerDao = workerDao;
		return this;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String form() throws Exception {
		return "worker/ProductManamgement";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		Worker worker = (Worker) session.getAttribute("authInfo");
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String savePath = "/img/Simage"; // 여기를 바꿔주면 다운받는 경로가 바뀜
			// 폴더를 만들어줘야하며 그래야 실제경로에 파일이 올라감 (webapp에는 파일이 올라가지 않음)
			int uploadFileSizeLimit = 10 * 1080 * 1920; // 최대 업로드 파일 크기 10mb로 제한
			PrintWriter out = response.getWriter();
			String encType = "UTF-8"; // 인코딩 방식 지정
			ServletContext context = request.getServletContext();
			String uploadFilePath = context.getRealPath(savePath); // 서버상의 실체 디렉토리
			// 실제로 올라가면 지정되어 저장되는 경로
			MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType,
					new DefaultFileRenamePolicy());

			Product addproduct = new Product();
			addproduct.setPrice(Integer.parseInt(multi.getParameter("price")));
			addproduct.setName(multi.getParameter("name"));
			addproduct.setSseq(999);
			addproduct.setQuantity(Integer.parseInt(multi.getParameter("quantity")));
			addproduct.setKind(Integer.parseInt(multi.getParameter("kind")));
			addproduct.setSale(multi.getParameter("sale"));

			ArrayList<String> urlImage = new ArrayList<String>();
			
			Enumeration files = multi.getFileNames();

			while (files.hasMoreElements()) {
				String file = (String) files.nextElement();
				String file_name = multi.getFilesystemName(file);
				String ori_file_name = multi.getOriginalFileName(file);
//				System.out.println("사진"+ori_file_name);
				if (!file.equals("productImage")) {
					urlImage.add(file);
				}
			}

			productDao.SAddProduct(addproduct);
			int pseq = productDao.selectPseqByAdd();
//			System.out.println(pseq);

			for (String url : urlImage) {
				productDao.SAddProductImage(url, pseq);
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return "redirect:reg";
	}
}
