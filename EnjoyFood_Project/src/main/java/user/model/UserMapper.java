package user.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import user.model.User;

public interface UserMapper {

	@Select("SELECT * FROM user WHERE user_ID=#{id}")
	User selectById(@Param("id") String id);

	@Insert("INSERT INTO user (user_ID, user_PW, user_PhoneNumber, user_Type, user_OwnerNumber, user_Picture) VALUES (#{user_ID}, #{user_PW}, #{user_PhoneNumber}, #{user_Type}, #{user_OwnerNumber}, #{user_Picture})")
	int insert(User user);

	@Select("SELECT count(*) FROM user WHERE user_ID=#{id}")
	int isIdDuplicate(@Param("id") String id);

	@Select("SELECT count(*) FROM user WHERE user_PhonNumber=#{phoneNumber}")
	int checkDuplicatePhoneNumber(@Param("phoneNumber") String phoneNumber);

	@Select("SELECT count(*) FROM user WHERE user_OwnerNumber=#{ownerNumber}")
	int checkDuplicateOwnerNumber(@Param("ownerNumber") String ownerNumber);
}
