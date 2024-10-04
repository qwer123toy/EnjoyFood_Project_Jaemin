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
	@Insert({ " INSERT INTO cafeteria (cafe_num, cafe_name, cafe_openTime, cafe_phoneNumber,",
			" cafe_address, cafe_price, cafe_owner)",
			" VALUES (#{cafe_num}, #{cafe_name}, #{cafe_openTime}, #{cafe_phoneNumber}, ",
			" #{cafe_address}, #{cafe_price}, #{cafe_owner}" })
	int insert(Cafeteria cafeteria);

	@Select("SELECT * FROM cafeteria WHERE cafe_num=#{num}")
	Cafeteria selectByNum(@Param("cafe_num") int num);

	// 맛집명 조회
	@Select("SELECT * FROM cafeteria WHERE cafe_name=#{name}")
	Cafeteria selectByName(@Param("cafe_name") String name);

	// 맛집 정보 수정
	@Update({ "UPDATE cafeteria SET cafe_num=#{cafe_num}, cafe_name=#{cafe_name}, ",
			"cafe_openTime=#{cafe_openTime}, cafe_phoneNumber=#{cafe_phoneNumber}, ",
			"cafe_address=#{cafe_address}, cafe_price=#{cafe_price}, cafe_owner=#{cafe_owner}" })
	int update(Cafeteria cafeteria);


	// 삭제
	@Delete("DELETE FROM Cafeteria WHERE cafeNum=#{cafeNum}")
	int delete(int cafeNum);


	// 가게 메뉴도 같이 확인, 태그, 카테고리를 같이 OR로 묶어서 찾기

}
