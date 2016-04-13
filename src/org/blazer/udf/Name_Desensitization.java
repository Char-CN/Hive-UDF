package org.blazer.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 名字脱敏
 * 
 * 1.如果为null直接返回null
 * 
 * 2.只有1个长度的字符串，直接返回字符串
 * 
 * 3.大于1个长度字符串，不转换第一个字符，转换后面的所有字符
 * 
 * @author hyy
 *
 */
public class Name_Desensitization extends UDF {

	// 汉字范围 \u4e00-\u9fa5 (中文)
	private static final int START = Integer.parseInt("4e00", 16);

	private static final int END = Integer.parseInt("9fa5", 16);

	private static final int ADD = 100;

	public String evaluate(String str) {
		if (str == null || str.length() <= 1) {
			return str;
		}
		String s = str.substring(1);
		String rst = str.substring(0, 1);
		for (int i = 0; i < s.length(); i++) {
			rst += nextChar(s.charAt(i));
		}
		return rst;
	}

	public static void main(String[] args) {
		Name_Desensitization han = new Name_Desensitization();
		System.out.println(han.evaluate("我一"));
		System.out.println(han.evaluate(" 2一"));

		System.out.println(Integer.parseInt("4e0a", 16));
		System.out.println(Integer.parseInt("9fa5", 16));

		System.out.println(ASCII('我'));
		System.out.println(HANZI(ASCII('我')));
		System.out.println(HANZI(25106));
		System.out.println(ASCII(HANZI(25106)));

		int tn = START;
		System.out.println();
		System.out.println(HANZI(tn) + " next " + nextChar(HANZI(tn)));
		System.out.println(nextChar(HANZI(tn)) + " prev " + previousChar(nextChar(HANZI(tn))));

		System.out.println(HANZI(tn) + " prev " + previousChar(HANZI(tn)));
		System.out.println(previousChar(HANZI(tn)) + " next " + nextChar(previousChar(HANZI(tn))));

		tn = START + 23;
		System.out.println();
		System.out.println(HANZI(tn) + " next " + nextChar(HANZI(tn)));
		System.out.println(nextChar(HANZI(tn)) + " prev " + previousChar(nextChar(HANZI(tn))));

		System.out.println(HANZI(tn) + " prev " + previousChar(HANZI(tn)));
		System.out.println(previousChar(HANZI(tn)) + " next " + nextChar(previousChar(HANZI(tn))));
	}

	public static char nextChar(char c) {
		int position = ASCII(c);
		if (position < START || position > END) {
			return c;
		}
		if (position + ADD > END) {
			int add = (position + ADD) - END;
			position = START + add - 1;
		} else {
			position = position + ADD;
		}
		return HANZI(position);
	}

	public static char previousChar(char c) {
		int position = ASCII(c);
		if (position < START || position > END) {
			return c;
		}
		if (position - ADD < START) {
			int add = START - (position - ADD);
			position = END - add + 1;
		} else {
			position = position - ADD;
		}
		return HANZI(position);
	}

	public static int ASCII(char c) {
		return c;
	}

	public static char HANZI(int i) {
		return (char) i;
	}

}
