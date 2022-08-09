package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Like;

public interface LikeMapper {
	//좋아요 테이블이 상품 넣기
	@Insert("insert like_table(email, pseq) select #{email}, #{pseq} from Dual where not exists(select lseq from like_table where email=#{email} and pseq=#{pseq})")
	public void insertLike(@Param("email")String email, @Param("pseq")int pseq);
	
	//사용자 별 좋아요 리스트 출력
	@Select("select * from like_view where email=#{email}")
	public List<Like> selectLike(@Param("email")String email);
	
	//상품이 좋아요에 있는지 없는지 판별 버튼 활성화/비활성화에 필요함
	@Select("select * from like_table where email=#{email} and pseq=#{pseq} and result='n'")
	public Like checkLikeProduct(@Param("email")String email, @Param("pseq")int pseq);
	
	//like_table에 있는지 없는지 확인 없으면 insert 있으면 result update
	@Select("select * from like_table where email=#{email} and pseq=#{pseq}")
	public Like checkLikeProductAll(@Param("email")String email, @Param("pseq")int pseq);
	
	//좋아요 취소 누르면 좋아요 view에서 삭제
	@Update("update like_table set result='y' where pseq=#{pseq} and email=#{email}")
	public void deleteLikeView(@Param("email")String email, @Param("pseq")int pseq);
	
	//좋아요 다시 누르면 좋아요 view에 다시 등록
	@Update("update like_table set result='n' where pseq=#{pseq} and email=#{email}")
	public void changeLike(@Param("email")String email, @Param("pseq")int pseq);
}
