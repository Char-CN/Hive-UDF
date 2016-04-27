package org.blazer.udf;

public class Phone_CheckMobileNumber extends Phone_Base {

	public boolean evaluate(String number) throws Exception {
		if (!super.isMobileNumber(number)) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		Phone_CheckMobileNumber n = new Phone_CheckMobileNumber();
		System.out.println(n.evaluate("13207011111"));
		System.out.println(n.evaluate("03207011111"));
		System.out.println(n.evaluate("1230210111111"));
		System.out.println(n.evaluate("1230210111111"));
		System.out.println(n.evaluate("10400606696"));
		System.out.println(n.evaluate("12312313133"));
	}

}
