package Cafeteria;

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
@JsonPropertyOrder({ "cafe_categoryNum", "cafe_category" })
@Generated("jsonschema2pojo")
public class Cafe_Category {

	@JsonProperty("cafe_categoryNum")
	private Integer cafeCategoryNum;
	@JsonProperty("cafe_category")
	private String cafeCategory;
}
