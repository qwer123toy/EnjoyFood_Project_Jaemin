package Cafeteria;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

public interface CafeteriaDynamicMapper {
	@SelectProvider(type = CafeSQLProvider.class, method = "getCafeByPrice")
	@Results({ @Result(column = "cafe_name", property = "cafeName"),
			@Result(column = "cafe_price", property = "cafePrice"),
			@Result(column = "cafe_address", property = "cafeAddress"),
			@Result(column = "cafe_phoneNumber", property = "cafePhoneNumber"),
			@Result(column = "cafeTag", property = "cafeTag"),
			@Result(column = "cafeCategory", property = "cafeCategory") })
	List<Cafeteria> getCafeByPrice(@Param("start") Integer start, @Param("end") Integer end);
}

class CafeSQLProvider {
// 가격 범위 해당 없으면 전부?
	public static String getCafeByPrice(@Param("start") Integer start, @Param("end") Integer end) {
		return new SQL() {
			{
				SELECT("c.cafe_name, c.cafe_price, c.cafe_address, c.cafe_phoneNumber, t.cafe_tag, cat.cafe_category");
				FROM("cafeteria c");
				JOIN("menu m ON c.cafe_num = m.cafe_num");
				JOIN("cafe_tag t ON c.cafe_num = t.cafe_num");
				JOIN("cafe_category cat ON c.cafe_num = cat.cafe_num");

				if (start != null && end != null) {
					WHERE("c.cafe_price BETWEEN #{start} AND #{end}"); // 메뉴 가격을 기준으로 범위 설정
				}
			}
		}.toString();
	}
}
