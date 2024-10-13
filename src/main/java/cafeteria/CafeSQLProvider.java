package cafeteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class CafeSQLProvider {
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
	
	public static String getCafeByPriceAndTags(@Param("cafePrice") int cafePrice, @Param("cafetag") List<String> cafetags) {
	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT DISTINCT c.cafeNum, c.cafeName, c.cafeAddress, c.cafePhoneNumber, c.cafePrice ");
	    sql.append("FROM cafeteria c ");
	    sql.append("NATURAL JOIN cafecategory cc ");
	    sql.append("NATURAL JOIN category_management cm ");
	    sql.append("NATURAL JOIN cafetag t ");
	    sql.append("WHERE c.cafePrice <= #{cafePrice} ");

	    // cafetag 리스트가 비어 있지 않으면 동적 필터링 추가
	    if (cafetags != null && !cafetags.isEmpty()) {
	        sql.append("AND t.cafeTag IN (");
	        for (int i = 0; i < cafetags.size(); i++) {
	            sql.append("#{cafetag[").append(i).append("]}"); // 각 태그를 인덱스로 지정
	            if (i < cafetags.size() - 1) {
	                sql.append(", "); // 마지막 요소가 아닐 경우 콤마 추가
	            }
	        }
	        sql.append(") ");
	    }

	    return sql.toString();
	}

}
