package Cafeteria;

import java.util.List;

public interface CafeteriaService {
	// 메서드 간단하게 사용하는 클래스
	List<Cafeteria> selectAll();

	Cafeteria update(Cafeteria cafeteria);

	int delete(int cafeNum);

	int insert(Cafeteria cafetria);

	Cafeteria selectByName(String cafeName);

	List<Cafeteria> showByPic(int cafeNum);
}
