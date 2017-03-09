package br.com.prime.crypto;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import br.com.prime.crypto.enums.TipoCryptoEnum;
import br.com.prime.crypto.exception.NaoDecriptaException;
import br.com.prime.crypto.interfaces.Crypto;

public class CryptoHMAC implements Crypto{
	
	private SecretKey secret;
	private TipoCryptoEnum tipoHmac;
	
	public CryptoHMAC() {
	}
	
	public CryptoHMAC(byte[] chave, TipoCryptoEnum tipoHmac){
		this.tipoHmac = tipoHmac;
		this.secret = new SecretKeySpec(chave, tipoHmac.getLabel());
	}
	
	public byte[] encriptarToByte(String mensagem){
		try{
			Mac mac = Mac.getInstance(tipoHmac.getLabel());
			mac.init(secret);
			return mac.doFinal(mensagem.getBytes());
		}catch (Exception e) {
			return null;
			//logar erro de criptografia
		}
	}

	public byte[] encriptarToByte(String mensagem, Object secret){
		this.secret = (SecretKey) secret;
		return encriptarToByte(mensagem);
	}
	
	public String encriptarToString(String mensagem){
		return Crypto.toHexString(encriptarToByte(mensagem));
	}
	
	public String decriptar(byte[] mensagem) throws NaoDecriptaException{
		throw new NaoDecriptaException(Crypto.MSG_NAO_DECRIPTA);
	}
	
	public void setChave(byte[] chave) {
		this.secret = new SecretKeySpec(chave, tipoHmac.getLabel());
	}
}
