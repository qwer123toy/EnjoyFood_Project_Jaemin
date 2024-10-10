package user.model;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {

	@Select("SELECT * FROM user WHERE userID=#{id}")
	@Results(id = "userResults", value = {
			@Result(column = "userID", property = "userID", jdbcType = JdbcType.VARCHAR, id = true),
			@Result(column = "userPW", property = "userPW", jdbcType = JdbcType.VARCHAR),
			@Result(column = "userPhoneNumber", property = "userPhoneNumber", jdbcType = JdbcType.VARCHAR),
			@Result(column = "userNickname", property = "userNickname", jdbcType = JdbcType.VARCHAR),
			@Result(column = "userType", property = "userType", jdbcType = JdbcType.INTEGER),
			@Result(column = "userOwnerNumber", property = "userOwnerNumber", jdbcType = JdbcType.VARCHAR),
			@Result(column = "userPicture", property = "userPicture", jdbcType = JdbcType.BLOB) })
	User selectById(@Param("id") String id);

	@Select("SELECT * FROM user WHERE userPhoneNumber=#{phoneNumber}")
	@ResultMap(value = "userResults")
	User selectByPhoneNumber(@Param("phoneNumber") String phoneNumber);

	@Insert("INSERT INTO user (userID, userPW, userPhoneNumber, userNickname, userType, userOwnerNumber, userPicture) VALUES (#{userID}, #{userPW}, #{userPhoneNumber}, #{userNickname}, #{userType}, #{userOwnerNumber}, #{userPicture})")
	int insert(User user);

	@Select("SELECT count(*) FROM user WHERE userID=#{id}")
	int countUserById(@Param("id") String id);

	@Select("SELECT count(*) FROM user WHERE userPhoneNumber=#{phoneNumber}")
	int countByPhoneNumber(@Param("phoneNumber") String phoneNumber);

	@Select("SELECT count(*) FROM user WHERE userNickname=#{nickname}")
	int countByNickname(@Param("nickname") String nickname);

	@Select("SELECT count(*) FROM user WHERE userOwnerNumber=#{ownerNumber}")
	int countByOwnerNumber(@Param("ownerNumber") String ownerNumber);

//	@Update({ "UPDATE Book", "SET title=#{title}, author=#{author}, publisher=#{publisher}, price=#{price}",
//			"WHERE bookid=#{bookId}" })
//	int update(Book book);

	@Update({ "UPDATE user SET userPW=#{userPW}, userPhoneNumber=#{userPhoneNumber}, userNickname=#{userNickname},",
			"userOwnerNumber=#{userOwnerNumber}, userPicture=#{userPicture} WHERE userID=#{userID}" })
	int update(User user);

	@Delete("DELETE FROM user WHERE userID = #{userId}")
	int delete(@Param("id") String id);

	@Update("UPDATE user SET userPW=#{userPW} WHERE userID=#{userID}")
	int updatePW(User user);
}
