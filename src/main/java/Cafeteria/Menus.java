package Cafeteria;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "cafe_num", "menu_num", "menu_name", "menu_price", "menu_namepic" })
@Generated("jsonschema2pojo")
public class Menus {

	@JsonProperty("cafe_num")
	private Integer cafeNum;
	@JsonProperty("menu_num")
	private Integer menuNum;
	@JsonProperty("menu_name")
	private String menuName;
	@JsonProperty("menu_price")
	private Integer menuPrice;
	@JsonProperty("menu_namepic")
	private String menuNamepic;
}