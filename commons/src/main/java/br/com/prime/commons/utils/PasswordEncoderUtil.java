package br.com.prime.commons.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtil {

	private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public static String encriptar(String texto){
		return passwordEncoder.encode(texto);
	}
}
