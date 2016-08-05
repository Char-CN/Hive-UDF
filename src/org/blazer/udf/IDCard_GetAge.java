package org.blazer.udf;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;

public class IDCard_GetAge extends UDF {

	public Integer evaluate(String idcard) throws Exception {
		Integer birthday = null;
		if (StringUtils.isBlank(idcard)) {
			return null;
		}
		if (idcard.length() == 18) {
			try {
				birthday = Integer.parseInt(idcard.substring(6, 14));
			} catch (Exception e) {
			}
		} else if (idcard.length() == 15) {
			try {
				birthday = Integer.parseInt("19" + idcard.substring(6, 12));
			} catch (Exception e) {
			}
		}
		if (birthday == null) {
			return null;
		}
		return calcAge(birthday);
	}

	private int calcAge(int birthday) {
		Calendar c = Calendar.getInstance();
		String y = "" + c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH);
		int d = c.get(Calendar.DAY_OF_MONTH);
		int today = Integer.parseInt(y + (m < 10 ? "0" + m : m) + (d < 10 ? "0" + d : d));
		if (today - birthday < 10000)
			return 0;
		return (today - birthday) / 10000;
	}

	public static void main(String[] args) throws Exception {
		IDCard_GetAge s = new IDCard_GetAge();
		System.out.println(s.evaluate("130503900509001"));
		System.out.println(s.evaluate("130503900101001"));
		System.out.println(s.evaluate("130503901231001"));
		System.out.println(s.evaluate("430211199007020000"));
		System.out.println(s.evaluate("430211198207020000"));
	}

}
