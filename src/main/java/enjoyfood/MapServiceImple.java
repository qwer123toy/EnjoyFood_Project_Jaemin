package enjoyfood;

public class MapServiceImple implements MapService {
	private static final MapServiceImple instance = new MapServiceImple();

	private MapServiceImple() {

	}
	public static MapService getInstance() {
		return instance;
	}
}
