package org.blazer.udf;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;

public class IDCard_GetAge extends UDF {

	public static final String DEFAULT = "yyyy-MM-dd hh:mm:ss";

	public String evaluate(String idcard) throws Exception {
		return evaluate(idcard, DEFAULT);
	}

	@SuppressWarnings("unused")
	public String evaluate(String idcard, String format) throws Exception {
		if (StringUtils.isBlank(idcard)) {
			return null;
		} else if (idcard.length() == 18) {
			int birthday = 0;
			try {
				birthday = Integer.parseInt(idcard.substring(6, 14));
			} catch (Exception e) {
				return null;
			}
			
		} else if (idcard.length() == 15) {
			String birthday = idcard.substring(6, 12);
			try {
				Integer.parseInt(birthday);
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		IDCard_GetAge s = new IDCard_GetAge();
		System.out.println(s.evaluate("130503670401001"));
		System.out.println(s.evaluate("130503670401001", "yyyyMMdd"));
		System.out.println(s.evaluate("43021119900702041X"));
		System.out.println(s.evaluate("43021119900702041X", "yyyyMMdd"));

		Calendar c = Calendar.getInstance();
		String y = "" + c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH);
		int d = c.get(Calendar.DAY_OF_MONTH);
		
		int today = Integer.parseInt(y + (m < 10 ? "0" + m : m) + (d < 10 ? "0" + d : d));
		
		System.out.println();
		System.out.println(calcAge(19900702, today));
	}

	private static int calcAge(int birthday, int today) {
		if (today - birthday < 10000)
			return 0;
		return (today - birthday) / 10000;
	}

}
