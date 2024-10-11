package cafeteria;

import java.util.List;

// 메서드 간단하게 사용하는 클래스
public interface CafeteriaService {
	List<Cafeteria> selectAll();

	List<Cafeteria> showByPic(int cafeNum);

	Cafeteria update(Cafeteria cafeteria);

	Cafeteria selectByName(String cafeName);

	int insert(Cafeteria cafetria);

	int insertMenu(Menu menu);

	int delete(int cafeNum);

	List<Cafeteria> searchByAll(String menuName, String categoryName, String cafetag, String cafeName,
			String cafeAddress);

	double selectAvg(int cafeNum);

//	List<Cafeteria> searchByPT(int cafePrice, List<String> cafetags);
	List<Cafeteria> getCafeByPriceAndTags(int cafePrice, List<String> cafetags);

	List<Menu> showCafeMenu(int cafeNum);

	int insertReview(CafeReview cafeReview);

}
