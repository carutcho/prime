package br.com.prime.mail.enums;

public enum TipoCryptoEnum {
	
	RSA("RSA"),
	HMACSHA1("HmacSHA1"),
	HMACSHA256("HmacSHA256"),
	HMACMD5("HmacMD5"),
	BCRYPT("BCrypt"),
	AES("AES");
	
	private String label;
	
	private TipoCryptoEnum(String label){
		this.label = label;
	}
	
	public String getLabel(){
		return label;
	}
}