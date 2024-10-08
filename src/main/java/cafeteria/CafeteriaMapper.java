package cafeteria;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface CafeteriaMapper {

	// 메뉴명, 카테고리, 태그, 카페이름, 주소
	@Select({
	    "SELECT c.cafeName, c.cafeAddress, m.menuName, cc.cafeCategory, t.cafeTag",
	    "FROM cafeteria c",
	    "JOIN menu m ON c.cafeNum = m.cafeNum",
	    "JOIN cafeTag t ON t.cafeNum = c.cafeNum",
	    "JOIN cafecategory cc ON cc.cafeCategoryNum = (SELECT cafeCategoryNum FROM cafeTag WHERE cafeNum = c.cafeNum)",
	    "WHERE (m.menuName LIKE CONCAT('%', #{menuName}, '%')",
	    "OR cc.cafeCategory LIKE CONCAT('%', #{cafeCategory}, '%')",
	    "OR t.cafeTag LIKE CONCAT('%', #{cafeTag}, '%')",
	    "OR c.cafeName LIKE CONCAT('%', #{cafeName}, '%')",
	    "OR c.cafeAddress LIKE CONCAT('%', #{cafeAddress}, '%'))",
	    "ORDER BY c.cafeName;"
	})
	@Results(id = "cafeSearch", value = {
	    @Result(column = "cafeName", property = "cafeName", jdbcType = JdbcType.VARCHAR),
	    @Result(column = "cafeAddress", property = "cafeAddress", jdbcType = JdbcType.VARCHAR),
	    @Result(column = "menuName", property = "menuName", jdbcType = JdbcType.VARCHAR),
	    @Result(column = "cafeCategory", property = "cafeCategory", jdbcType = JdbcType.VARCHAR),
	    @Result(column = "cafeTag", property = "cafeTag", jdbcType = JdbcType.VARCHAR)
	})
	List<Cafeteria> searchByAll(
	    @Param("menuName") String menuName,
	    @Param("cafeCategory") String cafeCategory,
	    @Param("cafeTag") String cafeTag,
	    @Param("cafeName") String cafeName,
	    @Param("cafeAddress") String cafeAddress
	);

	@Select("SELECT cafeNum, cafeName, cafeOpenTime, cafePhoneNumber, cafeAddress, cafePrice, cafeOwner FROM Cafeteria")
	@Results(id = "cafeResults", value = {
			@Result(column = "cafeNum", property = "cafeNum", jdbcType = JdbcType.INTEGER),
			@Result(column = "cafeName", property = "cafeName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "cafeOpenTime", property = "cafeOpenTime", jdbcType = JdbcType.VARCHAR),
			@Result(column = "cafePhoneNumber", property = "cafePhoneNumber", jdbcType = JdbcType.VARCHAR),
			@Result(column = "cafeAddress", property = "cafeAddress", jdbcType = JdbcType.VARCHAR),
			@Result(column = "cafePrice", property = "cafePrice", jdbcType = JdbcType.INTEGER),
			@Result(column = "cafeOwner", property = "cafeOwner", jdbcType = JdbcType.VARCHAR) })
	List<Cafeteria> selectAll();

	// 맛집 새로 추가
	@Insert({ "INSERT INTO cafeteria (cafeNum, cafeName, cafeOpenTime, cafePhoneNumber,",
			"cafeAddress, cafePrice, cafeOwner)",
			"VALUES (#{cafeNum}, #{cafeName}, #{cafeOpenTime}, #{cafePhoneNumber},",
			"#{cafeAddress}, #{cafePrice}, #{cafeOwner})" })
	int insert(Cafeteria cafeteria);

	@Select("SELECT * FROM cafeteria WHERE cafeNum=#{cafeNum}")
	@ResultMap("cafeResults")
	Cafeteria selectByNum(@Param("cafeNum") int cafeNum);

	// 맛집명 조회
	@Select("SELECT * FROM cafeteria WHERE cafeName=#{cafeName}")
	@ResultMap("cafeResults")
	Cafeteria selectByName(@Param("cafeName") String cafeName);

	// 맛집 정보 수정
	@Update({ "UPDATE cafeteria SET cafeNum=#{cafeNum}, cafeName=#{cafeName}, ",
			"cafeOpenTime=#{cafeOpenTime}, cafePhoneNumber=#{cafePhoneNumber}, ",
			"cafeAddress=#{cafeAddress}, cafePrice=#{cafePrice} ", "WHERE cafeNum=#{cafeNum}" })
	int update(Cafeteria cafeteria);

	// 삭제
	@Delete("DELETE FROM Cafeteria WHERE cafeNum=#{cafeNum}")
	int delete(int cafeNum);

	// 가격 범위 - 해당 범위외 값 안나옴
	@Select({ "SELECT cafeName, cafeOpenTime, cafePhoneNumber, cafeAddress, cafePrice", "FROM cafeteria",
			"WHERE cafePrice BETWEEN #{start} AND #{end}" })
	@ResultMap("cafeResults")
	List<Cafeteria> searchByPrice(@Param("start") int start, @Param("end") int end);

	// 가게 번호와 해당 이미지 조회
	@Select({ "SELECT cp.picNumber, cp.cafePic, cp.cafeNum, c.cafeName, c.cafeOpenTime, c.cafePhoneNumber",
			"c.cafeAddress, c.cafePrice, c.cafeOwner", "FROM cafePic AS cp",
			"JOIN cafeteria AS c ON cp.cafeNum = c.cafeNum", "WHERE cp.cafeNum = #{cafeNum}" })
	List<Cafeteria> showByPic(@Param("cafeNum") int cafeNum);

//	@Select("SELECT cafe_num, cafe_tag")
//	int selectByTag(Cafe_Tag cafeTag);

//	@Insert("INSERT INTO cafe_pic (cafe_num, cafe_pic) VALUES (#{cafe_num}, #{cafe_pic})")
//	int insertPic(@Param("cafe_num") int cafeNum, @Param("cafe_pic") String cafePic);

	@Insert("INSERT INTO cafeTag (cafeNum, cafeTag) VALUES (#{cafeNum}, #{cafeTag}")
	int insertTag(@Param("cafeNum") int cafeNum, @Param("cafeTag") String cafeTag);

	@Insert({ "INSERT INTO menu (cafeNum, menuNum, menuName, menuPrice, menuNamepic) ",
			"VALUES (#{cafeNum}, #{menuNum}, #{menuName}, #{menuPrice}, #{menuNamepic})" })
	int insertMenu(Menus menus);

}
