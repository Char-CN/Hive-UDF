package org.blazer.udf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;

@SuppressWarnings("unused")
public class IDCard_Check extends IDCard {

	public boolean evaluate(String idcard) throws Exception {
		if (StringUtils.isBlank(idcard)) {
			return false;
		}
		return isValidateIdcard(idcard);
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(isValidateIdcard("220121797009246429"));
		System.out.println(isValidateIdcard("220502798502251421"));
		System.out.println(isValidateIdcard("43021119900702041X"));
		System.out.println(isValidateIdcard("43021119900702041x"));
		System.out.println(isValidateIdcard("430211199007020412"));
		System.out.println(isValidateIdcard("111111111111111"));
		String birthday = "220502798502251421".substring(6, 14);
		Date date = new SimpleDateFormat("yyyyMMdd").parse(birthday);
	}

}
