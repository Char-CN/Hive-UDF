package org.blazer.udf;

public class IDCard_To18 extends IDCard {

	public String evaluate(String idcard) throws Exception {
		if (!is15Idcard(idcard)) {
			return idcard;
		}
		return convertIdcarBy15bit(idcard);
	}

	public static void main(String[] args) throws Exception {
		IDCard_To18 c = new IDCard_To18();
		System.out.println(c.evaluate("422724520905001"));
	}

}
