
<div align="center">
<img src="https://github.com/user-attachments/assets/dc72ed60-9e0d-4f59-91d1-e0c7a98cf4a3">

</div>
<h1 align="center">
  Cafeteria
</h1>
<p align="center">">맛집 관련 클래스, 맛집 관련 Mapper와 SQL문 관련 클래스 </p>
<p align="center">맛집, 리뷰, 사진 등의 클래스와 해당 클래스를 DB에 저장하기 위한 <br>
  Mapper, DynamicMapper 등의 SQL 문을 보유하고 있는 클래스들</p>

--- 

## Contents

<p align="left">목 차</p>
<p align="left">
  <a href="#클래스-설명">클래스 설명</a> <br>
  <a href="#주요-코드">주요 코드</a> 
</p>

---

## 클래스 설명

| 클래스명                | 설명                                                                                                                                                       |
|----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **[Cafeteria.java](Cafeteria.java)**                  |  - Cafeteria의 필드값을 정의하는 클래스<br> - DB와 연결할 수 있도록 필드값 선언                                |
| **[CafeCategory.java](CafeCategory.java)**                 |    - CafeteriaCategory의 필드값을 정의하는 클래스<br> - Cafeteria의 카테고리 관리를 위한 필드값 선언                                     |
| **[CafePic.java](CafePic.java)**                 |    - Cafeteria의 사진을 저장하기 위한 클래스 <br> - cafeteria의 사진을 저장하기 위한 필드값 선언                                  |
| **[CafeTag.java](CafeTag.java)**     |    - Cafeteria의 태그들을 저장하기 위한 클래스  <br> - cafeNum을 활용하여 여러개의 cafeTag를 들고있기 위해 필드값 선언                          |
| **[Menu.java](Menu.java)**           |    - Cafeteria의 태그들을 저장하기 위한 클래스  <br> - cafeNum을 활용하여 여러개의 메뉴 정보들을 들고있기 위해 필드값 선언                                                  |
| **[CafeReview.java](CafeReview.java)**      |    - Cafeteria의 리뷰들을 저장하기 위한 클래스<br> - cafeNum을 활용하여 여러개의 리뷰 정보를 저장할 수 있게 하기 위한 필드값 선언      |
| **[CafeteriaMapper.java](CafeteriaMapper.java)**        |    - Cafeteria 관련 SQL문(삽입, 조회, 수정 등)을 DB에 전달하기 위해 SQL문을 저장하고 있는 클래스 <br> - 맛집 검색, 특정 맛집 검색, 사용자 리뷰 저장 등의 SQL문들을 DB와 연결하기 위한 클래스  |
| **[CafeteriaDynamicMapper.java](CafeteriaDynamicMapper.java)**    |    - Cafeteria 관련 SQL문(가격 조회, 태그 및 가격 조회)을 DB에 전달하기 위해 SQL문을 저장하고 있는 클래스 <br> - 가격을 통해 조회하거나 태그를 통해 조회하는 SQL문을 Provider를 통해 작성한 클래스              |
| **[CafeSQLProvider.java](CafeSQLProvider.java)**               |    - Cafeteria 정보를 조회하기 위한 SQL 쿼리 문자열을 동적으로 생성<br> - 특정 가격 범위 내의 카페를 선택 <br> - 가격 및 태그에 따라 카페를 필터링             |
| **[CafeteriaService.java](CafeteriaService.java)**               |    - 카페 관련 데이터를 관리하는 다양한 메서드를 제공<br> - 데이터 조회, 삽입, 업데이트, 삭제 등의 기능을 담당    |
| **[CafeteriaServiceImple.java](CafeteriaServiceImple.java)**                  |  - 싱글톤 패턴을 사용하여 카페 정보를 관리하는 서비스 로직을 구현 <br> - 카페 데이터 조회, 삽입, 업데이트, 삭제 작업이 포함되며, SQL 세션과 MyBatis 매퍼를 활용해 DB와 상호작용
| **[CafeteriaWithPicDTO.java](CafeteriaWithPicDTO.java)**                  |  - Cafeteria 객체와 해당 카페의 여러 이미지를 담기 위한 DTO <br> - DB와 연결할 수 있도록 필드값 선언


<br> 

## 주요 코드

1. 가게 코드(Cafeteria.java)
 - Cafeteria를 DB와 연동하여 사용하기 위해 필드값들을 선언
 - lombok과 Json을 활용하여 생성자, Builder 등을 생성
     
