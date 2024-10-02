package Cafeteria;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CafeteriaMapper {

	@Insert({
			"INSERT INTO cafeteria (cafe_num, cafe_name, cafe_openTime, cafe_phoneNumber, cafe_address, cafe_price, cafe_owner)",
			"VALUES (#{cafe_num}, #{cafe_name}, #{cafe_openTime}, #{cafe_phoneNumber}, #{cafe_address}, #{cafe_price}, #{cafe_owner}" })
	int insert(Cafeteria cafeteria);

	@Select("SELECT * FROM cafeteria WHERE cafe_num=#{num}")
	Cafeteria selectByNum(@Param("cafe_num") int num);

	@Select("SELECT * FROM cafeteria WHERE cafe_name=#{name}")
	Cafeteria selectByName(@Param("cafe_name") String name);

	@Update("UPDATE cafeteria SET cafe_num=#{cafe_num}, cafe_name=#{cafe_name}, cafe_openTime=#{cafe_openTime}, cafe_phoneNumber=#{cafe_phoneNumber}, cafe_address=#{cafe_address}, cafe_price=#{cafe_price}, cafe_owner=#{cafe_owner}")
	int update(Cafeteria cafeteria);
}