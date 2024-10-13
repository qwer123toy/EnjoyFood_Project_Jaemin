package cafeteria;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

public interface CafeteriaDynamicMapper {
	@SelectProvider(type = CafeSQLProvider.class, method = "getCafeByPrice")
	@Results({ @Result(column = "cafeName", property = "cafeName"),
			@Result(column = "cafePrice", property = "cafePrice"),
			@Result(column = "cafeAddress", property = "cafeAddress"),
			@Result(column = "cafePhoneNumber", property = "cafePhoneNumber"),
			@Result(column = "cafeTag", property = "cafeTag"),
			@Result(column = "cafeCategory", property = "cafeCategory") })
	List<Cafeteria> getCafeByPrice(@Param("start") Integer start, @Param("end") Integer end);

	@SelectProvider(type = CafeSQLProvider.class, method = "getCafeByPriceAndTags")
	@Results({ @Result(column = "cafeName", property = "cafeName"),
			@Result(column = "cafePrice", property = "cafePrice"),
			@Result(column = "cafeAddress", property = "cafeAddress"),
			@Result(column = "cafePhoneNumber", property = "cafePhoneNumber") })
	List<Cafeteria> getCafeByPriceAndTags(@Param("cafePrice") int cafePrice, @Param("cafetag") List<String> cafetags);

	@SelectProvider(type = CafeSQLProvider.class, method = "getCafesByAddress")
	List<Cafeteria> getCafeByArea(@Param("addressList") List<String> addressList);
}
