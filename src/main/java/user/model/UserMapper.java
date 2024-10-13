package user.model;

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

	@Select("SELECT * FROM user")
	@ResultMap(value = "userResults")
	List<User> selectAll();

	@Select("SELECT * FROM user WHERE userID=#{id}")
	@ResultMap(value = "userResults")
	List<User> selectAllById(@Param("id") String id);

	@Update("UPDATE user SET active=#{active} where userID = #{id}")
	int updateActivationStatus(@Param("id") String id, @Param("active") int active);

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

	@Update({
			"UPDATE user SET userPW=#{userPW}, userPhoneNumber=#{userPhoneNumber}, userNickname=#{userNickname} WHERE userID=#{userID}" })
	int updateAll(User user);

	@Update({
			"UPDATE user SET userPhoneNumber=#{userPhoneNumber}, userNickname=#{userNickname} WHERE userID=#{userID}" })
	int updatePNAndNN(User user);

	@Update("UPDATE user SET userPW=#{userPW} WHERE userID=#{userID}")
	int updatePW(User user);

	@Delete("DELETE FROM user WHERE userID = #{userId}")
	int delete(@Param("id") String id);
}
