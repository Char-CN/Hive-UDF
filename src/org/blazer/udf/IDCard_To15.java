package org.blazer.udf;

public class IDCard_To15 extends IDCard {

	public String evaluate(String idcard) throws Exception {
		if (!is18Idcard(idcard)) {
			return idcard;
		}
		return convertIdcarBy18bit(idcard);
	}

	public static void main(String[] args) {
		String idcard = "43021119900702041X";

		// 非18位身份证
		if (idcard.length() != 18) {
			System.out.println(false);
		}

		System.out.println(idcard.substring(0, 6) + "" + idcard.substring(8, 17));

		System.out.println(convertIdcarBy15bit("430211900702041"));
	}
}
