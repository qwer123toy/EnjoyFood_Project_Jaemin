package user.suggestion;

import java.util.List;

public interface SuggestionService {
	boolean insert(Suggestion suggestion);
	
	List<Suggestion> select();
}
