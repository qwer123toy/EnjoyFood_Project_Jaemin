package Cafeteria;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "cafe_num", "cafe_name", "cafe_openTime", "cafe_phoneNumber", "cafe_address"
					, "cafe_price", "cafe_owner" })
@Generated("jsonschema2pojo")
public class Cafeteria {

	@JsonProperty("cafe_num")
	private Integer cafeNum;
	@JsonProperty("cafe_name")
	private String cafeName;
	@JsonProperty("cafe_openTime")
	private String cafeOpenTime;
	@JsonProperty("cafe_phoneNumber")
	private String cafePhoneNumber;
	@JsonProperty("cafe_address")
	private String cafeAddress;
	@JsonProperty("cafe_price")
	private Integer cafePrice;
	@JsonProperty("cafe_owner")
	private String cafeOwner;
}
