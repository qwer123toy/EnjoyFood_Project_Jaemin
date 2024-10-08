package user.model;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class UserValidator {
	public static Map<String, String> validate(User user) {
		Map<String, String> map = new HashMap<String, String>();
		if (!isValidID(user.getUserID())) {
			map.put("error", "아이디 유효성 에러");
		} else if (!isValidPW(user.getUserPW())) {
			map.put("error", "비밀번호 유효성 에러");
		} else if (!isValidNickname(user.getUserNickname())) {
			map.put("error", "닉네임 유효성 에러");
		} else if (!isValidPhoneNumber(user.getUserPhoneNumber())) {
			map.put("error", "전화번호 유효성 에러");
		} else if (!isValidOwnerInput(user.getUserOwnerNumber(), user.getUserPicture())) {
			map.put("error", "사업자 유효성 에러");
		} else if (user.getUserOwnerNumber().length() > 0 && !isValidOwnerNumber(user.getUserOwnerNumber())) {
			map.put("error", "사업자번호 유효성 에러");
		} else {
			map.put("valid", "유효함");
		}
		return map;
	}

	public static boolean isValidID(String id) {
		String regex = "^[a-zA-Z][a-zA-Z0-9]{3,19}$";
		return Pattern.matches(regex, id);
	}

	public static boolean isValidPW(String pw) {
		if (pw.length() < 8 || pw.length() > 20) {
			return false;
		}

		boolean hasUpperCase = pw.chars().anyMatch(Character::isUpperCase);
		boolean hasLowerCase = pw.chars().anyMatch(Character::isLowerCase);
		boolean hasNumber = pw.chars().anyMatch(Character::isDigit);
		boolean hasSpecialChar = pw.chars().anyMatch(ch -> "~!@#$%^&*_-+=`|\\(){}[\\]:;\"'<>,.?/".indexOf(ch) >= 0);

		return (hasUpperCase || hasLowerCase) && hasNumber && hasSpecialChar;
	}

	public static boolean isValidNickname(String nickname) {
		String regex = "^[a-zA-Z][a-zA-Z0-9]{3,19}$";
		return Pattern.matches(regex, nickname);
	}

	public static boolean isValidPhoneNumber(String phoneNumber) {
		String regex = "^(\\d{3}-\\d{4}-\\d{4}|\\d{3}-\\d{3}-\\d{4})$";
		return Pattern.matches(regex, phoneNumber);
	}

	public static boolean isValidOwnerNumber(String ownerNumber) {
		String regex = "^\\d{3}-\\d{2}-\\d{5}$";
		return Pattern.matches(regex, ownerNumber);
	}

	public static boolean isValidOwnerInput(String ownerNumber, String picture) {
		return (ownerNumber.length() == 0 && picture.length() == 0)
				|| (ownerNumber.length() != 0 && picture.length() != 0);
	}
}
