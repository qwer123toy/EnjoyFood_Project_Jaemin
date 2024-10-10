package cafeteria;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

public interface CafeteriaDynamicMapper {
	@SelectProvider(type = CafeSQLProvider.class, method = "getCafeByPrice")
	@Results({ @Result(column = "cafeName", property = "cafeName"),
			@Result(column = "cafePrice", property = "cafePrice"),
			@Result(column = "cafeAddress", property = "cafeAddress"),
			@Result(column = "cafePhoneNumber", property = "cafePhoneNumber"),
			@Result(column = "cafeTag", property = "cafeTag"),
			@Result(column = "cafeCategory", property = "cafeCategory") })
	List<Cafeteria> getCafeByPrice(@Param("start") Integer start, @Param("end") Integer end);
}

class CafeSQLProvider {
// 가격 범위 해당 없으면 전부?
	public static String getCafeByPrice(@Param("start") Integer start, @Param("end") Integer end) {
		return new SQL() {
			{
				SELECT("c.cafeName, c.cafePrice, c.cafeAddress, c.cafePhoneNumber, t.cafeTag, cat.cafeCategory");
				FROM("cafeteria c");
				JOIN("menu m ON c.cafeNum = m.cafeNum");
				JOIN("cafeTag t ON c.cafeNum = t.cafeNum");
				JOIN("cafeCategory cat ON c.cafeNum = cat.cafeNum");

				if (start != null && end != null) {
					WHERE("c.cafePrice BETWEEN #{start} AND #{end}"); // 메뉴 가격을 기준으로 범위 설정
				}
			}
		}.toString();
	}
}
