package br.com.prime.crypto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.prime.crypto.exception.NaoDecriptaException;
import br.com.prime.crypto.interfaces.Crypto;

public class CryptoBcypt implements Crypto{

	public CryptoBcypt(){
	}
	
	private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public String encriptarToString(String mensagem){
		return passwordEncoder.encode(mensagem);
	}

	public byte[] encriptarToByte(String mensagem){
		return passwordEncoder.encode(mensagem).getBytes();
	}
	
	public String decriptar(byte[] texto) throws NaoDecriptaException{
		throw new NaoDecriptaException(Crypto.MSG_NAO_DECRIPTA);
	}

	@Override
	public byte[] encriptarToByte(String mensagem, Object key) {
		return encriptarToByte(mensagem);
	}

	@Override
	public String encriptarToString(String texto, Object key) {
		return encriptarToString(texto);
	}
}
