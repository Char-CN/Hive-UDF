package org.blazer.udf;

import org.apache.commons.lang.StringUtils;

public class IDCard_GetArea extends IDCard {

	public String evaluate(String idcard) throws Exception {
		if (StringUtils.isBlank(idcard) || (idcard.length() != 18 && idcard.length() != 15)) {
			return null;
		}
		return cityCodeMap.get(idcard.substring(0, 2));
	}

	public static void main(String[] args) throws Exception {
		IDCard_GetArea a = new IDCard_GetArea();
		System.out.println(a.evaluate("123213123123111"));
	}

}
