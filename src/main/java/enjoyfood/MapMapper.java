package enjoyfood;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MapMapper {
	
	// 식당명으로 검색했을 때 식당의 주소 찾는 쿼리문 추가
		@Select("SELECT cafeAddress FROM cafeteria WHERE cafeName=#{cafeName}")
		String findAddressByName(@Param("cafeName") String name);
}
