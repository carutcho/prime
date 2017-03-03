package br.com.prime.commons.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {
	
	public static boolean isValorMaiorZero(BigDecimal obj){
		return Utils.isNotEmpty(obj) && obj.compareTo(new BigDecimal("0")) > 0;
	}

	public static BigDecimal parseBigDecimal(String valor) {
		BigDecimal resultado = BigDecimal.valueOf(Double.valueOf(valor));
		return resultado;
	}
}
