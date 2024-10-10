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
@JsonPropertyOrder({ "cafeNum", "cafeTag" })
@Generated("jsonschema2pojo")
public class CafeTag {

	@JsonProperty("cafeNum")
	private Integer cafeNum;
	@JsonProperty("cafeTag")
	private String cafeTag;
}
