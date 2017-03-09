package br.com.prime.crypto.main;

import java.security.KeyPair;

import br.com.prime.crypto.CryptoRSA;
import br.com.prime.crypto.interfaces.Crypto;

public class Main {

	public static void main(String args[]) {

		try {
			// Usando exemplo com RSA
			KeyPair chaves = CryptoRSA.gerarChaves(1024);
			Crypto crypto = new CryptoRSA(chaves.getPublic(), chaves.getPrivate());
			byte[] nomeCrypt = crypto.encriptarToByte("Reinaldo");			
			System.out.println("Enciptando meu nome: " + new String(nomeCrypt));
			System.out.println("Decriptando meu nome: " + crypto.decriptar(nomeCrypt));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}