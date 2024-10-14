package config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public <T> T readBodyJson(HttpServletRequest req, Class<T> valueType) throws IOException {
		String body = readBody(req);
		JsonMapper mapper = new JsonMapper();
		T t = mapper.readValue(body, valueType);
		return t;
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
	
	public <T> T readBodyJsonForMenu(String json, Class<T> valueType) throws IOException {
		JsonMapper mapper = new JsonMapper();
		T t = mapper.readValue(json, valueType);
		return t;
	}
	
//	public <T> T readBodyJsonForImg(HttpServletRequest req, Class<T> valueType) throws IOException {
//		String body = readBodyForImg(req);
//		JsonMapper mapper = new JsonMapper();
//		T t = mapper.readValue(body, valueType);
//		return t;
//	}
//
//	 // 1. JSON 본문 읽기
//    public String readBodyForImg(HttpServletRequest req) throws IOException {
//        StringBuilder stringBuilder = new StringBuilder();
//        BufferedReader bufferedReader = req.getReader();
//        String line;
//        
//        while ((line = bufferedReader.readLine()) != null) {
//            stringBuilder.append(line);
//        }
//        
//        // 읽은 JSON 본문을 문자열로 반환
//        String jsonBody = stringBuilder.toString();
//
//        System.out.println(extractImageUpload(jsonBody));
//        // imageUpload 필드만 추출해서 반환
//        return extractImageUpload(jsonBody);
//    }
//
//    // 2. imageUpload 필드 추출 메서드
//    private String extractImageUpload(String jsonBody) {
//        // 정규표현식: "imageUpload" 필드의 중괄호 안 내용 매칭
//        String regex = "\"cafePic\":\\{(.*?)\\}";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(jsonBody);
//
//        if (matcher.find()) {
//            // 중괄호 포함한 내용 반환
//            return "{" + matcher.group(1) + "}";
//        } else {
//            // imageUpload 필드가 없으면 빈 객체 반환
//            return "{}";
//        }
//    }
	
	

	public void writeBodyJson(HttpServletResponse resp, Object object) throws IOException {
		JsonMapper mapper = new JsonMapper();
		String json = mapper.writeValueAsString(object);
		writeBody(resp, json);
	}

	public void writeBody(HttpServletResponse resp, String string) throws IOException {
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
