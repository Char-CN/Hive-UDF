package org.blazer.udf;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;

public class IDCard_GetSex extends UDF {

	public String evaluate(String idcard) throws Exception {
		if (StringUtils.isBlank(idcard)) {
			return "NA";
		}
		char bit = '0';
		if (idcard.length() == 18) {
			// 第17位
			bit = idcard.charAt(16);
		} else if (idcard.length() == 15) {
			// 第14位
			bit = idcard.charAt(14);
		} else {
			return "NA";
		}
		if (bit == '0' || bit == '2' || bit == '4' || bit == '6' || bit == '8') {
			return "F";
		}
		return "M";
	}

	public static void main(String[] args) throws Exception {
		IDCard_GetSex s = new IDCard_GetSex();
		System.out.println(s.evaluate("130503670401001"));
		System.out.println(s.evaluate("130503670401012"));
		System.out.println(s.evaluate("130503670401003"));
		System.out.println(s.evaluate("43021111100702041S"));
		System.out.println(s.evaluate("43021111100702043S"));
		System.out.println(s.evaluate("43021111100702044S"));
		System.out.println(s.evaluate("43021111100702045S"));
	}

}
