package cafeteria;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "cafeNum", "cafeName", "cafeOpenTime", "cafePhoneNumber", "cafeAddress", "cafePrice",
		"cafeOwner", "cafeExplain" })
@Generated("jsonschema2pojo")
public class Cafeteria {

	@JsonProperty("cafeNum")
	private Integer cafeNum;
	@JsonProperty("cafeName")
	private String cafeName;
	@JsonProperty("cafeOpenTime")
	private String cafeOpenTime;
	@JsonProperty("cafePhoneNumber")
	private String cafePhoneNumber;
	@JsonProperty("cafeAddress")
	private String cafeAddress;
	@JsonProperty("cafePrice")
	private Integer cafePrice;
	@JsonProperty("cafeOwner")
	private String cafeOwner;
	@JsonProperty("cafeExplain")
	private String cafeExplain;
}
