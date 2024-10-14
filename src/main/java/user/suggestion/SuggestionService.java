package user.suggestion;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SuggestionService {
	boolean insert(Suggestion suggestion);
	
	List<Suggestion> select();
	
	int updateSuggestionStatus(int suggestionId, int chkProcess);

}
