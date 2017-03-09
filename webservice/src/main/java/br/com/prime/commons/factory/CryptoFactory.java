package br.com.prime.commons.factory;

import br.com.prime.crypto.CryptoAES;
import br.com.prime.crypto.CryptoBcypt;
import br.com.prime.crypto.CryptoHMAC;
import br.com.prime.crypto.CryptoRSA;
import br.com.prime.crypto.enums.TipoCryptoEnum;
import br.com.prime.crypto.interfaces.Crypto;

public class CryptoFactory {

	public static Crypto getCrypto(TipoCryptoEnum tipo){
		
		switch (tipo) {
		case AES:
			return new CryptoAES();
		case BCRYPT:
			return new CryptoBcypt();
		case HMACMD5:
		case HMACSHA1:
		case HMACSHA256:
			return new CryptoHMAC();
		case RSA:
			return new CryptoRSA();
		default:
			return null;
		}
		
	}
}
