package user.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import user.model.User;

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

	@Insert("INSERT INTO user (userID, userPW, userPhoneNumber, userNickname, userType, userOwnerNumber, userPicture) VALUES (#{userID}, #{userPW}, #{userPhoneNumber}, #{userNickname}, #{userType}, #{userOwnerNumber}, #{userPicture})")
	int insert(User user);

	@Select("SELECT count(*) FROM user WHERE userID=#{id}")
	int countUserById(@Param("id") String id);

	@Select("SELECT count(*) FROM user WHERE userPhoneNumber=#{phoneNumber}")
	int countUserByPhoneNumber(@Param("phoneNumber") String phoneNumber);

	@Select("SELECT count(*) FROM user WHERE userNickname=#{nickname}")
	int countUserByNickname(@Param("nickname") String nickname);

	@Select("SELECT count(*) FROM user WHERE userOwnerNumber=#{ownerNumber}")
	int countUserByOwnerNumber(@Param("ownerNumber") String ownerNumber);
}
