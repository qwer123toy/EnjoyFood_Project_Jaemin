package user.suggestion;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface SuggestionMapper {

	@Select("SELECT * FROM user_suggestion")
	List<Suggestion> select();

	@Insert("INSERT INTO `user_suggestion` (`userId`, `title`, `suggestion`) VALUES (#{userId}, #{title}, #{suggestion})")
	int insert(Suggestion suggestion);
	
	@Update("UPDATE user_suggestion SET chkProcess=#{chkProcess} where suggestionId = #{suggestionId}")
	int updateSuggestionStatus(@Param("suggestionId") int suggestionId, @Param("chkProcess") int chkProcess);
	

}
