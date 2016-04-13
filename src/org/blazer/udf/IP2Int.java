package org.blazer.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * ip转换int
 * 
 * 1.按点分割，转换每个段成二进制
 * 
 * 2.将二进制连接起来
 * 
 * 3.转换成十进制
 * 
 * @author hyy
 *
 */
public class IP2Int extends UDF {

	public static void main(String[] args) {
		String ip = "255.255.255.255";
		IP2Int eip = new IP2Int();
		System.out.println(eip.evaluate(ip));
	}

	public String evaluate(String str) {
		if (str != null && checkIP(str)) {
			return "" + ip2int(str);
		}
		return str;
	}

	private static long ip2int(String ip) {
		String str = "";
		for (String num : ip.split("\\.")) {
			str += str2binary(num, 8);
		}
		return Long.parseLong(str, 2);
	}

	private static String str2binary(String str, int len) {
		String rst = Integer.toBinaryString(Integer.parseInt(str));
		while (rst.length() < len) {
			rst = "0" + rst;
		}
		return rst;
	}

	private static boolean checkIP(String str) {
		return str.matches("\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}");
	}

}
