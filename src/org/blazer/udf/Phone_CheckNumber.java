package org.blazer.udf;

import org.apache.commons.lang.StringUtils;

public class Phone_CheckNumber extends Phone_Base {

	public boolean evaluate(String mobile) throws Exception {

		if (StringUtils.isBlank(mobile)) {
			return false;
		}

		if (mobile.length() < 10) {
			return false;
		}

		if (!super.isNumber(mobile)) {
			return false;
		}

		return true;
	}

	public static void main(String[] args) throws Exception {
		Phone_CheckNumber n = new Phone_CheckNumber();
		System.out.println(n.evaluate("13207011111"));
		System.out.println(n.evaluate("03207011111"));
		System.out.println(n.evaluate("1230210111111"));
		System.out.println(n.evaluate("1230210111111"));
	}

}
