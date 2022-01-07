package ePlattform.Client.helperClasses;

public class PasswordChecker {
	
	public static boolean check(String pwField, String pwFieldRepeat, int reqLength, String sonderzeichen, boolean number, boolean uppercase) {
		String result = "";
		if(!checkLength(reqLength, pwField)) {
			return false;
		}
		if(!checkRepeat(pwField, pwFieldRepeat)) {
			return false;	
		}
		if(sonderzeichen != null) {
			if(!checkSonderzeichen(pwField, sonderzeichen)) {
				return false;
			}
		}
		if(!number) {
			return false;
		}
		if(!uppercase) {
			return false;
		}
		
		return true;
	}

	public static boolean checkLength(int reqLength, String password) {		
		if(password.length() < reqLength) {
			return false;
		}
		return true;
	}
	
	public static boolean checkRepeat(String pw1, String pw2) {		
		return pw1.equals(pw2);
	}
	
	public static boolean checkSonderzeichen(String pw, String sonderzeichen) {
		for(int i = 0; i  < sonderzeichen.length(); i++) {
			if(pw.contains(sonderzeichen.charAt(i)+"")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkNumber(String pw) {
		for(int i = 0; i < 9; i++) {
			if(pw.contains(i+"")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkUppercase(String pw) {
		String uppercases = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i = 0; i < uppercases.length(); i++) {
			if(pw.contains(uppercases.charAt(i)+"")) {
				return true;
			}
		}
		return false;
	}
}
