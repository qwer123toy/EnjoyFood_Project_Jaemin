package enjoyfood;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MapMapper {
	
	// 식당명으로 검색했을 때 식당의 주소 찾는 쿼리문 추가
		@Select("SELECT cafe_address FROM cafeteria WHERE cafe_name=#{cafe_name}")
		String findAddressByName(@Param("cafe_name") String name);
}
