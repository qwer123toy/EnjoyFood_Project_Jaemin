package user.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {
	public static String encryptPassword(String password) {
		try {
			// SHA-256 해시 함수 사용
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());

			// 해시 계산
			byte[] byteData = md.digest();

			// 16진수로 변환
			StringBuilder sb = new StringBuilder();
			for (byte b : byteData) {
				sb.append(String.format("%02x", b));
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		System.out.println(encryptPassword("0000"));
	}
}
