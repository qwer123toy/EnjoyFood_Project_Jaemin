package user.model;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "user_ID", "user_PW", "user_PhoneNumber", "user_Type", "user_OwnerNumber", "user_Picture" })
@Generated("jsonschema2pojo")
public class User {

	@JsonProperty("user_ID")
	public String userID;
	@JsonProperty("user_PW")
	public String userPW;
	@JsonProperty("user_PhoneNumber")
	public String userPhoneNumber;
	@JsonProperty("user_Type")
	public int userType;
	@JsonProperty("user_OwnerNumber")
	public String userOwnerNumber;
	@JsonProperty("user_Picture")
	public String userPicture;

}