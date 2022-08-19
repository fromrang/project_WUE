package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.Seller;
import dto.SellerFarm;
import dto.SellerInstagram;
import dto.Wnotice;
import mapper.SellerMapper;

public class SellerDaoImpl implements SellerDao {
	private SqlSessionFactory sqlSessionFactory;

	public void setDataSource(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public Seller SelectCByEmail(String email) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(SellerMapper.class).getLoginInfo(email);
			// return mapper.getLoginInfo(email);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Seller SelectCByEamilAndPw(String email, String pw) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(SellerMapper.class).getMember(email,pw);
		}finally{
			// TODO: handle exception
			sqlSession.close();
		}
	}
	
	@Override
	public void joinSeller(Seller seller)  {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(SellerMapper.class).joinSeller(seller);
			sqlSession.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			// TODO: handle exception
			sqlSession.close();
		}
		
	}
	
	@Override
	public List<Seller> selectList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(SellerMapper.class).getSellerAll();
		}finally{
			sqlSession.close();
		}
	}

	@Override
	public void delete(Seller seller) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(SellerMapper.class).getDelete(seller);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
		
	}
	
	@Override
	public List<Seller> leaveList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(SellerMapper.class).getLeave();	
		}finally {
			sqlSession.close();
		}
	}
	
	@Override
	public void updateSeller(Seller seller) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(SellerMapper.class).updateSeller(seller);
			sqlSession.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			// TODO: handle exception
			sqlSession.close();
		}
	}
	
	
