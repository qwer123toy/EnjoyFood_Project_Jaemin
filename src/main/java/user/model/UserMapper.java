package user.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import user.model.User;

public interface UserMapper {

	@Select("SELECT * FROM user WHERE user_ID=#{id}")
	@Results(id = "userResults", value = {
			@Result(column = "user_ID", property = "userID", jdbcType = JdbcType.VARCHAR, id = true),
			@Result(column = "user_PW", property = "userPW", jdbcType = JdbcType.VARCHAR),
			@Result(column = "user_PhoneNumber", property = "userPhoneNumber", jdbcType = JdbcType.VARCHAR),
			@Result(column = "user_Nickname", property = "userNickname", jdbcType = JdbcType.VARCHAR),
			@Result(column = "user_Type", property = "userType", jdbcType = JdbcType.INTEGER),
			@Result(column = "user_OwnerNumber", property = "userOwnerNumber", jdbcType = JdbcType.VARCHAR),
			@Result(column = "user_Picture", property = "userPicture", jdbcType = JdbcType.BLOB) })
	User selectById(@Param("id") String id);

	@Insert("INSERT INTO user (user_ID, user_PW, user_PhoneNumber, user_Nickname, user_Type, user_OwnerNumber, user_Picture) VALUES (#{userID}, #{userPW}, #{userPhoneNumber}, #{userNickname}, #{userType}, #{userOwnerNumber}, #{userPicture})")
	int insert(User user);

	@Select("SELECT count(*) FROM user WHERE user_ID=#{id}")
	int countUserById(@Param("id") String id);

	@Select("SELECT count(*) FROM user WHERE user_PhoneNumber=#{phoneNumber}")
	int countUserByPhoneNumber(@Param("phoneNumber") String phoneNumber);

	@Select("SELECT count(*) FROM user WHERE user_Nickname=#{nickname}")
	int countUserByNickname(@Param("nickname") String nickname);

	@Select("SELECT count(*) FROM user WHERE user_OwnerNumber=#{ownerNumber}")
	int countUserByOwnerNumber(@Param("ownerNumber") String ownerNumber);
}
