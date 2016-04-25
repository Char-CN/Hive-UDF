package org.blazer.udf;

import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;

public class IDCard_GetBirthday extends UDF {

	public static final String DEFAULT = "yyyy-MM-dd";

	public String evaluate(String idcard) throws Exception {
		return evaluate(idcard, DEFAULT);
	}

	public String evaluate(String idcard, String format) throws Exception {
		if (StringUtils.isBlank(idcard)) {
			return null;
		} else if (idcard.length() == 18) {
			String birthday = idcard.substring(6, 14);
			try {
				Integer.parseInt(birthday);
			} catch (Exception e) {
				return null;
			}
			return new SimpleDateFormat(format).format(new SimpleDateFormat("yyyyMMdd").parse(birthday));
		} else if (idcard.length() == 15) {
			String birthday = idcard.substring(6, 12);
			try {
				Integer.parseInt(birthday);
			} catch (Exception e) {
				return null;
			}
			return new SimpleDateFormat(format).format(new SimpleDateFormat("yyMMdd").parse(birthday));
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		IDCard_GetBirthday s = new IDCard_GetBirthday();
		System.out.println(s.evaluate("130503670401001"));
		System.out.println(s.evaluate("130503670401001", "yyyyMMdd"));
		System.out.println(s.evaluate("43021119900702041X"));
		System.out.println(s.evaluate("43021119900702041X", "yyyyMMdd"));
	}

}
