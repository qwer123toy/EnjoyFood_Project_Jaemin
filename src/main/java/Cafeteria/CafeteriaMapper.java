package Cafeteria;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface CafeteriaMapper {

	@Select("SELECT cafe_num, cafe_name, cafe_openTime, cafe_phoneNumber, cafe_address, cafe_price, cafe_owner FROM Cafeteria")
	@Results(id = "cafeResults", value = {
			@Result(column = "cafe_num", property = "cafeNum", jdbcType = JdbcType.INTEGER),
			@Result(column = "cafe_name", property = "cafeName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "cafe_openTime", property = "cafeOpenTime", jdbcType = JdbcType.VARCHAR),
			@Result(column = "cafe_phoneNumber", property = "cafePhoneNumber", jdbcType = JdbcType.VARCHAR),
			@Result(column = "cafe_address", property = "cafeAddress", jdbcType = JdbcType.VARCHAR),
			@Result(column = "cafe_price", property = "cafePrice", jdbcType = JdbcType.INTEGER),
			@Result(column = "cafe_owner", property = "cafeOwner", jdbcType = JdbcType.VARCHAR) })
	List<Cafeteria> selectAll();

	// 맛집 새로 추가
	@Insert({ "INSERT INTO cafeteria (cafe_num, cafe_name, cafe_openTime, cafe_phoneNumber,",
			"cafe_address, cafe_price, cafe_owner)",
			"VALUES (#{cafeNum}, #{cafeName}, #{cafeOpenTime}, #{cafePhoneNumber},",
			"#{cafeAddress}, #{cafePrice}, #{cafeOwner})" })
	int insert(Cafeteria cafeteria);

	@Select("SELECT * FROM cafeteria WHERE cafe_num=#{cafe_num}")
	Cafeteria selectByNum(@Param("cafe_num") int cafeNum);

	// 맛집명 조회
	@Select("SELECT * FROM cafeteria WHERE cafe_name=#{cafe_name}")
	Cafeteria selectByName(@Param("cafe_name") String cafeName);

	// 맛집 정보 수정
	@Update({ "UPDATE cafeteria SET cafe_num=#{cafeNum}, cafe_name=#{cafeName}, ",
			"cafe_openTime=#{cafeOpenTime}, cafe_phoneNumber=#{cafePhoneNumber}, ",
			"cafe_address=#{cafeAddress}, cafe_price=#{cafePrice} ",
		    "WHERE cafe_num=#{cafeNum}" 
			})
	int update(Cafeteria cafeteria);

	// 삭제
	@Delete("DELETE FROM Cafeteria WHERE cafe_num=#{cafeNum}")
	int delete(int cafeNum);

	// 가격 범위 - 해당 범위외 값 안나옴
	@Select({ "SELECT cafe_name, cafe_openTime, cafe_phoneNumber, cafe_address, cafe_price", "FROM cafeteria",
			"WHERE cafe_price BETWEEN #{start} AND #{end}" })
	List<Cafeteria> searchByPrice(@Param("start") int start, @Param("end") int end);

	// 가게 메뉴도 같이 확인, 태그, 카테고리를 같이 OR로 묶어서 찾기

}
