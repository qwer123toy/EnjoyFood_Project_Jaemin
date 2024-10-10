package user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	private String userID;
	private String userPW;
	private String userPhoneNumber;
	private String userNickname;
	private int userType;
	private String userOwnerNumber;
	private String userPicture;
	private int active;

}