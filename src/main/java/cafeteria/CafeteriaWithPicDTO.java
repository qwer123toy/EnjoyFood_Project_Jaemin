package cafeteria;

import java.util.List;

public class CafeteriaWithPicDTO {
    private Cafeteria cafeteria;
    private List<CafePic> cafePics;

    // 생성자
    public CafeteriaWithPicDTO(Cafeteria cafeteria, List<CafePic> cafePics) {
        this.cafeteria = cafeteria;
        this.cafePics = cafePics;
    }

    // Getter 메서드
    public Cafeteria getCafeteria() {
        return cafeteria;
    }

    public List<CafePic> getCafePics() {
        return cafePics;
    }
}
