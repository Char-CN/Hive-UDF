package org.blazer.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 身份证号脱敏
 * 
 * 1.字符串为null或字符串长度小于等于7，直接返回字符串
 * 
 * @author hyy
 *
 */
public class IDCard_Desensitization extends UDF {

	public static void main(String[] args) {
		IDCard_Desensitization mo = new IDCard_Desensitization();
		System.out.println(("510681198507034786"));
		System.out.println(mo.evaluate("510681198507034786"));
		System.out.println(("510681850703478"));
		System.out.println(mo.evaluate("510681850703478"));
		System.out.println(mo.evaluate(null));
		System.out.println(mo.evaluate("430211199007027788"));
		System.out.println(mo.evaluate("asd123asd232asd12"));

		long l1 = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			mo.evaluate("510681198507034786");
		}
		long l2 = System.currentTimeMillis();
		System.out.println("waster time : " + (l2 - l1));
	}

	public String evaluate(String str) {
		if (str == null || (str.length() != 15 && str.length() != 18)) {
			return str;
		}
		int security_len = str.length();
		if (str.length() == 15) {
			security_len = 8;
		} else if (str.length() == 18) {
			security_len = 10;
		}
		String tmp_str = str.substring(security_len);
		str = str.substring(0, security_len);
		Integer add = toInteger(str.charAt(security_len - 1));
		if (add == null) {
			return str + tmp_str;
		}
		for (int i = 0; i < tmp_str.length(); i++) {
			char c = tmp_str.charAt(i);
			Integer number = toInteger(c);
			if (number != null) {
				str += int2int(number, add == 0 ? -1 : add);
			} else {
				str += c;
			}
		}
		return str;
	}

	private int int2int(int number, int add) {
		if (number + add >= 10) {
			return number + add - 10;
		}
		if (number + add < 0) {
			return (number + add) * -1;
		}
		return number + add;
	}

	private static Integer toInteger(char c) {
		switch (c) {
		case 48:
			return 0;
		case 49:
			return 1;
		case 50:
			return 2;
		case 51:
			return 3;
		case 52:
			return 4;
		case 53:
			return 5;
		case 54:
			return 6;
		case 55:
			return 7;
		case 56:
			return 8;
		case 57:
			return 9;
		default:
			return null;
		}
	}

}
