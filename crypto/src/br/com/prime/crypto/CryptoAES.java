package br.com.prime.crypto;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import br.com.prime.crypto.enums.TipoCryptoEnum;
import br.com.prime.crypto.interfaces.Crypto;

public class CryptoAES implements Crypto{

	private byte[] chave;
	
	private SecretKey secret;
	private static String AES = TipoCryptoEnum.AES.getLabel();
	
	public CryptoAES(){
		
	}
	
	public CryptoAES(byte[] chave){
		this.chave = chave;
		secret = new SecretKeySpec(chave, AES);
	}
	
	public String decriptar(byte[] mensagemEncriptada){

		try {
			Cipher cipher = Cipher.getInstance(TipoCryptoEnum.AES.getLabel());
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(chave, AES));
			return new String(cipher.doFinal(mensagemEncriptada));
		} catch (Exception e) {
			return null;
			//logar erro de criptografia
		}
	}
	
	public byte[] encriptarToByte(String mensagem){
		try{
			Cipher cipher = Cipher.getInstance(TipoCryptoEnum.AES.getLabel());
			cipher.init(Cipher.ENCRYPT_MODE, secret);
			return cipher.doFinal(mensagem.getBytes());
		}catch (Exception e) {
			return null;
		}
	}

	public byte[] encriptarToByte(String mensagem, Object secret){
		
		this.secret = (SecretKey) secret;
		return encriptarToByte(mensagem);
	}
	
	public String encriptarToString(String mensagem){
		return Crypto.toHexString(encriptarToByte(mensagem));
	}
	
	public static byte[] gerarChave() throws NoSuchAlgorithmException {
		KeyGenerator geradorChave = KeyGenerator.getInstance(AES);
		SecretKey chave = geradorChave.generateKey();
		return chave.getEncoded();
	}
	
	public void setChave(byte[] chave) {
		this.chave = chave;
	}
}
