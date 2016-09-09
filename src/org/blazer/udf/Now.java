package org.blazer.udf;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.UDF;

public class Now extends UDF {

	private Date firstShellDate = new Date();

	private String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public String evaluate() {
		return evaluate(DEFAULT_PATTERN, false);
	}

	public String evaluate(boolean getFirstShellDate) {
		return evaluate(DEFAULT_PATTERN, getFirstShellDate);
	}

	public String evaluate(String pattern) {
		return evaluate(pattern, false);
	}

	/**
	 * 
	 * @param pattern
	 * @param getFirstShellDate
	 *            是否取得当时进入hive使用的时间，若为true，当每晚23点50跑的任务，跨越到24点之后，
	 *            还是取得日期为23点50时候的日期
	 * @return
	 */
	public String evaluate(String pattern, boolean getFirstShellDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String rst = null;
		if (getFirstShellDate) {
			rst = sdf.format(firstShellDate);
		} else {
			rst = sdf.format(new Date());
		}
		return rst;
	}

	public static void main(String[] args) {
		Now now = new Now();
		System.out.println(now.evaluate(true));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(now.evaluate());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(now.evaluate(true));
	}

}
