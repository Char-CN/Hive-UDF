package org.blazer.udf;

public class Int2IP {

	public static void main(String[] args) {
		String ip = "192.16.2.1";
		IP2Int eip = new IP2Int();
		String v1 = eip.evaluate(ip);
		System.out.println(v1);
		Int2IP eip2 = new Int2IP();
		String v2 = eip2.evaluate(v1);
		System.out.println(v2);
		System.out.println("转换结果：" + ip.equals(v2));
	}

	public String evaluate(String str) {
		if (str != null && checkInt(str)) {
			return "" + int2ip(str);
		}
		return str;
	}

	private static String int2ip(String in) {
		String str = Long.toBinaryString(Long.parseLong(in));
		String s1 = "" + Integer.parseInt(str.substring(0, 8), 2);
		String s2 = "" + Integer.parseInt(str.substring(8, 16), 2);
		String s3 = "" + Integer.parseInt(str.substring(16, 24), 2);
		String s4 = "" + Integer.parseInt(str.substring(24, 32), 2);
		return s1 + "." + s2 + "." + s3 + "." + s4;
	}

	private static boolean checkInt(String str) {
		try {
			Long l = Long.parseLong(str);
			if (l > 4294967295L || l < 0)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
