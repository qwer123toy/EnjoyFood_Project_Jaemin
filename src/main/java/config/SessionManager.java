package config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class SessionManager {
	private static Map<String, HttpSession> map = new HashMap<String, HttpSession>();

	public static HttpSession getSession(String id) {
		return map.get(id);
	}

	public static void addSession(String id, HttpSession session) {
		map.put(id, session);
	}

	public static void removeSession(String id) {
		map.remove(id);
	}
}
