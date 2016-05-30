package org.blazer.udf;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;

public class IDCard_GetAge extends UDF {

	public Integer evaluate(String idcard) throws Exception {
		String birthday = null;
		if (StringUtils.isBlank(idcard)) {
			return null;
		}
		if (idcard.length() == 18) {
			try {
				birthday = "" + Integer.parseInt(idcard.substring(6, 14));
			} catch (Exception e) {
			}

		} else if (idcard.length() == 15) {
			try {
				birthday = "19" + Integer.parseInt(idcard.substring(6, 12));
			} catch (Exception e) {
			}
		}
		if (birthday == null) {
			return null;
		}
		return calcAge(birthday);
	}

	public static void main(String[] args) throws Exception {
		IDCard_GetAge s = new IDCard_GetAge();
		System.out.println(s.evaluate("130503900509001"));
		System.out.println(s.evaluate("130503900101001"));
		System.out.println(s.evaluate("130503901231001"));
	}

	private static int calcAge(String birthday) {
		Integer birthYear = Integer.parseInt(birthday.substring(0, 4));
		Integer birthMonth = Integer.parseInt(birthday.substring(4, 6));
		Integer birthDay = Integer.parseInt(birthday.substring(6, 8));
		Calendar c = Calendar.getInstance();
		int nowYear = c.get(Calendar.YEAR);
		int nowMonth = c.get(Calendar.MONTH) + 1;
		int nowDay = c.get(Calendar.DAY_OF_MONTH);
		int add = 0;
		if (birthMonth < nowMonth || (birthMonth == nowMonth && birthDay <= nowDay)) {
			add = -1;
		}
		return nowYear - birthYear + add;
	}

}
