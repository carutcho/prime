package br.com.prime.commons.utils;

import java.util.Collection;
import java.util.Map;

public class Utils {

	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof String) {
			if (((String) obj).trim().equals("")) {
				return true;
			}
		}

		if (obj instanceof Collection<?>) {
			if (((Collection<?>) obj).size() == 0) {
				return true;
			}
		}

		if (obj instanceof Map<?, ?>) {
			if (((Map<?, ?>) obj).isEmpty()) {
				return true;
			}
		}

		return false;
	}

	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
}
