package br.com.prime.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

import br.com.prime.crypto.enums.TipoCryptoEnum;
import br.com.prime.crypto.exception.NaoDecriptaException;
import br.com.prime.crypto.interfaces.Crypto;

public class CryptoRSA implements Crypto{

	private static String RSA = TipoCryptoEnum.RSA.getLabel();
	private PublicKey chavePublica;
	private PrivateKey chavePrivada;

	public CryptoRSA() { }
	
	public CryptoRSA(PublicKey chavePublica, PrivateKey chavePrivada){
		this.chavePublica = chavePublica;
		this.chavePrivada = chavePrivada;
	}
	
	public static KeyPair gerarChaves(int tamanho) throws Exception {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance(RSA);
		keyGen.initialize(tamanho);
		return keyGen.generateKeyPair();
	}

	public byte[] encriptarToByte(String texto){
		try{
			final Cipher cipher = Cipher.getInstance(RSA);
			cipher.init(Cipher.ENCRYPT_MODE, chavePublica);
			return cipher.doFinal(texto.getBytes());
		}catch (Exception e) {
			return null;
			//logar erro de criptografia
		}
	}

	public byte[] encriptarToByte(String texto, Object publica){
		setChavePublica((PublicKey) publica);
		return encriptarToByte(texto);
	}
	
	public String encriptarToString(String texto, Object publica){
		return Crypto.toHexString(encriptarToByte(texto, publica));
	}
	
	public String encriptarToString(String texto){
		return Crypto.toHexString(encriptarToByte(texto));
	}

	public String decriptar(byte[] texto) throws NaoDecriptaException{
		try{
			final Cipher cipher = Cipher.getInstance(RSA);
			cipher.init(Cipher.DECRYPT_MODE, chavePrivada);
			return new String(cipher.doFinal(texto));
		}catch (Exception e) {
			return null;
			//logar erro de criptografia
		}
	}

	public void setChavePublica(PublicKey chavePublica) {
		this.chavePublica = chavePublica;
	}

	public void setChavePrivada(PrivateKey chavePrivada) {
		this.chavePrivada = chavePrivada;
	}
	
}
