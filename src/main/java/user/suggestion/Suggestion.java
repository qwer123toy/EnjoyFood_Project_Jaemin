package user.suggestion;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Suggestion {
	private int suggestionId;
	private String userId;
	private String title;
	private String suggestion;
	private int chkProcess;
	private LocalDateTime uploadTime;
}
