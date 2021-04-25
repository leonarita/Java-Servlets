package utils.cookie;

import java.util.Objects;

import javax.servlet.http.Cookie;

public abstract class CookieUtils {
	
	public static boolean temAutorizacao(Cookie[] cookies) {

		if(Objects.isNull(cookies)) {
			return false;
		}
		
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("id")) {
				return true;
			}
		}
		
		return false;
	}

}
