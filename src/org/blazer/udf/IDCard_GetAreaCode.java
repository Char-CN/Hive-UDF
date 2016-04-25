package org.blazer.udf;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;

public class IDCard_GetAreaCode extends UDF {

	public String evaluate(String idcard) throws Exception {
		if (StringUtils.isBlank(idcard) || (idcard.length() != 18 && idcard.length() != 15)) {
			return null;
		}
		return idcard.substring(0, 2);
	}

	public static void main(String[] args) throws Exception {
		IDCard_GetAreaCode a = new IDCard_GetAreaCode();
		System.out.println(a.evaluate("123213123123111"));
		;
	}

}
