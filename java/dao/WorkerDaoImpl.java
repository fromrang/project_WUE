package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.Sales;
import dto.SellerSales;
import dto.Wnotice;
import dto.Worker;
import mapper.WorkerMapper;

public class WorkerDaoImpl implements WorkerDao {
	private SqlSessionFactory sqlSessionFactory;

	public void setDataSource(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public Worker SelectById(String id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getLoginInfo(id);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Worker exist(String id, String pw) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getWorker(id, pw);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public List<Worker> selectList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getWorkerAll();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int insert(Worker worker) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.getMapper(WorkerMapper.class).getAdd(worker);
			sqlSession.commit();
			return count;
		} catch (Exception e) {
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return -1;
	}

	@Override
	public Worker idCheck(String id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getIdCheck(id);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Worker selectOne(String id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getSelect(id);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int update(Worker worker) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.getMapper(WorkerMapper.class).getUpdate(worker);
			sqlSession.commit(); // transaction ó =>save point
			return count;
		} catch (Exception e) {
			sqlSession.rollback(); // Ѱ ϰڴ => update Ϸ Ǿ commit ǰ ϰڴ , update
									// rollback Ű ڴ
		} finally {
			sqlSession.close();
		}
		return -1;
	}

	@Override
	public int insert(Wnotice notice) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		System.out.println("dfjke" + notice.getTitle());
		try {
			int count = sqlSession.getMapper(WorkerMapper.class).getNoticeAdd(notice);
			sqlSession.commit();
			return count;
		} catch (Exception e) {
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return -1;
	}

	@Override
	public List<Wnotice> noticeList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getNoticeAll();
		} finally {
			sqlSession.close();
		}

	}

	@Override
	public List<Wnotice> qnaList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getQnaAll();
		} finally {
			sqlSession.close();
		}

	}

	@Override
	public int update(Wnotice notice) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		System.out.println("!!!" + notice.getDetail());
		try {
			int count = sqlSession.getMapper(WorkerMapper.class).getBoardUpdate(notice);
			sqlSession.commit(); // transaction 처리 =>save point
			System.out.println("ddddd" + count);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(); // 지금 명령한것을 취소하겠다 => update가 완료되어야 commit이 되게 하겠다 즉, update가 되지 않으면 rollback
									// 시키겠다
		} finally {
			sqlSession.close();
		}
		return -1;
	}

	@Override
	public void delete(int notice) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.getMapper(WorkerMapper.class).getBoardDelete(notice);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Wnotice select(int wseq) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getBoardAll(wseq);
		} finally {
			sqlSession.close();
		}
	}

	@Override // 사용자 매출 관리 리스트
	public List<Sales> selectSales() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getCustomerSale();
		} finally {
			sqlSession.close();
		}
	}

	@Override // 사용자 별 총 매출
	public List<Integer> sales() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getSales();
		} finally {
			sqlSession.close();
		}
	}

	@Override // 날짜별 총 매출
	public List<Integer> dateSales() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getDateSales();
		} finally {
			sqlSession.close();
		}
	}

	@Override // ranking list
	public List<Integer> rank() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getRank();
		} finally {
			sqlSession.close();
		}
	}

	@Override // best n->y 상태 변경
	public int best(int pseq) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.getMapper(WorkerMapper.class).getBest(pseq);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override // best_p_view List
	public List<Integer> bestList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getBestList();
		} finally {
			sqlSession.close();
		}
	}

	@Override // best y->n 상태 변경
	public int deleteBest(int pseq) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.getMapper(WorkerMapper.class).getDeleteBest(pseq);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override // ranking list
	public List<Integer> likeRank() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getLikeRank();
		} finally {
			sqlSession.close();
		}
	}

	@Override // recommend n->y 상태 변경
	public int recommend(int pseq) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.getMapper(WorkerMapper.class).getRecommend(pseq);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override // recommend_p_view List
	public List<Integer> recommendList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getRecommendList();
		} finally {
			sqlSession.close();
		}
	}

	@Override // recommend y->n 상태 변경
	public int deleteRecommend(int pseq) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.getMapper(WorkerMapper.class).getDeleteRecommend(pseq);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override // 1번 seller 수수료
	public List<dto.SellerSales> Seller1Sales() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getSeller1Sale();
		} finally {
			sqlSession.close();
		}
	}

	@Override // 2번 seller 수수료
	public List<SellerSales> Seller2Sales() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getSeller2Sale();
		} finally {
			sqlSession.close();
		}
	}

	@Override // 3번 seller 수수료
	public List<SellerSales> Seller3Sales() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getSeller3Sale();
		} finally {
			sqlSession.close();
		}
	}

	@Override // 4번 seller 수수료
	public List<SellerSales> Seller4Sales() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getSeller4Sale();
		} finally {
			sqlSession.close();
		}
	}

	@Override // 5번 seller 수수료
	public List<SellerSales> Seller5Sales() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getSeller5Sale();
		} finally {
			sqlSession.close();
		}
	}

	@Override // 6번 seller 수수료
	public List<SellerSales> Seller6Sales() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(WorkerMapper.class).getSeller6Sale();
		} finally {
			sqlSession.close();
		}
	}
}
