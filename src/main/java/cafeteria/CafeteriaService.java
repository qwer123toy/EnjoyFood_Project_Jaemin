package cafeteria;

import java.util.List;

// 메서드 간단하게 사용하는 클래스
public interface CafeteriaService {
	List<Cafeteria> selectAll();

	List<Cafeteria> showByPic(int cafeNum);

	Cafeteria update(Cafeteria cafeteria);

	Cafeteria selectByName(String cafeName);

	int insert(Cafeteria cafetria);

	int insertMenu(Menus menus);

	int delete(int cafeNum);

	List<Cafeteria> searchByAll(String menuName, String cafeCategory, String cafeTag, String cafeName,
			String cafeAddress);
}
