import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import user.model.User;

public interface UserMapper {
	
	@Select("SELECT * FROM user WHERE user_id=#{id}")
	User SelectById(@Param("id") String id);
	
	
}
