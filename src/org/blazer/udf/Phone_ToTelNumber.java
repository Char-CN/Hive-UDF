package org.blazer.udf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * Specific UDF Function
 * 
 * @author heyunyang
 * 
 */
public class Phone_ToTelNumber extends Phone_Base {

	public String evaluate(String field) throws Exception {
		if (StringUtils.isBlank(field)) {
			return null;
		}
		// 先把不符合规范的字符全部去掉
		Iterator<String> it = collection.iterator();
		while (it.hasNext()) {
			field = field.replaceAll(it.next(), EMPTY);
		}

		int len = field.length();

		if (len < 10) {
			// do nothing
		} else {
			// 获得手机或固话
			field = super.getTelNumber(field);
		}
		return field;
	}

	public static void main(String[] args) throws Exception {
		Phone_ToTelNumber t = new Phone_ToTelNumber();
		System.out.println(t.evaluate("+86-1asdasd38-000???1-0010"));
		System.out.println(t.evaluate("+8626402755"));
		System.out.println(t.evaluate("3807111234"));
		System.out.println(t.evaluate("13807111234"));
		System.out.println(t.evaluate("0712313我2ss123s*//  ； dsas1123s/"));
		System.out.println(t.evaluate("113807111234"));
		System.out.println(t.evaluate("+86086-1as/dasd38-0我00???1-0010"));
		System.out.println(t.evaluate("02128838088"));
		System.out.println(t.evaluate("ads1阿萨德  asd asd 2；‘【000"));
		System.out.println(t.evaluate(null));
		System.out.println(t.evaluate("18105408877"));

		String s = "ads1阿萨德  asd asd 2；‘【000";
		Matcher matcher = Pattern.compile("\\d").matcher(s);
		String ss = "";
		while (matcher.find()) {
			ss += matcher.group();
		}
		System.out.println(ss);
		long l1 = System.currentTimeMillis();
		for (int i = 0; i < 4000000; i++) {
			t.getTelNumber("8620733812001");
		}
		System.out.println((System.currentTimeMillis() - l1) + "||||||");
		System.out.println(t.getTelNumber("8621083812001"));
		System.out.println(t.getTelNumber("8621183812001"));
		System.out.println(t.getTelNumber("8627083812001"));
		System.out.println(t.getTelNumber("8620733812001"));
		System.out.println(t.getTelNumber("18105408877"));
	}

	private static final String EMPTY = "";

	private static final Collection<String> collection = new ArrayList<String>() {
		private static final long serialVersionUID = 7596007268473147414L;

		{
			this.add("[a-z]");
			// this.add("[\u4E00-\u9FA5]");
			// 匹配双字节字符(包括汉字在内)：[^x00-xff]
			this.add("[^x00-xff]");
			this.add("[ ]");
			this.add("[-]");
			this.add("[/]");
			this.add("[*]");
			this.add("[.]");
			this.add("[?]");
			this.add("[+]86");
		}
	};

}