```
    
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "cafeNum", "cafeName", "cafeOpenTime", "cafePhoneNumber", "cafeAddress", "cafePrice",
		"cafeOwner", "cafeExplain" })
@Generated("jsonschema2pojo")
public class Cafeteria {

	@JsonProperty("cafeNum")
	private Integer cafeNum;
	@JsonProperty("cafeName")
	private String cafeName;
	@JsonProperty("cafeOpenTime")
	private String cafeOpenTime;
	@JsonProperty("cafePhoneNumber")
	private String cafePhoneNumber;
	@JsonProperty("cafeAddress")
	private String cafeAddress;
	@JsonProperty("cafePrice")
	private Integer cafePrice;
	@JsonProperty("cafeOwner")
	private String cafeOwner;
	@JsonProperty("cafeExplain")
	private String cafeExplain;
}

```
<br>

2. 전체 가게 조회 코드(CafeteriaMapper.java)
 - 카페 정보를 전체적으로 조회하는 코드
 - Results를 통해 다른 메서드에서도 활용가능

```
@Select("SELECT * FROM Cafeteria")
	@Results(id = "cafeResults", value = {
			@Result(column = "cafeNum", property = "cafeNum", jdbcType = JdbcType.INTEGER),
			@Result(column = "cafeName", property = "cafeName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "cafeOpenTime", property = "cafeOpenTime", jdbcType = JdbcType.VARCHAR),
			@Result(column = "cafePhoneNumber", property = "cafePhoneNumber", jdbcType = JdbcType.VARCHAR),
			@Result(column = "cafeAddress", property = "cafeAddress", jdbcType = JdbcType.VARCHAR),
			@Result(column = "cafePrice", property = "cafePrice", jdbcType = JdbcType.INTEGER),
			@Result(column = "cafeOwner", property = "cafeOwner", jdbcType = JdbcType.VARCHAR) })
	List<Cafeteria> selectAll();
```
<br>

3. 카테고리 검색 코드(CafeSQLProvider.java)
 - 특정 가격 이하의 카페 목록을 조회하는 SQL 쿼리를 생성하며, 태그 리스트가 제공되면 해당 태그들에 맞는 카페만 필터링하여 반환
 - cafePrice와 cafetags 조건을 반영해 동적으로 쿼리를 구성
```
public static String getCafeByPriceAndTags(@Param("cafePrice") int cafePrice,
                                           @Param("cafetag") List<String> cafetags) {
    // SQL 쿼리 빌드 시작
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT DISTINCT c.cafeNum, c.cafeName, c.cafeAddress, c.cafePhoneNumber, c.cafePrice ");
    sql.append("FROM cafeteria c ");
    sql.append("NATURAL JOIN cafecategory cc ");
    sql.append("NATURAL JOIN category_management cm ");
    sql.append("NATURAL JOIN cafetag t ");
    sql.append("WHERE c.cafePrice <= #{cafePrice} "); // 가격 조건 필터

    // cafetag 리스트가 비어 있지 않으면 동적 필터링 추가
    if (cafetags != null && !cafetags.isEmpty()) {
        sql.append("AND t.cafeTag IN (");
        for (int i = 0; i < cafetags.size(); i++) {
            sql.append("#{cafetag[").append(i).append("]}"); // 각 태그를 인덱스로 지정
            if (i < cafetags.size() - 1) {
                sql.append(", "); // 마지막 요소가 아닐 경우 콤마 추가
            }
        }
        sql.append(") ");
    }

    return sql.toString(); // 최종 SQL 쿼리 문자열 반환
}


```
<br>

4. 가게 정보 삽입 코드(CafeteriaServiceImple.java)
 -  클라이언트로부터 JSON 형식의 카페 정보를 받아, 가게 데이터를 추출하고 Cafeteria 객체를 생성하여 데이터베이스에 삽입
 -  카페에 대한 카테고리와 태그 정보를 처리하며, 최대 5개의 태그만 유지하여 삽입
 -  응답 코드와 메시지를 설정하여 클라이언트에 결과를 반환
```
@Override
	public List<Cafeteria> getCafeByPriceAndTags(int cafePrice, List<String> cafetags) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CafeteriaDynamicMapper mapper = sqlSession.getMapper(CafeteriaDynamicMapper.class);

			List<Cafeteria> resultList = mapper.getCafeByPriceAndTags(cafePrice, cafetags);

			return resultList;
		}
	}
```
<br>


