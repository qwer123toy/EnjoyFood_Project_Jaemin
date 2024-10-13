package cafeteria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class CafeSQLProvider {
	public static String getCafeByPrice(@Param("start") Integer start, @Param("end") Integer end) {
		return new SQL() {
			{
				SELECT("c.cafeNum, c.cafeName, c.cafePrice, c.cafeAddress, c.cafePhoneNumber, t.cafeTag, cat.cafeCategory");
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

	public static String getCafeByPriceAndTags(@Param("cafePrice") int cafePrice,
			@Param("cafetag") List<String> cafetags) {
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

	public static String getCafesByAddress(@Param("addressList") List<String> addressList) {
		StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM cafeteria WHERE ");
		List<String> addressConditions = new ArrayList<>();

		for (String address : addressList) {
			String condition = Arrays.stream(address.split(" - ")).filter(part -> !part.equals("전체")).map(part -> {
				if (part.equals("충북"))
					return "충청|충청북";
				else if (part.equals("충남"))
					return "충청|충청남";
				else if (part.equals("전북"))
					return "전라|전라북";
				else if (part.equals("전남"))
					return "전라|전라남";
				else if (part.equals("경북"))
					return "경북|경상북";
				else if (part.equals("경남"))
					return "경남|경상남";
				else
					return part;
			}).map(part -> "cafeAddress regexp '" + part + "'").collect(Collectors.joining(" AND ", "(", ")"));

			addressConditions.add(condition);
		}
		if (addressList.size() > 0)
			sqlBuilder.append(String.join(" OR ", addressConditions));
		else {
			sqlBuilder.replace(sqlBuilder.lastIndexOf("WHERE"), sqlBuilder.capacity(), "");
		}
		return sqlBuilder.toString();
	}
}