//========================인스타 그램=======================
	@Override
	public List<SellerInstagram> InstagramPost() throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(SellerMapper.class).getPostAllList();
		}finally{
			// TODO: handle exception
			sqlSession.close();
		}
	}

	@Override
	public List<String> instagramImages(int postnumber) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(SellerMapper.class).getPostImage(postnumber);
		}finally{
			// TODO: handle exception
			sqlSession.close();
		}
	}
	
	@Override
	public List<Integer> instagramUserPostNumber(String email) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(SellerMapper.class).getUserPostNumber(email);
		}finally{
			// TODO: handle exception
			sqlSession.close();
		}
	}
	
	
	@Override
	public Seller SelectPost(String email, int postnumber) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SellerInstagram InstagramProfile(String seller_email) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(SellerMapper.class).getFrofile(seller_email);
		}finally{
			// TODO: handle exception
			sqlSession.close();
		}
	}
	
	@Override
	public void joinInstaSeller(SellerInstagram seller) throws Exception{
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(SellerMapper.class).joinInstaSeller(seller);
			sqlSession.commit();
		}finally{
			// TODO: handle exception
			sqlSession.close();
		}
	}
	
	@Override
	public void UpdateInstaSeller(SellerInstagram seller) throws Exception{
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(SellerMapper.class).UpdateInstaSeller(seller);
			sqlSession.commit();
		}finally{
			// TODO: handle exception
			sqlSession.close();
		}
	}

	@Override
	 public void delteSeller(Seller seller) throws Exception{
			SqlSession sqlSession=sqlSessionFactory.openSession();
			try {
				sqlSession.getMapper(SellerMapper.class).delteSeller(seller);
				sqlSession.commit();
			}finally{
				// TODO: handle exception
				sqlSession.close();
			}
	 }

	@Override
	public int selectFseqByAdd() throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
		return	sqlSession.getMapper(SellerMapper.class).selectFseqByAdd();
		}finally{
			// TODO: handle exception
			sqlSession.close();
		}
	}

	@Override
	public void SAddfarmImage(String url, int fseq, String email) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(SellerMapper.class).SAddfarmImage(url, fseq, email);
			sqlSession.commit();
		}finally{
			// TODO: handle exception
			sqlSession.close();
		}
		
	}

	@Override
	public List<SellerFarm> Sconfirmfarm(String email) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(SellerMapper.class).Sconfirmfarm(email);
		}finally{
			// TODO: handle exception
			sqlSession.close();
		}
	}


	@Override
	public List<Seller> SellerList() throws Exception {
	     SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(SellerMapper.class).SellerList();
			// return mapper.getLoginInfo(email);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void UpdateInstafarm(int fseq, String experience_time, int experience_price, String experience_date,
			int experience_number, String experience_context) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(SellerMapper.class).UpdateInstafarm(fseq, experience_time, experience_price, experience_date, experience_number, experience_context);;
			sqlSession.commit();
		}finally{
			// TODO: handle exception
			sqlSession.close();
		}
		
	}

	@Override
	public void SfarmDelte(int fseq) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(SellerMapper.class).SfarmDelte(fseq);
			sqlSession.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			// TODO: handle exception
			sqlSession.close();
		}
		
		
	}

	@Override
	public void SfarmImageDelte(int fseq) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(SellerMapper.class).SfarmImageDelte(fseq);
			sqlSession.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			// TODO: handle exception
			sqlSession.close();
		}
		
		
	}

	@Override
	public int selectPostNumberByAdd() throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
		return sqlSession.getMapper(SellerMapper.class).selectPostNumberByAdd();
		}
		finally{
			// TODO: handle exception
			sqlSession.close();
		}
	}

	@Override
	public void SAddinstaPost(int postnumber,String email,String context) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(SellerMapper.class).SAddinstaPost(postnumber, email, context);
			sqlSession.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			// TODO: handle exception
			sqlSession.close();
		}
		
	}

	@Override
	public void SAddPostImage(String url, int postnumber, String email) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(SellerMapper.class).SAddPostImage(url, postnumber, email);
			sqlSession.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			// TODO: handle exception
			sqlSession.close();
		}
		
	}

	@Override
	public SellerInstagram getpostSelect(String email, int postnumber) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(SellerMapper.class).getpostSelect(email, postnumber);
			// return mapper.getLoginInfo(email);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void SPostImageDelte(int postnumber) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(SellerMapper.class).SPostImageDelte(postnumber);
			sqlSession.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			// TODO: handle exception
			sqlSession.close();
		}
		
		
	}

	@Override
	public void SPostUpdate(int postnumber,String context) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(SellerMapper.class).SPostUpdate(postnumber,context);
			sqlSession.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			// TODO: handle exception
			sqlSession.close();
		}
		
		
	}

	@Override
	public void SinstaPostDelte(int postnumber) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(SellerMapper.class).SinstaPostDelte(postnumber);
			sqlSession.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			// TODO: handle exception
			sqlSession.close();
		}
		
		
	}

	@Override
	public void SAddinstaRely(int postnumber, String email, String content) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(SellerMapper.class).SAddinstaRely(postnumber, email, content);
			sqlSession.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			// TODO: handle exception
			sqlSession.close();
		}
		
	}

	@Override
	public List<SellerInstagram> getPostRely(int postnumber) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(SellerMapper.class).getPostRely(postnumber);
			// return mapper.getLoginInfo(email);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void SAddfarmRely(int fseq, String email, String content) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(SellerMapper.class).SAddfarmRely(fseq, email, content);
			sqlSession.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			// TODO: handle exception
			sqlSession.close();
		}
		
	}

	@Override
	public List<SellerFarm> getRramRely(int fseq) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(SellerMapper.class).getRramRely(fseq);
			// return mapper.getLoginInfo(email);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Integer getPostRelyCount(int postnumber) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(SellerMapper.class).getPostRelyCount(postnumber);
			// return mapper.getLoginInfo(email);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Integer getRramRelyCount(int fseq) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(SellerMapper.class).getRramRelyCount(fseq);
			// return mapper.getLoginInfo(email);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void SfarmAdd(SellerFarm farm) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(SellerMapper.class).SfarmAdd(farm);
			sqlSession.commit();
		}finally{
			// TODO: handle exception
			sqlSession.close();
	}
	}

	@Override
	public List<String> getfarmImage(int fseq, String email) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(SellerMapper.class).getfarmImage(fseq,email);
			// return mapper.getLoginInfo(email);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void SPostRelyeDelte(int postnumber) throws Exception {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(SellerMapper.class).SPostRelyeDelte(postnumber);
			sqlSession.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			// TODO: handle exception
			sqlSession.close();
		}
		
		
	}

	@Override
	public List<Wnotice> noticeList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(SellerMapper.class).getNoticeAll();
		} finally {
			sqlSession.close();
		}
	}
	@Override
	public SellerInstagram getSellerImageUrl(int sseq) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Seller seller = sqlSession.getMapper(SellerMapper.class).getSellerInfoforWithSseq(sseq);
			String email = seller.getEmail();
			return sqlSession.getMapper(SellerMapper.class).getFrofile(email);

		}finally {
			sqlSession.close();		
		}
		
	}
	
	

}
