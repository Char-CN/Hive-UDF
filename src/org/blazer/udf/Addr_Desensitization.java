package org.blazer.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 地址脱敏
 * 
 * 1.保留10位
 * 
 * @author hyy
 *
 */
public class Addr_Desensitization extends UDF {

	private static final int LEN = 10;

	public static void main(String[] args) {
		Addr_Desensitization ad = new Addr_Desensitization();
		System.out.println(ad.evaluate("广东省广州市天河区天河南一路六运小区3栋501"));
		System.out.println(ad.evaluate("广东省广"));
		System.out.println(ad.evaluate(""));
		System.out.println(ad.evaluate(null));
	}

	public String evaluate(String str) {
		if (str == null || str.length() <= LEN) {
			return str;
		}
		return str.substring(0, LEN);
	}

	public String evaluate(String str, int len) {
		if (str == null || str.length() <= len) {
			return str;
		}
		return str.substring(0, len);
	}

}
