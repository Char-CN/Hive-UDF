package org.blazer.udf;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.UDF;

public class WeekOfYear2 extends UDF {
	private final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	private final Calendar calendar = Calendar.getInstance();

	public WeekOfYear2() {
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setMinimalDaysInFirstWeek(4);
	}

	public String evaluate(String str) {
		if (str == null) {
			return null;
		}
		Date date = null;
		try {
			date = sdf1.parse(str);
		} catch (Exception e) {
			return null;
		}
		calendar.setTime(date);
		Integer week = calendar.get(Calendar.WEEK_OF_YEAR);

		String year = str.substring(0, 4);
		String month = str.substring(5, 7);
		String day = str.substring(8, 10);

		Integer monthI = Integer.parseInt(month);
		Integer dayI = Integer.parseInt(day);
		// 31 30 29 28 27 26 25
		if (monthI == 12 && dayI > 25 && week == 1) {
			String downYear = "" + (Integer.parseInt(year) + 1);
			return downYear + week;
		} else
		// 1 2 3 4 5 6 7
		if (monthI == 1 && dayI < 7 && week > 2) {
			String upYear = "" + (Integer.parseInt(year) - 1);
			return upYear + week;
		}
		return year + week;
	}

	public static void main(String[] args) {
		WeekOfYear2 w = new WeekOfYear2();
		System.out.println(w.evaluate("2014-12-28"));
		System.out.println(w.evaluate("2014-12-31"));
		System.out.println(w.evaluate("2014-12-31"));
		System.out.println(w.evaluate("2015-01-01"));
		System.out.println(w.evaluate("2016-01-01"));
	}

}
