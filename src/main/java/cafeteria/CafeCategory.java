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
@JsonPropertyOrder({ "categoryNum", "categoryName" })
@Generated("jsonschema2pojo")
public class CafeCategory {

	@JsonProperty("categoryNum")
	private Integer categoryNum;
	@JsonProperty("categoryName")
	private String categoryName;
}
