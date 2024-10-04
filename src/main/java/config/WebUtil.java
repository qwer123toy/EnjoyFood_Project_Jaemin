package config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.json.JsonMapper;

public class WebUtil {
	private HashMap<String, String> mimeTypes;

	public WebUtil() {
		mimeTypes = new HashMap<String, String>();
		mimeTypes.put("html", "text/html; charset=utf-8");
		mimeTypes.put("plain", "text/plain; charset=utf-8");
		mimeTypes.put("json", "application/json; charset=utf-8");
	}

	public String readBody(HttpServletRequest req) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = req.getReader();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line);
		}
		return stringBuilder.toString();
	}

	public void writeBodyJson(HttpServletResponse resp, Object object) throws IOException {
		PrintWriter pw = resp.getWriter();
		JsonMapper mapper = new JsonMapper();
		String json = mapper.writeValueAsString(object);
		pw.print(json);
		pw.flush();
	}

	public void writeBodyPlain(HttpServletResponse resp, String string) throws IOException {
		PrintWriter pw = resp.getWriter();
		pw.print(string);
		pw.flush();
	}

	public void setCodeAndMimeType(HttpServletResponse resp, int status, String mimeType) {
		setStatus(resp, status);
		setMimeType(resp, mimeType);
	}

	private void setStatus(HttpServletResponse resp, int status) {
		resp.setStatus(status);
	}

	private void setMimeType(HttpServletResponse resp, String mimeType) {
		resp.setHeader("Content-Type", mimeTypes.get(mimeType));
	}
}
