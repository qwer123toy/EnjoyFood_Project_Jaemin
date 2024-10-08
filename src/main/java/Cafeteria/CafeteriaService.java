package Cafeteria;

import java.util.List;

// 메서드 간단하게 사용하는 클래스
public interface CafeteriaService {
	List<Cafeteria> selectAll();

	Cafeteria update(Cafeteria cafeteria);

	int delete(int cafeNum);

	int insert(Cafeteria cafetria);

	Cafeteria selectByName(String cafeName);

	List<Cafeteria> showByPic(int cafeNum);

	int insertMenu(Menus menus);
}
